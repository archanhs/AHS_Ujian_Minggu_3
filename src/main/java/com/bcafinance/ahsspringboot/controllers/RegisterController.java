package com.bcafinance.ahsspringboot.controllers;
/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 03/12/2022
@Last Modified on 03/12/2022 17:06
Version 1.0
*/


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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
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


    @PostMapping("/register")
    public ResponseEntity<Object>
    saveRegister(@Valid @RequestBody Register register, @RequestHeader Map<String,String> headers,
                 @RequestParam Map<String,String> params,
                 WebRequest request, Error error) throws Exception {
        if(register==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        registerService.saveRegister(register);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }

}
