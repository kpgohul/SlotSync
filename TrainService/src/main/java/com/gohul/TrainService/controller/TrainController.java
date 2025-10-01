package com.gohul.TrainService.controller;

import com.gohul.TrainService.dto.request.ScheduleFilterRequest;
import com.gohul.TrainService.dto.request.TrainCreateRequest;
import com.gohul.TrainService.dto.request.TrainUpdateRequest;
import com.gohul.TrainService.dto.response.ScheduleResponse;
import com.gohul.TrainService.dto.response.TrainResponse;
import com.gohul.TrainService.service.TrainService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/train")
@RequiredArgsConstructor
@Validated
public class TrainController {

    private final TrainService service;

    @PostMapping
    public ResponseEntity<?> createTrain(@Valid @RequestBody TrainCreateRequest request){

        Long id = service.createTrain(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Train Record created with ID:: "+id);

    }


    @PutMapping
    public ResponseEntity<?> updateExistingTrain(@Valid @RequestBody TrainUpdateRequest request){

        service.updateTrain(request);
        return ResponseEntity.ok("Train details updated successfully.");

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTrainById(@PathVariable("id") Long id){

        TrainResponse response = service.getTrainById(id);
        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<?> getSchedulesByGivenFilter(
            @RequestParam(value = "page", defaultValue = "1") @Min(value = 1, message = "page value should be start from 1") int page,
            @RequestParam(value = "limit", defaultValue = "10") @Min(value = 1, message = "limit should be at least one") int limit,
            @RequestParam(value = "sort", defaultValue = "asc") String sort,
            @RequestParam(value = "name", defaultValue = "all") String name
    ) {

        if (!sort.equalsIgnoreCase("asc") && !sort.equalsIgnoreCase("desc")) {
            return ResponseEntity.badRequest()
                    .body("Sort must be either 'asc' or 'desc'");
        }
        List<TrainResponse> responses;
        if(name.equalsIgnoreCase("all"))
            responses = service.getAllTheTrains(page - 1, limit, sort);
        else
            responses = service.getTrainsByName(page - 1, limit, sort, name);
        return ResponseEntity.ok(responses);

    }





}
