package com.gohul.TrainService.dto.request;

import com.gohul.TrainService.constant.SeatStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatUpdateRequest {

    @NotBlank(message = "Seat ID must not be blank")
    private Long id;
    @NotNull(message = "Seat status must not be null")
    private SeatStatus status;

}
