package com.siukatech.poc.react.backend.app.item.data.repository;

import com.siukatech.poc.react.backend.app.item.data.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    List<ItemEntity> findAllByUserIdOrderByLastModifiedDatetimeDesc(Long userId);

    Page<ItemEntity> findAllByUserId(Long userId, Pageable pageable);
}
