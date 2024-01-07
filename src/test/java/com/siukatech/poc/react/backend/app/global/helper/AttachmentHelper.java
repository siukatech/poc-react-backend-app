package com.siukatech.poc.react.backend.app.global.helper;

import com.siukatech.poc.react.backend.app.business.dto.AttachmentDto;
import com.siukatech.poc.react.backend.app.business.form.AttachmentForm;
import com.siukatech.poc.react.backend.app.data.entity.AttachmentEntity;
import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class AttachmentHelper extends AbstractHelper {

    public final static String RESOURCE_FILE_NAME = "12-Light-thumbnail-768x768.jpg";

    private Tika tika = new Tika();

    public AttachmentEntity prepareAttachmentEntity_basic() {
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
        attachmentEntity.setId(UUID.randomUUID());
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

    public AttachmentForm prepareAttachmentForm_basic() {
        AttachmentEntity attachmentEntity = prepareAttachmentEntity_basic();
        MockMultipartFile multipartFile = new MockMultipartFile(
                "multipartFile", attachmentEntity.getFileName()
                , attachmentEntity.getContentType(), attachmentEntity.getFileContent());
        AttachmentForm attachmentForm = new AttachmentForm();
        attachmentForm.setId(attachmentEntity.getId());
        attachmentForm.setFileName(attachmentEntity.getFileName());
        attachmentForm.setContentType(attachmentEntity.getContentType());
        attachmentForm.setFileSize(attachmentEntity.getFileSize());
        attachmentForm.setMultipartFile(multipartFile);

        attachmentForm.setVersionNo(1L);
        return attachmentForm;
    }

    public AttachmentDto convertToAttachmentDto(AttachmentForm attachmentForm) {
        ModelMapper mapper = new ModelMapper();
        AttachmentDto attachmentDto = mapper.map(attachmentForm, AttachmentDto.class);
        return attachmentDto;
    }

}
