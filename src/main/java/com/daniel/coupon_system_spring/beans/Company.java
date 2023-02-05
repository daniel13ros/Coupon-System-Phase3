package com.daniel.coupon_system_spring.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielR on 15/11/2022
 */
@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = true)
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "company")
    @Singular
    @JsonIgnore
    private List<Coupon> coupons=new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private ClientType type;
}
