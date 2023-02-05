package com.daniel.coupon_system_spring.controllers;

import com.daniel.coupon_system_spring.beans.Category;
import com.daniel.coupon_system_spring.beans.ClientType;
import com.daniel.coupon_system_spring.beans.Coupon;
import com.daniel.coupon_system_spring.beans.Customer;
import com.daniel.coupon_system_spring.dto.LoginReqDto;
import com.daniel.coupon_system_spring.dto.LoginResDto;
import com.daniel.coupon_system_spring.exceptions.CouponCostumeException;
import com.daniel.coupon_system_spring.exceptions.SecMsg;
import com.daniel.coupon_system_spring.exceptions.SecuritySystemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by danielR on 16/11/2022
 */

@RestController
@RequestMapping("api/customers")
public class CustomerController extends ClientController   {

    @PostMapping("/purchase/{couponId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void purchaseCoupon(@RequestHeader("Authorization") UUID token, @PathVariable int couponId) throws CouponCostumeException, SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Customer)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        int customerId= tokenService.getCustomerID(token);
        customerService.purchaseCoupon(customerId,couponId);
        System.out.println("Success");
    }

    @GetMapping("/coupons")
    public List<Coupon> getCustomerCoupons(@RequestHeader("Authorization") UUID token) throws SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Customer)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        int customerId= tokenService.getCustomerID(token);
        return customerService.getCustomerCoupons(customerId);
    }

    @GetMapping("/coupons/category")
    public List<Coupon> getCustomerCoupons(@RequestHeader("Authorization") UUID token,@RequestParam Category category) throws SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Customer)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        int customerId= tokenService.getCustomerID(token);
        return customerService.getCustomerCoupons(customerId,category);
    }

    @GetMapping("/coupons/price")
    public List<Coupon> getCustomerCoupons(@RequestHeader("Authorization") UUID token,@RequestParam double maxPrice) throws SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Customer)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        int customerId= tokenService.getCustomerID(token);
        return customerService.getCustomerCoupons(customerId,maxPrice);
    }

    @GetMapping("/coupons/all")
    public List<Coupon> getAllCoupons(@RequestHeader("Authorization") UUID token) throws SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Customer)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        return customerService.getAllCoupons();
    }

    @GetMapping("/details")
    public Customer getCustomerDetails(@RequestHeader("Authorization") UUID token) throws SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Customer)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        int customerId= tokenService.getCustomerID(token);
        return customerService.getCustomerDetails(customerId);
    }


    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto login(@RequestBody LoginReqDto req) throws SecuritySystemException, SecuritySystemException {

        String email = req.getEmail();
        String pass = req.getPassword();
        return adminService.getCustomer(email,pass);
    }
}
