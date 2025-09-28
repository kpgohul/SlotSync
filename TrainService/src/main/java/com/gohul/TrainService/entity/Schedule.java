package com.gohul.TrainService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Long trainId;
    @Column(nullable = false)
    private Long routeId;
    @Column(nullable = false)
    private Instant departureTime;
    @Column(nullable = false)
    private Instant arrivalTime;
    @Column(nullable = false)
    private Integer totalSeats;

}
