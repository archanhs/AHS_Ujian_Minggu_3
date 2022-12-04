package com.bcafinance.ahsspringboot.models;
/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 02/12/2022
@Last Modified on 02/12/2022 20:47
Version 1.0
*/

import com.bcafinance.ahsspringboot.utils.ConstantMessage;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MstExpedition")
public class Expedition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ExpeditionID")
    private Long id;

    @NotEmpty(message = ConstantMessage.WARNING_EXPEDITION_NAME_REQUIRED)
    @Length(message = ConstantMessage.WARNING_NAME_MAX_LENGTH)
    @Column(name = "ExpeditionName",length = 60, nullable = false,unique = true)
    private String expeditionName;

    @NotEmpty(message = ConstantMessage.WARNING_ADDRESS_REQUIRED)
    @Column(name = "Address",length = 255,nullable = false,unique = true)
    private String address;

    @Column(name = "Phone",length = 16,nullable = false,unique = true)
    private String phone;
    @NotEmpty(message = ConstantMessage.WARNING_PHONE_REQUIRED)
    @Length(message = ConstantMessage.WARNING_PHONE_MAX_LENGTH)
    @Column(name = "Country",length = 40,unique = false,nullable = false)
    private String country;


    @NotEmpty(message = ConstantMessage.WARNING_EMAIL_REQUIRED)
    @Length(message = ConstantMessage.WARNING_EMAIL_MAX_LENGTH)
    @Column(name = "Email",length = 50,nullable = false,unique = true)
    private String email;

    @Column(name = "CreatedBy",nullable = false)
    private String createdBy = "1";

    @Column(name = "CreatedDate",nullable = false)
    private Date createdDate = new Date();//JANGAN GUNAKAN columnDefinition untuk set default kolom, langsung set di variabel nya saja.

    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy ;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = false)
    private boolean isActive = true;


    @ManyToMany(mappedBy = "expeditions")
    @JsonBackReference
    private Set<Reseller> resellersList = new HashSet<Reseller>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpeditionName() {
        return expeditionName;
    }

    public void setExpeditionName(String expeditionName) {
        this.expeditionName = expeditionName;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public Set<Reseller> getResellersList() {
        return resellersList;
    }

    public void setResellersList(Set<Reseller> resellersList) {
        this.resellersList = resellersList;
    }

}
