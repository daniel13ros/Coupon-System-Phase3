package com.daniel.coupon_system_spring.beans;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by danielR on 15/11/2022
 */
@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @Singular
    private List<Coupon>coupons;
    @Enumerated(EnumType.STRING)
    private ClientType type;


}
