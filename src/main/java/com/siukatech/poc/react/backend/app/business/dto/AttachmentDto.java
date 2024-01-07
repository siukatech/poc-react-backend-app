package com.siukatech.poc.react.backend.app.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.siukatech.poc.react.backend.app.business.form.AttachmentForm;
import com.siukatech.poc.react.backend.app.data.entity.AttachmentEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({
//        "multipartFile"
        "fileContent"
})
public class AttachmentDto extends AttachmentEntity {

}
