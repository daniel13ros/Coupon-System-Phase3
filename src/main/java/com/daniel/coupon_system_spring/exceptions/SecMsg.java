package com.daniel.coupon_system_spring.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Created by kobis on 29 Dec, 2022
 */
@Getter
public enum SecMsg {

    EMAIL_ALREADY_EXIST("email already exist",HttpStatus.CONFLICT),
    INVALID_EMAIL_OR_PASS("invalid email or password",HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN("invalid token plese login again",HttpStatus.UNAUTHORIZED);

    private String message;
    private HttpStatus status;

    SecMsg(String message,HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
