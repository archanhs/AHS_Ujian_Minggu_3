package com.bcafinance.ahsspringboot.models;

import com.bcafinance.ahsspringboot.utils.ConstantMessage;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 30/11/2022
@Last Modified on 30/11/2022 13:59
Version 1.0
*/
@Data
@Entity
@Table(name = "MstReseller")
public class Reseller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ResellerID")
    private Long id;

    @NotEmpty(message = ConstantMessage.WARNING_EMAIL_REQUIRED)
    @Column(name = "ResellerName",length = 60, nullable = false,unique = true)
    private String resellerName;

//    @Column(name="BusinessType", length = 60,nullable = false,unique = false)
//    private String businessType;

    @Column(name = "Address",length = 255,nullable = false,unique = true)
    private String address;

    @Column(name = "Phone",length = 16,nullable = false,unique = true)
    private String phone;

    @Column(name = "Country",length = 40,unique = false,nullable = false)
    private String country;

    @Column(name = "Email",length = 50,nullable = false,unique = true)
    private String email;

    @ManyToOne
    private BusinessType businessType;

    @NotNull(message = ConstantMessage.WARNING_NUMBER_OF_EMPLOYEES_REQUIRED)
    @Column(name="NumberEmployees",nullable = false)
    private Integer numberEmployees;

    @Column(name = "CreatedBy",nullable = false)
    private String createdBy = "1";

    //    @Column(name = "CreatedDate",nullable = true, columnDefinition = "datetime2(7) DEFAULT GETDATE() ")
    @Column(name = "CreatedDate",nullable = false)
    private Date createdDate = new Date();//JANGAN GUNAKAN columnDefinition untuk set default kolom, langsung set di variabel nya saja.

    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy ;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = false)
    private boolean isActive = true;

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }
}
