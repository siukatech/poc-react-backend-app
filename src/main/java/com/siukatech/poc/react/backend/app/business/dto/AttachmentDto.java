package com.siukatech.poc.react.backend.app.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.siukatech.poc.react.backend.app.business.form.AttachmentForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({"multipartFile"})
public class AttachmentDto extends AttachmentForm {
    private String createdBy;
    private LocalDateTime createdDatetime;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDatetime;
}
