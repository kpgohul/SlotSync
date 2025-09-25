package com.gohul.TrainService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "station")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Station {

    @Id
    private Long id;
    private String name;
    private String address;
    private String district;
    private String state;
    private Integer zipcode;
    private String country;
    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;

}
