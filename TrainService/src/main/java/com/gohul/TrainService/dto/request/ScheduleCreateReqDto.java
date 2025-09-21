package com.gohul.TrainService.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ScheduleCreateReqDto {

    private Long trainId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer noOfSeats;

}
