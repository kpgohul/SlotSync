package com.gohul.TrainService.dto.response;

import com.gohul.TrainService.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResponse {

    private Long id;
    private Long trainId;
    private Long routeId;
    private Instant arrivalTime;
    private Instant departureTime;
    private Integer totalSeats;

}
