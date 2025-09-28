package com.gohul.TrainService.dto.request;

import com.gohul.TrainService.constant.TrainType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainUpdateRequest {

    @NotBlank(message = "Train ID must not be blank")
    private Long id;
    @NotBlank(message = "Train number must not be blank")
    private String number;
    @NotBlank(message = "Train name must not be blank")
    private String name;
    @NotNull(message = "Train type must not be null")
    private TrainType type;

}
