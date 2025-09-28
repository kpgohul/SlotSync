package com.gohul.TrainService.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Station extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false, unique = true)
    private Integer zipcode;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String country;

    @OneToOne(mappedBy = "station", cascade = CascadeType.ALL)
    private StationMapping stationMapping;

}
