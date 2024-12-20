package com.siukatech.poc.react.backend.app.base.web.controller;

import com.siukatech.poc.react.backend.app.item.business.dto.ItemDto;
import com.siukatech.poc.react.backend.app.item.business.form.ItemForm;
import com.siukatech.poc.react.backend.app.item.business.service.ItemService;
import com.siukatech.poc.react.backend.app.item.data.entity.ItemEntity;
import com.siukatech.poc.react.backend.app.item.data.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
//@EncryptedApiV1Controller
public class PaymentController {

    private ModelMapper modelMapper;
    private ItemRepository itemRepository;
    private ItemService itemService;
    private PropertyMap<ItemForm, ItemEntity> skipItemFormItemEntityModifiedFieldsMap = new PropertyMap<ItemForm, ItemEntity>() {
        protected void configure() {
            skip().setId(null);
        }
    };

    public PaymentController(ModelMapper modelMapper, ItemRepository itemRepository, ItemService itemService) {
        this.modelMapper = modelMapper;
        try {
            boolean isExisted = this.modelMapper.getTypeMaps().stream()
                    .anyMatch(typeMap -> {
                        log.debug("PaymentController - typeMap.getName: [" + typeMap.getName()
                                + "], typeMap.getMappings.size: [" + typeMap.getMappings().size()
                                + "]");
                        typeMap.getMappings().stream().forEach(mapping -> {
                            log.debug("PaymentController - mapping.getSourceType.getName: [" + mapping.getSourceType().getName()
                                    + "], mapping.getPath: [" + mapping.getPath()
                                    + "], mapping.toString: [" + mapping.toString()
                                    + "]");
                        });
                        boolean result = typeMap.equals(this.skipItemFormItemEntityModifiedFieldsMap);
                        return result;
                    });
            log.debug("PaymentController - isExisted: [" + isExisted
                    + "]");
            if (!isExisted) {
                this.modelMapper.addMappings(this.skipItemFormItemEntityModifiedFieldsMap);
            }
        }
        catch ( Exception e ) {
            log.error("PaymentController - e.getMessage: [" + e.getMessage() + "]", e);
        }
        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }

    @PostMapping("/items/{targetItemId}")
    public ResponseEntity<?> getItemById(
            @PathVariable(required = true) String targetItemId
            , @RequestBody ItemForm itemForm) {
////        ItemDto itemDto = new ItemDto();
////        itemDto.setId(targetItemId);
////        return ResponseEntity.ok(itemDto);
//        ItemDto itemDto = this.itemRepository.findById(targetItemId)
//                .map(itemEntity -> this.modelMapper.map(itemEntity, ItemDto.class))
//                .orElseThrow(() -> new EntityNotFoundException("targetItemId: %s".formatted(targetItemId)));
        log.debug("getItemById - targetItemId: [" + targetItemId + "], itemForm: [" + itemForm + "]");
        ItemDto itemDto = this.itemService.findItemById(targetItemId);
        return ResponseEntity.ok(itemDto);
    }

}
