package com.siukatech.poc.react.backend.app.data;


import com.siukatech.poc.react.backend.app.AbstractJpaTests;
import com.siukatech.poc.react.backend.app.business.dto.AttachmentDto;
import com.siukatech.poc.react.backend.app.business.form.AttachmentForm;
import com.siukatech.poc.react.backend.app.data.entity.AttachmentEntity;
import com.siukatech.poc.react.backend.app.data.repository.AttachmentRepository;
import com.siukatech.poc.react.backend.app.global.helper.AttachmentHelper;
import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AttachmentRepositoryTests extends AbstractJpaTests {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AttachmentRepository attachmentRepository;

    private AttachmentHelper attachmentHelper = new AttachmentHelper();

    @BeforeEach()
    public void setup(TestInfo testInfo) {
        AttachmentEntity attachmentEntity = this.attachmentHelper.prepareAttachmentEntity_basic();
        this.attachmentRepository.save(attachmentEntity);
    }

    @AfterEach
    public void teardown(TestInfo testInfo) {
        AttachmentEntity attachmentEntity = this.attachmentHelper.prepareAttachmentEntity_basic();
        this.attachmentRepository.delete(attachmentEntity);
    }

    @Test
    public void findAll_basic() {
        List<AttachmentEntity> attachmentEntityList = attachmentRepository.findAll();
        assertThat(attachmentEntityList).filteredOn(attachmentEntity -> {
            logger.debug("findAll_basic - attachmentEntity.getFileContent: [{}]"
                    , (attachmentEntity.getFileContent() == null ? "NULL" : "NOT-NULL")
            );
            return attachmentEntity.getFileName().contains(AttachmentHelper.RESOURCE_FILE_NAME);
        });
    }

}
