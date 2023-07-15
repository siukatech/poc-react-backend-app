package com.siukatech.poc.react.backend.app.data.repository;

import com.siukatech.poc.react.backend.app.data.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

}
