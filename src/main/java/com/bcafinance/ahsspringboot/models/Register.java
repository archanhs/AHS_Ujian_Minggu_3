package com.bcafinance.ahsspringboot.models;
/*
Created By IntelliJ IDEA 2022.2.3 (Community Edition) 
@Author ASUS a.k.a. Archan
ITDP 7
Created on 03/12/2022
@Last Modified on 03/12/2022 16:56
Version 1.0
*/

import com.bcafinance.ahsspringboot.utils.ConstantMessage;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "MstRegister")
public class Register implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RegisterID")
    private Long id;

    @NotEmpty(message = ConstantMessage.WARNING_NAME_REQUIRED)
    @Length(message = ConstantMessage.WARNING_NAME_MAX_LENGTH)
    @Column(name = "RegisterName",length = 60, nullable = false,unique = true)
    private String registerName;


    @NotEmpty(message = ConstantMessage.WARNING_ADDRESS_REQUIRED)
    @Column(name = "Address",length = 255,nullable = false,unique = true)
    private String address;


    @NotNull(message = ConstantMessage.WARNING_BIRTHDATE_REQUIRED)
    @Column(name = "BirthDate",nullable = false)
    private LocalDate birthDate;

    @NotNull(message = ConstantMessage.WARNING_KODEPOS_REQUIRED)
    @Column(name = "Kodepos",length = 6,nullable = false)
    private Integer kodepos;

    @NotEmpty(message = ConstantMessage.WARNING_PHONE_REQUIRED)
    @Length(message = ConstantMessage.WARNING_PHONE_MAX_LENGTH)
    @Column(name = "Phone",length = 16,nullable = false,unique = true)
    private String phone;

    @NotEmpty(message = ConstantMessage.WARNING_EMAIL_REQUIRED)
    @Length(message = ConstantMessage.WARNING_EMAIL_MAX_LENGTH)
    @Column(name = "Email",length = 35,nullable = false,unique = true)
    private String email;

    @Column(name = "Token",length = 255, nullable = true,unique = true)
    private String token;

    @Column(name = "isVerify",nullable = true)
    private boolean isVerify = false;

    @Column(name = "CreatedBy",nullable = false)
    private String createdBy = "1";

    //    @Column(name = "CreatedDate",nullable = true, columnDefinition = "datetime2(7) DEFAULT GETDATE() ")
    @Column(name = "CreatedDate",nullable = false)
    private Date createdDate = new Date();//JANGAN GUNAKAN columnDefinition untuk set default kolom, langsung set di variabel nya saja.


    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy ;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = false)
    private boolean isActive = true;
}
