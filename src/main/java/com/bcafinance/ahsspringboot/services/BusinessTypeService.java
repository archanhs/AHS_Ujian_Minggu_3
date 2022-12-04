package com.bcafinance.ahsspringboot.services;

import com.bcafinance.ahsspringboot.DTO.ResellerDTO;
import com.bcafinance.ahsspringboot.handler.FormatValidation;
import com.bcafinance.ahsspringboot.handler.ResourceNotFoundException;
import com.bcafinance.ahsspringboot.handler.ResponseHandler;
import com.bcafinance.ahsspringboot.models.BusinessType;
import com.bcafinance.ahsspringboot.models.Expedition;
import com.bcafinance.ahsspringboot.models.Reseller;
import com.bcafinance.ahsspringboot.repos.BusinessTypeRepo;
import com.bcafinance.ahsspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Objects;
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

    @Transactional(rollbackFor = {Exception.class})
    public void saveAllBusinessType(List<BusinessType> ls){
        businessTypeRepo.saveAll(ls);
    }


    public BusinessType findByIdBusinessType(Long id) throws Exception
    {
        return businessTypeRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_BUSINESS_TYPE_NOT_FOUND));
    }


    public List<BusinessType> findByName(String name) throws Exception
    {
        if (businessTypeRepo.searchByBusinessTypeNameContaining(name).size()==0){
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }else{
            return businessTypeRepo.searchByBusinessTypeNameContaining(name);
        }
    }



    @Transactional
    public void updateBusinessTypeByID(BusinessType r) throws Exception{
        BusinessType businessType = businessTypeRepo.findById(r.getId()).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_RESELLER_NOT_FOUND));

        businessType.setModifiedBy("1");
        businessType.setModifiedDate(new Date());

        if(r.getBusinessTypeName() != null &&
                r.getBusinessTypeName().length()>0 &&
                !Objects.equals(businessType.getBusinessTypeName(),r.getBusinessTypeName()))

        {
            Optional<BusinessType> cBeanOptional = businessTypeRepo.findByBusinessTypeName(r.getBusinessTypeName());
            if(cBeanOptional.isPresent())//it means if exists
            {
                throw new ResourceNotFoundException(ConstantMessage.WARNING_NAME_EXIST);
            }
            businessType.setBusinessTypeName(r.getBusinessTypeName());
        }


        if(r.getBusinessTypeDescription() != null
                && !Objects.equals(businessType.getBusinessTypeDescription(),r.getBusinessTypeDescription())
                && !r.getBusinessTypeDescription().equals(""))
        {
            businessType.setBusinessTypeDescription(r.getBusinessTypeDescription());//BERARTI ADA PERUBAHAN DI SINI
        }



        if(r.getBusinessTypeCategory() != null
                && !Objects.equals(businessType.getBusinessTypeCategory(),r.getBusinessTypeCategory())
                && !r.getBusinessTypeCategory().equals(""))
        {
            businessType.setBusinessTypeCategory(r.getBusinessTypeCategory());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(!Objects.equals(businessType.getIsActive(),r.getIsActive()))
        {
            businessType.setIsActive(r.getIsActive());
        }

    }

}
