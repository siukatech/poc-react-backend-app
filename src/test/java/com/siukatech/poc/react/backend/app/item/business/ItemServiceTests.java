package com.siukatech.poc.react.backend.app.item.business;

import com.siukatech.poc.react.backend.app.item.business.dto.ItemDto;
import com.siukatech.poc.react.backend.app.item.business.service.ItemService;
import com.siukatech.poc.react.backend.app.item.data.entity.ItemEntity;
import com.siukatech.poc.react.backend.app.item.data.repository.ItemRepository;
import com.siukatech.poc.react.backend.app.item.helper.ItemTestDataHelper;
import com.siukatech.poc.react.backend.core.AbstractUnitTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;

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

    @SpyBean
    private ItemTestDataHelper itemTestDataHelper;

    @BeforeAll
    public static void init() {
        // If sub-class has her own init, then super-class's init is required to trigger manually
        AbstractUnitTests.init();
    }

    @Test
    public void findItemAll_basic() {
        // given
        List<ItemEntity> itemEntityList = this.itemTestDataHelper.prepareItemEntityList_basic(true);
        when(itemRepository.findAll()).thenReturn(itemEntityList);

        // when
        List<ItemDto> itemDtoList = itemService.findItemAll();

        // then
        assertThat(itemDtoList).hasSize(1);
    }

}
