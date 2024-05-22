package com.siukatech.poc.react.backend.app.item.data.repository;

import com.siukatech.poc.react.backend.app.item.data.entity.MerchantEntity;
import com.siukatech.poc.react.backend.parent.data.repository.FindByMidRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<MerchantEntity, Long>, FindByMidRepository<MerchantEntity, String> {

    List<MerchantEntity> findAllByOrderByName();

}
