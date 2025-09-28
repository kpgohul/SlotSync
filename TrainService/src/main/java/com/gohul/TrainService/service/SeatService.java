package com.gohul.TrainService.service;

import com.gohul.TrainService.dto.request.SeatCreateRequest;
import com.gohul.TrainService.dto.request.SeatUpdateRequest;
import com.gohul.TrainService.dto.response.SeatResponse;

import java.util.List;

public interface SeatService {

    void createSeats(int numOfSeats, Long scheduleId);

    void createSeat(SeatCreateRequest request);

    void updateSeat(SeatUpdateRequest request);

    void deleteSeat(Long id);

    void deleteSeat(Long scheduleId, Integer seatNumber);

    void deleteSeatsByScheduleId(Long scheduleId);

    List<SeatResponse> getSeatsByScheduleId(Long scheduleId, int page, int limit, String sort);
}