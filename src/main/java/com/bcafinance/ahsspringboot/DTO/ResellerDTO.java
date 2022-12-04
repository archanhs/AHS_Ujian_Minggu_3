package com.bcafinance.ahsspringboot.DTO;

import com.bcafinance.ahsspringboot.models.BusinessType;
import com.bcafinance.ahsspringboot.models.Expedition;
import com.bcafinance.ahsspringboot.utils.ConstantMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 02/12/2022
@Last Modified on 02/12/2022 9:54
Version 1.0
*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResellerDTO {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
    @NotEmpty(message = ConstantMessage.WARNING_NAME_REQUIRED)
    @Length(message = ConstantMessage.WARNING_NAME_MAX_LENGTH)
    private String resellerName;


    @NotEmpty(message = ConstantMessage.WARNING_ADDRESS_REQUIRED)
    private String address;


    @NotEmpty(message = ConstantMessage.WARNING_PHONE_REQUIRED)
    @Length(message = ConstantMessage.WARNING_PHONE_MAX_LENGTH)
    private String phone;

    private String country;

    @NotEmpty(message = ConstantMessage.WARNING_EMAIL_REQUIRED)
    @Length(message = ConstantMessage.WARNING_EMAIL_MAX_LENGTH)
    private String email;

    public Set<Expedition> getExpeditions() {
        return expeditions;
    }

    public void setExpeditions(Set<Expedition> expeditions) {
        this.expeditions = expeditions;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    private Set<Expedition> expeditions = new HashSet<Expedition>();

    private BusinessType businessType;

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

}
