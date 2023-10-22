package com.siukatech.poc.react.backend.app.business.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class MerchantDto {
    private Long id;
    private String mid;
    private String name;
    private String website;
    private String description;
    private String status;
    private String createdBy;
    private LocalDateTime createdDatetime;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDatetime;

}
