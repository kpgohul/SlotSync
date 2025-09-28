package com.gohul.TrainService.service;

import com.gohul.TrainService.dto.request.TrainCreateRequest;
import com.gohul.TrainService.dto.request.TrainUpdateRequest;
import com.gohul.TrainService.dto.response.TrainResponse;

import java.util.List;

public interface TrainService {

    void createTrain(TrainCreateRequest request);
    void updateTrain(TrainUpdateRequest request);
    void deleteTrain(Long id);
    TrainResponse getTrainById(Long id);
    TrainResponse getTrainByNumber(String number);
    List<TrainResponse> getTrainsByName(int page, int limit, String sort, String name);
    List<TrainResponse> getAllTheTrains(int page, int limit, String sort);


}