package com.daniel.coupon_system_spring.services;

import com.daniel.coupon_system_spring.beans.Category;
import com.daniel.coupon_system_spring.beans.Company;
import com.daniel.coupon_system_spring.beans.Coupon;
import com.daniel.coupon_system_spring.exceptions.CouponCostumeException;

import java.util.List;

/**
 * Created by danielR on 15/11/2022
 */
public interface CompanyService {

    void addCoupon(int companyId,Coupon coupon) throws CouponCostumeException;

    void updateCoupon(int companyId, Coupon coupon , int couponId) throws CouponCostumeException;

    void deleteCoupon(int companyId , int couponId) throws CouponCostumeException;

    List<Coupon> getCompanyCoupons(int companyId);

    List<Coupon> getCompanyCoupons(int companyId,Category category);

    List<Coupon> getCompanyCoupons(int companyId,double maxPrice);

    Company getCompanyDetails(int companyId);

}
