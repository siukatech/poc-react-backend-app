package com.siukatech.poc.react.backend.app.item.data;


import com.siukatech.poc.react.backend.app.item.business.dto.ItemDto;
import com.siukatech.poc.react.backend.app.item.data.entity.ItemEntity;
import com.siukatech.poc.react.backend.app.item.data.repository.ItemRepository;
import com.siukatech.poc.react.backend.core.AbstractJpaTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
public class ItemRepositoryTests extends AbstractJpaTests {

    @Autowired
    private ItemRepository itemRepository;

    private String lastRecordId;


    private ItemEntity prepareItemEntity_basic(boolean withId) {
        ItemEntity itemEntity = new ItemEntity();
        if (withId) {
            itemEntity.setId(UUID.randomUUID().toString());
        }
        itemEntity.setName("shf figure 1");
        itemEntity.setPurchasedDate(LocalDate.now());
        itemEntity.setCreatedBy("admin");
        itemEntity.setCreatedDatetime(LocalDateTime.now());
        itemEntity.setLastModifiedBy("admin");
        itemEntity.setLastModifiedDatetime(LocalDateTime.now());
        itemEntity.setVersionNo(1L);
        return itemEntity;
    }

    private ItemDto prepareItemDto_basic() {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(UUID.randomUUID().toString());
        itemDto.setName("shf figure 1");
        itemDto.setPurchasedDate(LocalDate.now());
        itemDto.setCreatedBy("admin");
        itemDto.setCreatedDatetime(LocalDateTime.now());
        itemDto.setLastModifiedBy("admin");
        itemDto.setLastModifiedDatetime(LocalDateTime.now());
        itemDto.setVersionNo(1L);
        return itemDto;
    }

    @BeforeEach
    public void setup(TestInfo testInfo) {
        ItemEntity itemEntity = this.prepareItemEntity_basic(false);
        itemEntity = this.itemRepository.save(itemEntity);
        this.lastRecordId = itemEntity.getId();
    }

    @AfterEach
    public void teardown(TestInfo testInfo) {
//        ItemEntity itemEntity = this.prepareItemEntity_basic();
//        this.itemRepository.delete(itemEntity);
        this.itemRepository.findById(this.lastRecordId)
                .ifPresent(itemEntity -> this.itemRepository.delete(itemEntity));
    }

    @Test
    public void findAll_basic() {
        List<ItemEntity> itemEntityList = itemRepository.findAll();
        assertThat(itemEntityList).filteredOn(itemEntity -> {
            return itemEntity.getName().contains("shf ");
        });
    }

}
