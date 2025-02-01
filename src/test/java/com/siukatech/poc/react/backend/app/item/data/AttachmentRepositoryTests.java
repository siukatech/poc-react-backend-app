package com.siukatech.poc.react.backend.app.item.data;


import com.siukatech.poc.react.backend.app.item.data.entity.AttachmentEntity;
import com.siukatech.poc.react.backend.app.item.data.repository.AttachmentRepository;
import com.siukatech.poc.react.backend.app.item.helper.AttachmentHelper;
import com.siukatech.poc.react.backend.core.AbstractJpaTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
public class AttachmentRepositoryTests extends AbstractJpaTests {

    @Autowired
    private AttachmentRepository attachmentRepository;

    private AttachmentHelper attachmentHelper = new AttachmentHelper();

    private UUID lastRecordId;

    @BeforeEach
    public void setup(TestInfo testInfo) {
        AttachmentEntity attachmentEntity = this.attachmentHelper.prepareAttachmentEntity_basic(false);
        attachmentEntity = this.attachmentRepository.save(attachmentEntity);
        this.lastRecordId = attachmentEntity.getId();
    }

    @AfterEach
    public void teardown(TestInfo testInfo) {
//        AttachmentEntity attachmentEntity = this.attachmentHelper.prepareAttachmentEntity_basic();
//        this.attachmentRepository.delete(attachmentEntity);
        this.attachmentRepository.findById(this.lastRecordId)
                .ifPresent(attachmentEntity -> this.attachmentRepository.delete(attachmentEntity));
    }

    @Test
    public void findAll_basic() {
        List<AttachmentEntity> attachmentEntityList = attachmentRepository.findAll();
        assertThat(attachmentEntityList).filteredOn(attachmentEntity -> {
                    log.debug("findAll_basic - attachmentEntity.getFileContent: [{}]"
                            , (attachmentEntity.getFileContent() == null ? "NULL" : "NOT-NULL")
                    );
                    return attachmentEntity.getFileName().contains(AttachmentHelper.RESOURCE_FILE_NAME);
                })
                .hasSize(1);
    }

}
