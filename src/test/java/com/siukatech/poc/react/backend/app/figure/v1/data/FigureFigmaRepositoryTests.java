package com.siukatech.poc.react.backend.app.figure.v1.data;


import com.siukatech.poc.react.backend.app.figure.v1.business.dto.FigureFigmaDto;
import com.siukatech.poc.react.backend.app.figure.v1.data.entity.FigureFigmaEntity;
import com.siukatech.poc.react.backend.app.figure.v1.data.repository.FigureFigmaRepository;
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
public class FigureFigmaRepositoryTests extends AbstractJpaTests {

    @Autowired
    private FigureFigmaRepository figureFigmaRepository;


    private FigureFigmaEntity prepareFigureFigmaEntity_basic() {
        FigureFigmaEntity figureFigmaEntity = new FigureFigmaEntity();
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

    private FigureFigmaDto prepareFigureFigmaDto_basic() {
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

    @BeforeEach
    public void setup(TestInfo testInfo) {
        FigureFigmaEntity figureFigmaEntity = this.prepareFigureFigmaEntity_basic();
        this.figureFigmaRepository.save(figureFigmaEntity);
    }

    @AfterEach
    public void teardown(TestInfo testInfo) {
        FigureFigmaEntity figureFigmaEntity = this.prepareFigureFigmaEntity_basic();
        this.figureFigmaRepository.delete(figureFigmaEntity);
    }

    @Test
    public void findAll_basic() {
        List<FigureFigmaEntity> figureFigmaEntityList = figureFigmaRepository.findAll();
        log.debug("findAll_basic - figureFigmaEntityList.size: [{}]", figureFigmaEntityList.size());
        assertThat(figureFigmaEntityList).filteredOn(figureFigmaEntity -> {
                    log.debug("findAll_basic - figureFigmaEntity: [{}]", figureFigmaEntity);
                    return StringUtils.hasText(figureFigmaEntity.getName())
                            && figureFigmaEntity.getName().contains("figma ");
                })
                .hasSize(1);
    }

    private void findByHasPreorderBonus_basic(boolean hasPreorderBonus, int expectedSize) {
        List<FigureFigmaEntity> figureFigmaEntityList = figureFigmaRepository.findByHasPreorderBonus(hasPreorderBonus);
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
