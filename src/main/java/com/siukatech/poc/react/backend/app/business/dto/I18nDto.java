package com.siukatech.poc.react.backend.app.business.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class I18nDto {
    private String code;
    private String message;
}
