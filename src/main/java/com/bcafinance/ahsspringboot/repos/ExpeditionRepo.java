package com.bcafinance.ahsspringboot.repos;
/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 03/12/2022
@Last Modified on 03/12/2022 8:46
Version 1.0
*/

import com.bcafinance.ahsspringboot.models.BusinessType;
import com.bcafinance.ahsspringboot.models.Customers;
import com.bcafinance.ahsspringboot.models.Expedition;
import com.bcafinance.ahsspringboot.models.Reseller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExpeditionRepo extends JpaRepository<Expedition,Long> {


    List<Expedition> searchByExpeditionNameContaining(@Param("expeditionName") String expeditionName);


    Optional<Expedition> findByExpeditionName(String name);


    Optional<Expedition> findByEmail(String email);
}
