package com.gohul.TrainService.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ScheduleUpdateRequest {

    @NotNull(message = "Schedule ID must not be null")
    private Long id;

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
    @Max(value = 1000, message = "Total seats must be at most 1000")
    private Integer totalSeats;

}
