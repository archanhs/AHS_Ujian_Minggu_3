package com.bcafinance.ahsspringboot.services;
/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 30/11/2022
@Last Modified on 30/11/2022 14:27
Version 1.0
*/

import com.bcafinance.ahsspringboot.handler.FormatValidation;
import com.bcafinance.ahsspringboot.handler.ResourceNotFoundException;
import com.bcafinance.ahsspringboot.models.Customers;
import com.bcafinance.ahsspringboot.models.Reseller;
import com.bcafinance.ahsspringboot.repos.ResellerRepo;
import com.bcafinance.ahsspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ResellerService {

    @Getter
    private ResellerRepo resellerRepo;

    @Autowired
    public ResellerService(ResellerRepo resellerRepo){
        this.resellerRepo = resellerRepo;
    }
//
//    public Reseller findByIdReseller(Long id) throws Exception{
//        return resellerRepo.findById(id).orElseThrow(()->new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
//    }

    public List<Reseller> findAllReseller()
    {
        return resellerRepo.findAll();
    }
    public List<Reseller> findByNameNotContaining(String name) throws Exception
    {
        if (resellerRepo.findByResellerNameNotContaining(name).size()==0){
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_NOT_FOUND);
        }else{
            return resellerRepo.findByResellerNameNotContaining(name);
        }
    }

    public List<Reseller> findByNameNotLike(String name) throws Exception
    {
        if (resellerRepo.findByResellerNameNotLike(name).size()==0){
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_NOT_FOUND);
        }else{
            return resellerRepo.findByResellerNameNotContaining(name);
        }
    }

    public List<Reseller> findByNameStartsWith(String name) throws Exception
    {
            return resellerRepo.searchByResellerNameStartsWith(name);
    }
    public List<Reseller> findByNameEndWith(String name) throws Exception
    {
        return resellerRepo.searchByResellerNameEndsWith(name);
    }

    public Reseller findByEmailReseller(String email) throws Exception
    {
        return resellerRepo.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_RESELLER_NOT_FOUND));
    }
    public Reseller findByNameReseller(String name) throws Exception
    {
        return resellerRepo.findByResellerName(name).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_RESELLER_NOT_FOUND));
    }
    public List<Reseller> findByAddress(String address) throws Exception
    {
        if (resellerRepo.searchByAddressContaining(address).size()==0){
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_NOT_FOUND);
        }else{
            return resellerRepo.searchByAddressContaining(address);
        }
    }
    public void saveReseller(Reseller reseller) throws Exception{
        if(reseller.getResellerName()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(reseller.getBusinessType()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(reseller.getAddress()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(reseller.getPhone()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(reseller.getCountry()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(reseller.getEmail()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        FormatValidation.phoneNumberFormatValidation(reseller.getPhone());
        FormatValidation.emailFormatValidation(reseller.getEmail());
        //filter email exist
        Optional<Reseller> resellerByEmail = resellerRepo.findByEmail(reseller.getEmail());
        if(resellerByEmail.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
        }
        //filter name exist
        Optional<Reseller> resellerByName = resellerRepo.findByResellerName(reseller.getResellerName());
        if(resellerByName.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_RESELLER_NAME_EXIST);
        }
        //filter count karyawan
        if (reseller.getNumberEmployees()<=0){
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NUMBER_OF_EMPLOYEES);
        }
        resellerRepo.save(reseller);
    }


    @Transactional
    public void updateResellerByID(Reseller r) throws Exception{
        Reseller resellers = resellerRepo.findById(r.getId()).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_RESELLER_NOT_FOUND));

        resellers.setModifiedBy("1");
        resellers.setModifiedDate(new Date());

        if(r.getResellerName() != null &&
                r.getResellerName().length()>0 &&
                !Objects.equals(resellers.getResellerName(),r.getResellerName()))

        {
            Optional<Reseller> cBeanOptional = resellerRepo.findByResellerName(r.getResellerName());
            if(cBeanOptional.isPresent())//it means if exists
            {
                throw new ResourceNotFoundException(ConstantMessage.WARNING_RESELLER_NAME_EXIST);
            }
            resellers.setResellerName(r.getResellerName());
        }


        if(r.getAddress() != null
                && !Objects.equals(resellers.getAddress(),r.getAddress())
                && !r.getAddress().equals(""))
        {
            resellers.setAddress(r.getAddress());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(r.getEmail() != null &&
                r.getEmail().length()>0 &&
                !Objects.equals(resellers.getEmail(),r.getEmail()))

        {
            FormatValidation.emailFormatValidation(r.getEmail());

            Optional<Reseller> cBeanOptional = resellerRepo.findByEmail(r.getEmail());
            if(cBeanOptional.isPresent())//it means if exists
            {
                throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
            }
            resellers.setEmail(r.getEmail());
        }

        if(r.getCountry() != null
                && !Objects.equals(resellers.getCountry(),r.getCountry())
                && !r.getCountry().equals(""))
        {
            resellers.setCountry(r.getCountry());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(r.getBusinessType() != null
                && !Objects.equals(resellers.getBusinessType(),r.getBusinessType())
                && !r.getBusinessType().equals(""))
        {
            resellers.setBusinessType(r.getBusinessType());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(r.getPhone() != null &&
                r.getPhone().length()>0 &&
                !Objects.equals(resellers.getPhone(),r.getPhone())){

            FormatValidation.phoneNumberFormatValidation(resellers.getPhone());
            resellers.setPhone(r.getPhone());
        }

        if(!Objects.equals(resellers.getNumberEmployees(),r.getNumberEmployees()) && r.getNumberEmployees()!=0)
        {
            if (r.getNumberEmployees()<=0){
                throw new ResourceNotFoundException(ConstantMessage.WARNING_NUMBER_OF_EMPLOYEES);
            }
            resellers.setNumberEmployees(r.getNumberEmployees());
        }
        if(!Objects.equals(resellers.getIsActive(),r.getIsActive()))
        {
            resellers.setIsActive(r.getIsActive());
        }

    }




}
