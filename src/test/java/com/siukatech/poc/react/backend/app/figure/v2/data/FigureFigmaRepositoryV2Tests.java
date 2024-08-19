package com.siukatech.poc.react.backend.app.figure.v2.data;


import com.siukatech.poc.react.backend.app.figure.v2.business.dto.FigureFigmaDtoV2;
import com.siukatech.poc.react.backend.app.figure.v2.data.entity.FigureFigmaEntityV2;
import com.siukatech.poc.react.backend.app.figure.v2.data.repository.FigureFigmaRepositoryV2;
import com.siukatech.poc.react.backend.parent.AbstractJpaTests;
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
public class FigureFigmaRepositoryV2Tests extends AbstractJpaTests {

    @Autowired
    private FigureFigmaRepositoryV2 figureFigmaRepositoryV2;


    private FigureFigmaEntityV2 prepareFigureFigmaEntity_basic() {
        FigureFigmaEntityV2 figureFigmaEntity = new FigureFigmaEntityV2();
        figureFigmaEntity.setId(UUID.randomUUID());
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

    private FigureFigmaDtoV2 prepareFigureFigmaDto_basic() {
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

    @BeforeEach
    public void setup(TestInfo testInfo) {
        FigureFigmaEntityV2 figureFigmaEntity = this.prepareFigureFigmaEntity_basic();
        this.figureFigmaRepositoryV2.save(figureFigmaEntity);
    }

    @AfterEach
    public void teardown(TestInfo testInfo) {
        FigureFigmaEntityV2 figureFigmaEntity = this.prepareFigureFigmaEntity_basic();
        this.figureFigmaRepositoryV2.delete(figureFigmaEntity);
    }

    @Test
    public void findAll_basic() {
        List<FigureFigmaEntityV2> figureFigmaEntityList = figureFigmaRepositoryV2.findAll();
        log.debug("findAll_basic - figureFigmaEntityList.size: [{}]", figureFigmaEntityList.size());
        assertThat(figureFigmaEntityList).filteredOn(figureFigmaEntity -> {
                    log.debug("findAll_basic - figureFigmaEntity: [{}]", figureFigmaEntity);
                    return StringUtils.hasText(figureFigmaEntity.getName())
                            && figureFigmaEntity.getName().contains("figma ");
                })
                .hasSize(1);
    }

    private void findByHasPreorderBonus_basic(boolean hasPreorderBonus, int expectedSize) {
        List<FigureFigmaEntityV2> figureFigmaEntityList = figureFigmaRepositoryV2.findByHasPreorderBonus(hasPreorderBonus);
        log.debug("findByHasPreorderBonus_basic - figureFigmaEntityList.size: [{}]", figureFigmaEntityList.size());
        log.debug("findByHasPreorderBonus_basic - figureFigmaEntityList: [{}]", figureFigmaEntityList);
        assertThat(figureFigmaEntityList).hasSize(expectedSize);
    }

    @Test
    public void findByHasPreorderBonus_true() {
        findByHasPreorderBonus_basic(true, 1);
    }

    @Test
    public void findByHasPreorderBonus_false() {
        findByHasPreorderBonus_basic(false, 0);
    }

}
