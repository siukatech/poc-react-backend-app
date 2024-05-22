package com.siukatech.poc.react.backend.app.figure.data.repository;

import com.siukatech.poc.react.backend.app.figure.data.entity.FigureBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FigureBaseRepository extends JpaRepository<FigureBaseEntity, UUID> {
}
