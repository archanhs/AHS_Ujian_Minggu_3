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
import com.bcafinance.ahsspringboot.models.Reseller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessTypeRepo extends JpaRepository<BusinessType,Long> {
}
