package com.siukatech.poc.react.backend.app.item.business;

import com.siukatech.poc.react.backend.app.item.business.dto.AttachmentContentDto;
import com.siukatech.poc.react.backend.app.item.business.dto.AttachmentDto;
import com.siukatech.poc.react.backend.app.item.business.form.AttachmentForm;
import com.siukatech.poc.react.backend.app.item.business.service.AttachmentService;
import com.siukatech.poc.react.backend.app.item.data.entity.AttachmentEntity;
import com.siukatech.poc.react.backend.app.item.data.repository.AttachmentRepository;
import com.siukatech.poc.react.backend.app.item.helper.AttachmentTestDataHelper;
import com.siukatech.poc.react.backend.core.AbstractUnitTests;
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
@Import({AttachmentTestDataHelper.class})
public class AttachmentServiceTests extends AbstractUnitTests {

    @InjectMocks
    private AttachmentService attachmentService;
    @Spy
    private ModelMapper modelMapper;
    @Mock
    private AttachmentRepository attachmentRepository;
    @Spy
    private AttachmentTestDataHelper attachmentTestDataHelper;

    @BeforeAll
    public static void init() {
        // If sub-class has her own init, then super-class's init is required to trigger manually
        AbstractUnitTests.init();
    }

    @Test
    public void findAttachmentAll_basic() {
        // given
        AttachmentEntity attachmentEntity1 = this.attachmentTestDataHelper.prepareAttachmentEntity_basic(true);
        when(this.attachmentRepository.findAll()).thenReturn(List.of(attachmentEntity1));

        // when
        List<AttachmentDto> attachmentDtoList = this.attachmentService.findAttachmentAll();

        // then / verify
        assertThat(attachmentDtoList)
                .hasSize(1)
                .extracting(AttachmentDto::getFileName)
                .containsExactlyInAnyOrder(AttachmentTestDataHelper.RESOURCE_FILE_NAME);
    }

    @Test
    public void findAttachmentAllByUserId_basic() {
        // given
        AttachmentEntity attachmentEntity1 = this.attachmentTestDataHelper.prepareAttachmentEntity_basic(true);
        AttachmentEntity attachmentEntity2 = this.attachmentTestDataHelper.prepareAttachmentEntity_basic(true);
        when(this.attachmentRepository.findAll()).thenReturn(List.of(attachmentEntity1, attachmentEntity2));

        // when
        List<AttachmentDto> attachmentDtoList = this.attachmentService.findAttachmentAllByUserId(UUID.randomUUID().toString());

        // then / verify
        assertThat(attachmentDtoList)
                .hasSize(2)
                .extracting(AttachmentDto::getFileName)
                .containsExactlyInAnyOrder(AttachmentTestDataHelper.RESOURCE_FILE_NAME, AttachmentTestDataHelper.RESOURCE_FILE_NAME);
    }

    @Test
    public void downloadAttachmentById_basic() {
        // given
        AttachmentEntity attachmentEntity = this.attachmentTestDataHelper.prepareAttachmentEntity_basic(true);
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
        AttachmentEntity attachmentEntity = this.attachmentTestDataHelper.prepareAttachmentEntity_basic(true);
        AttachmentForm attachmentForm = this.attachmentTestDataHelper.prepareAttachmentForm_basic(true);
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
        AttachmentEntity attachmentEntity = this.attachmentTestDataHelper.prepareAttachmentEntity_basic(true);
        when(this.attachmentRepository.findById(any(UUID.class))).thenReturn(Optional.of(attachmentEntity));
        doNothing().when(this.attachmentRepository).delete(any(AttachmentEntity.class));

        // when
        this.attachmentService.deleteAttachment(attachmentEntity.getId());

        // then / verify
        // nothing to check
    }

}
