package com.siukatech.poc.react.backend.app.figure.v1.helper;

import com.siukatech.poc.react.backend.app.figure.v1.business.dto.FigureFigmaDto;
import com.siukatech.poc.react.backend.app.figure.v1.data.entity.FigureFigmaEntity;
import com.siukatech.poc.react.backend.core.global.helper.AbstractTestDataHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
public class FigureFigmaTestDataHelper extends AbstractTestDataHelper {

    public FigureFigmaEntity prepareFigureFigmaEntity_basic(boolean withId) {
        FigureFigmaEntity figureFigmaEntity = new FigureFigmaEntity();
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

    public FigureFigmaDto prepareFigureFigmaDto_basic() {
        FigureFigmaDto figureFigmaDto = new FigureFigmaDto();
        figureFigmaDto.setId(UUID.randomUUID());
        figureFigmaDto.setName("figma figure 1");
        figureFigmaDto.setFirstReleaseDate(LocalDate.of(2023,8,21));
        figureFigmaDto.setHasPreorderBonus(true);
        figureFigmaDto.setFirstReleaseDate(LocalDate.now());
        figureFigmaDto.setCreatedBy("admin");
        figureFigmaDto.setCreatedDatetime(LocalDateTime.now());
        figureFigmaDto.setLastModifiedBy("admin");
        figureFigmaDto.setLastModifiedDatetime(LocalDateTime.now());
        figureFigmaDto.setVersionNo(1L);
        return figureFigmaDto;
    }

}
