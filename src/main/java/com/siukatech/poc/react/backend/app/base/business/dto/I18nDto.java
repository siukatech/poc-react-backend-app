package com.siukatech.poc.react.backend.app.base.business.dto;

import com.siukatech.poc.react.backend.core.business.dto.AbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
//@EqualsAndHashCode(callSuper = true)
public class I18nDto extends AbstractDto {
    private String key;
    private String message;
}
