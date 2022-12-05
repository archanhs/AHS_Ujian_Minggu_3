package com.bcafinance.ahsspringboot.repos;
/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 03/12/2022
@Last Modified on 03/12/2022 16:59
Version 1.0
*/

import com.bcafinance.ahsspringboot.models.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterRepo extends JpaRepository<Register,Long> {


    Optional<Register> findByEmail(String email);

    Optional<Register> findByRegisterName(String name);

    Optional<Register> findByToken(String token);
}
