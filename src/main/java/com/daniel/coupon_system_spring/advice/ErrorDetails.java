package com.daniel.coupon_system_spring.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by danielR on 16/11/2022
 */
@Data
@AllArgsConstructor
@Builder
public class ErrorDetails {

    private final String key = "CS 151";
    private String value;
}
