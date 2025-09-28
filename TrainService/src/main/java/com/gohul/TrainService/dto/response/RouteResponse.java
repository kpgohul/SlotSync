package com.gohul.TrainService.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RouteResponse {

    private Long id;
    private Long sourceStationId;
    private Long destinationStationId;
    private List<Long> path;

}
