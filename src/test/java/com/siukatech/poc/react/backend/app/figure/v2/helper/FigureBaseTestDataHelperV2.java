package com.siukatech.poc.react.backend.app.figure.v2.helper;

import com.siukatech.poc.react.backend.app.figure.v2.business.dto.FigureBaseDtoV2;
import com.siukatech.poc.react.backend.app.figure.v2.data.entity.FigureBaseEntityV2;
import com.siukatech.poc.react.backend.core.global.helper.AbstractTestDataHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
public class FigureBaseTestDataHelperV2 extends AbstractTestDataHelper {

    public FigureBaseEntityV2 prepareFigureBaseEntity_basic(boolean withId) {
        FigureBaseEntityV2 figureBaseEntity = new FigureBaseEntityV2();
        if (withId) {
            figureBaseEntity.setId(UUID.randomUUID());
        }
        figureBaseEntity.setSid("shf-figure-1");
        figureBaseEntity.setName("shf figure 1");
        figureBaseEntity.setFirstReleaseDate(LocalDate.of(2023,8,21));
        figureBaseEntity.setCreatedBy("admin");
        figureBaseEntity.setCreatedDatetime(LocalDateTime.now());
        figureBaseEntity.setLastModifiedBy("admin");
        figureBaseEntity.setLastModifiedDatetime(LocalDateTime.now());
        figureBaseEntity.setVersionNo(1L);
        return figureBaseEntity;
    }

    public FigureBaseDtoV2 prepareFigureBaseDto_basic() {
        FigureBaseDtoV2 figureBaseDtoV2 = new FigureBaseDtoV2();
        figureBaseDtoV2.setId(UUID.randomUUID());
        figureBaseDtoV2.setSid("shf-figure-1");
        figureBaseDtoV2.setName("shf figure 1");
        figureBaseDtoV2.setFirstReleaseDate(LocalDate.of(2023,8,21));
        figureBaseDtoV2.setCreatedBy("admin");
        figureBaseDtoV2.setCreatedDatetime(LocalDateTime.now());
        figureBaseDtoV2.setLastModifiedBy("admin");
        figureBaseDtoV2.setLastModifiedDatetime(LocalDateTime.now());
        figureBaseDtoV2.setVersionNo(1L);
        return figureBaseDtoV2;
    }

}
