package com.siukatech.poc.react.backend.app.item.data.repository;

import com.siukatech.poc.react.backend.app.item.data.entity.AttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AttachmentRepository extends JpaRepository<AttachmentEntity, UUID> {

    List<AttachmentEntity> findAllByUserIdOrderByLastModifiedDatetimeDesc(String userId);

}
