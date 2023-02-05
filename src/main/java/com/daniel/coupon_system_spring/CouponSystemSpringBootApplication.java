package com.daniel.coupon_system_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CouponSystemSpringBootApplication {

	public static void main(String[] args) {
		System.out.println("Application started!!");
		SpringApplication.run(CouponSystemSpringBootApplication.class, args);
	}

}
