package com.bcafinance.ahsspringboot.services;

import com.bcafinance.ahsspringboot.handler.FormatValidation;
import com.bcafinance.ahsspringboot.handler.ResourceNotFoundException;
import com.bcafinance.ahsspringboot.models.BusinessType;
import com.bcafinance.ahsspringboot.models.Reseller;
import com.bcafinance.ahsspringboot.repos.BusinessTypeRepo;
import com.bcafinance.ahsspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 01/12/2022
@Last Modified on 01/12/2022 15:08
Version 1.0
*/
@Service
@Transactional
public class BusinessTypeService {
    @Getter
    private BusinessTypeRepo businessTypeRepo;

    @Autowired
    public BusinessTypeService(BusinessTypeRepo businessTypeRepo){
        this.businessTypeRepo = businessTypeRepo;
    }

    public List<BusinessType> findAllBusinessType()
    {
        return businessTypeRepo.findAll();
    }

    public void saveBusinessType(BusinessType businessType) throws Exception{
        if(businessType.getBusinessTypeName()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(businessType.getBusinessTypeDescription()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        //filter name exist
//        Optional<Reseller> resellerByName = resellerRepo.findByResellerName(reseller.getResellerName());
//        if(resellerByName.isPresent())
//        {
//            throw new ResourceNotFoundException(ConstantMessage.WARNING_RESELLER_NAME_EXIST);
//        }

        businessTypeRepo.save(businessType);
    }
}
