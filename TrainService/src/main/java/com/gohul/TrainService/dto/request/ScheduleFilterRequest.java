package com.gohul.TrainService.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleFilterRequest {

    private Long sourceStationId;
    private Long destinationStationId;
    @Builder.Default
    private Instant startTime = Instant.EPOCH;  // 1970-01-01T00:00:00Z
    @Builder.Default
    private Instant endTime = Instant.ofEpochSecond(253402300799L); //// 9999-12-31T23:59:59Z

}
