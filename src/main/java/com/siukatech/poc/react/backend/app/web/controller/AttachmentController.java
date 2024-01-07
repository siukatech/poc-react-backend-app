package com.siukatech.poc.react.backend.app.web.controller;

import com.siukatech.poc.react.backend.app.business.dto.AttachmentDto;
import com.siukatech.poc.react.backend.app.business.service.AttachmentService;
import com.siukatech.poc.react.backend.app.business.form.AttachmentForm;
import com.siukatech.poc.react.backend.parent.security.authentication.MyAuthenticationToken;
import com.siukatech.poc.react.backend.parent.web.annotation.v1.ProtectedApiV1Controller;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@ProtectedApiV1Controller
public class AttachmentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @GetMapping("/attachments")
    public ResponseEntity<?> listAttachments(MyAuthenticationToken myAuthenticationToken) {
        List<AttachmentDto> attachmentDtoList = this.attachmentService
                .findAttachmentAllByUserId(myAuthenticationToken.getUserId());
        return ResponseEntity.ok(attachmentDtoList);
    }

    @GetMapping("/attachments/{id}")
    public ResponseEntity<?> getAttachmentById(MyAuthenticationToken myAuthenticationToken
            , @PathVariable(required = true, name = "id") UUID targetAttachmentId) {
        AttachmentDto attachmentDto = this.attachmentService.findAttachmentById(targetAttachmentId);
        return ResponseEntity.ok(attachmentDto);
    }

    @PostMapping(value = "/attachments", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadAttachment(MyAuthenticationToken myAuthenticationToken
            , @Valid @ModelAttribute AttachmentForm attachmentForm) {
        logger.debug("uploadAttachment - start");
        AttachmentDto attachmentDto = this.attachmentService.uploadAttachment(attachmentForm);
        logger.debug("uploadAttachment - end");
        return ResponseEntity.status(HttpStatus.CREATED).body(attachmentDto);
    }

    @DeleteMapping("/attachments/{id}")
    public HttpStatus deleteAttachment(@PathVariable(required = true, name = "id") UUID targetAttachmentId) {
        this.attachmentService.deleteAttachment(targetAttachmentId);
        return HttpStatus.OK;
    }


}
