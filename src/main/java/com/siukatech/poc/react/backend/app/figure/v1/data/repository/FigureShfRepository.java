package com.siukatech.poc.react.backend.app.figure.v1.data.repository;

import com.siukatech.poc.react.backend.app.figure.v1.data.entity.FigureShfEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FigureShfRepository extends JpaRepository<FigureShfEntity, UUID> {

}
