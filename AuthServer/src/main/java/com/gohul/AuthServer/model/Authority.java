package com.gohul.AuthServer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    @Id
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = true, unique = true)
    private String description;

    @ManyToMany(mappedBy = "authorities")
    private Set<Customer> customers = new HashSet<>();
}
