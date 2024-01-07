package com.siukatech.poc.react.backend.app.business.service;

import com.siukatech.poc.react.backend.app.business.dto.AttachmentContentDto;
import com.siukatech.poc.react.backend.app.business.dto.AttachmentDto;
import com.siukatech.poc.react.backend.app.business.form.AttachmentForm;
import com.siukatech.poc.react.backend.app.data.entity.AttachmentEntity;
import com.siukatech.poc.react.backend.app.data.repository.AttachmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.tika.Tika;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AttachmentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ModelMapper modelMapper;
    private final AttachmentRepository attachmentRepository;

    private final Tika tika;

    public AttachmentService(ModelMapper modelMapper, AttachmentRepository attachmentRepository) {
        this.modelMapper = modelMapper;
        this.attachmentRepository = attachmentRepository;

        this.tika = new Tika();
    }

    public List<AttachmentDto> findAttachmentAll() {
        List<AttachmentEntity> attachmentEntityList = this.attachmentRepository.findAll();
        List<AttachmentDto> attachmentDtoList = attachmentEntityList.stream().map(attachmentEntity -> this.modelMapper.map(attachmentEntity, AttachmentDto.class))
//                .sorted((a1, a2) -> a1.getLastModifiedDatetime().isAfter(a2.getLastModifiedDatetime()) ? 1 : -1) // ascending order
                .sorted((a1, a2) -> a1.getLastModifiedDatetime().isAfter(a2.getLastModifiedDatetime()) ? -1 : 1) // descending order
                .collect(Collectors.toList());
        return attachmentDtoList;
    }

    public List<AttachmentDto> findAttachmentAllByUserId(Long userId) {
        List<AttachmentEntity> attachmentEntityList = this.attachmentRepository
//                .findAllByUserIdOrderByLastModifiedDatetimeDesc(userId)
                .findAll();
        List<AttachmentDto> attachmentDtoList = attachmentEntityList.stream().map(attachmentEntity -> this.modelMapper.map(attachmentEntity, AttachmentDto.class))
//                .sorted((a1, a2) -> a1.getLastModifiedDatetime().isAfter(a2.getLastModifiedDatetime()) ? 1 : -1) // ascending order
                .sorted((a1, a2) -> a1.getLastModifiedDatetime().isAfter(a2.getLastModifiedDatetime()) ? -1 : 1) // descending order
                .collect(Collectors.toList());
        return attachmentDtoList;
    }

    public AttachmentDto findAttachmentById(UUID targetAttachmentId) {
        AttachmentDto attachmentDto = this.attachmentRepository.findById(targetAttachmentId)
                .map(attachmentEntity -> this.modelMapper.map(attachmentEntity, AttachmentDto.class))
                .orElseThrow(() -> new EntityNotFoundException("targetAttachmentId: %s".formatted(targetAttachmentId)));
        return attachmentDto;
    }

    public AttachmentContentDto downloadAttachmentById(UUID targetAttachmentId) {
        AttachmentContentDto attachmentDto = this.attachmentRepository.findById(targetAttachmentId)
                .map(attachmentEntity -> this.modelMapper.map(attachmentEntity, AttachmentContentDto.class))
                .orElseThrow(() -> new EntityNotFoundException("targetAttachmentId: %s".formatted(targetAttachmentId)));
        return attachmentDto;
    }

    public AttachmentDto uploadAttachment(AttachmentForm attachmentForm) throws IOException {
        // This converts AttachmentForm to blank new AttachmentEntity
        AttachmentEntity attachmentReq = this.modelMapper.map(attachmentForm, AttachmentEntity.class);

        MultipartFile multipartFile = attachmentForm.getMultipartFile();
        String contentType = this.tika.detect(multipartFile.getOriginalFilename());
        attachmentReq.setFileName(multipartFile.getOriginalFilename());
        attachmentReq.setContentType(contentType);
        attachmentReq.setFileContent(multipartFile.getBytes());
        attachmentReq.setFileSize(attachmentReq.getFileContent() == null ? -1 : attachmentReq.getFileContent().length);

        logger.debug("uploadAttachment - before save - attachmentForm.getId: [" + attachmentForm.getId()
                + "], attachmentForm: [" + attachmentForm
                + "], attachmentReq.getId: [" + attachmentReq.getId()
                + "], attachmentReq.getFileName: [" + attachmentReq.getFileName()
                + "], attachmentReq.getContentType: [" + attachmentReq.getContentType()
                + "], attachmentReq.getFileContent: [" + (attachmentReq.getFileContent() == null ? "NULL" : "NOT-NULL")
                + "], attachmentReq.getFileSize: [" + (attachmentReq.getFileSize())
                + "], attachmentReq: [" + attachmentReq
                + "]");
        attachmentReq = this.attachmentRepository.save(attachmentReq);
        //attachmentForm.setId(attachmentReq.getId());
        //AttachmentDto attachmentDto = this.modelMapper.map(attachmentReq, AttachmentDto.class);
        UUID createdAttachmentId = attachmentReq.getId();
        //AttachmentDto attachmentDto = this.modelMapper.map(this.attachmentRepository.findById(createdAttachmentId).orElseThrow(() -> new EntityNotFoundException("createdAttachmentId: %s".formatted(createdAttachmentId))), AttachmentDto.class);
        AttachmentDto attachmentDto = this.findAttachmentById(createdAttachmentId);
        //
        logger.debug("uploadAttachment - after save - createdAttachmentId: [" + createdAttachmentId
                + "], attachmentForm.getId: [" + attachmentForm.getId()
                + "], attachmentForm: [" + attachmentForm
                + "], attachmentReq.getId: [" + attachmentReq.getId()
                + "], attachmentReq: [" + attachmentReq
                + "]");
        return attachmentDto;
    }

    public void deleteAttachment(UUID targetAttachmentId) {
        AttachmentEntity attachmentEntity = this.attachmentRepository.findById(targetAttachmentId).orElseThrow(() -> new EntityNotFoundException("targetAttachmentId: %s".formatted(targetAttachmentId)));
        this.attachmentRepository.delete(attachmentEntity);
    }

}
