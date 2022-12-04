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
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "MstBusinessType")
public class BusinessType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BusinessTypeID")
    private Long id;

    @NotEmpty(message = ConstantMessage.WARNING_BUSINESS_NAME_REQUIRED)
    @Length(message = ConstantMessage.WARNING_NAME_MAX_LENGTH)
    @Column(name = "BusinessTypeName",length = 60, nullable = false,unique = true)
    private String businessTypeName;

    @NotEmpty(message = ConstantMessage.WARNING_BUSINESS_DESCRIPTION_REQUIRED)
    @Column(name = "BusinessTypeDescription",length = 255,nullable = false,unique = true)
    private String businessTypeDescription;


    public String getBusinessTypeCategory() {
        return businessTypeCategory;
    }

    public void setBusinessTypeCategory(String businessTypeCategory) {
        this.businessTypeCategory = businessTypeCategory;
    }

    @NotEmpty(message = ConstantMessage.WARNING_BUSINESS_CATEGORY_REQUIRED)
    @Length(message = ConstantMessage.WARNING_BUSINESS_CATEGORY_MAX_LENGTH)
    @Column(name = "BusinessTypeCategory",length = 60, nullable = false)
    private String businessTypeCategory;

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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    public String getBusinessTypeDescription() {
        return businessTypeDescription;
    }

    public void setBusinessTypeDescription(String businessTypeDescription) {
        this.businessTypeDescription = businessTypeDescription;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }
}
