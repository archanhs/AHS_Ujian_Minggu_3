package com.bcafinance.ahsspringboot.controllers;
/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 03/12/2022
@Last Modified on 03/12/2022 8:53
Version 1.0
*/

import com.bcafinance.ahsspringboot.DTO.ExpeditionDTO;
import com.bcafinance.ahsspringboot.DTO.ResellerDTO;
import com.bcafinance.ahsspringboot.handler.ResourceNotFoundException;
import com.bcafinance.ahsspringboot.handler.ResponseHandler;
import com.bcafinance.ahsspringboot.models.BusinessType;
import com.bcafinance.ahsspringboot.models.Expedition;
import com.bcafinance.ahsspringboot.models.Reseller;
import com.bcafinance.ahsspringboot.services.ExpeditionService;
import com.bcafinance.ahsspringboot.services.ResellerService;
import com.bcafinance.ahsspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
public class ExpeditionController {

    @Getter
    private ExpeditionService expeditionService;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    public ExpeditionController(ExpeditionService expeditionService) {
        this.expeditionService = expeditionService;
    }

    @PostMapping("/expedition")
    public ResponseEntity<Object>
    saveReseller(@Valid @RequestBody Expedition expedition, @RequestHeader Map<String,String> headers,
                 @RequestParam Map<String,String> params,
                 WebRequest request, Error error) throws Exception {
        if(expedition==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        expeditionService.saveExpedition(expedition);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/expedition/datas/dto/all")
    public ResponseEntity<Object> findAllExpedition()throws Exception{

        List<Expedition> lsExpedition = expeditionService.findAllExpedition();

        if(lsExpedition.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
        List<ExpeditionDTO> lsExpeditionDTO = modelMapper.map(lsExpedition, new TypeToken<List<ExpeditionDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsExpeditionDTO,null,null);
    }


    @PostMapping("/expedition/bat")
    public ResponseEntity<Object>
    saveAllExpedition(@RequestBody List<Expedition> expeditions) throws Exception {

        if(expeditions==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        expeditionService.saveAllExpedition(expeditions);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }


    @GetMapping("/expedition/datas/id/{id}")
    public ResponseEntity<Object> findAllResellerById(@PathVariable("id") Long id)throws Exception{

        Expedition expedition = expeditionService.findByIdExpedition(id);

        if(expedition != null)
        {
            ExpeditionDTO expeditionDTO = modelMapper.map(expedition,ExpeditionDTO.class);
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,expeditionDTO,null,null);
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }

    }
    @GetMapping("/expedition/datas/search/n/{name}")
    public ResponseEntity<Object> getExpeditionByName(@PathVariable("name") String name)throws Exception{
        List<Expedition> lsExpedition = expeditionService.findByName(name);

        if(lsExpedition.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        List<ExpeditionDTO> lsExpeditionDTO = modelMapper.map(lsExpedition, new TypeToken<List<ExpeditionDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsExpeditionDTO,null,null);
    }


    @PutMapping("/expedition/update")
    public ResponseEntity<Object> updateExpeditionByID(@RequestBody Expedition expedition)throws Exception{
        expeditionService.updateExpeditionByID(expedition);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }


}
