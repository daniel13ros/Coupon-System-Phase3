package com.daniel.coupon_system_spring.controllers;

import com.daniel.coupon_system_spring.dto.LoginReqDto;
import com.daniel.coupon_system_spring.dto.LoginResDto;
import com.daniel.coupon_system_spring.exceptions.CouponCostumeException;
import com.daniel.coupon_system_spring.exceptions.SecuritySystemException;
import com.daniel.coupon_system_spring.login.LoginManager;
import com.daniel.coupon_system_spring.services.AdminService;
import com.daniel.coupon_system_spring.services.CompanyService;
import com.daniel.coupon_system_spring.services.CustomerService;
import com.daniel.coupon_system_spring.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by danielR on 16/11/2022
 */


public abstract class ClientController {


    @Autowired
    protected LoginManager loginManager;
    @Autowired
    protected AdminService adminService;

    @Autowired
    protected CompanyService companyService;

    @Autowired
    protected CustomerService customerService;
    @Autowired
    protected TokenService tokenService;

    public abstract LoginResDto login(LoginReqDto req) throws CouponCostumeException, SecuritySystemException;





}
