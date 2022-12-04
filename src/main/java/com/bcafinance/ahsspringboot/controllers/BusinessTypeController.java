package com.bcafinance.ahsspringboot.controllers;

import com.bcafinance.ahsspringboot.DTO.BusinessTypeDTO;
import com.bcafinance.ahsspringboot.DTO.ExpeditionDTO;
import com.bcafinance.ahsspringboot.DTO.ResellerDTO;
import com.bcafinance.ahsspringboot.handler.ResourceNotFoundException;
import com.bcafinance.ahsspringboot.handler.ResponseHandler;
import com.bcafinance.ahsspringboot.models.BusinessType;
import com.bcafinance.ahsspringboot.models.Expedition;
import com.bcafinance.ahsspringboot.models.Reseller;
import com.bcafinance.ahsspringboot.services.BusinessTypeService;
import com.bcafinance.ahsspringboot.services.ResellerService;
import com.bcafinance.ahsspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 01/12/2022
@Last Modified on 01/12/2022 15:22
Version 1.0
*/
@RestController
@RequestMapping("api/v1/")
public class BusinessTypeController {

    @Getter
    private BusinessTypeService businessTypeService;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public BusinessTypeController(BusinessTypeService businessTypeService) {
        this.businessTypeService = businessTypeService;
    }

    @PostMapping("/business_type")
    public ResponseEntity<Object>
    saveBusinessType(@Valid @RequestBody BusinessType businessType) throws Exception {
        if(businessType==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        businessTypeService.saveBusinessType(businessType);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/business_type/datas/all")
    public ResponseEntity<Object> findAllBusinessType()throws Exception{

        List<BusinessType> lsBusinessType = businessTypeService.findAllBusinessType();

        if(lsBusinessType.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        List<BusinessTypeDTO> lsBusinessTypeDTO = modelMapper.map(lsBusinessType, new TypeToken<List<BusinessTypeDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsBusinessTypeDTO,null,null);
    }

    @PostMapping("/business_type/bat")
    public ResponseEntity<Object>
    saveAllBusinessType(@RequestBody List<BusinessType> businessTypes) throws Exception {

        if(businessTypes==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        businessTypeService.saveAllBusinessType(businessTypes);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }


    @GetMapping("/business_type/datas/id/{id}")
    public ResponseEntity<Object> findAllBusinessTypeById(@PathVariable("id") Long id)throws Exception{

        BusinessType businessType = businessTypeService.findByIdBusinessType(id);

        if(businessType != null)
        {
            BusinessTypeDTO businessTypeDTO = modelMapper.map(businessType,BusinessTypeDTO.class);
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,businessTypeDTO,null,null);
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }

    }
    @GetMapping("/business_type/datas/search/n/{name}")
    public ResponseEntity<Object> getBusinessTypeByName(@PathVariable("name") String name)throws Exception{
        List<BusinessType> lsBusinessType = businessTypeService.findByName(name);

        if(lsBusinessType.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        List<BusinessTypeDTO> lsBusinessTypeDTO = modelMapper.map(lsBusinessType, new TypeToken<List<BusinessTypeDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsBusinessTypeDTO,null,null);
    }


    @PutMapping("/business_type/update")
    public ResponseEntity<Object> updateBusinessTypeByID(@RequestBody BusinessType businessType)throws Exception{
        businessTypeService.updateBusinessTypeByID(businessType);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }




}
