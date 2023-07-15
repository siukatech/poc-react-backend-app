package com.siukatech.poc.react.backend.app.data.repository;

import com.siukatech.poc.react.backend.app.data.entity.I18nEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface I18nRepository extends JpaRepository<I18nEntity, Long> {
}
