package com.daniel.coupon_system_spring.exceptions;

/**
 * Created by danielR on 15/11/2022
 */
public class CouponCostumeException extends Exception {

    public CouponCostumeException(ErrMsg errMsg) {
        super(errMsg.getMsg());
    }
}
