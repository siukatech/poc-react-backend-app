package com.siukatech.poc.react.backend.app.item.web.controller;

import com.siukatech.poc.react.backend.app.item.business.dto.ItemDto;
import com.siukatech.poc.react.backend.app.item.business.form.ItemForm;
import com.siukatech.poc.react.backend.app.item.business.service.ItemService;
import com.siukatech.poc.react.backend.core.security.annotation.PermissionControl;
import com.siukatech.poc.react.backend.core.util.HttpHeaderUtils;
import com.siukatech.poc.react.backend.core.web.annotation.v1.ProtectedApiV1Controller;
import com.siukatech.poc.react.backend.core.web.micrometer.CorrelationIdHandler;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@ProtectedApiV1Controller
public class ItemController {

    //    private final ModelMapper modelMapper;
////    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final CorrelationIdHandler correlationIdHandler;

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
            ItemService itemService, CorrelationIdHandler correlationIdHandler) {
//        this.modelMapper = modelMapper;
////        try {
////            boolean isExisted = this.modelMapper.getTypeMaps().stream()
////                    .anyMatch(typeMap -> {
////                        log.debug("ItemController - typeMap.getName: [" + typeMap.getName()
////                                + "], typeMap.getMappings.size: [" + typeMap.getMappings().size()
////                                + "]");
////                        typeMap.getMappings().stream().forEach(mapping -> {
////                            log.debug("ItemController - mapping.getSourceType.getName: [" + mapping.getSourceType().getName()
////                                    + "], mapping.getPath: [" + mapping.getPath()
////                                    + "], mapping.toString: [" + mapping.toString()
////                                    + "]");
////                        });
////                        boolean result = typeMap.equals(this.skipItemFormItemEntityModifiedFieldsMap);
////                        return result;
////                    });
////            log.debug("ItemController - isExisted: [" + isExisted
////                    + "]");
////            if (!isExisted) {
////                this.modelMapper.addMappings(this.skipItemFormItemEntityModifiedFieldsMap);
////            }
////        }
////        catch ( Exception e ) {
////            log.error("ItemController - e.getMessage: [" + e.getMessage() + "]", e);
////        }
        //
//        this.itemRepository = itemRepository;
        this.itemService = itemService;
        this.correlationIdHandler = correlationIdHandler;
    }

    //@CrossOrigin(origins = "*")
    @GetMapping("/items")
    @PermissionControl(appResourceId = "app.item.listItems", accessRight = "view")
    public ResponseEntity<?> listItems(@RequestHeader HttpHeaders httpHeaders) {
//        List<ItemEntity> itemEntityList = this.itemRepository.findAll();
//        List<ItemDto> itemDtoList = itemEntityList.stream().map(itemEntity -> this.modelMapper.map(itemEntity, ItemDto.class)).collect(Collectors.toList());
//        try {
//            Thread.sleep((5 * 1000));
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        //return ResponseEntity.ok(itemEntityList);
        HttpHeaderUtils.logHttpHeaders(httpHeaders);
        log.debug("listItems - correlationIdHandler.getCorrelationId: [{}]", correlationIdHandler.getCorrelationId());
        List<ItemDto> itemDtoList = this.itemService.findItemAll();
        return ResponseEntity.ok(itemDtoList);
    }

    //@CrossOrigin(origins = "*")
//    @GetMapping("/items")
    @PermissionControl(appResourceId = "app.item.pageItems", accessRight = "view")
    public ResponseEntity<?> pageItems(@Param("name") String name, Pageable pageable) {
        List<ItemDto> itemDtoList = this.itemService.findItemAll();
        return ResponseEntity.ok(itemDtoList);
    }

    @GetMapping("/items/{targetItemId}")
    @PermissionControl(appResourceId = "app.item.getItemById", accessRight = "view")
    public ResponseEntity<?> getItemById(@PathVariable(required = true) String targetItemId) {
//        ItemDto itemDto = this.itemRepository.findById(targetItemId)
//                .map(itemEntity -> this.modelMapper.map(itemEntity, ItemDto.class))
//                .orElseThrow(() -> new EntityNotFoundException("targetItemId: %s".formatted(targetItemId)));
        ItemDto itemDto = this.itemService.findItemById(targetItemId);
        return ResponseEntity.ok(itemDto);
    }

    @PostMapping(value = "/items")
    @PermissionControl(appResourceId = "app.item.createItem", accessRight = "create")
    public ResponseEntity<?> createItem(@Valid @RequestBody ItemForm itemForm) {
        log.debug("createItem - start");
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
        log.debug("createItem - end");
        return ResponseEntity.status(HttpStatus.CREATED).body(itemDto);
    }

    @PutMapping("/items/{targetItemId}")
    @PermissionControl(appResourceId = "app.item.updateItem", accessRight = "update")
    public ResponseEntity<?> updateItem(@Valid @RequestBody ItemForm itemForm, @PathVariable(required = true) String targetItemId) {
//        ItemEntity itemEntity = this.itemRepository.findById(targetItemId).orElseThrow(() -> new EntityNotFoundException("targetItemId: %s".formatted(targetItemId)));
//        ItemEntity itemReq = this.prepareEntityBeforeSave(itemForm, itemEntity);
//        log.debug("updateItem - before save - itemEntity.getId: [" + itemEntity.getId()
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
//        log.debug("prepareEntityBeforeSave - itemEntity.getId: [" + itemEntity.getId()
//                + "], itemEntity: [" + itemEntity
//                + "], itemReq.getId: [" + itemReq.getId()
//                + "], itemReq: [" + itemReq
//                + "]");
//        return itemReq;
//    }

    @DeleteMapping("/items/{targetItemId}")
    @PermissionControl(appResourceId = "app.item.deleteItem", accessRight = "delete")
    public HttpStatus deleteItem(@PathVariable(required = true) String targetItemId) {
//        ItemEntity itemEntity = this.itemRepository.findById(targetItemId).orElseThrow(() -> new EntityNotFoundException("targetItemId: %s".formatted(targetItemId)));
//        this.itemRepository.delete(itemEntity);
        this.itemService.deleteItem(targetItemId);
        return HttpStatus.OK;
    }


}
