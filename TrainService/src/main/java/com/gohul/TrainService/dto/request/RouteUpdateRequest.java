package com.gohul.TrainService.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouteUpdateRequest {

    @NotNull(message = "Route ID must not be null")
    private Long id;

    @NotNull(message = "Source station ID must not be null")
    private Long sourceStationId;

    @NotNull(message = "Destination station ID must not be null")
    private Long destinationStationId;

    @NotEmpty(message = "Path must not be empty")
    private Long[] path;

}
