package com.siukatech.poc.react.backend.app.item.web.controller;

import com.siukatech.poc.react.backend.app.item.business.dto.AttachmentContentDto;
import com.siukatech.poc.react.backend.app.item.business.dto.AttachmentDto;
import com.siukatech.poc.react.backend.app.item.business.form.AttachmentForm;
import com.siukatech.poc.react.backend.app.item.business.service.AttachmentService;
import com.siukatech.poc.react.backend.core.security.authentication.MyAuthenticationToken;
import com.siukatech.poc.react.backend.core.web.annotation.v1.ProtectedApiV1Controller;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@ProtectedApiV1Controller
public class AttachmentController {

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

    @GetMapping(value = "/attachments/{id}/download", produces = {
            MediaType.APPLICATION_OCTET_STREAM_VALUE
            , MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<?> downloadAttachmentById(MyAuthenticationToken myAuthenticationToken
            , @PathVariable(required = true, name = "id") UUID targetAttachmentId) {
        AttachmentContentDto attachmentContentDto = this.attachmentService.downloadAttachmentById(targetAttachmentId);
        //
        // Reference:
        // https://stackoverflow.com/a/35683261
        // https://stackoverflow.com/a/59588856
        String headerDisposition = "attachment; filename=\"" + attachmentContentDto.getFileName() + "\"";
//        String encodedStr = Base64.getEncoder().encodeToString(attachmentContentDto.getFileContent());
//        log.debug("downloadAttachmentById - encodedStr: [{}]", encodedStr);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, headerDisposition)
                .contentLength(attachmentContentDto.getFileContent().length)
                .contentType(MediaType.valueOf(attachmentContentDto.getContentType()))
                .body(new ByteArrayResource(attachmentContentDto.getFileContent()))
//                .ok(encodedStr)
//                .body(encodedStr)
                ;
    }

    @PostMapping(value = "/attachments", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadAttachment(MyAuthenticationToken myAuthenticationToken
            , @Valid @ModelAttribute AttachmentForm attachmentForm) throws IOException {
        log.debug("uploadAttachment - start");
        AttachmentDto attachmentDto = this.attachmentService.uploadAttachment(attachmentForm);
        log.debug("uploadAttachment - end");
        return ResponseEntity.status(HttpStatus.CREATED).body(attachmentDto);
    }

    @DeleteMapping("/attachments/{id}")
    public HttpStatus deleteAttachment(@PathVariable(required = true, name = "id") UUID targetAttachmentId) {
        this.attachmentService.deleteAttachment(targetAttachmentId);
        return HttpStatus.OK;
    }


}
