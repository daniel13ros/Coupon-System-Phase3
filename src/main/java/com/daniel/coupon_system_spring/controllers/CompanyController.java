package com.daniel.coupon_system_spring.controllers;

import com.daniel.coupon_system_spring.beans.Category;
import com.daniel.coupon_system_spring.beans.ClientType;
import com.daniel.coupon_system_spring.beans.Company;
import com.daniel.coupon_system_spring.beans.Coupon;
import com.daniel.coupon_system_spring.dto.LoginReqDto;
import com.daniel.coupon_system_spring.dto.LoginResDto;
import com.daniel.coupon_system_spring.exceptions.CouponCostumeException;
import com.daniel.coupon_system_spring.exceptions.SecMsg;
import com.daniel.coupon_system_spring.exceptions.SecuritySystemException;
import com.daniel.coupon_system_spring.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by danielR on 16/11/2022
 */
@RestController
@RequestMapping("api/companies")
@CrossOrigin(origins = "*")
public class CompanyController extends ClientController {

    @Autowired
    private TokenService tokenService;
    @PostMapping("/coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon(@RequestHeader("Authorization") UUID token, @RequestBody Coupon coupon) throws CouponCostumeException, SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Company)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        int companyId= tokenService.getCompanyID(token);
        companyService.addCoupon(companyId, coupon);
    }

    @PutMapping("/coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCoupon(@RequestHeader("Authorization") UUID token, @RequestBody Coupon coupon, @PathVariable int couponId) throws CouponCostumeException, SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Company)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        int companyId= tokenService.getCompanyID(token);
        companyService.updateCoupon( couponId,coupon, companyId);
        System.out.println("Updated");
    }


    @DeleteMapping("/coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@RequestHeader("Authorization") UUID token, @PathVariable int couponId) throws CouponCostumeException, SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Company)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);

        }
        int companyId= tokenService.getCustomerID(token);
        companyService.deleteCoupon(companyId, couponId);
        System.out.println("Deleted");
    }

    @GetMapping("/coupons/")
    public List<Coupon> getCompanyCoupons(@RequestHeader("Authorization") UUID token) throws SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Company)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        int companyId= tokenService.getCompanyID(token);
        return companyService.getCompanyCoupons(companyId);
    }

    @GetMapping("/coupons/category")
    public List<Coupon> getCompanyCoupons(@RequestHeader("Authorization") UUID token, @RequestParam Category category) throws SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Company)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        int companyId= tokenService.getCompanyID(token);
        return companyService.getCompanyCoupons(companyId, category);
    }

    @GetMapping("/coupons/price")
    public List<Coupon> getCompanyCoupons(@RequestHeader("Authorization") UUID token, @RequestParam double maxPrice) throws SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Company)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        int companyId= tokenService.getCompanyID(token);
        return companyService.getCompanyCoupons(companyId, maxPrice);
    }

    @GetMapping("/details")
    public Company getCompanyDetails(@RequestHeader("Authorization") UUID token) throws SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Company)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        int companyId= tokenService.getCompanyID(token);
        return companyService.getCompanyDetails(companyId);
    }


    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto login(@RequestBody LoginReqDto req) throws SecuritySystemException {

        String email = req.getEmail();
        String pass = req.getPassword();

        return adminService.getCompany(email, pass);
    }
}
