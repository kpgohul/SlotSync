package com.gohul.TrainService.dto.request;

import com.gohul.TrainService.constant.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatCreateRequest {

    private Long scheduleId;
    private Integer number;
    private SeatStatus status;

}
