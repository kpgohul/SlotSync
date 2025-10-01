package com.gohul.TrainService.controller;

import com.gohul.TrainService.dto.request.StationCreateRequest;
import com.gohul.TrainService.dto.request.StationUpdateRequest;
import com.gohul.TrainService.dto.response.StationResponse;
import com.gohul.TrainService.service.StationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stations")
@RequiredArgsConstructor
@Validated
public class StationController {

    private final StationService service;

    @PostMapping
    public ResponseEntity<?> createStation(@Valid @RequestBody StationCreateRequest request) {

        Long id = service.createStation(request);
        return ResponseEntity.ok("Station successfully created aith ID:: "+id);

    }

    @PutMapping
    public ResponseEntity<?> updateStation(@Valid @RequestBody StationUpdateRequest request) {

        service.updateStation(request);
        return ResponseEntity.ok("Station updated successfully");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStation(@PathVariable Long id) {

        service.deleteStationById(id);
        return ResponseEntity.ok("Station deleted successfully");

    }

    @GetMapping("/{id}")
    public ResponseEntity<StationResponse> getStationById(@PathVariable Long id) {

        StationResponse response = service.getStationById(id);
        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<?> getStationByName(
            @RequestParam(value = "page", defaultValue = "1") @Min(value = 1, message = "page value should be start from 1") int page,
            @RequestParam(value = "limit", defaultValue = "10") @Min(value = 1, message = "limit should be at least one") int limit,
            @RequestParam(value = "sort", defaultValue = "asc") String sort,
            @RequestParam(value = "name", defaultValue = "all", required = false) String name
    ) {

        if (!sort.equalsIgnoreCase("asc") && !sort.equalsIgnoreCase("desc")) {
            return ResponseEntity.badRequest().body(List.of());
        }
        List<StationResponse> responses;
        if(name.equalsIgnoreCase("all"))
            responses = service.getAllStations(page - 1, limit, sort);
        else
            responses = service.getStationByName(page - 1, limit, sort, name);

        return ResponseEntity.ok(responses);

    }

}
