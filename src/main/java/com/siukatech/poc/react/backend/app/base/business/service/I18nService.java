package com.siukatech.poc.react.backend.app.base.business.service;

import com.siukatech.poc.react.backend.app.base.business.dto.I18nDto;
import com.siukatech.poc.react.backend.app.base.business.form.I18nForm;
import com.siukatech.poc.react.backend.app.base.data.entity.I18nEntity;
import com.siukatech.poc.react.backend.app.base.data.repository.I18nRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class I18nService {

    private final ModelMapper modelMapper;
    private final I18nRepository i18nRepository;

    public I18nService(ModelMapper modelMapper, I18nRepository i18nRepository) {
        this.modelMapper = modelMapper;
        this.i18nRepository = i18nRepository;
    }

    public Map<String, Map<String, String>> findI18nMap() {
        List<I18nEntity> i18nEntityList = this.i18nRepository.findAll();
        Map<String, String> i18nMapEn = findI18nMap(i18nEntityList, Locale.ENGLISH.toLanguageTag());
        Map<String, String> i18nMapTc = findI18nMap(i18nEntityList, Locale.TRADITIONAL_CHINESE.toLanguageTag());
        Map<String, String> i18nMapSc = findI18nMap(i18nEntityList, Locale.SIMPLIFIED_CHINESE.toLanguageTag());
        Map<String, Map<String, String>> i18nMap = Map.of(
                Locale.ENGLISH.toLanguageTag(), i18nMapEn
                , Locale.TRADITIONAL_CHINESE.toLanguageTag(), i18nMapTc
                , Locale.SIMPLIFIED_CHINESE.toLanguageTag(), i18nMapSc
        );
        return i18nMap;
    }

    public Map<String, String> findI18nMap(String langTag) {
        List<I18nEntity> i18nEntityList = this.i18nRepository.findAll();
        Map<String, String> i18nMap = findI18nMap(i18nEntityList, langTag);
        return i18nMap;
    }

    protected Map<String, String> findI18nMap(List<I18nEntity> i18nEntityList, String langTag) {
        Locale locale = Locale.forLanguageTag(langTag);
        log.debug("findI18nMap - langTag: [" + langTag + "], locale: [" + locale + "]");
        //List<I18nDto> i18nDtoList = i18nEntityList.stream().map(i18nEntity -> modelMapper.map(i18nEntity, I18nDto.class)).collect(Collectors.toList());
        //Map<String, List<String>> i18nMapList = i18nEntityList.stream().collect(Collectors.groupingBy(I18nEntity::getCode, Collectors.mapping(I18nEntity::getMessageEn, Collectors.toList())));
        Map<String, String> i18nMap = i18nEntityList.stream()
                .collect(Collectors.toMap(i18nEntity -> i18nEntity.getI18nId()
                , i18nEntity -> {
                    if (Locale.TRADITIONAL_CHINESE.equals(locale))
                        return i18nEntity.getMessageTc();
                    else if (Locale.SIMPLIFIED_CHINESE.equals(locale))
                        return i18nEntity.getMessageSc();
                    else
                        return i18nEntity.getMessageEn();
                }
                // add this to handle duplication when using Collectors.toMap
                // https://www.baeldung.com/java-duplicate-keys-when-producing-map-using-stream
                , (first, second) -> first
        ));
        return i18nMap;
    }

    public I18nDto createI18n(I18nForm i18nForm) {
        log.debug("createI18n - start");
        I18nEntity i18nReq = this.modelMapper.map(i18nForm, I18nEntity.class);
        log.debug("createI18n - before save - i18nReq: [" + i18nReq + "]");
        this.i18nRepository.save(i18nReq);
        i18nForm.setId(i18nReq.getId());
        return this.modelMapper.map(i18nForm, I18nDto.class);
    }

    public I18nDto updateI18n(I18nForm i18nForm, String targetI18nId) {
        I18nEntity i18nEntity = this.i18nRepository.findById(targetI18nId).orElseThrow(() -> new EntityNotFoundException("targetI18nId: %s".formatted(targetI18nId)));
        I18nEntity i18nReq = new I18nEntity();
        this.modelMapper.map(i18nEntity, i18nReq);
        this.modelMapper.map(i18nForm, i18nReq);
        log.debug("updateI18n - i18nEntity: [" + i18nEntity + "], i18nReq: [" + i18nReq + "]");
        this.i18nRepository.save(i18nReq);
        i18nForm.setId(i18nReq.getId());
        return this.modelMapper.map(i18nForm, I18nDto.class);
    }

    public void deleteI18n(String targetI18nId) {
        I18nEntity i18nEntity = this.i18nRepository.findById(targetI18nId).orElseThrow(() -> new EntityNotFoundException("targetI18nId: %s".formatted(targetI18nId)));
        this.i18nRepository.delete(i18nEntity);
    }

}
