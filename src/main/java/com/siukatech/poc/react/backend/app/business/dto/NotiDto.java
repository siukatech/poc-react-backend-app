package com.siukatech.poc.react.backend.app.business.dto;

import com.siukatech.poc.react.backend.parent.web.model.AbstractForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class NotiDto {

    private String subject;
    private String message;
    private String status;
    private String relType;
    private Long relId;
    private Long userId;
    private String createdBy;
    private LocalDateTime createdDatetime;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDatetime;

}
