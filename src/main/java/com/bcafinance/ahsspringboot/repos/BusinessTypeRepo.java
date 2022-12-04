package com.bcafinance.ahsspringboot.repos;
/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 01/12/2022
@Last Modified on 01/12/2022 15:08
Version 1.0
*/

import com.bcafinance.ahsspringboot.models.BusinessType;
import com.bcafinance.ahsspringboot.models.Expedition;
import com.bcafinance.ahsspringboot.models.Reseller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BusinessTypeRepo extends JpaRepository<BusinessType,Long> {


    List<BusinessType> searchByBusinessTypeNameContaining(@Param("businessTypeName") String businessTypeName);

    Optional<BusinessType> findByBusinessTypeName(String name);
}
