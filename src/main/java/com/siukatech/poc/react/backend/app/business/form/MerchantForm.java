package com.siukatech.poc.react.backend.app.business.form;

import com.siukatech.poc.react.backend.parent.business.form.AbstractForm;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

