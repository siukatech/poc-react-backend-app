package com.siukatech.poc.react.backend.app.base.data.repository;

import com.siukatech.poc.react.backend.app.base.data.entity.NotiEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotiRepository extends JpaRepository<NotiEntity, Long> {
    Page<NotiEntity> findAllByUserIdAndStatus(Long userId, String status, Pageable pageable);
}
