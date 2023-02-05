package com.daniel.coupon_system_spring.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Created by kobis on 29 Dec, 2022
 */
@Getter
public class SecuritySystemException extends Exception{

    private HttpStatus status;

    public SecuritySystemException(SecMsg secMsg) {
        super(secMsg.getMessage());
        this.status = secMsg.getStatus();
    }
}
