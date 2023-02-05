package com.daniel.coupon_system_spring.services;

import com.daniel.coupon_system_spring.beans.ClientType;
import com.daniel.coupon_system_spring.beans.Company;
import com.daniel.coupon_system_spring.beans.Customer;
import com.daniel.coupon_system_spring.exceptions.SecuritySystemException;


import java.util.UUID;

/**
 * Created by kobis on 29 Dec, 2022
 */
public interface TokenService {

    UUID addCustomer(Customer customer);
    UUID addCompany(Company company);
    UUID addAdmin();
    void clearTokens();
    boolean isValid(UUID token, ClientType type);
    int getCustomerID(UUID token) throws SecuritySystemException;
    int getCompanyID(UUID token) throws SecuritySystemException;
}
