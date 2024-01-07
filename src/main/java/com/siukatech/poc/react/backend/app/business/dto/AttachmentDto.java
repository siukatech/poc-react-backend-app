package com.siukatech.poc.react.backend.app.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.siukatech.poc.react.backend.app.business.form.AttachmentForm;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({"multipartFile"})
public class AttachmentDto extends AttachmentForm {

    protected String fileName;

    protected String contentType;
    protected int fileSize;

    protected Long userId;

    private String createdBy;
    private LocalDateTime createdDatetime;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDatetime;
}
