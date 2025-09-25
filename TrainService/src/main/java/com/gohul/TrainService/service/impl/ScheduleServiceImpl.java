package com.gohul.TrainService.service.impl;

import com.gohul.TrainService.dto.request.ScheduleCreateReqDto;
import com.gohul.TrainService.entity.Schedule;
import com.gohul.TrainService.entity.Train;
import com.gohul.TrainService.exception.ResourceAlreadyExistException;
import com.gohul.TrainService.exception.ResourceNotFoundException;
import com.gohul.TrainService.repo.ScheduleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.gohul.TrainService.service.ScheduleService;
import com.gohul.TrainService.service.TrainService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepo scheduleRepo;
    private final TrainService trainService;

    @Override
    public void makeSchedule(ScheduleCreateReqDto reqDto) {
        Train alreadyScheduledTrain = trainService.getTrainById(reqDto.getTrainId());
        if(alreadyScheduledTrain == null)
            throw new ResourceNotFoundException("Train", "TrainID", reqDto.getTrainId().toString());
        List<Train> sameSourceAndDestinations = trainService.getTrainListBySourceStationAndDestinationStation(
                alreadyScheduledTrain.getSourceStation(),
                alreadyScheduledTrain.getDestinationStation()
        );
        List<Schedule> trainSchedules = scheduleRepo.findByTrainIdIn(
                sameSourceAndDestinations.stream()
                        .map(Train::getId)
                        .collect(Collectors.toList())
        );
        LocalDateTime reqStBefore = reqDto.getArrivalTime().minusMinutes(20L);
        LocalDateTime reqStAfter = reqDto.getArrivalTime().plusMinutes(20L);

        LocalDateTime reqDepBefore = reqDto.getDepartureTime().minusMinutes(20L);
        LocalDateTime reqDepAfter = reqDto.getDepartureTime().plusMinutes(20L);

        for(Schedule schedule: trainSchedules){

            LocalDateTime arrivalTime = schedule.getArrivalTime();
            LocalDateTime departureTime = schedule.getDepartureTime();

            if(
                    (arrivalTime.isAfter(reqStBefore) & arrivalTime.isBefore(reqStAfter)) ||
                    (departureTime.isAfter(reqStBefore) & departureTime.isBefore(reqStAfter))
            ){
                throw new ResourceAlreadyExistException("Train", "ArrivalTime", reqDto.getArrivalTime().toString());
            }

            if(
                    (arrivalTime.isAfter(reqDepBefore) & arrivalTime.isBefore(reqDepAfter)) ||
                    (departureTime.isAfter(reqDepBefore) & departureTime.isBefore(reqDepAfter))
            ){
                throw new ResourceAlreadyExistException("Train", "DepartureTime", reqDto.getDepartureTime().toString());
            }
        }



    }

    @Override
    public void updateSchedule() {

    }
}
