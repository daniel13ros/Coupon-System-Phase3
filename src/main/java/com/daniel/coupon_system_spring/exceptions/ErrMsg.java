package com.daniel.coupon_system_spring.exceptions;

import lombok.Getter;

/**
 * Created by danielR on 15/11/2022
 */
@Getter
public enum ErrMsg {


    COMPANY_NAME_EMAIL_EXIST("Cannot add company with exiting name or email"),
    CUSTOMER_NAME_EMAIL_EXIST("Cannot add  with exiting email"),
    CUSTOMER_NOT_EXIST("Cannot delete non exist customer"),
    COMPANY_NOT_EXIST("Cannot delete non exist company"),
    CUSTOMER_NAME_EXIST("Cannot update with exiting name"),
    COMPANY_NAME_EXIST("Cannot update with exiting name"),
    COUPON_EXIST_BT_TITLE("Cannot add coupon with exiting title"),
    COUPON_NOT_EXIST("Cannot delete non exist coupon"),
    COUPON_IS_EXIST_IN_CUSTOMER("Customer has this coupon already"),
    COUPON_AMOUNT_IS_ZERO("Coupon sold out"),
    EMAIL_OR_PASS_WRONG("email or password are wrong"),
    NO_COMPANIES("you are not able to add coupon to non exist company"),
    NO_CUSTOMERS("you are not able to purchase coupon to non exist company"),
    COUPON_EXPIRED("Coupon expired"),
    CANNOT_UPDATE_ID_CUSTOMER("cannot update customer id"),
    CANNOT_UPDATE_ID_COUPON("cannot update coupon id"),
    ID_NOT_FOUND("id not found"),
    COUPON_DOES_NOT_BELONG_TO_COMPANY("No access to the company to delete a coupon");


    private String msg;

    ErrMsg(String msg) {
        this.msg = msg;
    }
}
