package com.gohul.TrainService.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RouteUpdateRequest {

    @NotNull(message = "Route ID must not be null")
    private Long id;

    @NotNull(message = "Source station ID must not be null")
    private Long sourceStationId;

    @NotNull(message = "Destination station ID must not be null")
    private Long destinationStationId;

    @NotEmpty(message = "Path must not be empty")
    private List<Long> path;

}
