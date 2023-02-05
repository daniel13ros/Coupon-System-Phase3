package com.daniel.coupon_system_spring.repos;

import com.daniel.coupon_system_spring.beans.Category;
import com.daniel.coupon_system_spring.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

/**
 * Created by danielR on 15/11/2022
 */
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    List<Coupon> findByEndDateBefore(Date date);

    boolean existsByTitleAndCompanyId(String title, int id);

    List<Coupon> findByCategoryAndCompanyId(Category category, int companyId);

    List<Coupon> findByPriceLessThanAndCompanyId(double maxPrice, int companyId);

    List<Coupon> findAllByCompanyId(int companyId);

    @Query(value = "SELECT * FROM `coupons_system`.coupons LEFT JOIN `coupons_system`.customers_coupons ON coupons.id=customers_coupons.coupons_id WHERE customer_id=?1 And category=?2", nativeQuery = true)
    List<Coupon> findByCustomerAndCategory(int customerId, String category);

    @Query(value = "SELECT * FROM `coupons_system`.coupons LEFT JOIN `coupons_system`.customers_coupons ON coupons.id=customers_coupons.coupons_id WHERE customer_id=?1 And price<?2", nativeQuery = true)
    List<Coupon> findByCustomerAndPrice(int customerId, int price);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `coupons_system`.`coupons` m WHERE m.end_date < :date", nativeQuery = true)
    void deleteExpired(@Param("date") Date date);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `coupons_system`.`customers_coupons` m WHERE NOT EXISTS(SELECT NULL FROM `coupons_system`.`coupons` c where c.id=m.coupons_id)", nativeQuery = true)
    void deleteExpiredPurchase();

    boolean existsByCompanyId(int companyId);
}
