package com.gohul.TrainService.dto.response;

import com.gohul.TrainService.constant.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatResponse {

    private Integer number;
    private SeatStatus status;

}
