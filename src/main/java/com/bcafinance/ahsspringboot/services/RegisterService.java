package com.bcafinance.ahsspringboot.services;
/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 03/12/2022
@Last Modified on 03/12/2022 17:00
Version 1.0
*/

import com.bcafinance.ahsspringboot.core.Crypto;
import com.bcafinance.ahsspringboot.handler.FormatValidation;
import com.bcafinance.ahsspringboot.handler.ResourceNotFoundException;
import com.bcafinance.ahsspringboot.models.Register;
import com.bcafinance.ahsspringboot.models.Reseller;
import com.bcafinance.ahsspringboot.repos.RegisterRepo;
import com.bcafinance.ahsspringboot.repos.ResellerRepo;
import com.bcafinance.ahsspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class RegisterService {


    @Getter
    private RegisterRepo registerRepo;

    @Autowired
    public RegisterService(RegisterRepo registerRepo){
        this.registerRepo = registerRepo;
    }

    public void saveRegister(Register register) throws Exception{
        if(register.getRegisterName()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(register.getAddress()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(register.getPhone()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(register.getEmail()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        FormatValidation.phoneNumberFormatValidation(register.getPhone());
        FormatValidation.emailFormatValidation(register.getEmail());

        register.setToken(new Crypto().performEncrypt(register.getPhone()+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis()))));
        //filter email exist
        Optional<Register> registerByEmail = registerRepo.findByEmail(register.getEmail());
        if(registerByEmail.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
        }
        //filter name exist
        Optional<Register> registerByName = registerRepo.findByRegisterName(register.getRegisterName());
        if(registerByName.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NAME_EXIST);
        }

        int firstDigit = Integer.parseInt(Integer.toString(register.getKodepos()).substring(0, 1));
        if(firstDigit == 0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NAME_EXIST);
        }
        registerRepo.save(register);
    }


    public Register activationToken(String token) throws Exception
    {
        return registerRepo.findByToken(token).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_OTENTIKASI_FAIL));
    }
}
