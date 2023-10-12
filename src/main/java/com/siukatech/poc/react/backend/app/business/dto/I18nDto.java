package com.siukatech.poc.react.backend.app.business.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class I18nDto {
    private String key;
    private String message;
}
