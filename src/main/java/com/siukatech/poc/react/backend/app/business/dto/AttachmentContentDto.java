package com.siukatech.poc.react.backend.app.business.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AttachmentContentDto extends AttachmentDto {
    private byte[] fileContent;
}
