package com.siukatech.poc.react.backend.app.web.controller;

import com.siukatech.poc.react.backend.app.business.dto.AttachmentContentDto;
import com.siukatech.poc.react.backend.app.business.service.AttachmentService;
import com.siukatech.poc.react.backend.parent.security.authentication.MyAuthenticationToken;
import com.siukatech.poc.react.backend.parent.web.annotation.v1.PublicApiV1Controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Slf4j
@PublicApiV1Controller
public class AttachmentDownloadController {

    private final AttachmentService attachmentService;

    public AttachmentDownloadController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
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

}
