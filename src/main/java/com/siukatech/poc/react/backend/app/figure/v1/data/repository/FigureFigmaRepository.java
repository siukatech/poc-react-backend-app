package com.siukatech.poc.react.backend.app.figure.v1.data.repository;

import com.siukatech.poc.react.backend.app.figure.v1.data.entity.FigureFigmaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FigureFigmaRepository extends JpaRepository<FigureFigmaEntity, UUID> {

    List<FigureFigmaEntity> findByHasPreorderBonus(boolean hasPreorderBonus);

}
