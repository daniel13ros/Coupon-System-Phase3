package com.daniel.coupon_system_spring.services;

import com.daniel.coupon_system_spring.beans.Category;
import com.daniel.coupon_system_spring.beans.Coupon;
import com.daniel.coupon_system_spring.beans.Customer;
import com.daniel.coupon_system_spring.exceptions.CouponCostumeException;

import java.util.List;

/**
 * Created by danielR on 15/11/2022
 */
public interface CustomerService {

    void purchaseCoupon(int customerId ,int couponId) throws CouponCostumeException;

    List<Coupon> getCustomerCoupons(int customerId);

    List<Coupon> getCustomerCoupons(int customerId,Category category);

    List<Coupon> getCustomerCoupons(int customerId,double maxPrice);
    List<Coupon> getAllCoupons();

    Customer getCustomerDetails(int customerId);
}
