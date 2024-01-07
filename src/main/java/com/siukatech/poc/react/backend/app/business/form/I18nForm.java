package com.siukatech.poc.react.backend.app.business.form;

import com.siukatech.poc.react.backend.parent.business.form.AbstractForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class I18nForm extends AbstractForm<Long> {

    protected String messageKey;
    protected String messageEn;
    protected String messageTc;
    protected String messageSc;

}
