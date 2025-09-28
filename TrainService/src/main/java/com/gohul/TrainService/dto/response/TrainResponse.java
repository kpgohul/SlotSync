package com.gohul.TrainService.dto.response;

import com.gohul.TrainService.constant.TrainType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainResponse {

    private Long id;
    private String number;
    private String name;
    private TrainType type;

}
