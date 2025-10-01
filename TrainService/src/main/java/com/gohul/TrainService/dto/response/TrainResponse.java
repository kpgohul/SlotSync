package com.gohul.TrainService.dto.response;

import com.gohul.TrainService.constant.TrainType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainResponse {

    private Long id;
    private String number;
    private String name;
    private TrainType type;

}
