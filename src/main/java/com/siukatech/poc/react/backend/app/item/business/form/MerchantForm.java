package com.siukatech.poc.react.backend.app.item.business.form;

import com.siukatech.poc.react.backend.parent.business.form.AbstractForm;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class MerchantForm extends AbstractForm<Long> {

    @NotNull
    protected String mid;
    @NotNull
    protected String name;
    private String website;
    private String description;
    private String status;

}

