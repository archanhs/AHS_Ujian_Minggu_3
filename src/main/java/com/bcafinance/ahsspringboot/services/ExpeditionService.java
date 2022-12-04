package com.bcafinance.ahsspringboot.services;
/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 03/12/2022
@Last Modified on 03/12/2022 8:46
Version 1.0
*/

import com.bcafinance.ahsspringboot.handler.FormatValidation;
import com.bcafinance.ahsspringboot.handler.ResourceNotFoundException;
import com.bcafinance.ahsspringboot.models.BusinessType;
import com.bcafinance.ahsspringboot.models.Expedition;
import com.bcafinance.ahsspringboot.models.Reseller;
import com.bcafinance.ahsspringboot.repos.ExpeditionRepo;
import com.bcafinance.ahsspringboot.repos.ResellerRepo;
import com.bcafinance.ahsspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
@Transactional
public class ExpeditionService {
    @Getter
    private ExpeditionRepo expeditionRepo;

    @Autowired
    public ExpeditionService(ExpeditionRepo expeditionRepo){
        this.expeditionRepo = expeditionRepo;
    }

    public void saveExpedition(Expedition expedition) throws Exception{

        expeditionRepo.save(expedition);
    }

    public List<Expedition> findAllExpedition()
    {
        return expeditionRepo.findAll();
    }

    @Transactional(rollbackFor = {Exception.class})
    public void saveAllExpedition(List<Expedition> ls){
        expeditionRepo.saveAll(ls);
    }

    public Expedition findByIdExpedition(Long id) throws Exception
    {
        return expeditionRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_EKSPEDITION_NOT_FOUND));
    }

    public List<Expedition> findByName(String name) throws Exception
    {
        if (expeditionRepo.searchByExpeditionNameContaining(name).size()==0){
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }else{
            return expeditionRepo.searchByExpeditionNameContaining(name);
        }
    }


    @Transactional
    public void updateExpeditionByID(Expedition r) throws Exception{
        Expedition expedition = expeditionRepo.findById(r.getId()).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_RESELLER_NOT_FOUND));

        expedition.setModifiedBy("1");
        expedition.setModifiedDate(new Date());

        if(r.getExpeditionName() != null &&
                r.getExpeditionName().length()>0 &&
                !Objects.equals(expedition.getExpeditionName(),r.getExpeditionName()))

        {
            Optional<Expedition> cBeanOptional = expeditionRepo.findByExpeditionName(r.getExpeditionName());
            if(cBeanOptional.isPresent())//it means if exists
            {
                throw new ResourceNotFoundException(ConstantMessage.WARNING_NAME_EXIST);
            }
            expedition.setExpeditionName(r.getExpeditionName());
        }


        if(r.getAddress() != null
                && !Objects.equals(expedition.getAddress(),r.getAddress())
                && !r.getAddress().equals(""))
        {
            expedition.setAddress(r.getAddress());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(r.getEmail() != null &&
                r.getEmail().length()>0 &&
                !Objects.equals(expedition.getEmail(),r.getEmail()))

        {
            FormatValidation.emailFormatValidation(r.getEmail());

            Optional<Expedition> cBeanOptional = expeditionRepo.findByEmail(r.getEmail());
            if(cBeanOptional.isPresent())//it means if exists
            {
                throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
            }
            expedition.setEmail(r.getEmail());
        }

        if(r.getCountry() != null
                && !Objects.equals(expedition.getCountry(),r.getCountry())
                && !r.getCountry().equals(""))
        {
            expedition.setCountry(r.getCountry());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(r.getPhone() != null &&
                r.getPhone().length()>0 &&
                !Objects.equals(expedition.getPhone(),r.getPhone())){

            FormatValidation.phoneNumberFormatValidation(expedition.getPhone());
            expedition.setPhone(r.getPhone());
        }

        if(!Objects.equals(expedition.isActive(),r.isActive()))
        {
            expedition.setActive(r.isActive());
        }

    }

}
