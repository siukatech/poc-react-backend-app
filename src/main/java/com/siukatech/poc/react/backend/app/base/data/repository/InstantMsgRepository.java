package com.siukatech.poc.react.backend.app.base.data.repository;

import com.siukatech.poc.react.backend.app.base.data.entity.InstantMsgEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstantMsgRepository extends JpaRepository<InstantMsgEntity, Long> {
    Page<InstantMsgEntity> findAllBySenderId(Long senderId, Pageable pageable);
    Page<InstantMsgEntity> findAllByUserId(Long userId, Pageable pageable);

}
