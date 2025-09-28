package com.gohul.TrainService.dto.request;

import com.gohul.TrainService.constant.SeatStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeatCreateRequest {

    private Long scheduleId;
    private Integer number;
    private SeatStatus status;

}
