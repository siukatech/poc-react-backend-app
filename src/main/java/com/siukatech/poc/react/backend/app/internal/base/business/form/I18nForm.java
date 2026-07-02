package com.siukatech.poc.react.backend.app.internal.base.business.form;

import com.siukatech.poc.react.backend.module.core.business.form.AbstractForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
//@EqualsAndHashCode(callSuper = true)
public class I18nForm extends AbstractForm<String> {

    protected String i18nId;
    protected String messageEn;
    protected String messageTc;
    protected String messageSc;

}
