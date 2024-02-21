package com.siukatech.poc.react.backend.app.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.siukatech.poc.react.backend.app.data.entity.AttachmentEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({
//        "multipartFile"
        "fileContent"
})
public class AttachmentDto extends AttachmentEntity {

}
