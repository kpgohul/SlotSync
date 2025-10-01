package com.gohul.TrainService.service;

import com.gohul.TrainService.dto.request.ScheduleCreateRequest;
import com.gohul.TrainService.dto.request.ScheduleFilterRequest;
import com.gohul.TrainService.dto.request.ScheduleUpdateRequest;
import com.gohul.TrainService.dto.response.ScheduleResponse;

import java.util.List;

public interface ScheduleService {

    Long createSchedule(ScheduleCreateRequest request);
    void updateSchedule(ScheduleUpdateRequest request);
    void deleteSchedule(Long id);
    ScheduleResponse getScheduleById(Long id);
    List<ScheduleResponse> getSchedulesWithInGiveDetails(int page, int limit, String sort, ScheduleFilterRequest request);
    List<ScheduleResponse> getAllSchedules(int page, int limit, String sort);

}
