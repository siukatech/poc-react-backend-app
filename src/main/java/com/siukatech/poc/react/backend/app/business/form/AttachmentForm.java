package com.siukatech.poc.react.backend.app.business.form;

import com.siukatech.poc.react.backend.parent.business.form.AbstractForm;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;


@Data
@EqualsAndHashCode(callSuper=false)
public class AttachmentForm extends AbstractForm {

    protected UUID id;

    @NotNull
    protected String fileName;
    @NotNull
    protected String contentType;
    @NotNull
    protected int fileSize;

    private MultipartFile multipartFile;

}
