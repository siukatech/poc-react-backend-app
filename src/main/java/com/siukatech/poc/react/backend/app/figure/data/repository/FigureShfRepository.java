package com.siukatech.poc.react.backend.app.figure.data.repository;

import com.siukatech.poc.react.backend.app.figure.data.entity.FigureShfEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FigureShfRepository extends JpaRepository<FigureShfEntity, UUID> {

}
