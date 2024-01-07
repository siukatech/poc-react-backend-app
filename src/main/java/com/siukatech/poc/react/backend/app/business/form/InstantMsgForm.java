package com.siukatech.poc.react.backend.app.business.form;

import com.siukatech.poc.react.backend.parent.web.model.AbstractForm;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class InstantMsgForm extends AbstractForm {
    protected Long id;

    @NotNull
    protected String subject;
    @NotNull
    protected String message;
    protected String status;
    protected Long userId;
    protected Long senderId;
}
