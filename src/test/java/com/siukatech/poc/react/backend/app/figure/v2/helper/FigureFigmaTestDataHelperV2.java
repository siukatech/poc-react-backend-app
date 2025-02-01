package com.siukatech.poc.react.backend.app.figure.v2.helper;

import com.siukatech.poc.react.backend.app.figure.v2.business.dto.FigureFigmaDtoV2;
import com.siukatech.poc.react.backend.app.figure.v2.data.entity.FigureFigmaEntityV2;
import com.siukatech.poc.react.backend.core.global.helper.AbstractTestDataHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
public class FigureFigmaTestDataHelperV2 extends AbstractTestDataHelper {

    public FigureFigmaEntityV2 prepareFigureFigmaEntity_basic(boolean withId) {
        FigureFigmaEntityV2 figureFigmaEntity = new FigureFigmaEntityV2();
        if (withId) {
            figureFigmaEntity.setId(UUID.randomUUID());
        }
        figureFigmaEntity.setName("figma figure 1");
        figureFigmaEntity.setFirstReleaseDate(LocalDate.of(2023,8,21));
        figureFigmaEntity.setHasPreorderBonus(true);
        figureFigmaEntity.setCreatedBy("admin");
        figureFigmaEntity.setCreatedDatetime(LocalDateTime.now());
        figureFigmaEntity.setLastModifiedBy("admin");
        figureFigmaEntity.setLastModifiedDatetime(LocalDateTime.now());
        figureFigmaEntity.setVersionNo(1L);
        return figureFigmaEntity;
    }

    public FigureFigmaDtoV2 prepareFigureFigmaDto_basic() {
        FigureFigmaDtoV2 figureFigmaDtoV2 = new FigureFigmaDtoV2();
        figureFigmaDtoV2.setId(UUID.randomUUID());
        figureFigmaDtoV2.setName("figma figure 1");
        figureFigmaDtoV2.setFirstReleaseDate(LocalDate.of(2023,8,21));
        figureFigmaDtoV2.setHasPreorderBonus(true);
        figureFigmaDtoV2.setFirstReleaseDate(LocalDate.now());
        figureFigmaDtoV2.setCreatedBy("admin");
        figureFigmaDtoV2.setCreatedDatetime(LocalDateTime.now());
        figureFigmaDtoV2.setLastModifiedBy("admin");
        figureFigmaDtoV2.setLastModifiedDatetime(LocalDateTime.now());
        figureFigmaDtoV2.setVersionNo(1L);
        return figureFigmaDtoV2;
    }

}
