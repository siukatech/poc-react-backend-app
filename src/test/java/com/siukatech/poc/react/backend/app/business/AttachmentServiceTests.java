package com.siukatech.poc.react.backend.app.business;

import com.siukatech.poc.react.backend.app.AbstractUnitTests;
import com.siukatech.poc.react.backend.app.business.dto.AttachmentContentDto;
import com.siukatech.poc.react.backend.app.business.dto.AttachmentDto;
import com.siukatech.poc.react.backend.app.business.form.AttachmentForm;
import com.siukatech.poc.react.backend.app.business.service.AttachmentService;
import com.siukatech.poc.react.backend.app.data.entity.AttachmentEntity;
import com.siukatech.poc.react.backend.app.data.repository.AttachmentRepository;
import com.siukatech.poc.react.backend.app.global.helper.AttachmentHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
@Import({AttachmentHelper.class})
public class AttachmentServiceTests extends AbstractUnitTests {

    @InjectMocks
    private AttachmentService attachmentService;
    @Spy
    private ModelMapper modelMapper;
    @Mock
    private AttachmentRepository attachmentRepository;

    private AttachmentHelper attachmentHelper = new AttachmentHelper();

    @BeforeAll
    public static void init() {
        // If sub-class has her own init, then super-class's init is required to trigger manually
        AbstractUnitTests.init();
    }

    @Test
    public void findAttachmentAll_basic() {
        // given
        AttachmentEntity attachmentEntity1 = this.attachmentHelper.prepareAttachmentEntity_basic();
        when(this.attachmentRepository.findAll()).thenReturn(List.of(attachmentEntity1));

        // when
        List<AttachmentDto> attachmentDtoList = this.attachmentService.findAttachmentAll();

        // then / verify
        assertThat(attachmentDtoList)
                .hasSize(1)
                .extracting(AttachmentDto::getFileName)
                .containsExactlyInAnyOrder(AttachmentHelper.RESOURCE_FILE_NAME);
    }

    @Test
    public void findAttachmentAllByUserId_basic() {
        // given
        AttachmentEntity attachmentEntity1 = this.attachmentHelper.prepareAttachmentEntity_basic();
        AttachmentEntity attachmentEntity2 = this.attachmentHelper.prepareAttachmentEntity_basic();
        when(this.attachmentRepository.findAll()).thenReturn(List.of(attachmentEntity1, attachmentEntity2));

        // when
        List<AttachmentDto> attachmentDtoList = this.attachmentService.findAttachmentAllByUserId(1L);

        // then / verify
        assertThat(attachmentDtoList)
                .hasSize(2)
                .extracting(AttachmentDto::getFileName)
                .containsExactlyInAnyOrder(AttachmentHelper.RESOURCE_FILE_NAME, AttachmentHelper.RESOURCE_FILE_NAME);
    }

    @Test
    public void downloadAttachmentById_basic() {
        // given
        AttachmentEntity attachmentEntity = this.attachmentHelper.prepareAttachmentEntity_basic();
        when(this.attachmentRepository.findById(any(UUID.class))).thenReturn(Optional.of(attachmentEntity));

        // when
        AttachmentContentDto attachmentContentDto = this.attachmentService.downloadAttachmentById(attachmentEntity.getId());

        // then / verify
        assertThat(attachmentContentDto)
                .hasFieldOrProperty("fileContent")
        ;
    }

    @Test
    public void uploadAttachment_basic() throws IOException {
        // given
        AttachmentEntity attachmentEntity = this.attachmentHelper.prepareAttachmentEntity_basic();
        AttachmentForm attachmentForm = this.attachmentHelper.prepareAttachmentForm_basic();
        when(this.attachmentRepository.save(any(AttachmentEntity.class))).thenReturn(attachmentEntity);
        when(this.attachmentRepository.findById(any(UUID.class))).thenReturn(Optional.of(attachmentEntity));

        // when
        AttachmentDto attachmentDto = this.attachmentService.uploadAttachment(attachmentForm);

        // then / verify
        assertThat(attachmentDto.getId()).isEqualTo(attachmentEntity.getId());
    }

    @Test
    public void deleteAttachment_basic() {
        // given
        AttachmentEntity attachmentEntity = this.attachmentHelper.prepareAttachmentEntity_basic();
        when(this.attachmentRepository.findById(any(UUID.class))).thenReturn(Optional.of(attachmentEntity));
        doNothing().when(this.attachmentRepository).delete(any(AttachmentEntity.class));

        // when
        this.attachmentService.deleteAttachment(attachmentEntity.getId());

        // then / verify
        // nothing to check
    }

}
