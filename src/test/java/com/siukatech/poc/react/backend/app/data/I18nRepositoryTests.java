package com.siukatech.poc.react.backend.app.data;

import com.siukatech.poc.react.backend.app.data.entity.I18nEntity;
import com.siukatech.poc.react.backend.app.data.repository.I18nRepository;
import com.siukatech.poc.react.backend.parent.AbstractJpaTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
//@DataJpaTest(properties = {
//        "spring.jpa.show-sql: true"
//        , "spring.jpa.properties.hibernate.format_sql: true"
//        , "spring.jpa.properties.hibernate.dialect: org.hibernate.dialect.H2Dialect"
//        , "logging.level.org.springframework.data: TRACE"
//})
////@ComponentScan(basePackages = {"com.siukatech.poc.react.backend.app.data.entity"})
public class I18nRepositoryTests extends AbstractJpaTests {

    @Autowired
    public I18nRepository i18nRepository;


    private I18nEntity prepareI18nEntity_basic() {
        I18nEntity i18nEntity = new I18nEntity();
        i18nEntity.setId(1L);
        i18nEntity.setMessageKey("testing.title");
        i18nEntity.setMessageEn("Testing title En");
        i18nEntity.setMessageTc("Testing title Tc");
        i18nEntity.setMessageSc("Testing title Sc");
        i18nEntity.setVersionNo(1L);
        return i18nEntity;
    }

    @BeforeEach
    public void setup(TestInfo testInfo) {
        I18nEntity i18nEntity = this.prepareI18nEntity_basic();
        this.i18nRepository.save(i18nEntity);
    }

    @AfterEach
    public void teardown(TestInfo testInfo) {
        I18nEntity i18nEntity = this.prepareI18nEntity_basic();
        this.i18nRepository.delete(i18nEntity);
    }


    /**
     * AssertJ example ***
     * Reference
     * https://reflectoring.io/assertj-lists/
     */
    @Test
    @Tag("basic")
    public void findAll_basic() {
        List<I18nEntity> i18nEntityList = i18nRepository.findAll();
        assertThat(i18nEntityList).filteredOn(i18nEntity -> {
            return i18nEntity.getMessageKey().equals("testing.title");
        });
    }


}
