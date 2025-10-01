package com.gohul.TrainService.controller;

import com.gohul.TrainService.dto.request.SeatCreateRequest;
import com.gohul.TrainService.dto.request.SeatUpdateRequest;
import com.gohul.TrainService.dto.response.SeatResponse;
import com.gohul.TrainService.service.SeatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seat")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService service;

    @PostMapping
    public ResponseEntity<?> createSeat(@Valid @RequestBody SeatCreateRequest request) {

        service.createSeat(request);
        return ResponseEntity.ok("Seat created successfully");

    }

    @PutMapping
    public ResponseEntity<?> updateSeat(@Valid @RequestBody SeatUpdateRequest request) {

        service.updateSeat(request);
        return ResponseEntity.ok("Seat updated successfully");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeatById(@PathVariable Long id) {

        service.deleteSeat(id);
        return ResponseEntity.ok("Seat deleted successfully");

    }

    @DeleteMapping
    public ResponseEntity<?> deleteSeatByScheduleIdAndNumber(
            @RequestParam Long scheduleId,
            @RequestParam Integer seatNumber
    ) {

        service.deleteSeat(scheduleId, seatNumber);
        return ResponseEntity.ok("Seat deleted successfully");

    }

    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<List<SeatResponse>> getSeatsBySchedule(
            @PathVariable Long scheduleId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "asc") String sort
    ) {

        if (!sort.equalsIgnoreCase("asc") && !sort.equalsIgnoreCase("desc")) {
            return ResponseEntity.badRequest().body(List.of());
        }

        List<SeatResponse> seats = service.getSeatsByScheduleId(scheduleId, page - 1, limit, sort);
        return ResponseEntity.ok(seats);

    }
}
