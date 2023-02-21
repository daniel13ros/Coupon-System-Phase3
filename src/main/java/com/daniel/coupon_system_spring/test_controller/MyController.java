package com.daniel.coupon_system_spring.test_controller;

import com.daniel.coupon_system_spring.beans.ClientType;
import com.daniel.coupon_system_spring.beans.Coupon;
import com.daniel.coupon_system_spring.exceptions.SecMsg;
import com.daniel.coupon_system_spring.exceptions.SecuritySystemException;
import com.daniel.coupon_system_spring.repos.CouponRepository;
import com.daniel.coupon_system_spring.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Created by danielR on 07/02/2023
 */

@RestController
@RequestMapping("api/test")
public class MyController {

    @Autowired
    private CouponRepository couponRepository;

    @GetMapping("/hi")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/coupons")
    public List<Coupon> showCoupons() {
        return couponRepository.findAll();
    }
}
