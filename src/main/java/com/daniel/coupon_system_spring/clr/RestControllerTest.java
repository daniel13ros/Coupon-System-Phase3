package com.daniel.coupon_system_spring.clr;

import com.daniel.coupon_system_spring.beans.*;
import com.daniel.coupon_system_spring.util.BeautifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by danielR on 21/11/2022
 */
//@Component
//@Order(3)
public class RestControllerTest implements CommandLineRunner {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${urlAdmin}")
    private String urlAdmin;
    @Value("${urlCompany}")
    private String urlCompany;

    @Value("${urlCustomer}")
    private String urlCustomer;

    @Override
    public void run(String... args) throws Exception {
        BeautifyUtil.startOrEnd("THIS IS REST CONTROLLER");
        Company company10 = Company.builder()
                .name("company10")
                .email("company10@company10.com")
                .password("1234")
                .type(ClientType.Company)
                .build();
        Company company11 = Company.builder()
                .name("company11")
                .email("company11@company11.com")
                .password("1234")
                .type(ClientType.Company)
                .build();
        Company company12 = Company.builder()
                .name("company12")
                .email("company12@company12.com")
                .password("1234")
                .type(ClientType.Company)
                .build();
        Company company13 = Company.builder()
                .name("company13")
                .email("company13@company13.com")
                .password("1234")
                .type(ClientType.Company)
                .build();

        Customer customer10 = Customer.builder()
                .firstName("customer10")
                .lastName("cus10")
                .email("customer10@cus10.com")
                .password("1234")
                .type(ClientType.Customer)
                .build();
        Customer customer11 = Customer.builder()
                .firstName("customer11")
                .lastName("cus11")
                .email("customer11@cus11.com")
                .password("1234")
                .type(ClientType.Customer)
                .build();
        Customer customer12 = Customer.builder()
                .firstName("customer12")
                .lastName("cus12")
                .email("customer12@cus12.com")
                .password("1234")
                .build();

        Coupon coupon10 = Coupon.builder()
                .company(company11)
                .category(Category.Electronics)
                .title("title10")
                .description("desc10")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(55)))
                .amount(14)
                .price(534)
                .image("https://picsum.photos/200")
                .build();

        Coupon coupon11 = Coupon.builder()
                .company(company10)
                .category(Category.Electronics)
                .title("title11")
                .description("desc11")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(15)))
                .amount(36)
                .price(14)
                .image("https://picsum.photos/200")
                .build();
        Coupon coupon12 = Coupon.builder()
                .company(company10)
                .category(Category.Gifts)
                .title("title12")
                .description("desc12")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusYears(5)))
                .amount(152)
                .price(1539)
                .image("https://picsum.photos/200")
                .build();

        //Admin functionality
        BeautifyUtil.title("add new Company");
        ResponseEntity<String> res1 = restTemplate.postForEntity(urlAdmin + "/companies", company10, String.class);
        System.out.println(res1);
        System.out.println((res1.getStatusCodeValue() == 201) ? "OK" : "SOMETHING WENT WRONG");

        BeautifyUtil.title("add new Company");
        ResponseEntity<String> res2 = restTemplate.postForEntity(urlAdmin + "/companies", company11, String.class);
        System.out.println(res2);
        System.out.println((res2.getStatusCodeValue() == 201) ? "OK" : "SOMETHING WENT WRONG");

        BeautifyUtil.title("add new Company");
        ResponseEntity<String> res3 = restTemplate.postForEntity(urlAdmin + "/companies", company12, String.class);
        System.out.println(res3);
        System.out.println((res3.getStatusCodeValue() == 201) ? "OK" : "SOMETHING WENT WRONG");

        BeautifyUtil.title("add new Company");
        ResponseEntity<String> result = restTemplate.postForEntity(urlAdmin + "/companies", company13, String.class);
        System.out.println(result);
        System.out.println((result.getStatusCodeValue() == 201) ? "OK" : "SOMETHING WENT WRONG");


        try {
            BeautifyUtil.title("update Company");
            System.out.println("before-" + company12);
            company12.setEmail("company_12@company_12.com");
            company12.setId(8);
            System.out.println("after" + company12);
            restTemplate.put(urlAdmin + "/companies/8", company12, String.class);
            System.out.println("after" + company12);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        BeautifyUtil.title("delete company 13");
        restTemplate.postForEntity(urlAdmin + "/companies/9", company13, String.class);

        BeautifyUtil.title("get all companies");
        Company[] allCompany = restTemplate.getForObject(urlAdmin + "/companies", Company[].class);
        List<Company> companies = Arrays.stream(allCompany).collect(Collectors.toList());
        companies.forEach(System.out::println);

        BeautifyUtil.title("get single Company id=1");
        Company fromDb = restTemplate.getForObject(urlAdmin + "/companies/1", Company.class);
        System.out.println(fromDb);

        BeautifyUtil.title("add new Customer");
        ResponseEntity<String> res4 = restTemplate.postForEntity(urlAdmin + "/customers/", customer10, String.class);
        System.out.println(res4);
        System.out.println((res4.getStatusCodeValue() == 201) ? "OK" : "SOMETHING WENT WRONG");

        BeautifyUtil.title("add new Customer");
        ResponseEntity<String> res5 = restTemplate.postForEntity(urlAdmin + "/customers/", customer11, String.class);
        System.out.println(res5);
        System.out.println((res5.getStatusCodeValue() == 201) ? "OK" : "SOMETHING WENT WRONG");

        BeautifyUtil.title("add new Customer");
        ResponseEntity<String> res6 = restTemplate.postForEntity(urlAdmin + "/customers/", customer12, String.class);
        System.out.println(res6);
        System.out.println((res6.getStatusCodeValue() == 201) ? "OK" : "SOMETHING WENT WRONG");

        try {
            BeautifyUtil.title("update Customer");
            System.out.println("before-" + customer12);
            customer12.setEmail("customer_8@cus_8.com");
            customer12.setId(8);
            System.out.println("after" + customer12);
            restTemplate.put(urlAdmin + "/customers/8", customer12, String.class);
            System.out.println("after" + customer12);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        BeautifyUtil.title("delete customer 11");
        restTemplate.postForEntity(urlAdmin + "/customers/7", customer11, String.class);

        BeautifyUtil.title("get all customers");
        Customer[] allCustomers = restTemplate.getForObject(urlAdmin + "/customers", Customer[].class);
        List<Customer> customers = Arrays.stream(allCustomers).collect(Collectors.toList());
        customers.forEach(System.out::println);

        BeautifyUtil.title("get single customer 10");
        Customer customerFromDb = restTemplate.getForObject(urlAdmin + "/customers/6", Customer.class);
        System.out.println(customerFromDb);

        //Company functionality
        BeautifyUtil.title("add new Coupon");
        ResponseEntity<String> res7 = restTemplate.postForEntity(urlCompany + "/7/coupons", coupon10, String.class);
        System.out.println(res7);
        System.out.println((res7.getStatusCodeValue() == 201) ? "OK" : "SOMETHING WENT WRONG");


        BeautifyUtil.title("add new Coupon");
        ResponseEntity<String> res8 = restTemplate.postForEntity(urlCompany + "/6/coupons", coupon11, String.class);
        System.out.println(res8);
        System.out.println((res8.getStatusCodeValue() == 201) ? "OK" : "SOMETHING WENT WRONG");

        BeautifyUtil.title("add new Coupon");
        ResponseEntity<String> res9 = restTemplate.postForEntity(urlCompany + "/6/coupons", coupon12, String.class);
        System.out.println(coupon12);
        System.out.println(res9);
        System.out.println((res9.getStatusCodeValue() == 201) ? "OK" : "SOMETHING WENT WRONG");


        try {
            BeautifyUtil.title("update coupon 10");
            System.out.println("before-" + coupon10);
            coupon10.setCategory(Category.Travel);
            coupon10.setId(7);
            restTemplate.put(urlCompany + "/7/coupons/7", coupon10, String.class);
            System.out.println("after" + coupon10);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        BeautifyUtil.title("delete coupon  12");
        restTemplate.postForEntity(urlCompany + "/6/coupons/9", coupon10, String.class);

        BeautifyUtil.title("get all coupons of company 1");
        Coupon[] allCouponsOf10 = restTemplate.getForObject(urlCompany + "/1/coupons/", Coupon[].class);
        List<Coupon> coupons10 = Arrays.stream(allCouponsOf10).collect(Collectors.toList());
        coupons10.forEach(System.out::println);

        BeautifyUtil.title("get all coupons of company 1 by category");
        Coupon[] allCouponsOf1Category = restTemplate.getForObject(urlCompany + "/1/coupons/category?category=Gifts", Coupon[].class);
        List<Coupon> coupons1Category = Arrays.stream(allCouponsOf1Category).collect(Collectors.toList());
        coupons1Category.forEach(System.out::println);

        BeautifyUtil.title("get all coupons of company 1 by price");
        Coupon[] allCouponsOf1Price = restTemplate.getForObject(urlCompany + "/1/coupons/price?maxPrice=150", Coupon[].class);
        List<Coupon> coupons1Price = Arrays.stream(allCouponsOf1Price).collect(Collectors.toList());
        coupons1Price.forEach(System.out::println);

        BeautifyUtil.title("get all coupons");
        Coupon[] allCoupons = restTemplate.getForObject(urlAdmin + "/coupons", Coupon[].class);
        List<Coupon> coupons = Arrays.stream(allCoupons).collect(Collectors.toList());
        coupons.forEach(System.out::println);

        BeautifyUtil.title("company 1 details");
        restTemplate.getForEntity(urlCompany + "/1/details", String.class);

        //Customer functionality
        BeautifyUtil.title("customer 2 purchase - coupon 10");
        restTemplate.postForEntity(urlCustomer + "/2/purchase/7", coupon10, String.class);

        BeautifyUtil.title("customer 2 all coupons:");
        Coupon[] allCouponsOf2 = restTemplate.getForObject(urlCustomer + "/2/coupons", Coupon[].class);
        List<Coupon> coupons2 = Arrays.stream(allCouponsOf2).collect(Collectors.toList());
        coupons2.forEach(System.out::println);

        BeautifyUtil.title("customer 2 all coupons by category:");
        Coupon[] allCouponsCategory = restTemplate.getForObject(urlCustomer + "/2/coupons/category?category=Gifts", Coupon[].class);
        List<Coupon> couponsCategory = Arrays.stream(allCouponsCategory).collect(Collectors.toList());
        couponsCategory.forEach(System.out::println);

        BeautifyUtil.title("customer 2 all coupons that price under 300");
        Coupon[] allCouponsUnder300 = restTemplate.getForObject(urlCustomer + "/2/coupons/price?maxPrice=150", Coupon[].class);
        List<Coupon> couponsUnder300 = Arrays.stream(allCouponsUnder300).collect(Collectors.toList());
        couponsUnder300.forEach(System.out::println);

        BeautifyUtil.title("customer 2 details");
        restTemplate.getForObject(urlCustomer + "/2/details", Customer.class);

        BeautifyUtil.startOrEnd("End of program");
    }

}
