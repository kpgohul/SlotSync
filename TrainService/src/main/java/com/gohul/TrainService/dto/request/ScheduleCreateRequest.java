package com.gohul.TrainService.dto.request;

import com.gohul.TrainService.dto.response.RouteResponse;
import com.gohul.TrainService.dto.response.TrainResponse;
import com.gohul.TrainService.entity.Seat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class ScheduleCreateRequest {

    @NotNull(message = "Train ID must not be null")
    private Long trainId;

    @NotNull(message = "Route ID must not be null")
    private Long routeId;

    @NotNull(message = "Arrival time must not be null")
    private Instant arrivalTime;

    @NotNull(message = "Departure time must not be null")
    private Instant departureTime;

    @NotNull(message = "Total seats must not be null")
    @Min(value = 200, message = "Total seats must be at least 200")
    private Integer totalSeats;

}
