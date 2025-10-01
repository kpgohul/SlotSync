package com.gohul.TrainService.controller;

import com.gohul.TrainService.dto.request.RouteCreateRequest;
import com.gohul.TrainService.dto.request.RouteUpdateRequest;
import com.gohul.TrainService.dto.response.RouteResponse;
import com.gohul.TrainService.service.RouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService service;

    @PostMapping
    public ResponseEntity<?> createRoute(@Valid @RequestBody RouteCreateRequest request) {

        service.createNewRoute(request);
        return ResponseEntity.ok("Route created successfully");

    }

    @PutMapping
    public ResponseEntity<?> updateRoute(@Valid @RequestBody RouteUpdateRequest request) {

        service.updateRoute(request);
        return ResponseEntity.ok("Route updated successfully");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoute(@PathVariable Long id) {

        service.deleteRouteById(id);
        return ResponseEntity.ok("Route deleted successfully");

    }


    @GetMapping("/{id}")
    public ResponseEntity<RouteResponse> getRouteById(@PathVariable Long id) {

        RouteResponse response = service.getRouteById(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);

    }

    @GetMapping("/search")
    public ResponseEntity<RouteResponse> getRouteBySourceAndDestination(
            @RequestParam Long source,
            @RequestParam Long destination
    ) {
        RouteResponse response = service.getRouteBySourceAndDestination(source, destination);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<RouteResponse>> getAllRoutes(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "asc") String sort
    ) {

        if (!sort.equalsIgnoreCase("asc") && !sort.equalsIgnoreCase("desc")) {
            return ResponseEntity.badRequest().body(List.of());
        }
        List<RouteResponse> responses = service.getAllRoutes(page - 1, limit, sort);
        return ResponseEntity.ok(responses);

    }
}
