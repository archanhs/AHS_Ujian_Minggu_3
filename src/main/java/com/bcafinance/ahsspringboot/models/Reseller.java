package com.bcafinance.ahsspringboot.models;

import com.bcafinance.ahsspringboot.utils.ConstantMessage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 30/11/2022
@Last Modified on 30/11/2022 13:59
Version 1.0
*/

@Entity
@Table(name = "MstReseller")
public class Reseller implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ResellerID")
    private Long id;


    @NotEmpty(message = ConstantMessage.WARNING_NAME_REQUIRED)
    @Length(message = ConstantMessage.WARNING_NAME_MAX_LENGTH)
    @Column(name = "ResellerName",length = 60, nullable = false,unique = true)
    private String resellerName;

//    @Column(name="BusinessType", length = 60,nullable = false,unique = false)
//    private String businessType;

    @NotEmpty(message = ConstantMessage.WARNING_ADDRESS_REQUIRED)
    @Column(name = "Address",length = 255,nullable = false,unique = true)
    private String address;

    @NotEmpty(message = ConstantMessage.WARNING_PHONE_REQUIRED)
    @Length(message = ConstantMessage.WARNING_PHONE_MAX_LENGTH)
    @Column(name = "Phone",length = 16,nullable = false,unique = true)
    private String phone;

    @Column(name = "Country",length = 40,unique = false,nullable = false)
    private String country;
    @NotEmpty(message = ConstantMessage.WARNING_EMAIL_REQUIRED)
    @Length(message = ConstantMessage.WARNING_EMAIL_MAX_LENGTH)
    @Column(name = "Email",length = 50,nullable = false,unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "BusinessTypeID")
    private BusinessType businessType;

    @ManyToMany
    @JoinTable(
            name = "ResellerExpedition",
            joinColumns = @JoinColumn(name="ResellerID",referencedColumnName = "ResellerID"),
            inverseJoinColumns = @JoinColumn(name = "ExpeditionID",referencedColumnName = "ExpeditionID")
    )
    @JsonManagedReference
    @JsonIgnore
    private Set<Expedition> expeditions = new HashSet<Expedition>();


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


    public Set<Expedition> getExpeditions() {
        return expeditions;
    }

    public void setExpeditions(Set<Expedition> expeditions) {
        this.expeditions = expeditions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResellerName() {
        return resellerName;
    }

    public void setResellerName(String resellerName) {
        this.resellerName = resellerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public Integer getNumberEmployees() {
        return numberEmployees;
    }

    public void setNumberEmployees(Integer numberEmployees) {
        this.numberEmployees = numberEmployees;
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
}
