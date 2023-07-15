package com.siukatech.poc.react.backend.app.web.controller;

import com.siukatech.poc.react.backend.app.business.dto.ItemDto;
import com.siukatech.poc.react.backend.app.business.service.ItemService;
import com.siukatech.poc.react.backend.app.web.model.ItemForm;
import com.siukatech.poc.react.backend.parent.web.annotation.v1.ProtectedApiV1Controller;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@ProtectedApiV1Controller
public class ItemController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    private final ModelMapper modelMapper;
////    private final ItemRepository itemRepository;
    private final ItemService itemService;

//    private PropertyMap<ItemForm, ItemEntity> skipItemFormItemEntityModifiedFieldsMap = new PropertyMap<ItemForm, ItemEntity>() {
//        protected void configure() {
//            skip().setId(null);
//        }
//    };
//    public ProtectedItemController() {}

    public ItemController(
//            ModelMapper modelMapper
////            , ItemRepository itemRepository
//            ,
            ItemService itemService) {
//        this.modelMapper = modelMapper;
////        try {
////            boolean isExisted = this.modelMapper.getTypeMaps().stream()
////                    .anyMatch(typeMap -> {
////                        logger.debug("ItemController - typeMap.getName: [" + typeMap.getName()
////                                + "], typeMap.getMappings.size: [" + typeMap.getMappings().size()
////                                + "]");
////                        typeMap.getMappings().stream().forEach(mapping -> {
////                            logger.debug("ItemController - mapping.getSourceType.getName: [" + mapping.getSourceType().getName()
////                                    + "], mapping.getPath: [" + mapping.getPath()
////                                    + "], mapping.toString: [" + mapping.toString()
////                                    + "]");
////                        });
////                        boolean result = typeMap.equals(this.skipItemFormItemEntityModifiedFieldsMap);
////                        return result;
////                    });
////            logger.debug("ItemController - isExisted: [" + isExisted
////                    + "]");
////            if (!isExisted) {
////                this.modelMapper.addMappings(this.skipItemFormItemEntityModifiedFieldsMap);
////            }
////        }
////        catch ( Exception e ) {
////            logger.error("ItemController - e.getMessage: [" + e.getMessage() + "]", e);
////        }
        //
//        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }

    //@CrossOrigin(origins = "*")
    @GetMapping("/items")
    public ResponseEntity<?> listItems() {
//        List<ItemEntity> itemEntityList = this.itemRepository.findAll();
//        List<ItemDto> itemDtoList = itemEntityList.stream().map(itemEntity -> this.modelMapper.map(itemEntity, ItemDto.class)).collect(Collectors.toList());
//        try {
//            Thread.sleep((5 * 1000));
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        //return ResponseEntity.ok(itemEntityList);
        List<ItemDto> itemDtoList = this.itemService.findItemAll();
        return ResponseEntity.ok(itemDtoList);
    }

    @GetMapping("/items/{targetItemId}")
    public ResponseEntity<?> getItemById(@PathVariable(required = true) Long targetItemId) {
//        ItemDto itemDto = this.itemRepository.findById(targetItemId)
//                .map(itemEntity -> this.modelMapper.map(itemEntity, ItemDto.class))
//                .orElseThrow(() -> new EntityNotFoundException("targetItemId: %s".formatted(targetItemId)));
        ItemDto itemDto = this.itemService.findItemById(targetItemId);
        return ResponseEntity.ok(itemDto);
    }

    @PostMapping(value = "/items")
    public ResponseEntity<?> createItem(@Valid @RequestBody ItemForm itemForm) {
        logger.debug("createItem - start");
        //
//        try {
//            Thread.sleep((5 * 1000));
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        //
//        ItemEntity itemReq = this.prepareEntityBeforeSave(itemForm, null);
//        itemReq = this.itemRepository.save(itemReq);
//        //itemForm.setId(itemReq.getId());
//        //ItemDto itemDto = this.modelMapper.map(itemReq, ItemDto.class);
//        Long updatedItemId = itemReq.getId();
//        ItemDto itemDto = this.modelMapper.map(this.itemRepository.findById(updatedItemId).orElseThrow(() -> new EntityNotFoundException("updatedItemId: %s".formatted(updatedItemId))), ItemDto.class);
        //
        ItemDto itemDto = this.itemService.createItem(itemForm);
        //
        logger.debug("createItem - end");
        return ResponseEntity.status(HttpStatus.CREATED).body(itemDto);
    }

    @PutMapping("/items/{targetItemId}")
    public ResponseEntity<?> updateItem(@Valid @RequestBody ItemForm itemForm, @PathVariable(required = true) Long targetItemId) {
//        ItemEntity itemEntity = this.itemRepository.findById(targetItemId).orElseThrow(() -> new EntityNotFoundException("targetItemId: %s".formatted(targetItemId)));
//        ItemEntity itemReq = this.prepareEntityBeforeSave(itemForm, itemEntity);
//        logger.debug("updateItem - before save - itemEntity.getId: [" + itemEntity.getId()
//                + "], itemEntity: [" + itemEntity
//                + "], itemReq.getId: [" + itemReq.getId()
//                + "], itemReq: [" + itemReq
//                + "]");
//        itemReq = this.itemRepository.save(itemReq);
//        //itemForm.setId(itemReq.getId());
//        //ItemDto itemDto = this.modelMapper.map(itemReq, ItemDto.class);
//        Long updatedItemId = itemReq.getId();
//        ItemDto itemDto = this.modelMapper.map(this.itemRepository.findById(updatedItemId).orElseThrow(() -> new EntityNotFoundException("updatedItemId: %s".formatted(updatedItemId))), ItemDto.class);
        ItemDto itemDto = this.itemService.updateItem(itemForm, targetItemId);
        return ResponseEntity.status(HttpStatus.OK).body(itemDto);
    }

//    private ItemEntity prepareEntityBeforeSave(ItemForm itemForm, ItemEntity itemEntity) {
//        ItemEntity itemReq = null;
//        if (itemEntity == null) {
//            itemReq = this.modelMapper.map(itemForm, ItemEntity.class);
//        } else {
//            itemReq = new ItemEntity();
//            this.modelMapper.map(itemEntity, itemReq);
//
//            // itemForm versionNo was null and caused the repository treats as "CREATE"
//            this.modelMapper.map(itemForm, itemReq);
//            //itemReq = itemEntity;
//        }
//        if (itemReq.getId() == null) {
////            itemReq.setCreatedBy(DEFAULT_USER);
////            itemReq.setCreationDatetime(LocalDateTime.now());
////            itemReq.setLastupdateDatetime(LocalDateTime.now());
//        } else {
////            itemReq.setCreatedBy(itemEntity.getCreatedBy());
////            itemReq.setCreationDatetime(itemEntity.getCreationDatetime());
////            itemReq.setLastupdateDatetime(itemEntity.getLastupdateDatetime());
//        }
////        itemReq.setLastupdatedBy(DEFAULT_USER);
//        logger.debug("prepareEntityBeforeSave - itemEntity.getId: [" + itemEntity.getId()
//                + "], itemEntity: [" + itemEntity
//                + "], itemReq.getId: [" + itemReq.getId()
//                + "], itemReq: [" + itemReq
//                + "]");
//        return itemReq;
//    }

    @DeleteMapping("/items/{targetItemId}")
    public HttpStatus deleteItem(@PathVariable(required = true) Long targetItemId) {
//        ItemEntity itemEntity = this.itemRepository.findById(targetItemId).orElseThrow(() -> new EntityNotFoundException("targetItemId: %s".formatted(targetItemId)));
//        this.itemRepository.delete(itemEntity);
        this.itemService.deleteItem(targetItemId);
        return HttpStatus.OK;
    }


}
