package com.siukatech.poc.react.backend.app.figure.v1.data;


import com.siukatech.poc.react.backend.app.figure.v1.data.entity.FigureBaseEntity;
import com.siukatech.poc.react.backend.app.figure.v1.data.repository.FigureBaseRepository;
import com.siukatech.poc.react.backend.app.figure.v1.helper.FigureBaseTestDataHelper;
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
public class FigureBaseRepositoryTests extends AbstractJpaTests {

    @Autowired
    private FigureBaseRepository figureBaseRepository;

    @SpyBean
    private FigureBaseTestDataHelper figureBaseTestDataHelper;

    private UUID lastRecordId;


    @BeforeEach
    public void setup(TestInfo testInfo) {
        FigureBaseEntity figureBaseEntity = this.figureBaseTestDataHelper.prepareFigureBaseEntity_basic(false);
        figureBaseEntity = this.figureBaseRepository.save(figureBaseEntity);
        this.lastRecordId = figureBaseEntity.getId();
    }

    @AfterEach
    public void teardown(TestInfo testInfo) {
//        FigureBaseEntity figureBaseEntity = this.prepareFigureBaseEntity_basic();
//        this.figureBaseRepository.delete(figureBaseEntity);
        this.figureBaseRepository.findById(this.lastRecordId)
                .ifPresent(figureBaseEntity -> this.figureBaseRepository.delete(figureBaseEntity));
    }

    @Test
    public void findAll_basic() {
        List<FigureBaseEntity> figureBaseEntityList = figureBaseRepository.findAll();
        log.debug("findAll_basic - figureBaseEntityList.size: [{}]", figureBaseEntityList.size());
        assertThat(figureBaseEntityList).filteredOn(figureBaseEntity -> {
                    log.debug("findAll_basic - figureBaseEntity: [{}]", figureBaseEntity);
                    return StringUtils.hasText(figureBaseEntity.getName())
                            && figureBaseEntity.getName().contains("shf ");
                })
                .hasSize(1);
    }

}
