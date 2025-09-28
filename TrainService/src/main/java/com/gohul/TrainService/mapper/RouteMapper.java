package com.gohul.TrainService.mapper;

import com.gohul.TrainService.dto.request.RouteCreateRequest;
import com.gohul.TrainService.dto.request.RouteUpdateRequest;
import com.gohul.TrainService.dto.response.RouteResponse;
import com.gohul.TrainService.entity.Route;
import org.springframework.stereotype.Component;

@Component
public class RouteMapper {

    public Route toRoute(RouteCreateRequest request){

        return Route.builder()
                .sourceStationId(request.getSourceStationId())
                .destinationId(request.getDestinationStationId())
                .path(request.getPath())
                .build();

    }

    public Route toRoute(RouteUpdateRequest request){

        return Route.builder()
                .id(request.getId())
                .sourceStationId(request.getSourceStationId())
                .destinationId(request.getDestinationStationId())
                .path(request.getPath())
                .build();

    }

    public RouteResponse toRouteResponse(Route route){

        return RouteResponse.builder()
                .sourceStationId(route.getSourceStationId())
                .destinationStationId(route.getDestinationId())
                .path(route.getPath())
                .build();

    }
}
