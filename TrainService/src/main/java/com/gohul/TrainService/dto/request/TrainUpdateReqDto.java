package com.gohul.TrainService.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainUpdateReqDto {

    private Long id;
    private String name;
    private String sourceStation;
    private String destinationStation;

}
