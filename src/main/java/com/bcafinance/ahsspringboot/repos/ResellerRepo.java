package com.bcafinance.ahsspringboot.repos;
/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 30/11/2022
@Last Modified on 30/11/2022 14:21
Version 1.0
*/

import com.bcafinance.ahsspringboot.models.Customers;
import com.bcafinance.ahsspringboot.models.Reseller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ResellerRepo extends JpaRepository<Reseller,Long> {
//
//    @Query("SELECT p FROM Products p WHERE p.description LIKE %:description%")
//    List<Reseller> searchByAddressLike(@Param("address") String address);

    Optional<Reseller> findByEmail(String email);

    Optional<Reseller> findById(long id);

    Optional<Reseller> findByResellerName(String name);

    List<Reseller> findByResellerNameNotContaining(String name);

    List<Reseller> searchByAddressContaining(@Param("address") String address);


//    @Query("SELECT p FROM Products p WHERE p.name LIKE ?1%")
    List<Reseller> findByResellerNameNotLike(String name);


//    @Query("SELECT p FROM Products p WHERE p.name LIKE ?1%")
    List<Reseller> searchByResellerNameStartsWith(String name);
//    @Query("SELECT p FROM Products p WHERE p.Name LIKE %?#{escape([0])} escape ?#{escapeCharacter()}")
    List<Reseller> searchByResellerNameEndsWith(String name);
}
