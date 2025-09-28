package com.gohul.TrainService.service.impl;

import com.gohul.TrainService.constant.SeatStatus;
import com.gohul.TrainService.dto.request.SeatCreateRequest;
import com.gohul.TrainService.dto.request.SeatUpdateRequest;
import com.gohul.TrainService.dto.response.SeatResponse;
import com.gohul.TrainService.entity.Seat;
import com.gohul.TrainService.exception.ResourceAlreadyExistException;
import com.gohul.TrainService.exception.ResourceNotFoundException;
import com.gohul.TrainService.mapper.SeatMapper;
import com.gohul.TrainService.repo.SeatRepo;
import com.gohul.TrainService.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepo repo;
    private final SeatMapper mapper;

    @Override
    public void createSeats(int numOfSeats, Long scheduleId) {

        long scheduleIdCount = repo.countByScheduleId(scheduleId);
        if(scheduleIdCount > 0)
            throw new ResourceAlreadyExistException("Seat", "ScheduleID", scheduleId.toString());
        List<Seat> seats = IntStream.range(0, numOfSeats)
                .mapToObj( i ->
                      Seat.builder()
                              .scheduleId(scheduleId)
                              .number(i+1)
                              .status(SeatStatus.AVAILABLE)
                              .build()
                ).toList();

    }

    @Override
    public void createSeat(SeatCreateRequest request) {

        long scheduleIdCount = repo.countByScheduleId(request.getScheduleId());
        if(scheduleIdCount == 0)
            throw new ResourceNotFoundException("Seat", "ScheduleID",request.getScheduleId().toString());
        // TODO - need to handle the seat numbers count
        Seat seat = mapper.toSeat(request);
        repo.save(seat);

    }

    @Override
    public void updateSeat(SeatUpdateRequest request) {

        Seat seat = repo.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Seat", "ID", request.getId().toString()));
        seat.setStatus(request.getStatus());
        repo.save(seat);

    }

    @Override
    public void deleteSeat(Long id) {

        Seat seat = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seat", "ID", id.toString()));
        if(seat.getStatus().equals(SeatStatus.BOOKED) || seat.getStatus().equals(SeatStatus.PENDING))
            throw new RuntimeException("Seat cannot be deleted. Current status: " + seat.getStatus());
        repo.deleteById(id);

    }

    @Override
    public void deleteSeat(Long scheduleId, Integer seatNumber) {

        Seat seat = repo.findByScheduleIdAndNumber(scheduleId, seatNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Seat", "ScheduleID and SeatNumber",
                        scheduleId.toString() +" and "+seatNumber.toString())
                );
        if(seat.getStatus().equals(SeatStatus.BOOKED) || seat.getStatus().equals(SeatStatus.PENDING))
            throw new RuntimeException("Seat cannot be deleted. Current status: " + seat.getStatus());
        repo.deleteById(seat.getId());

    }

    @Override
    public void deleteSeatsByScheduleId(Long scheduleId) {
        repo.deletedByScheduleId(scheduleId);
    }

    @Override
    public List<SeatResponse> getSeatsByScheduleId(Long scheduleId, int page, int limit, String sort) {

        Sort.Direction direction = sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(direction, "number"));

        Page<Seat> seatPage = repo.findByScheduleId(scheduleId, pageable);

        return seatPage.stream()
                .map(mapper::toSeatResponse)
                .toList();
    }
}
