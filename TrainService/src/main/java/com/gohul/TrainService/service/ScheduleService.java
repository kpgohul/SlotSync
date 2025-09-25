package com.gohul.TrainService.service;

import com.gohul.TrainService.dto.request.ScheduleCreateReqDto;

public interface ScheduleService {

    void makeSchedule(ScheduleCreateReqDto reqDto);

    void updateSchedule();

}
