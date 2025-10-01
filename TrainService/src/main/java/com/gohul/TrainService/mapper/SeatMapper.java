package com.gohul.TrainService.mapper;

import com.gohul.TrainService.dto.request.SeatCreateRequest;
import com.gohul.TrainService.dto.response.SeatResponse;
import com.gohul.TrainService.entity.Seat;
import com.gohul.TrainService.entity.composite.SeatId;
import org.springframework.stereotype.Component;

@Component
public class SeatMapper {

    public Seat toSeat(SeatCreateRequest request){

        return Seat.builder()
                .id(new SeatId(request.getScheduleId(), request.getNumber()))
                .status(request.getStatus())
                .build();

    }

    public SeatResponse toSeatResponse(Seat seat){

        return SeatResponse.builder()
                .number(seat.getId().getNumber())
                .status(seat.getStatus())
                .build();

    }
}
