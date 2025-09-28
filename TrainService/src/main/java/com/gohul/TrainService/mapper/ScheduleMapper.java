package com.gohul.TrainService.mapper;

import com.gohul.TrainService.dto.request.ScheduleCreateRequest;
import com.gohul.TrainService.dto.request.ScheduleUpdateRequest;
import com.gohul.TrainService.dto.response.ScheduleResponse;
import com.gohul.TrainService.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {

    public Schedule toSchedule(ScheduleCreateRequest request, Schedule schedule){

        schedule.setTrainId(request.getTrainId());
        schedule.setRouteId(request.getRouteId());
        schedule.setArrivalTime(request.getArrivalTime());
        schedule.setDepartureTime(request.getDepartureTime());
        schedule.setTotalSeats(request.getTotalSeats());
        return schedule;

    }

    public Schedule toSchedule(ScheduleUpdateRequest request, Schedule schedule){

        schedule.setId(request.getId());
        schedule.setTrainId(request.getTrainId());
        schedule.setRouteId(request.getRouteId());
        schedule.setArrivalTime(request.getArrivalTime());
        schedule.setDepartureTime(request.getDepartureTime());
        schedule.setTotalSeats(request.getTotalSeats());
        return schedule;

    }

    public ScheduleResponse toScheduleResponse(Schedule schedule, ScheduleResponse response){

        response.setId(schedule.getId());
        response.setArrivalTime(schedule.getArrivalTime());
        response.setDepartureTime(schedule.getDepartureTime());
        response.setTrainId(schedule.getTrainId());
        response.setRouteId(schedule.getRouteId());
        response.setTotalSeats(schedule.getTotalSeats());
        return response;

    }
}
