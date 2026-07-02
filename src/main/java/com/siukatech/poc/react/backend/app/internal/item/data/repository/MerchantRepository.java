package com.siukatech.poc.react.backend.app.internal.item.data.repository;

import com.siukatech.poc.react.backend.app.internal.item.data.entity.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<MerchantEntity, String>
//        , FindBySidRepository<MerchantEntity, String>
{

    List<MerchantEntity> findAllByOrderByName();

}
