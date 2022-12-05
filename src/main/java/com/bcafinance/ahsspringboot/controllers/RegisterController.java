package com.bcafinance.ahsspringboot.controllers;
/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 03/12/2022
@Last Modified on 03/12/2022 17:06
Version 1.0
*/


import com.bcafinance.ahsspringboot.DTO.ResellerDTO;
import com.bcafinance.ahsspringboot.configuration.ConfigProperties;
import com.bcafinance.ahsspringboot.core.Crypto;
import com.bcafinance.ahsspringboot.core.SMTPCore;
import com.bcafinance.ahsspringboot.handler.ResourceNotFoundException;
import com.bcafinance.ahsspringboot.handler.ResponseHandler;
import com.bcafinance.ahsspringboot.models.Register;
import com.bcafinance.ahsspringboot.models.Reseller;
import com.bcafinance.ahsspringboot.services.RegisterService;
import com.bcafinance.ahsspringboot.services.ResellerService;
import com.bcafinance.ahsspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
public class RegisterController {

    @Getter
    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

//
//    @PostMapping("/register")
//    public ResponseEntity<Object>
//    saveRegister(@Valid @RequestBody Register register, @RequestHeader Map<String,String> headers,
//                 @RequestParam Map<String,String> params,
//                 WebRequest request, Error error) throws Exception {
//        if(register==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
//        registerService.saveRegister(register);
//        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
//    }

    @PostMapping("/register")
    public ResponseEntity<Object>
    saveEmails( @RequestBody Register emails) throws Exception {
        if(emails==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        String[] strArr = new String[1];
        strArr[0] = emails.getEmail();

        String token = new Crypto().performEncrypt(emails.getPhone()+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis())));
        emails.setToken(token);
        registerService.saveRegister(emails);
        System.out.println(System.getProperty("user.dir"));
        SMTPCore sc = new SMTPCore();
        ConfigProperties.getEmailPassword();
        String s = "coba";
        System.out.println(sc.sendMailWithAttachment(strArr,
                "<h1>EMAIL AUTHENTICATION</h1>","TOKEN REGISTRATION : "+"http://localhost:8080/api/v1/a/"+token,
                "SSL"));


        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SEND_EMAIL, HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/a/{token}")
    public ResponseEntity<Object> activationToken(@PathVariable("token") String token)throws Exception{

        Register register = registerService.activationToken(token);

        if(register != null)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_OTENTIKASI, HttpStatus.OK,null,null,null);
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_OTENTIKASI_FAIL);
        }
    }



}
