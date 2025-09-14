package com.gohul.CustomerService.model;

import com.gohul.CustomerService.constant.GenderType;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, updatable = false, insertable = false)
    private String email;
    @Column(unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private int age;

}
