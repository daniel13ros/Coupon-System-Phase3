package com.daniel.coupon_system_spring.controllers;

import com.daniel.coupon_system_spring.beans.ClientType;
import com.daniel.coupon_system_spring.beans.Company;
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
@RequestMapping("api/admin")
public class AdminController extends ClientController {

    @PostMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestHeader("Authorization") UUID token,@RequestBody Company company) throws SecuritySystemException, CouponCostumeException {
        if(!tokenService.isValid(token, ClientType.Administrator)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        adminService.addCompany(company);
    }

    @PutMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCompany(@RequestHeader("Authorization") UUID token,@PathVariable int companyId, @RequestBody Company company) throws CouponCostumeException, SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Administrator)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        adminService.updateCompany(companyId, company);
        System.out.println("Updated");
    }

    @DeleteMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@RequestHeader("Authorization") UUID token,@PathVariable int companyId) throws CouponCostumeException, SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Administrator)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        adminService.deleteCompany(companyId);
        System.out.println("Deleted");
    }

    @GetMapping("companies")
    public List<Company> getAllCompanies(@RequestHeader("Authorization") UUID token) throws SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Administrator)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        return adminService.getAllCompanies();
    }

    @GetMapping("companies/{companyId}")
    public Company getSingleCompany(@RequestHeader("Authorization") UUID token,@PathVariable int companyId) throws CouponCostumeException, SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Administrator)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        return adminService.getSingleCompany(companyId);
    }

    @PostMapping("customers/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestHeader("Authorization") UUID token,@RequestBody Customer customer) throws CouponCostumeException, SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Administrator)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        adminService.addCustomer(customer);
    }

    @PutMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestHeader("Authorization") UUID token,@PathVariable int customerId, @RequestBody Customer customer) throws CouponCostumeException, SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Administrator)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        adminService.updateCustomer(customerId, customer);
        System.out.println("Updated");
    }

    @DeleteMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@RequestHeader("Authorization") UUID token,@PathVariable int customerId) throws CouponCostumeException, SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Administrator)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        adminService.deleteCustomer(customerId);
        System.out.println("Deleted");
    }

    @GetMapping("customers")
    public List<Customer> getAllCustomers(@RequestHeader("Authorization") UUID token) throws SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Administrator)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        return adminService.getAllCustomers();
    }

    @GetMapping("/coupons")
    public List<Coupon> getAllCoupons(@RequestHeader("Authorization") UUID token) throws SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Administrator)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        return adminService.getAllCoupons();
    }

    @GetMapping("customers/{customerId}")
    public Customer getSingleCustomer(@RequestHeader("Authorization") UUID token,@PathVariable int customerId) throws CouponCostumeException, SecuritySystemException {
        if(!tokenService.isValid(token, ClientType.Administrator)){
            throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
        }
        return adminService.getSingleCustomer(customerId);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto login(@RequestBody LoginReqDto req) throws SecuritySystemException {

        String email = req.getEmail();
        String pass = req.getPassword();

        return adminService.getAdmin(email,pass);
    }

}

