package com.siukatech.poc.react.backend.app.figure.v2.data;


import com.siukatech.poc.react.backend.app.figure.v2.data.entity.FigureBaseEntityV2;
import com.siukatech.poc.react.backend.app.figure.v2.data.repository.FigureBaseRepositoryV2;
import com.siukatech.poc.react.backend.app.figure.v2.helper.FigureBaseTestDataHelperV2;
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
public class FigureBaseRepositoryV2Tests extends AbstractJpaTests {

    @Autowired
    private FigureBaseRepositoryV2 figureBaseRepositoryV2;

    @SpyBean
    private FigureBaseTestDataHelperV2 figureBaseTestDataHelperV2;

    private UUID lastRecordId;


    @BeforeEach
    public void setup(TestInfo testInfo) {
        if (figureBaseTestDataHelperV2 == null) {
            this.figureBaseTestDataHelperV2 = new FigureBaseTestDataHelperV2();
        }
        FigureBaseEntityV2 figureBaseEntity = this.figureBaseTestDataHelperV2.prepareFigureBaseEntity_basic(false);
        figureBaseEntity = this.figureBaseRepositoryV2.save(figureBaseEntity);
        this.lastRecordId = figureBaseEntity.getId();
    }

    @AfterEach
    public void teardown(TestInfo testInfo) {
//        FigureBaseEntityV2 figureBaseEntity = this.prepareFigureBaseEntity_basic();
//        this.figureBaseRepositoryV2.delete(figureBaseEntity);
        this.figureBaseRepositoryV2.findById(this.lastRecordId)
                .ifPresent(figureBaseEntity -> this.figureBaseRepositoryV2.delete(figureBaseEntity));
    }

    @Test
    public void findAll_basic() {
        List<FigureBaseEntityV2> figureBaseEntityList = figureBaseRepositoryV2.findAll();
        log.debug("findAll_basic - figureBaseEntityList.size: [{}]", figureBaseEntityList.size());
        assertThat(figureBaseEntityList).filteredOn(figureBaseEntity -> {
                    log.debug("findAll_basic - figureBaseEntity: [{}]", figureBaseEntity);
                    return StringUtils.hasText(figureBaseEntity.getName())
                            && figureBaseEntity.getName().contains("shf ");
                })
                .hasSize(1);
    }

}
