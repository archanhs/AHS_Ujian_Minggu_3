package com.bcafinance.ahsspringboot.DTO;
/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 03/12/2022
@Last Modified on 03/12/2022 12:11
Version 1.0
*/

import com.bcafinance.ahsspringboot.models.Reseller;
import com.bcafinance.ahsspringboot.utils.ConstantMessage;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpeditionDTO {

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

    private Long id;


    @NotEmpty(message = ConstantMessage.WARNING_EXPEDITION_NAME_REQUIRED)
    @Length(message = ConstantMessage.WARNING_NAME_MAX_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_EXPEDITION_NAME_REQUIRED)
    private String expeditionName;

    @NotEmpty(message = ConstantMessage.WARNING_ADDRESS_REQUIRED)
    private String address;

    @NotEmpty(message = ConstantMessage.WARNING_PHONE_REQUIRED)
    @Length(message = ConstantMessage.WARNING_PHONE_MAX_LENGTH)
    private String phone;

    private String country;


    @NotEmpty(message = ConstantMessage.WARNING_EMAIL_REQUIRED)
    @Length(message = ConstantMessage.WARNING_EMAIL_MAX_LENGTH)
    private String email;

    public Set<Reseller> getResellersList() {
        return resellersList;
    }

    public void setResellersList(Set<Reseller> resellersList) {
        this.resellersList = resellersList;
    }

    private Set<Reseller> resellersList = new HashSet<Reseller>();
}
