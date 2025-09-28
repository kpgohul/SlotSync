package com.gohul.TrainService.mapper;

import com.gohul.TrainService.dto.request.SeatCreateRequest;
import com.gohul.TrainService.dto.response.SeatResponse;
import com.gohul.TrainService.entity.Seat;
import org.springframework.stereotype.Component;

@Component
public class SeatMapper {

    public Seat toSeat(SeatCreateRequest request){

        return Seat.builder()
                .scheduleId(request.getScheduleId())
                .number(request.getNumber())
                .status(request.getStatus())
                .build();

    }

    public SeatResponse toSeatResponse(Seat seat){

        return SeatResponse.builder()
                .id(seat.getId())
                .number(seat.getNumber())
                .status(seat.getStatus())
                .build();

    }
}
