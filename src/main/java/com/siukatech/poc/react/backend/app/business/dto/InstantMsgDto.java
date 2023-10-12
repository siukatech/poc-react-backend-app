package com.siukatech.poc.react.backend.app.business.dto;

import com.siukatech.poc.react.backend.app.web.model.InstantMsgForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class InstantMsgDto extends InstantMsgForm {
    private String createdBy;
    private LocalDateTime createdDatetime;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDatetime;
}
