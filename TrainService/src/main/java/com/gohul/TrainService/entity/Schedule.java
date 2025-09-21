package com.gohul.TrainService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime departureTime;
    @Column(nullable = false)
    private LocalDateTime arrivalTime;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;
    @CreatedBy
    @Column(updatable = false, nullable = false)
    private Long createdBy;
    @LastModifiedBy
    @Column(insertable = false)
    private Long updatedBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Train.class)
    @JoinColumn(name = "train_id", nullable = true)
    private Train train;

    @OneToMany  (
            mappedBy = "schedule",
            cascade = CascadeType.ALL,
            targetEntity = Seat.class
    )
    private List<Seat> seatList;

}
