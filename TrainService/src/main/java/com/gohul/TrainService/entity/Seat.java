package com.gohul.TrainService.entity;

import com.gohul.TrainService.constant.SeatStatus;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(
        name = "seat",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"schedule_id", "number"})
        }
)
@Builder

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Seat extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private Long scheduleId;
    @Column(nullable = false)
    private Integer number;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus status;

}
