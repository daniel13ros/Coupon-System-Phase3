package com.daniel.coupon_system_spring.config;

import com.daniel.coupon_system_spring.security.Information;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Daniel Rosman on 29 Dec, 2022
 */
@Configuration
public class MapConfig {

    @Bean
    public Map<UUID, Information> map(){
        return new HashMap<>();
    }
 }
