package com.siukatech.poc.react.backend.app.business.service;

import com.siukatech.poc.react.backend.app.business.dto.ItemDto;
import com.siukatech.poc.react.backend.app.data.entity.ItemEntity;
import com.siukatech.poc.react.backend.app.data.repository.ItemRepository;
import com.siukatech.poc.react.backend.app.web.model.ItemForm;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ModelMapper modelMapper;
    private final ItemRepository itemRepository;

    public ItemService(ModelMapper modelMapper, ItemRepository itemRepository) {
        this.modelMapper = modelMapper;
        this.itemRepository = itemRepository;
    }

    public List<ItemDto> findItemAll() {
        List<ItemEntity> itemEntityList = this.itemRepository.findAll();
        List<ItemDto> itemDtoList = itemEntityList.stream().map(itemEntity -> this.modelMapper.map(itemEntity, ItemDto.class)).collect(Collectors.toList());
        return itemDtoList;
    }

    public ItemDto findItemById(Long targetItemId) {
        ItemDto itemDto = this.itemRepository.findById(targetItemId)
                .map(itemEntity -> this.modelMapper.map(itemEntity, ItemDto.class))
                .orElseThrow(() -> new EntityNotFoundException("targetItemId: %s".formatted(targetItemId)));
        return itemDto;
    }

    public ItemDto createItem(ItemForm itemForm) {
        // This converts ItemForm to blank new ItemEntity
        ItemEntity itemReq = this.modelMapper.map(itemForm, ItemEntity.class);
        logger.debug("createItem - before save - itemForm.getId: [" + itemForm.getId()
                + "], itemForm: [" + itemForm
                + "], itemReq.getId: [" + itemReq.getId()
                + "], itemReq: [" + itemReq
                + "]");
        itemReq = this.itemRepository.save(itemReq);
        //itemForm.setId(itemReq.getId());
        //ItemDto itemDto = this.modelMapper.map(itemReq, ItemDto.class);
        Long createdItemId = itemReq.getId();
        //ItemDto itemDto = this.modelMapper.map(this.itemRepository.findById(createdItemId).orElseThrow(() -> new EntityNotFoundException("createdItemId: %s".formatted(createdItemId))), ItemDto.class);
        ItemDto itemDto = this.findItemById(createdItemId);
        //
        logger.debug("createItem - after save - createdItemId: [" + createdItemId
                + "], itemForm.getId: [" + itemForm.getId()
                + "], itemForm: [" + itemForm
                + "], itemReq.getId: [" + itemReq.getId()
                + "], itemReq: [" + itemReq
                + "]");
        return itemDto;
    }

    public ItemDto updateItem(ItemForm itemForm, Long targetItemId) {
        // findItemById returns ItemDto, not ItemEntity
        // Therefore, itemRepository is required at this point
        ItemEntity itemEntity = this.itemRepository.findById(targetItemId).orElseThrow(() -> new EntityNotFoundException("targetItemId: %s".formatted(targetItemId)));

        ItemEntity itemReq = new ItemEntity();
        // This maps itemEntity's values to itemReq
        this.modelMapper.map(itemEntity, itemReq);

        // itemForm versionNo was null and caused the repository treats as "CREATE"
        // This maps itemForm's values to itemReq
        this.modelMapper.map(itemForm, itemReq);
        //
        logger.debug("updateItem - before save - itemEntity.getId: [" + itemEntity.getId()
                + "], itemEntity: [" + itemEntity
                + "], itemReq.getId: [" + itemReq.getId()
                + "], itemReq: [" + itemReq
                + "]");
        itemReq = this.itemRepository.save(itemReq);
        //itemForm.setId(itemReq.getId());
        //ItemDto itemDto = this.modelMapper.map(itemReq, ItemDto.class);
        Long updatedItemId = itemReq.getId();
        //ItemDto itemDto = this.modelMapper.map(this.itemRepository.findById(updatedItemId).orElseThrow(() -> new EntityNotFoundException("updatedItemId: %s".formatted(updatedItemId))), ItemDto.class);
        ItemDto itemDto = this.findItemById(updatedItemId);
        //
        logger.debug("updateItem - after save - updatedItemId: [" + updatedItemId
                + "], itemForm.getId: [" + itemForm.getId()
                + "], itemForm: [" + itemForm
                + "], itemReq.getId: [" + itemReq.getId()
                + "], itemReq: [" + itemReq
                + "]");
        return itemDto;
    }

    public void deleteItem(Long targetItemId) {
        ItemEntity itemEntity = this.itemRepository.findById(targetItemId).orElseThrow(() -> new EntityNotFoundException("targetItemId: %s".formatted(targetItemId)));
        this.itemRepository.delete(itemEntity);
    }

}
