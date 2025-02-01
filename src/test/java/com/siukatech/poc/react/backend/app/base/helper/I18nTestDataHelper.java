package com.siukatech.poc.react.backend.app.base.helper;

import com.siukatech.poc.react.backend.app.base.business.dto.I18nDto;
import com.siukatech.poc.react.backend.app.base.business.form.I18nForm;
import com.siukatech.poc.react.backend.app.base.data.entity.I18nEntity;
import com.siukatech.poc.react.backend.core.global.helper.AbstractTestDataHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class I18nTestDataHelper extends AbstractTestDataHelper {

    public I18nEntity prepareI18nEntity_basic(boolean withId) {
        I18nEntity i18nEntity = new I18nEntity();
//        i18nEntity.setId(1L);
//        i18nEntity.setMessageKey("testing.title");
        if (withId) {
            i18nEntity.setId(UUID.randomUUID().toString());
        }
        i18nEntity.setI18nId("testing.title");
        i18nEntity.setMessageEn("Testing title En");
        i18nEntity.setMessageTc("Testing title Tc");
        i18nEntity.setMessageSc("Testing title Sc");
        i18nEntity.setVersionNo(1L);
        return i18nEntity;
    }

    public I18nForm prepareI18nForm() {
        I18nForm i18nForm = new I18nForm();
//        i18nForm.setId(2L);
//        i18nForm.setMessageKey("testing.title.2");
        i18nForm.setId("testing.title.2");
        i18nForm.setMessageEn("Testing");
        i18nForm.setMessageEn("Testing title En");
        i18nForm.setMessageTc("Testing title Tc");
        i18nForm.setMessageSc("Testing title Sc");
        return i18nForm;
    }

    public I18nDto convertToI18nDto(I18nForm i18nForm, String langTag) {
//        ModelMapper mapper = new ModelMapper();
        I18nDto i18nDto = new I18nDto();
//        i18nDto.setKey(i18nForm.getMessageKey());
        i18nDto.setKey(i18nForm.getId());
        switch (langTag) {
            case "zh-TW":
                i18nDto.setMessage(i18nForm.getMessageTc());
            case "zh-CN":
                i18nDto.setMessage(i18nForm.getMessageSc());
            default:
                i18nDto.setMessage(i18nForm.getMessageEn());
        }
        return i18nDto;
    }

}
