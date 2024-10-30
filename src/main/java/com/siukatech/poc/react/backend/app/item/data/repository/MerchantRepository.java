package com.siukatech.poc.react.backend.app.item.data.repository;

import com.siukatech.poc.react.backend.app.item.data.entity.MerchantEntity;
import com.siukatech.poc.react.backend.parent.data.repository.FindBySidRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<MerchantEntity, String>
//        , FindBySidRepository<MerchantEntity, String>
{

    List<MerchantEntity> findAllByOrderByName();

}
