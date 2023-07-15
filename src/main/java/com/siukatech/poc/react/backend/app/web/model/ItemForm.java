package com.siukatech.poc.react.backend.app.web.model;

import com.siukatech.poc.react.backend.parent.web.model.AbstractForm;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper=false)
public class ItemForm extends AbstractForm {
    protected Long id;

    @NotNull
    protected String name;

    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    protected LocalDate purchasedDate;
}
