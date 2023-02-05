package com.daniel.coupon_system_spring.advice;

import com.daniel.coupon_system_spring.exceptions.CouponCostumeException;
import com.daniel.coupon_system_spring.exceptions.SecuritySystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by danielR on 16/11/2022
 */
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = {CouponCostumeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handle(Exception e) {
        return new ErrorDetails(e.getMessage());
    }

    @ExceptionHandler(value = {SecuritySystemException.class})
    public ResponseEntity<?> handleError(SecuritySystemException e) {
        ErrorDetails err = ErrorDetails.builder().value(e.getMessage()).build();
        HttpStatus status = e.getStatus();
        return new ResponseEntity<>(err, status);
    }
}


