package com.bcafinance.ahsspringboot.controllers;
/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 30/11/2022
@Last Modified on 30/11/2022 14:26
Version 1.0
*/

import com.bcafinance.ahsspringboot.DTO.ResellerDTO;
import com.bcafinance.ahsspringboot.handler.ResourceNotFoundException;
import com.bcafinance.ahsspringboot.handler.ResponseHandler;
import com.bcafinance.ahsspringboot.models.Expedition;
import com.bcafinance.ahsspringboot.models.Reseller;
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
public class ResellerController {

    @Getter
    private ResellerService resellerService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ResellerController(ResellerService resellerService) {
        this.resellerService = resellerService;
    }

    @PostMapping("/reseller")
    public ResponseEntity<Object>
    saveReseller(@Valid @RequestBody Reseller reseller,@RequestHeader Map<String,String> headers,
                 @RequestParam Map<String,String> params,
                 WebRequest request, Error error) throws Exception {
        if(reseller==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        resellerService.saveReseller(reseller);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }


    @GetMapping("/reseller/datas/all")
    public ResponseEntity<Object> findAllReseller()throws Exception{

        int data = 0;
        List<Reseller> lsReseller = resellerService.findAllReseller();

        if(lsReseller.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsReseller,null,null);
    }

    @GetMapping("/reseller/datas/dto/all")
    public ResponseEntity<Object> findAllResellerDTO()throws Exception{

        List<Reseller> lsReseller = resellerService.findAllReseller();

        if(lsReseller.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
//        TypeMap<Reseller, ResellerDTO> propertyMapper = modelMapper.createTypeMap(Reseller.class, ResellerDTO.class);
//        propertyMapper.addMappings(
//                mapper -> mapper.map(src -> src.getBusinessType().getBusinessTypeName(), ResellerDTO::setBusinessTypeName)
//        );
//
//        propertyMapper.addMappings(
//                mapper -> mapper.map(src -> src.getBusinessType().getBusinessTypeDescription(), ResellerDTO::setBusinessTypeDescription)
//        );

//        if(modelMapper.getTypeMap(Reseller.class,ResellerDTO.class)==null){
//            TypeMap<Reseller, ResellerDTO> propertyMapper = modelMapper.createTypeMap(Reseller.class, ResellerDTO.class);
//            propertyMapper.addMappings(
//                    mapper -> mapper.map(src -> src.getBusinessType().getBusinessTypeName(), ResellerDTO::setBusinessTypeName)
//            );
//
//            propertyMapper.addMappings(
//                    mapper -> mapper.map(src -> src.getBusinessType().getBusinessTypeDescription(), ResellerDTO::setBusinessTypeDescription)
//            );
//        }
        List<ResellerDTO> lsResellerDTO = modelMapper.map(lsReseller, new TypeToken<List<ResellerDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsResellerDTO,null,null);
    }

    @GetMapping("/reseller/datas/id/{id}")
    public ResponseEntity<Object> findAllResellerById(@PathVariable("id") Long id)throws Exception{

        Reseller reseller = resellerService.findByIdReseller(id);

        if(reseller != null)
        {
            ResellerDTO resellerDTO = modelMapper.map(reseller,ResellerDTO.class);
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,resellerDTO,null,null);
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }

    }


    @GetMapping("/reseller/datas/search/e/{email}")
    public ResponseEntity<Object> getResellerByEmail(@PathVariable("email") String email)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,resellerService.findByEmailReseller(email),null,null);
    }

    @GetMapping("/reseller/datas/search/n/{name}")
    public ResponseEntity<Object> getResellerByName(@PathVariable("name") String name)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,resellerService.findByNameReseller(name),null,null);
    }

    @GetMapping("/reseller/datas/search/a/{address}")
    public ResponseEntity<Object> getResellerByAddress(@PathVariable("address") String address)throws Exception{
        List<Reseller> lsReseller = resellerService.findByAddress(address);

        if(lsReseller.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        List<ResellerDTO> lsResellerDTO = modelMapper.map(lsReseller, new TypeToken<List<ResellerDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsResellerDTO,null,null);
    }


    @GetMapping("/reseller/datas/search/nc/{name}")
    public ResponseEntity<Object> getResellerNotContainingName(@PathVariable("name") String name)throws Exception{

        List<Reseller> lsReseller = resellerService.findByNameNotContaining(name);

        if(lsReseller.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsReseller,null,null);
    }
    @GetMapping("/reseller/datas/search/nl/{name}")
    public ResponseEntity<Object> getResellerNotLikeName(@PathVariable("name") String name)throws Exception{

        List<Reseller> lsReseller = resellerService.findByNameNotLike(name);

        if(lsReseller.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsReseller,null,null);
    }
    @GetMapping("/reseller/datas/search/sw/{name}")
    public ResponseEntity<Object> getResellerNameStartsWith(@PathVariable("name") String name)throws Exception{

        List<Reseller> lsReseller = resellerService.findByNameStartsWith(name);

        if(lsReseller.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsReseller,null,null);
    }
    @GetMapping("/reseller/datas/search/ew/{name}")
    public ResponseEntity<Object> getResellerNameEndWith(@PathVariable("name") String name)throws Exception{

        List<Reseller> lsReseller = resellerService.findByNameEndWith(name);

        if(lsReseller.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsReseller,null,null);
    }



    @PutMapping("/reseller/update")
    public ResponseEntity<Object> updateCustomerByID(@RequestBody Reseller reseller)throws Exception{
        resellerService.updateResellerByID(reseller);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }

    @PostMapping("/reseller/bat")
    public ResponseEntity<Object>
    saveAllResellers(@RequestBody List<Reseller> resellers) throws Exception {

        if(resellers==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        resellerService.saveAllReseller(resellers);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }


    @PostMapping("/reseller/exp/{id}")
    public ResponseEntity<Object> addExpedition(@RequestBody Expedition expedition, @PathVariable("id") Long resellerId) throws Exception {
        resellerService.addExpedition(expedition,resellerId);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }

}
