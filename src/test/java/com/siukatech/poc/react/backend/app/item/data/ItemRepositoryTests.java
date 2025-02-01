package com.siukatech.poc.react.backend.app.item.data;


import com.siukatech.poc.react.backend.app.item.data.entity.ItemEntity;
import com.siukatech.poc.react.backend.app.item.data.repository.ItemRepository;
import com.siukatech.poc.react.backend.app.item.helper.ItemTestDataHelper;
import com.siukatech.poc.react.backend.core.AbstractJpaTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
public class ItemRepositoryTests extends AbstractJpaTests {

    @Autowired
    private ItemRepository itemRepository;

    @SpyBean
    private ItemTestDataHelper itemTestDataHelper;

    private String lastRecordId;


    @BeforeEach
    public void setup(TestInfo testInfo) {
        ItemEntity itemEntity = this.itemTestDataHelper.prepareItemEntity_basic(false);
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
