package com.daniel.coupon_system_spring.services;

import com.daniel.coupon_system_spring.beans.ClientType;
import com.daniel.coupon_system_spring.beans.Company;
import com.daniel.coupon_system_spring.beans.Customer;
import com.daniel.coupon_system_spring.exceptions.SecMsg;
import com.daniel.coupon_system_spring.exceptions.SecuritySystemException;
import com.daniel.coupon_system_spring.security.Information;
import com.daniel.coupon_system_spring.util.TokenInstanceRemover;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Created by kobis on 29 Dec, 2022
 */
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final Map<UUID, Information> map;


    @Override
    public UUID addAdmin() {

        UUID token = UUID.randomUUID();
        Information information = Information.builder()
                .type(ClientType.Administrator)
                .time(LocalDateTime.now())
                .build();

        TokenInstanceRemover.removePreviousInstance(map,information);
        map.put(token, information);
        System.out.println(map);

        return token;
    }
    @Override
    public UUID addCustomer(Customer customer) {

        UUID token = UUID.randomUUID();
        Information information = Information.builder()
                .id(customer.getId())
                .type(customer.getType())
                .time(LocalDateTime.now())
                .build();

        TokenInstanceRemover.removePreviousInstance(map,information);

        map.put(token, information);

        return token;
    }
    @Override
    public UUID addCompany(Company company) {

        UUID token = UUID.randomUUID();
        Information information = Information.builder()
                .id(company.getId())
                .type(company.getType())
                .time(LocalDateTime.now())
                .build();

        TokenInstanceRemover.removePreviousInstance(map,information);

        map.put(token, information);
        return token;
    }

    @Override
    public void clearTokens() {
        map.values().removeIf(obj->obj.getTime().isBefore(LocalDateTime.now().minusMinutes(30)));
    }

    @Override
    public boolean isValid(UUID token, ClientType type) {
        Information info = map.get(token);
        if(info!=null){
            return info.getType().equals(type);
        }
        return false;
    }

    @Override
    public int getCustomerID(UUID token) throws SecuritySystemException {

        Information info = map.get(token);
        if(info!=null){
            return info.getId();
        }
        throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
    }
    @Override
    public int getCompanyID(UUID token) throws SecuritySystemException {
        Information info = map.get(token);
        if(info!=null){
            return info.getId();
        }
        throw new SecuritySystemException(SecMsg.INVALID_TOKEN);
    }
}
