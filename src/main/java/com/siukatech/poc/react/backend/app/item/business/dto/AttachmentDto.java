package com.siukatech.poc.react.backend.app.item.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.siukatech.poc.react.backend.app.item.data.entity.AttachmentEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonIgnoreProperties({
//        "multipartFile"
        "fileContent"
})
public class AttachmentDto extends AttachmentEntity {

}
