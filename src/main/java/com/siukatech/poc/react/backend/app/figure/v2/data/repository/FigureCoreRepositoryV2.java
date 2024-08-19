package com.siukatech.poc.react.backend.app.figure.v2.data.repository;

import com.siukatech.poc.react.backend.app.figure.v2.data.entity.FigureCoreEntityV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FigureCoreRepositoryV2 extends JpaRepository<FigureCoreEntityV2, String> {
}
