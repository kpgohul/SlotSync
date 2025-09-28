package com.gohul.TrainService.service;

import com.gohul.TrainService.dto.request.RouteCreateRequest;
import com.gohul.TrainService.dto.request.RouteUpdateRequest;
import com.gohul.TrainService.dto.response.RouteResponse;

import java.util.List;

public interface RouteService {

    void createNewRoute(RouteCreateRequest request);

    void updateRoute(RouteUpdateRequest request);

    void deleteRouteById(Long id);

    RouteResponse getRouteBySourceAndDestination(Long source, Long destination);

    List<RouteResponse> getAllRoutes(int page, int limit, String sort);

    RouteResponse getRouteById(Long id);

}
