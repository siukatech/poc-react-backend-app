package com.siukatech.poc.react.backend.app.item.business;

import com.siukatech.poc.react.backend.app.item.business.dto.ItemDto;
import com.siukatech.poc.react.backend.app.item.business.service.ItemService;
import com.siukatech.poc.react.backend.app.item.data.entity.ItemEntity;
import com.siukatech.poc.react.backend.app.item.data.repository.ItemRepository;
import com.siukatech.poc.react.backend.parent.AbstractUnitTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest(classes = {ItemService.class})
public class ItemServiceTests extends AbstractUnitTests {

    @Autowired
    private ItemService itemService;

    @MockBean
    private ModelMapper modelMapper;
    @MockBean
    private ItemRepository itemRepository;


    @BeforeAll
    public static void init() {
        // If sub-class has her own init, then super-class's init is required to trigger manually
        AbstractUnitTests.init();
    }

    private ItemEntity prepareItemEntity_basic() {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(UUID.randomUUID().toString());
        itemEntity.setName("item 1");
        itemEntity.setPurchasedDate(LocalDate.of(2024, 8, 9));
        itemEntity.setCreatedBy("admin");
        itemEntity.setCreatedDatetime(LocalDateTime.now());
        itemEntity.setLastModifiedBy("admin");
        itemEntity.setLastModifiedDatetime(LocalDateTime.now());
        itemEntity.setVersionNo(1L);
        return itemEntity;
    }
    private List<ItemEntity> prepareItemEntityList_basic() {
        ItemEntity itemEntity = prepareItemEntity_basic();
        return List.of(itemEntity);
    }

    @Test
    public void findItemAll_basic() {
        // given
        List<ItemEntity> itemEntityList = prepareItemEntityList_basic();
        when(itemRepository.findAll()).thenReturn(itemEntityList);

        // when
        List<ItemDto> itemDtoList = itemService.findItemAll();

        // then
        assertThat(itemDtoList).hasSize(1);
    }

}
