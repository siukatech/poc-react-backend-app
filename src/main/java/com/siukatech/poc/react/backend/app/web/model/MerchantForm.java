package com.siukatech.poc.react.backend.app.web.model;

import com.siukatech.poc.react.backend.parent.web.model.AbstractForm;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper=false)
public class MerchantForm extends AbstractForm {
    protected Long id;
    @NotNull
    protected String mid;
    @NotNull
    protected String name;
    private String website;
    private String description;
    private String status;

}

