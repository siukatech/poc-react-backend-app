package com.siukatech.poc.react.backend.app.figure.v2.data.repository;

import com.siukatech.poc.react.backend.app.figure.v2.data.entity.FigureFigmaEntityV2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FigureFigmaRepositoryV2 extends JpaRepository<FigureFigmaEntityV2, String> {

    List<FigureFigmaEntityV2> findByHasPreorderBonus(boolean hasPreorderBonus);

}
