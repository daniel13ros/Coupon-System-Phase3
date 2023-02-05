package com.daniel.coupon_system_spring.services;

import com.daniel.coupon_system_spring.beans.Company;
import com.daniel.coupon_system_spring.beans.Coupon;
import com.daniel.coupon_system_spring.beans.Customer;
import com.daniel.coupon_system_spring.dto.LoginResDto;
import com.daniel.coupon_system_spring.exceptions.CouponCostumeException;
import com.daniel.coupon_system_spring.exceptions.SecuritySystemException;

import java.util.List;

/**
 * Created by danielR on 15/11/2022
 */
public interface AdminService {

    void addCompany(Company company) throws CouponCostumeException;

    void updateCompany(int id, Company company) throws CouponCostumeException;

    void deleteCompany(int id) throws CouponCostumeException;

    List<Company> getAllCompanies();
    List<Coupon> getAllCoupons();

   Company getSingleCompany(int companyId) throws CouponCostumeException;

    void addCustomer(Customer customer) throws CouponCostumeException;

    void updateCustomer(int customerId, Customer customer) throws CouponCostumeException;

    void deleteCustomer(int id) throws CouponCostumeException;

    List<Customer> getAllCustomers();

    Customer getSingleCustomer(int id) throws CouponCostumeException;
    LoginResDto getCompany(String email, String password) throws SecuritySystemException;
    LoginResDto getCustomer(String email, String password) throws  SecuritySystemException;
    LoginResDto getAdmin(String email, String password) throws  SecuritySystemException;

}
