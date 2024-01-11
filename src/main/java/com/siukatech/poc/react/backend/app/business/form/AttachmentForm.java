package com.siukatech.poc.react.backend.app.business.form;

import com.siukatech.poc.react.backend.parent.business.form.AbstractForm;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@Data
@EqualsAndHashCode(callSuper=false)
public class AttachmentForm extends AbstractForm<UUID> {

    @NotNull
    private MultipartFile file;

}
