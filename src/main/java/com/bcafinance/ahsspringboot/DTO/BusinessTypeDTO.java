package com.bcafinance.ahsspringboot.DTO;
/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 03/12/2022
@Last Modified on 03/12/2022 13:55
Version 1.0
*/

import com.bcafinance.ahsspringboot.utils.ConstantMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessTypeDTO {

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

    private Long id;

    @NotEmpty(message = ConstantMessage.WARNING_BUSINESS_NAME_REQUIRED)
    private String businessTypeName;


    public String getBusinessTypeCategory() {
        return businessTypeCategory;
    }

    public void setBusinessTypeCategory(String businessTypeCategory) {
        this.businessTypeCategory = businessTypeCategory;
    }

    @NotEmpty(message = ConstantMessage.WARNING_BUSINESS_CATEGORY_REQUIRED)
    @Length(message = ConstantMessage.WARNING_BUSINESS_CATEGORY_MAX_LENGTH)
    private String businessTypeCategory;

    @NotEmpty(message = ConstantMessage.WARNING_BUSINESS_DESCRIPTION_REQUIRED)
    private String businessTypeDescription;

}
