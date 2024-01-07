package com.siukatech.poc.react.backend.app.business.dto;

import com.siukatech.poc.react.backend.app.business.form.ItemForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class ItemDto extends ItemForm {
    private String createdBy;
    private LocalDateTime createdDatetime;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDatetime;
}
