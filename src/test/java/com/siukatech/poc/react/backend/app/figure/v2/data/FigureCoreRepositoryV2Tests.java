package com.siukatech.poc.react.backend.app.figure.v2.data;


import com.siukatech.poc.react.backend.app.figure.v2.business.dto.FigureBaseDtoV2;
import com.siukatech.poc.react.backend.app.figure.v2.data.entity.FigureBaseEntityV2;
import com.siukatech.poc.react.backend.app.figure.v2.data.entity.FigureCoreEntityV2;
import com.siukatech.poc.react.backend.app.figure.v2.data.repository.FigureBaseRepositoryV2;
import com.siukatech.poc.react.backend.app.figure.v2.data.repository.FigureCoreRepositoryV2;
import com.siukatech.poc.react.backend.core.AbstractJpaTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
public class FigureCoreRepositoryV2Tests extends AbstractJpaTests {

    @Autowired
    private FigureBaseRepositoryV2 figureBaseRepositoryV2;

    @Autowired
    private FigureCoreRepositoryV2 figureCoreRepositoryV2;

    private UUID lastRecordId;


    private FigureBaseEntityV2 prepareFigureBaseEntity_basic(boolean withId) {
        FigureBaseEntityV2 figureBaseEntity = new FigureBaseEntityV2();
        if (withId) {
            figureBaseEntity.setId(UUID.randomUUID());
        }
        figureBaseEntity.setName("shf figure 1");
        figureBaseEntity.setFirstReleaseDate(LocalDate.of(2023,8,21));
        figureBaseEntity.setCreatedBy("admin");
        figureBaseEntity.setCreatedDatetime(LocalDateTime.now());
        figureBaseEntity.setLastModifiedBy("admin");
        figureBaseEntity.setLastModifiedDatetime(LocalDateTime.now());
        figureBaseEntity.setVersionNo(1L);
        return figureBaseEntity;
    }

    private FigureBaseDtoV2 prepareFigureBaseDto_basic() {
        FigureBaseDtoV2 figureBaseDtoV2 = new FigureBaseDtoV2();
        figureBaseDtoV2.setId(UUID.randomUUID());
        figureBaseDtoV2.setName("shf figure 1");
        figureBaseDtoV2.setFirstReleaseDate(LocalDate.of(2023,8,21));
        figureBaseDtoV2.setCreatedBy("admin");
        figureBaseDtoV2.setCreatedDatetime(LocalDateTime.now());
        figureBaseDtoV2.setLastModifiedBy("admin");
        figureBaseDtoV2.setLastModifiedDatetime(LocalDateTime.now());
        figureBaseDtoV2.setVersionNo(1L);
        return figureBaseDtoV2;
    }

    @BeforeEach
    public void setup(TestInfo testInfo) {
        FigureBaseEntityV2 figureBaseEntity = this.prepareFigureBaseEntity_basic(false);
        log.debug("setups - testInfo: [{}], figureBaseEntity: [{}]", testInfo, figureBaseEntity);
        figureBaseEntity = this.figureBaseRepositoryV2.save(figureBaseEntity);
        this.lastRecordId = figureBaseEntity.getId();
    }

    @AfterEach
    public void teardown(TestInfo testInfo) {
//        FigureBaseEntityV2 figureBaseEntity = this.prepareFigureBaseEntity_basic();
//        log.debug("teardown - testInfo: [{}], figureBaseEntity: [{}]", testInfo, figureBaseEntity);
//        this.figureBaseRepositoryV2.delete(figureBaseEntity);
        this.figureBaseRepositoryV2.findById(this.lastRecordId)
                .ifPresent(figureBaseEntity -> {
                    log.debug("teardown - testInfo: [{}], figureBaseEntity: [{}]", testInfo, figureBaseEntity);
                    this.figureBaseRepositoryV2.delete(figureBaseEntity);
                });
    }

    @Test
    public void findAll_basic() {
        List<FigureCoreEntityV2> figureCoreEntityList = figureCoreRepositoryV2.findAll();
        log.debug("findAll_basic - figureCoreEntityList.size: [{}]", figureCoreEntityList.size());
        assertThat(figureCoreEntityList).filteredOn(figureCoreEntity -> {
                    log.debug("findAll_basic - figureCoreEntity: [{}]", figureCoreEntity);
                    return StringUtils.hasText(figureCoreEntity.getName())
                            && figureCoreEntity.getName().contains("shf ");
                })
                .hasSize(1);
    }

}
