package com.daniel.coupon_system_spring.repos;

import com.daniel.coupon_system_spring.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by danielR on 15/11/2022
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {

    boolean existsByEmail(String email);
    boolean existsByNameOrEmail(String name,String email);
    boolean existsByEmailAndPassword(String email, String password);
    Company findByEmailAndPassword(String email, String password);
}
