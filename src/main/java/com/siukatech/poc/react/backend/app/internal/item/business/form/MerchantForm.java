package com.siukatech.poc.react.backend.app.internal.item.business.form;

import com.siukatech.poc.react.backend.module.core.business.form.AbstractForm;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class MerchantForm extends AbstractForm<String> {

    @NotNull
    protected String mid;
    @NotNull
    protected String name;
    private String website;
    private String description;
    private String status;

}

