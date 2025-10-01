package com.gohul.TrainService.entity.composite;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SeatId implements Serializable {
    private Long scheduleId;
    private Integer number;
}
