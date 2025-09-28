package com.gohul.TrainService.entity;

import lombok.*;
import java.time.LocalTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NearbyStation{

    private Long stationId;
    private Integer distance;
    private LocalTime duration;

}
