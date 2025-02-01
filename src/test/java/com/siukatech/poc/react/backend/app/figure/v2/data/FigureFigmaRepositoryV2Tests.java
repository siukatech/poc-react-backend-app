package com.siukatech.poc.react.backend.app.figure.v2.data;


import com.siukatech.poc.react.backend.app.figure.v2.data.entity.FigureFigmaEntityV2;
import com.siukatech.poc.react.backend.app.figure.v2.data.repository.FigureFigmaRepositoryV2;
import com.siukatech.poc.react.backend.app.figure.v2.helper.FigureFigmaTestDataHelperV2;
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
public class FigureFigmaRepositoryV2Tests extends AbstractJpaTests {

    @Autowired
    private FigureFigmaRepositoryV2 figureFigmaRepositoryV2;

    @SpyBean
    private FigureFigmaTestDataHelperV2 figureFigmaTestDataHelperV2;

    private UUID lastRecordId;


    @BeforeEach
    public void setup(TestInfo testInfo) {
        FigureFigmaEntityV2 figureFigmaEntity = this.figureFigmaTestDataHelperV2.prepareFigureFigmaEntity_basic(false);
        figureFigmaEntity = this.figureFigmaRepositoryV2.save(figureFigmaEntity);
        this.lastRecordId = figureFigmaEntity.getId();
    }

    @AfterEach
    public void teardown(TestInfo testInfo) {
//        FigureFigmaEntityV2 figureFigmaEntity = this.prepareFigureFigmaEntity_basic();
//        this.figureFigmaRepositoryV2.delete(figureFigmaEntity);
        this.figureFigmaRepositoryV2.findById(this.lastRecordId)
                .ifPresent(figureFigmaEntity -> this.figureFigmaRepositoryV2.delete(figureFigmaEntity));
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
