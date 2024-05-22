package com.siukatech.poc.react.backend.app.item.business.service;

import com.siukatech.poc.react.backend.app.item.business.dto.ItemDto;
import com.siukatech.poc.react.backend.app.item.business.form.ItemForm;
import com.siukatech.poc.react.backend.app.item.data.entity.ItemEntity;
import com.siukatech.poc.react.backend.app.item.data.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ItemService {

    private final ModelMapper modelMapper;
    private final ItemRepository itemRepository;

    public ItemService(ModelMapper modelMapper, ItemRepository itemRepository) {
        this.modelMapper = modelMapper;
        this.itemRepository = itemRepository;
    }

    public List<ItemDto> findItemAll() {
        List<ItemEntity> itemEntityList = this.itemRepository.findAll();
        List<ItemDto> itemDtoList = itemEntityList.stream().map(itemEntity -> this.modelMapper.map(itemEntity, ItemDto.class))
//                .sorted((a1, a2) -> a1.getLastModifiedDatetime().isAfter(a2.getLastModifiedDatetime()) ? 1 : -1) // ascending order
                .sorted((a1, a2) -> a1.getLastModifiedDatetime().isAfter(a2.getLastModifiedDatetime()) ? -1 : 1) // descending order
                .collect(Collectors.toList());
        return itemDtoList;
    }

    public Page<ItemDto> pageItemAll(Pageable pageable) {
        Page<ItemEntity> itemEntityPage = this.itemRepository.findAll(pageable);
        List<ItemDto> itemDtoList = itemEntityPage.stream().map(itemEntity -> this.modelMapper.map(itemEntity, ItemDto.class))
                .sorted((a1, a2) -> a1.getLastModifiedDatetime().isAfter(a2.getLastModifiedDatetime()) ? -1 : 1) // descending order
                .collect(Collectors.toList());
        Page<ItemDto> itemDtoPage = new PageImpl<>(itemDtoList, itemEntityPage.getPageable(), itemEntityPage.getTotalElements());
        return itemDtoPage;
    }

    public List<ItemDto> findItemAllByUserId(Long userId) {
        List<ItemEntity> itemEntityList = this.itemRepository
//                .findAllByUserIdOrderByLastModifiedDatetimeDesc(userId)
                .findAll();
        List<ItemDto> itemDtoList = itemEntityList.stream().map(itemEntity -> this.modelMapper.map(itemEntity, ItemDto.class))
//                .sorted((a1, a2) -> a1.getLastModifiedDatetime().isAfter(a2.getLastModifiedDatetime()) ? 1 : -1) // ascending order
                .sorted((a1, a2) -> a1.getLastModifiedDatetime().isAfter(a2.getLastModifiedDatetime()) ? -1 : 1) // descending order
                .collect(Collectors.toList());
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
        log.debug("createItem - before save - itemForm.getId: [" + itemForm.getId()
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
        log.debug("createItem - after save - createdItemId: [" + createdItemId
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
        log.debug("updateItem - before save - itemEntity.getId: [" + itemEntity.getId()
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
        log.debug("updateItem - after save - updatedItemId: [" + updatedItemId
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
