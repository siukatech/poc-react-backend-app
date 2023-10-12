package com.siukatech.poc.react.backend.app.business;


import com.siukatech.poc.react.backend.app.AbstractUnitTests;
import com.siukatech.poc.react.backend.app.business.service.I18nService;
import com.siukatech.poc.react.backend.app.data.entity.I18nEntity;
import com.siukatech.poc.react.backend.app.data.repository.I18nRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class I18nServiceTests extends AbstractUnitTests {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @InjectMocks
    private I18nService i18nService;
    @Spy
    private ModelMapper modelMapper;
    @Mock
    private I18nRepository i18nRepository;

    @BeforeAll
    public static void init() {
        // If sub-class has her own init, then super-class's init is required to trigger manually
        AbstractUnitTests.init();
    }

    @Test
    public void findAll_basic() {
        // given
        I18nEntity i18nEntity = new I18nEntity();
        i18nEntity.setId(1L);
        i18nEntity.setMessageKey("testing.title");
        i18nEntity.setMessageEn("Testing title En");
        i18nEntity.setMessageZh("Testing title Zh");
        i18nEntity.setMessageCn("Testing title Cn");
        i18nEntity.setVersionNo(1L);
        when(this.i18nRepository.findAll()).thenReturn(List.of(i18nEntity));

        // when
        Map<String, String> i18nMapEn = this.i18nService.findI18nMap(Locale.ENGLISH.toLanguageTag());
        Map<String, String> i18nMapZh = this.i18nService.findI18nMap(Locale.TRADITIONAL_CHINESE.toLanguageTag());
        Map<String, String> i18nMapCn = this.i18nService.findI18nMap(Locale.SIMPLIFIED_CHINESE.toLanguageTag());

        // then / verify
        assertThat(i18nMapEn).containsKey("testing.title");
        assertThat(i18nMapZh).containsKey("testing.title");
        assertThat(i18nMapCn).containsKey("testing.title");
        assertThat(i18nMapEn.get("testing.title")).contains(" En");
        assertThat(i18nMapZh.get("testing.title")).contains(" Zh");
        assertThat(i18nMapCn.get("testing.title")).contains(" Cn");
        assertThat(i18nMapEn).containsValue("Testing title En");
        assertThat(i18nMapZh).containsValue("Testing title Zh");
        assertThat(i18nMapCn).containsValue("Testing title Cn");
//        assertThat(i18nMapCn).doesNotContainValue(" En");
//        assertThat(i18nMapCn).doesNotContainValue(" Zh");
    }

}
