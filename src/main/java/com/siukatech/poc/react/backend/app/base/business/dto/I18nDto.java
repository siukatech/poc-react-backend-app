package com.siukatech.poc.react.backend.app.base.business.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
//@EqualsAndHashCode(callSuper = true)
public class I18nDto {
    private String key;
    private String message;
}
