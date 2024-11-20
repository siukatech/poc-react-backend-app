package com.siukatech.poc.react.backend.app.figure.v1.data;


import com.siukatech.poc.react.backend.app.figure.v1.business.dto.FigureBaseDto;
import com.siukatech.poc.react.backend.app.figure.v1.data.entity.FigureBaseEntity;
import com.siukatech.poc.react.backend.app.figure.v1.data.repository.FigureBaseRepository;
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
public class FigureBaseRepositoryTests extends AbstractJpaTests {

    @Autowired
    private FigureBaseRepository figureBaseRepository;


    private FigureBaseEntity prepareFigureBaseEntity_basic() {
        FigureBaseEntity figureBaseEntity = new FigureBaseEntity();
        figureBaseEntity.setId(UUID.randomUUID());
        figureBaseEntity.setName("shf figure 1");
        figureBaseEntity.setFirstReleaseDate(LocalDate.of(2023,8,21));
        figureBaseEntity.setCreatedBy("admin");
        figureBaseEntity.setCreatedDatetime(LocalDateTime.now());
        figureBaseEntity.setLastModifiedBy("admin");
        figureBaseEntity.setLastModifiedDatetime(LocalDateTime.now());
        figureBaseEntity.setVersionNo(1L);
        return figureBaseEntity;
    }

    private FigureBaseDto prepareFigureBaseDto_basic() {
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

    @BeforeEach
    public void setup(TestInfo testInfo) {
        FigureBaseEntity figureBaseEntity = this.prepareFigureBaseEntity_basic();
        this.figureBaseRepository.save(figureBaseEntity);
    }

    @AfterEach
    public void teardown(TestInfo testInfo) {
        FigureBaseEntity figureBaseEntity = this.prepareFigureBaseEntity_basic();
        this.figureBaseRepository.delete(figureBaseEntity);
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
