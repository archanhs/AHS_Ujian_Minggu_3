package com.bcafinance.ahsspringboot.controllers;


import com.bcafinance.ahsspringboot.configuration.ConfigProperties;
import com.bcafinance.ahsspringboot.utils.ConstantMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/taketour")
public class TakeTourController {

    @GetMapping("/welcome")
    public String getTakeTour(){
        return ConstantMessage.WELCOME_MESSAGE;
    }

    @PostMapping("/readytostart")
    public String postTakeTour(){
        return ConstantMessage.TAKE_TOUR;
    }

    @GetMapping("/runnerz")
    public String executionClass(){

        ConfigProperties.getEmailPassword();
        return "OK";
    }
}
