package com.daniel.coupon_system_spring.jobs;

import com.daniel.coupon_system_spring.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by kobis on 29 Dec, 2022
 */
@Component
@RequiredArgsConstructor
public class TokenRemoval {

    private final TokenService tokenService;

    @Scheduled(fixedRate = 60*1000*10)
    public void initTokenRemoval(){
        tokenService.clearTokens();
    }

}
