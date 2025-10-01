package com.gohul.TrainService.entity;

import com.gohul.TrainService.constant.SeatStatus;
import com.gohul.TrainService.entity.composite.SeatId;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Seat extends BaseEntity {

    @EmbeddedId
    private SeatId id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus status;

}