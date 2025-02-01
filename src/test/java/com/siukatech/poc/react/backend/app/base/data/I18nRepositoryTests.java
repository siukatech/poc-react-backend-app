package com.siukatech.poc.react.backend.app.base.data;

import com.siukatech.poc.react.backend.app.base.data.entity.I18nEntity;
import com.siukatech.poc.react.backend.app.base.data.repository.I18nRepository;
import com.siukatech.poc.react.backend.app.base.helper.I18nTestDataHelper;
import com.siukatech.poc.react.backend.core.AbstractJpaTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.mockito.Spy;
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
    private I18nRepository i18nRepository;

    @Spy
    private I18nTestDataHelper i18nTestDataHelper;

    private String lastRecordId;


    @BeforeEach
    public void setup(TestInfo testInfo) {
        I18nEntity i18nEntity = this.i18nTestDataHelper.prepareI18nEntity_basic(false);
        i18nEntity = this.i18nRepository.save(i18nEntity);
        this.lastRecordId = i18nEntity.getId();
    }

    @AfterEach
    public void teardown(TestInfo testInfo) {
//        I18nEntity i18nEntity = this.prepareI18nEntity_basic();
//        this.i18nRepository.delete(i18nEntity);
        this.i18nRepository.findById(this.lastRecordId)
                .ifPresent(i18nEntity -> this.i18nRepository.delete(i18nEntity));

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
//            return i18nEntity.getMessageKey().equals("testing.title");
            return i18nEntity.getI18nId().equals("testing.title");
        });
    }


}
