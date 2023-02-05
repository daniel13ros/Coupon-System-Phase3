package com.daniel.coupon_system_spring.services;

import com.daniel.coupon_system_spring.beans.Category;
import com.daniel.coupon_system_spring.beans.Coupon;
import com.daniel.coupon_system_spring.beans.Customer;
import com.daniel.coupon_system_spring.exceptions.CouponCostumeException;
import com.daniel.coupon_system_spring.exceptions.ErrMsg;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by danielR on 15/11/2022
 */
@Service
public class CustomerServiceImpl extends ClientService implements CustomerService{


    @Override
    public boolean login(String email, String password) throws CouponCostumeException {
        if (!this.customerRepository.existsByEmailAndPassword(email, password)) {
            throw new CouponCostumeException(ErrMsg.EMAIL_OR_PASS_WRONG);
        }
        Customer customer = this.customerRepository.findByEmailAndPassword(email, password);
        /*int customerId = customer.getId();*/
        return true;
    }

    @Override
    public void purchaseCoupon(int customerId,int couponId) throws CouponCostumeException {
        if(this.customerRepository.findById(customerId).get().getCoupons().contains(this.couponRepository.findById(couponId).get())){
            throw new CouponCostumeException(ErrMsg.COUPON_IS_EXIST_IN_CUSTOMER);
        }
        if(this.couponRepository.findById(couponId).get().getAmount()==0){
            throw new CouponCostumeException(ErrMsg.COUPON_AMOUNT_IS_ZERO);
        }
        if(this.couponRepository.findById(couponId).get().getEndDate().before(Date.valueOf(LocalDate.now()))){
            throw new CouponCostumeException(ErrMsg.COUPON_EXPIRED);
        }
        Coupon coupon=this.couponRepository.findById(couponId).get();
        Customer c = this.customerRepository.findById(customerId).orElseThrow();
        coupon.setAmount(coupon.getAmount()-1);
        coupon.setCompany(couponRepository.findById(couponId).get().getCompany());
        c.getCoupons().add(coupon);
        this.customerRepository.save(c);
        this.couponRepository.save(coupon);
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId) {
        return this.customerRepository.findById(customerId).get().getCoupons();
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId,Category category) {
        return this.couponRepository.findByCustomerAndCategory(customerId,category.name());
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId,double maxPrice) {
        return this.couponRepository.findByCustomerAndPrice(customerId, (int) maxPrice);
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return this.couponRepository.findAll().stream().filter(coupon->coupon.getAmount()>0).collect(Collectors.toList());
    }

    @Override
    public Customer getCustomerDetails(int customerId) {
        System.out.println("id :"+customerRepository.findById(customerId).get().getId());
        System.out.println("first name :"+customerRepository.findById(customerId).get().getFirstName());
        System.out.println("last name :"+customerRepository.findById(customerId).get().getLastName());
        System.out.println("email :"+customerRepository.findById(customerId).get().getEmail());
        System.out.println("password :"+customerRepository.findById(customerId).get().getPassword());
        System.out.println("coupons :");
        customerRepository.findById(customerId).get().getCoupons().forEach(System.out::println);
        return customerRepository.findById(customerId).get();
    }
}
