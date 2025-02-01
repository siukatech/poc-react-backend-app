package com.siukatech.poc.react.backend.app.figure.v1.helper;

import com.siukatech.poc.react.backend.app.figure.v1.business.dto.FigureBaseDto;
import com.siukatech.poc.react.backend.app.figure.v1.data.entity.FigureBaseEntity;
import com.siukatech.poc.react.backend.core.global.helper.AbstractTestDataHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
public class FigureCoreTestDataHelper extends AbstractTestDataHelper {

    public FigureBaseEntity prepareFigureBaseEntity_basic(boolean withId) {
        FigureBaseEntity figureBaseEntity = new FigureBaseEntity();
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

    public FigureBaseDto prepareFigureBaseDto_basic() {
        FigureBaseDto figureBaseDto = new FigureBaseDto();
        figureBaseDto.setId(UUID.randomUUID());
        figureBaseDto.setName("shf figure 1");
        figureBaseDto.setFirstReleaseDate(LocalDate.of(2023,8,21));
        figureBaseDto.setCreatedBy("admin");
        figureBaseDto.setCreatedDatetime(LocalDateTime.now());
        figureBaseDto.setLastModifiedBy("admin");
        figureBaseDto.setLastModifiedDatetime(LocalDateTime.now());
        figureBaseDto.setVersionNo(1L);
        return figureBaseDto;
    }

}
