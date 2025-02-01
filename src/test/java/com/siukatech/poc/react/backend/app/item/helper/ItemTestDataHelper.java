package com.siukatech.poc.react.backend.app.item.helper;

import com.siukatech.poc.react.backend.app.item.business.dto.ItemDto;
import com.siukatech.poc.react.backend.app.item.business.form.ItemForm;
import com.siukatech.poc.react.backend.app.item.data.entity.ItemEntity;
import com.siukatech.poc.react.backend.core.global.helper.AbstractTestDataHelper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class ItemTestDataHelper extends AbstractTestDataHelper {

    public ItemEntity prepareItemEntity_basic(boolean withId) {
        ItemEntity itemEntity = new ItemEntity();
        if (withId) {
            itemEntity.setId(UUID.randomUUID().toString());
        }
        itemEntity.setName("item 1");
        itemEntity.setPurchasedDate(LocalDate.of(2024, 8, 9));
        itemEntity.setCreatedBy("admin");
        itemEntity.setCreatedDatetime(LocalDateTime.now());
        itemEntity.setLastModifiedBy("admin");
        itemEntity.setLastModifiedDatetime(LocalDateTime.now());
        itemEntity.setVersionNo(1L);
        return itemEntity;
    }

    public List<ItemEntity> prepareItemEntityList_basic(boolean withId) {
        ItemEntity itemEntity = prepareItemEntity_basic(withId);
        return List.of(itemEntity);
    }

    public ItemDto prepareItemDto_basic(boolean withId) {
        ItemDto itemDto = new ItemDto();
        if (withId) {
            itemDto.setId(UUID.randomUUID().toString());
        }
        itemDto.setName("shf figure 1");
        itemDto.setPurchasedDate(LocalDate.now());
        itemDto.setCreatedBy("admin");
        itemDto.setCreatedDatetime(LocalDateTime.now());
        itemDto.setLastModifiedBy("admin");
        itemDto.setLastModifiedDatetime(LocalDateTime.now());
        itemDto.setVersionNo(1L);
        return itemDto;
    }

    public ItemForm prepareItemForm() {
        ItemForm itemForm = new ItemForm();
        itemForm.setId(UUID.randomUUID().toString());
        itemForm.setName("shf figure 2");
        itemForm.setPurchasedDate(LocalDate.now());
        itemForm.setVersionNo(1L);
        return itemForm;
    }

    public ItemDto convertToItemDto(ItemForm itemForm) {
        ModelMapper mapper = new ModelMapper();
        ItemDto itemDto = mapper.map(itemForm, ItemDto.class);
        return itemDto;
    }


}
