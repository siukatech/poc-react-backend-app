package com.siukatech.poc.react.backend.app.business.service;

import com.siukatech.poc.react.backend.app.business.dto.I18nDto;
import com.siukatech.poc.react.backend.app.data.entity.I18nEntity;
import com.siukatech.poc.react.backend.app.data.repository.I18nRepository;
import com.siukatech.poc.react.backend.app.web.model.I18nForm;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class I18nService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ModelMapper modelMapper;
    private final I18nRepository i18nRepository;

    public I18nService(ModelMapper modelMapper, I18nRepository i18nRepository) {
        this.modelMapper = modelMapper;
        this.i18nRepository = i18nRepository;
    }

    public Map<String, String> findI18nMap(String langTag) {
        List<I18nEntity> i18nEntityList = this.i18nRepository.findAll();
        Locale locale = Locale.forLanguageTag(langTag);
        logger.debug("findI18nMap - langTag: [" + langTag + "], locale: [" + locale + "]");
        //List<I18nDto> i18nDtoList = i18nEntityList.stream().map(i18nEntity -> modelMapper.map(i18nEntity, I18nDto.class)).collect(Collectors.toList());
        //Map<String, List<String>> i18nMapList = i18nEntityList.stream().collect(Collectors.groupingBy(I18nEntity::getCode, Collectors.mapping(I18nEntity::getMessageEn, Collectors.toList())));
        Map<String, String> i18nMap = i18nEntityList.stream().collect(Collectors.toMap(i18nEntity -> i18nEntity.getCode()
                , i18nEntity -> {
                    if (Locale.TRADITIONAL_CHINESE.equals(locale))
                        return i18nEntity.getMessageZh();
                    else if (Locale.SIMPLIFIED_CHINESE.equals(locale))
                        return i18nEntity.getMessageCn();
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
        logger.debug("createI18n - start");
        I18nEntity i18nReq = this.modelMapper.map(i18nForm, I18nEntity.class);
        logger.debug("createI18n - before save - i18nReq: [" + i18nReq + "]");
        this.i18nRepository.save(i18nReq);
        i18nForm.setId(i18nReq.getId());
        return this.modelMapper.map(i18nForm, I18nDto.class);
    }

    public I18nDto updateI18n(I18nForm i18nForm, Long targetI18nId) {
        I18nEntity i18nEntity = this.i18nRepository.findById(targetI18nId).orElseThrow(() -> new EntityNotFoundException("targetI18nId: %s".formatted(targetI18nId)));
        I18nEntity i18nReq = new I18nEntity();
        this.modelMapper.map(i18nEntity, i18nReq);
        this.modelMapper.map(i18nForm, i18nReq);
        logger.debug("updateI18n - i18nEntity: [" + i18nEntity + "], i18nReq: [" + i18nReq + "]");
        this.i18nRepository.save(i18nReq);
        i18nForm.setId(i18nReq.getId());
        return this.modelMapper.map(i18nForm, I18nDto.class);
    }

    public void deleteI18n(Long targetI18nId) {
        I18nEntity i18nEntity = this.i18nRepository.findById(targetI18nId).orElseThrow(() -> new EntityNotFoundException("targetI18nId: %s".formatted(targetI18nId)));
        this.i18nRepository.delete(i18nEntity);
    }

}
