package com.daniel.coupon_system_spring.dto;

import com.daniel.coupon_system_spring.beans.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResDto {

    private int id;
    private String email;
    private String pass;
    private UUID token;
    private ClientType clientType;
}
