package com.daniel.coupon_system_spring.services;

import com.daniel.coupon_system_spring.exceptions.CouponCostumeException;
import com.daniel.coupon_system_spring.repos.CompanyRepository;
import com.daniel.coupon_system_spring.repos.CouponRepository;
import com.daniel.coupon_system_spring.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by danielR on 15/11/2022
 */
public abstract class ClientService {
    @Autowired
    protected CouponRepository couponRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CompanyRepository companyRepository;

    public abstract boolean login(String email,String password) throws CouponCostumeException;

}
