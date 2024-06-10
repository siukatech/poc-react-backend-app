package com.siukatech.poc.react.backend.app.item.business.form;

import com.siukatech.poc.react.backend.parent.business.form.AbstractForm;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Data
public class ItemForm extends AbstractForm<Long> {

    @NotNull
    protected String name;

    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    protected LocalDate purchasedDate;
}
