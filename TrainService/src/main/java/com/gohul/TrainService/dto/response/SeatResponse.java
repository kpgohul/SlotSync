package com.gohul.TrainService.dto.response;

import com.gohul.TrainService.constant.SeatStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeatResponse {

    private Long id;
    private Integer number;
    private SeatStatus status;

}
