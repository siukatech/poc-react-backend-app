package com.siukatech.poc.react.backend.app.item.helper;

import com.siukatech.poc.react.backend.app.item.business.dto.AttachmentDto;
import com.siukatech.poc.react.backend.app.item.business.form.AttachmentForm;
import com.siukatech.poc.react.backend.app.item.data.entity.AttachmentEntity;
import com.siukatech.poc.react.backend.core.global.helper.AbstractTestDataHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
public class AttachmentTestDataHelper extends AbstractTestDataHelper {

    public final static String RESOURCE_FILE_NAME = "12-Light-thumbnail-768x768.jpg";

    private Tika tika = new Tika();

    public AttachmentEntity prepareAttachmentEntity_basic(boolean withId) {
        File file = getResourceFile("attachment", RESOURCE_FILE_NAME);
        String contentType = null;
        try {
            contentType = tika.detect(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] fileContent = new byte[0];
        try {
            fileContent = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AttachmentEntity attachmentEntity = new AttachmentEntity();
        if (withId) {
            attachmentEntity.setId(UUID.randomUUID());
        }
        attachmentEntity.setFileName(file.getName());
        attachmentEntity.setContentType(contentType);
        attachmentEntity.setFileSize(fileContent == null ? -1 : fileContent.length);
        attachmentEntity.setFileContent(fileContent);

        attachmentEntity.setCreatedBy("admin");
        attachmentEntity.setCreatedDatetime(LocalDateTime.now());
        attachmentEntity.setLastModifiedBy("admin");
        attachmentEntity.setLastModifiedDatetime(LocalDateTime.now());
        attachmentEntity.setVersionNo(1L);
        return attachmentEntity;
    }

    public AttachmentForm prepareAttachmentForm_basic(boolean withId) {
        AttachmentEntity attachmentEntity = prepareAttachmentEntity_basic(withId);
        MockMultipartFile multipartFile = new MockMultipartFile(
                "file", attachmentEntity.getFileName()
                , attachmentEntity.getContentType(), attachmentEntity.getFileContent());
        AttachmentForm attachmentForm = new AttachmentForm();
        attachmentForm.setId(attachmentEntity.getId());
//        attachmentForm.setFileName(attachmentEntity.getFileName());
//        attachmentForm.setContentType(attachmentEntity.getContentType());
//        attachmentForm.setFileSize(attachmentEntity.getFileSize());
        attachmentForm.setFile(multipartFile);

        attachmentForm.setVersionNo(1L);
        return attachmentForm;
    }

    public AttachmentDto convertToAttachmentDto(AttachmentEntity attachmentEntity) {
        ModelMapper mapper = new ModelMapper();
        AttachmentDto attachmentDto = mapper.map(attachmentEntity, AttachmentDto.class);
        return attachmentDto;
    }

}
