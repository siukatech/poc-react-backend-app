package com.siukatech.poc.react.backend.app.figure.v1.data;


import com.siukatech.poc.react.backend.app.figure.v1.data.entity.FigureBaseEntity;
import com.siukatech.poc.react.backend.app.figure.v1.data.entity.FigureCoreEntity;
import com.siukatech.poc.react.backend.app.figure.v1.data.repository.FigureBaseRepository;
import com.siukatech.poc.react.backend.app.figure.v1.data.repository.FigureCoreRepository;
import com.siukatech.poc.react.backend.app.figure.v1.helper.FigureCoreTestDataHelper;
import com.siukatech.poc.react.backend.core.AbstractJpaTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
public class FigureCoreRepositoryTests extends AbstractJpaTests {

    @Autowired
    private FigureBaseRepository figureBaseRepository;

    @Autowired
    private FigureCoreRepository figureCoreRepository;

    @SpyBean
    private FigureCoreTestDataHelper figureCoreTestDataHelper;

    private UUID lastRecordId;


    @BeforeEach
    public void setup(TestInfo testInfo) {
        FigureBaseEntity figureBaseEntity = this.figureCoreTestDataHelper.prepareFigureBaseEntity_basic(false);
        log.debug("setups - testInfo: [{}], figureBaseEntity: [{}]", testInfo, figureBaseEntity);
        figureBaseEntity = this.figureBaseRepository.save(figureBaseEntity);
        this.lastRecordId = figureBaseEntity.getId();
    }

    @AfterEach
    public void teardown(TestInfo testInfo) {
//        FigureBaseEntity figureBaseEntity = this.prepareFigureBaseEntity_basic();
//        log.debug("teardown - testInfo: [{}], figureBaseEntity: [{}]", testInfo, figureBaseEntity);
//        this.figureBaseRepository.delete(figureBaseEntity);
        this.figureBaseRepository.findById(this.lastRecordId)
                .ifPresent(figureBaseEntity -> {
                    log.debug("teardown - testInfo: [{}], figureBaseEntity: [{}]", testInfo, figureBaseEntity);
                    this.figureBaseRepository.delete(figureBaseEntity);
                });
    }

    @Test
    public void findAll_basic() {
        List<FigureCoreEntity> figureCoreEntityList = figureCoreRepository.findAll();
        log.debug("findAll_basic - figureCoreEntityList.size: [{}]", figureCoreEntityList.size());
        assertThat(figureCoreEntityList).filteredOn(figureCoreEntity -> {
                    log.debug("findAll_basic - figureCoreEntity: [{}]", figureCoreEntity);
                    return StringUtils.hasText(figureCoreEntity.getName())
                            && figureCoreEntity.getName().contains("shf ");
                })
                .hasSize(1);
    }

}
