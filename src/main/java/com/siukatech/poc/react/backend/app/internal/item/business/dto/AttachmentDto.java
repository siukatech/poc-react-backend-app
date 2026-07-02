package com.siukatech.poc.react.backend.app.internal.item.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.siukatech.poc.react.backend.app.internal.item.data.entity.AttachmentEntity;
import lombok.Data;

@Data
@JsonIgnoreProperties({
//        "multipartFile"
        "fileContent"
})
public class AttachmentDto extends AttachmentEntity {

}
