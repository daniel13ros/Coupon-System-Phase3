package com.daniel.coupon_system_spring.services;

import com.daniel.coupon_system_spring.beans.Category;
import com.daniel.coupon_system_spring.beans.Company;
import com.daniel.coupon_system_spring.beans.Coupon;
import com.daniel.coupon_system_spring.exceptions.CouponCostumeException;
import com.daniel.coupon_system_spring.exceptions.ErrMsg;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by danielR on 15/11/2022
 */
@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {




    @Override
    public void addCoupon(int companyId,Coupon coupon) throws CouponCostumeException {
        if (couponRepository.existsByTitleAndCompanyId(coupon.getTitle(), companyId)) {
            throw new CouponCostumeException(ErrMsg.COUPON_EXIST_BT_TITLE);
        }
        coupon.setCompany(companyRepository.findById(companyId).orElseThrow());
        couponRepository.save(coupon);
        companyRepository.save(companyRepository.findById(companyId).orElseThrow());
    }

    @Override
    public void updateCoupon(int couponId, Coupon coupon ,int companyId) throws CouponCostumeException {
        if (coupon.getId() != couponRepository.findById(couponId).get().getId()) {
            throw new CouponCostumeException(ErrMsg.CANNOT_UPDATE_ID_COUPON);
        }
        if (companyId!= couponRepository.findById(couponId).get().getCompany().getId()) {
            throw new CouponCostumeException(ErrMsg.CANNOT_UPDATE_ID_COUPON);
        }
        coupon.setCompany(companyRepository.findById(companyId).get());
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int companyId,int couponId) throws CouponCostumeException {
        if (!couponRepository.existsById(couponId)) {
            throw new CouponCostumeException(ErrMsg.COUPON_NOT_EXIST);
        }
        if(!couponRepository.existsByCompanyId(companyId)){
            throw new CouponCostumeException(ErrMsg.COUPON_DOES_NOT_BELONG_TO_COMPANY);
        }
        couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId) {
        return couponRepository.findAllByCompanyId(companyId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId,Category category) {
        return couponRepository.findByCategoryAndCompanyId(category, companyId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId,double maxPrice) {
        return couponRepository.findByPriceLessThanAndCompanyId(maxPrice, companyId);
    }

    @Override
    public Company getCompanyDetails(int companyId) {
        System.out.println("id :"+companyRepository.findById(companyId).get().getId());
        System.out.println("name :"+companyRepository.findById(companyId).get().getName());
        System.out.println("email :"+companyRepository.findById(companyId).get().getEmail());
        System.out.println("password :"+companyRepository.findById(companyId).get().getPassword());
        System.out.println("coupons :");
        companyRepository.findById(companyId).get().getCoupons().forEach(System.out::println);
        return companyRepository.findById(companyId).get();

    }

    @Override
    public boolean login(String email, String password) throws CouponCostumeException {
        if (!companyRepository.existsByEmailAndPassword(email, password)) {
            throw new CouponCostumeException(ErrMsg.EMAIL_OR_PASS_WRONG);
        }
        Company company = companyRepository.findByEmailAndPassword(email, password);
        /*int companyId = company.getId();*/
        return true;
    }
}
