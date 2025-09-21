package com.gohul.TrainService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Integer seatNumber;
    private boolean isBooked = false;

    @ManyToOne(
            fetch = FetchType.EAGER,
            targetEntity = Schedule.class
    )
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;
}
