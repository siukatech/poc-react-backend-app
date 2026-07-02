package com.siukatech.poc.react.backend.app.internal.base.data.repository;

import com.siukatech.poc.react.backend.app.internal.base.data.entity.InstantMsgEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstantMsgRepository extends JpaRepository<InstantMsgEntity, String> {
    Page<InstantMsgEntity> findAllBySenderId(String senderId, Pageable pageable);
    Page<InstantMsgEntity> findAllByUserId(String userId, Pageable pageable);

}
