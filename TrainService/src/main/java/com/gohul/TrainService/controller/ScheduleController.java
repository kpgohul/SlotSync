package com.gohul.TrainService.controller;

import com.gohul.TrainService.dto.request.ScheduleCreateRequest;
import com.gohul.TrainService.dto.request.ScheduleFilterRequest;
import com.gohul.TrainService.dto.request.ScheduleUpdateRequest;
import com.gohul.TrainService.dto.response.ScheduleResponse;
import com.gohul.TrainService.service.ScheduleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;

    @PostMapping
    public ResponseEntity<?> createSchedule(@Valid @RequestBody ScheduleCreateRequest request){

        Long id = service.createSchedule(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Schedule Created with ID:: "+id);

    }

    @PutMapping
    public ResponseEntity<?> updateSchedule(@Valid @RequestBody ScheduleUpdateRequest request){

        service.updateSchedule(request);
        return ResponseEntity.ok("Given schedule record successfully updated.");

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getScheduleById(@PathVariable("id") Long id){

        ScheduleResponse response = service.getScheduleById(id);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/filter")
    public ResponseEntity<?> getSchedulesByGivenFilter(
            @RequestParam(value = "page", defaultValue = "1") @Min(1) int page,
            @RequestParam(value = "limit", defaultValue = "10") @Min(1) int limit,
            @RequestParam(value = "sort", defaultValue = "asc") String sort,
            @Valid @RequestBody ScheduleFilterRequest request
    ) {

        if (!sort.equalsIgnoreCase("asc") && !sort.equalsIgnoreCase("desc")) {
            return ResponseEntity.badRequest()
                    .body("Sort must be either 'asc' or 'desc'");
        }

        List<ScheduleResponse> schedules = service.getSchedulesWithInGiveDetails(
                page - 1, limit,
                sort.toLowerCase(),
                request
        );
        return ResponseEntity.ok(schedules);
    }

    @GetMapping
    public ResponseEntity<?> getAllSchedules(
            @RequestParam(value = "page", defaultValue = "1") @Min(1) int page,
            @RequestParam(value = "limit", defaultValue = "10") @Min(1) int limit,
            @RequestParam(value = "sort", defaultValue = "asc") String sort
    ) {

        if (!sort.equalsIgnoreCase("asc") && !sort.equalsIgnoreCase("desc")) {
            return ResponseEntity.badRequest()
                    .body("Sort must be either 'asc' or 'desc'");
        }

        List<ScheduleResponse> schedules = service.getAllSchedules(
                page - 1, limit,
                sort.toLowerCase()
        );
        return ResponseEntity.ok(schedules);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScheduleById(@PathVariable("id") Long id){

        service.deleteSchedule(id);
        return ResponseEntity.ok("Schedule ID:: "+id+" is successfully deleted.");

    }



}
