package com.gohul.TrainService.dto.errorDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

    private String apiPath;

    private String errorCode;

    private String errorMessage;

    private Instant errorTime;
}
