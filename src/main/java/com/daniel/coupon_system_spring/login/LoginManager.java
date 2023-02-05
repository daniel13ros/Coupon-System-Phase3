package com.daniel.coupon_system_spring.login;

import com.daniel.coupon_system_spring.beans.ClientType;
import com.daniel.coupon_system_spring.exceptions.CouponCostumeException;
import com.daniel.coupon_system_spring.services.AdminService;
import com.daniel.coupon_system_spring.services.ClientService;
import com.daniel.coupon_system_spring.services.CompanyService;
import com.daniel.coupon_system_spring.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by danielR on 15/11/2022
 */
@Service
public class LoginManager {

    @Autowired
    AdminService adminService;

    @Autowired
    CompanyService companyService;

    @Autowired
    CustomerService customerService;

    private LoginManager() {

    }

    public ClientService login(String email , String password, ClientType clientType) throws CouponCostumeException {
        boolean boolLogin = false;
        ClientService clientService = null;
        switch (clientType) {
            case Administrator:
                clientService = (ClientService) adminService;
                boolLogin = clientService.login(email, password);
                break;
            case Company:
                clientService = (ClientService) companyService;
                boolLogin = clientService.login(email, password);
                break;
            case Customer:
                clientService = (ClientService) customerService;
                boolLogin = clientService.login(email, password);
                break;
        }

        if(!boolLogin)
            return null;
        else
            return clientService;
    }
}
