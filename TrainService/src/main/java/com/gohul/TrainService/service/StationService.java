package com.gohul.TrainService.service;

import com.gohul.TrainService.dto.request.StationCreateRequest;
import com.gohul.TrainService.dto.request.StationUpdateRequest;
import com.gohul.TrainService.dto.response.StationResponse;

import java.util.List;

public interface StationService {

    void createStation(StationCreateRequest request);

    void updateStation(StationUpdateRequest request);

    void deleteStationById(Long id);

    StationResponse getStationById(Long id);

    List<StationResponse> getStationByName(int page, int limit, String sort, String name);

    List<StationResponse> getAllStations(int page, int limit, String sort);



}
