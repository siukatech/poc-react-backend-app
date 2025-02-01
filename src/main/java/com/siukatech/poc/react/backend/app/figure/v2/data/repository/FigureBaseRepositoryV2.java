package com.siukatech.poc.react.backend.app.figure.v2.data.repository;

import com.siukatech.poc.react.backend.app.figure.v2.data.entity.FigureBaseEntityV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FigureBaseRepositoryV2 extends JpaRepository<FigureBaseEntityV2, UUID> {
}
