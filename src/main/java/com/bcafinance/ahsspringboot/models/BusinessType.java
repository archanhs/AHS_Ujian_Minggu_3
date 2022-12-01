package com.bcafinance.ahsspringboot.models;
/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 01/12/2022
@Last Modified on 01/12/2022 14:58
Version 1.0
*/

import com.bcafinance.ahsspringboot.utils.ConstantMessage;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Entity
@Table(name = "MstBusinessType")
public class BusinessType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BusinessTypeID")
    private Long id;

    @NotEmpty(message = ConstantMessage.WARNING_BUSINESS_NAME_REQUIRED)
    @Column(name = "BusinessTypeName",length = 60, nullable = false,unique = true)
    private String businessTypeName;

    @Column(name = "BusinessTypeDescription",length = 255,nullable = false,unique = true)
    private String businessTypeDescription;

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
