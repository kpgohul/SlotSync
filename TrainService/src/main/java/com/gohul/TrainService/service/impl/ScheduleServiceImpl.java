package com.gohul.TrainService.service.impl;

import com.gohul.TrainService.dto.request.ScheduleCreateRequest;
import com.gohul.TrainService.dto.request.ScheduleFilterRequest;
import com.gohul.TrainService.dto.request.ScheduleUpdateRequest;
import com.gohul.TrainService.dto.response.RouteResponse;
import com.gohul.TrainService.dto.response.ScheduleResponse;
import com.gohul.TrainService.dto.response.StationResponse;
import com.gohul.TrainService.dto.response.TrainResponse;
import com.gohul.TrainService.entity.NearbyStation;
import com.gohul.TrainService.entity.Schedule;
import com.gohul.TrainService.exception.ResourceAlreadyExistException;
import com.gohul.TrainService.exception.ResourceNotFoundException;
import com.gohul.TrainService.mapper.ScheduleMapper;
import com.gohul.TrainService.repo.ScheduleRepo;
import com.gohul.TrainService.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepo repo;
    private final ScheduleMapper mapper;
    private final StationService stationService;
    private final TrainService trainService;
    private final RouteService routeService;
    private final SeatService seatService;

    @Override
    public Long createSchedule(ScheduleCreateRequest request) {

        TrainResponse train = trainService.getTrainById(request.getTrainId());
        if (train == null)
            throw new ResourceNotFoundException("Train", "ID", request.getTrainId().toString());

        RouteResponse route = routeService.getRouteById(request.getRouteId());
        if (route == null)
            throw new ResourceNotFoundException("Route", "ID", request.getRouteId().toString());

        Instant currentTime = request.getArrivalTime(); // start from arrivalTime
        int speedKmph = train.getType().getAverageSpeed();
        Long[] path = route.getPath();

        for (int i = 0; i < path.length - 1; i++) {
            StationResponse currentStation = stationService.getStationById(path[i]);
            List<NearbyStation> nearbyStations = currentStation.getNearbyStationList();

            boolean found = false;
            for (NearbyStation nearby : nearbyStations) {
                if (nearby.getStationId().equals(path[i + 1])) {
                    double distanceKm = nearby.getDistance();
                    double timeHours = distanceKm / speedKmph;
                    long timeSeconds = (long) (timeHours * 3600);
                    currentTime = currentTime.plusSeconds(timeSeconds);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IllegalArgumentException(
                        "No nearby connection found between station " + path[i] + " and " + path[i + 1]
                );
            }
        }

        Instant finalDepartureTime = currentTime;

        List<Schedule> conflicts = repo.findConflictingSchedules(train.getId(), request.getArrivalTime(), finalDepartureTime);
        if (!conflicts.isEmpty()) {
            throw new ResourceAlreadyExistException(
                    "Schedule", "ArrivalTime and DepartureTime",
                    request.getArrivalTime() + " and " + finalDepartureTime
            );
        }

        Schedule schedule = mapper.toSchedule(request, new Schedule());
//        schedule.setDepartureTime(finalDepartureTime);

        Schedule saved = repo.save(schedule);
        seatService.createSeats(request.getTotalSeats(), saved.getId());

        return saved.getId();
    }

    @Override
    public void updateSchedule(ScheduleUpdateRequest request) {

        Schedule schedule = repo.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "ID", request.getId().toString()));

        TrainResponse train = trainService.getTrainById(request.getTrainId());
        if (train == null)
            throw new ResourceNotFoundException("Train", "ID", request.getTrainId().toString());

        RouteResponse route = routeService.getRouteById(request.getRouteId());
        if (route == null)
            throw new ResourceNotFoundException("Route", "ID", request.getRouteId().toString());

        Instant currentTime = request.getArrivalTime(); // start from arrivalTime
        int speedKmph = train.getType().getAverageSpeed();
        Long[] path = route.getPath();

        for (int i = 0; i < path.length - 1; i++) {
            StationResponse currentStation = stationService.getStationById(path[i]);
            List<NearbyStation> nearbyStations = currentStation.getNearbyStationList();

            boolean found = false;
            for (NearbyStation nearby : nearbyStations) {
                if (nearby.getStationId().equals(path[i + 1])) {
                    double distanceKm = nearby.getDistance();
                    double timeHours = distanceKm / speedKmph;
                    long timeSeconds = (long) (timeHours * 3600);
                    currentTime = currentTime.plusSeconds(timeSeconds);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IllegalArgumentException(
                        "No nearby connection found between station " + path[i] + " and " + path[i + 1]
                );
            }
        }

        Instant finalDepartureTime = currentTime;

        List<Schedule> conflicts = repo.findConflictingSchedules(train.getId(), request.getArrivalTime(), finalDepartureTime);
        if (!conflicts.isEmpty()) {
            throw new ResourceAlreadyExistException(
                    "Schedule", "ArrivalTime and DepartureTime",
                    request.getArrivalTime() + " and " + finalDepartureTime
            );
        }

        boolean isSeatsToBeUpdated = !schedule.getTotalSeats().equals(request.getTotalSeats());
        mapper.toSchedule(request, schedule);
//        schedule.setDepartureTime(finalDepartureTime); // update departure time after recalculation
        repo.save(schedule);

        if (isSeatsToBeUpdated) {
            seatService.deleteSeatsByScheduleId(request.getId());
            seatService.createSeats(request.getTotalSeats(), request.getId());
        }
    }


    @Override
    public void deleteSchedule(Long id) {

        Schedule schedule = repo.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Schedule", "ID",id.toString()));
        if(schedule.getArrivalTime().isAfter(Instant.now())){
            throw new RuntimeException("Future schedules cannot be deleted.");

        }
        if(schedule.getDepartureTime().isAfter(Instant.now())){
            throw new RuntimeException("Schedule is in progress and cannot be deleted at this time.");
        }
        repo.deleteById(id);



    }

    @Override
    public ScheduleResponse getScheduleById(Long id) {

        Schedule schedule = repo.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Schedule", "ID",id.toString()));
        return mapper.toScheduleResponse(schedule, new ScheduleResponse());

    }

    @Override
    public List<ScheduleResponse> getSchedulesWithInGiveDetails(int page, int limit, String sort, ScheduleFilterRequest request) {

        Sort.Direction direction = sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(direction, "createdAt"));
        Page<Schedule> scheduleList = repo.findScheduleWithGivenTimingRange(
                request.getStartTime(),
                request.getEndTime(),
                pageable
        );

        List<ScheduleResponse> response = new ArrayList<>();
        for(Schedule schedule: scheduleList){
            RouteResponse routeResponse = routeService.getRouteById(schedule.getRouteId());
            if (routeResponse != null) {
                if (request.getSourceStationId().equals(routeResponse.getSourceStationId())
                        && request.getDestinationStationId().equals(routeResponse.getDestinationStationId())) {
                    response.add(mapper.toScheduleResponse(schedule, new ScheduleResponse()));
                }
            }
        }
        return response;

    }

    @Override
    public List<ScheduleResponse> getAllSchedules(int page, int limit, String sort) {

        Sort.Direction direction = sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(direction, "createdAt"));
        Page<Schedule> schedules = repo.findAll(pageable);

        return schedules.stream()
                .map(schedule -> mapper.toScheduleResponse(schedule, new ScheduleResponse()))
                .toList();

    }
}
