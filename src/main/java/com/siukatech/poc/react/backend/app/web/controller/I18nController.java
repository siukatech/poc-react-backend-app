package com.siukatech.poc.react.backend.app.web.controller;

import com.siukatech.poc.react.backend.app.business.dto.I18nDto;
import com.siukatech.poc.react.backend.app.business.service.I18nService;
import com.siukatech.poc.react.backend.app.business.form.I18nForm;
import com.siukatech.poc.react.backend.parent.web.annotation.v1.PublicApiV1Controller;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * i18n support for react
 * https://medium.com/@a401120174/%E4%BD%BF%E7%94%A8-react-i18next-%E4%BE%86%E6%89%93%E9%80%A0%E5%A4%9A%E5%9C%8B%E8%AA%9E%E8%A8%80%E7%B6%B2%E7%AB%99%E5%90%A7-i18n-5b9c70c05d24
 */
@Slf4j
@PublicApiV1Controller
public class I18nController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //    private final ModelMapper modelMapper;
////    private final I18nRepository i18nRepository;
    private final I18nService i18nService;

//    private PropertyMap<I18nForm, I18nEntity> skipModifiedFieldsMap = new PropertyMap<I18nForm, I18nEntity>() {
//        protected void configure() {
//            skip().setId(null);
//        }
//    };

    public I18nController(
//            ModelMapper modelMapper
////            , I18nRepository i18nRepository
//            ,
            I18nService i18nService) {
//        this.modelMapper = modelMapper;
////        this.modelMapper.addMappings(new PropertyMap<I18nForm, I18nEntity>() {
////            protected void configure() {
////                skip().setId(null);
////            }
////        });
//        this.i18nRepository = i18nRepository;
        this.i18nService = i18nService;
    }

    @GetMapping("/i18n")
    public ResponseEntity<?> listI18ns() {
        Map<String, Map<String, String>> i18nMap = this.i18nService.findI18nMap();
        return ResponseEntity.ok(i18nMap);
    }

    @GetMapping("/i18n/{langTag}")
    public ResponseEntity<?> listI18ns(@PathVariable(required = true) String langTag) {
//        List<I18nEntity> i18nEntityList = this.i18nRepository.findAll();
//        Locale locale = Locale.forLanguageTag(langTag);
//        logger.debug("listI18ns - langTag: [" + langTag + "], locale: [" + locale + "]");
//        //List<I18nDto> i18nDtoList = i18nEntityList.stream().map(i18nEntity -> modelMapper.map(i18nEntity, I18nDto.class)).collect(Collectors.toList());
//        //Map<String, List<String>> i18nMapList = i18nEntityList.stream().collect(Collectors.groupingBy(I18nEntity::getMessageKey, Collectors.mapping(I18nEntity::getMessageEn, Collectors.toList())));
//        Map<String, String> i18nMap = i18nEntityList.stream().collect(Collectors.toMap(i18nEntity -> i18nEntity.getMessageKey()
//                , i18nEntity -> {
//                    if (Locale.TRADITIONAL_CHINESE.equals(locale))
//                        return i18nEntity.getMessageZh();
//                    else if (Locale.SIMPLIFIED_CHINESE.equals(locale))
//                        return i18nEntity.getMessageCn();
//                    else
//                        return i18nEntity.getMessageEn();
//                }
//                // add this to handle duplication when using Collectors.toMap
//                // https://www.baeldung.com/java-duplicate-keys-when-producing-map-using-stream
//                , (first, second) -> first
//        ));
        Map<String, String> i18nMap = this.i18nService.findI18nMap(langTag);
        return ResponseEntity.ok(i18nMap);
    }

    @PostMapping(value = "/i18n")
    public ResponseEntity<?> createI18n(@RequestBody I18nForm i18nForm) {
        logger.debug("createI18n");
//        I18nEntity i18nReq = this.modelMapper.map(i18nForm, I18nEntity.class);
//        logger.debug("createI18n - i18nReq: [" + i18nReq + "]");
//        this.i18nRepository.save(i18nReq);
//        i18nForm.setId(i18nReq.getId());
//        return ResponseEntity.status(HttpStatus.CREATED).body(i18nForm);
        I18nDto i18nDto = this.i18nService.createI18n(i18nForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(i18nDto);
    }

    @PutMapping("/i18n/{targetI18nId}")
    public ResponseEntity<?> updateI18n(@RequestBody I18nForm i18nForm, @PathVariable(required = true) Long targetI18nId) {
//        I18nEntity i18nEntity = this.i18nRepository.findById(targetI18nId).orElseThrow(() -> new EntityNotFoundException("targetI18nId: %s".formatted(targetI18nId)));
//        I18nEntity i18nReq = new I18nEntity();
//        this.modelMapper.map(i18nEntity, i18nReq);
//        this.modelMapper.map(i18nForm, i18nReq);
//        logger.debug("updateI18n - i18nEntity: [" + i18nEntity + "], i18nReq: [" + i18nReq + "]");
//        this.i18nRepository.save(i18nReq);
//        i18nForm.setId(i18nReq.getId());
//        return ResponseEntity.status(HttpStatus.OK).body(i18nForm);
        I18nDto i18nDto = this.i18nService.updateI18n(i18nForm, targetI18nId);
        return ResponseEntity.status(HttpStatus.OK).body(i18nDto);
    }

    @DeleteMapping("/i18n/{targetI18nId}")
    public HttpStatus deleteI18n(@PathVariable(required = true) Long targetI18nId) {
//        I18nEntity i18nEntity = this.i18nRepository.findById(targetI18nId).orElseThrow(() -> new EntityNotFoundException("targetI18nId: %s".formatted(targetI18nId)));
//        this.i18nRepository.delete(i18nEntity);
        this.i18nService.deleteI18n(targetI18nId);
        return HttpStatus.OK;
    }

}
