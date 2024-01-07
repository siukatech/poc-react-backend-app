package com.siukatech.poc.react.backend.app.business.form;

import com.siukatech.poc.react.backend.parent.web.model.AbstractForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class I18nForm extends AbstractForm {
    protected Long id;
    protected String messageKey;
    protected String messageEn;
    protected String messageZh;
    protected String messageCn;

}
