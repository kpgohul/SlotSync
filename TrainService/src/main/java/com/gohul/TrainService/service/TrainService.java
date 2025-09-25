package com.gohul.TrainService.service;

import com.gohul.TrainService.dto.request.TrainCreateReqDto;
import com.gohul.TrainService.dto.request.TrainUpdateReqDto;
import com.gohul.TrainService.entity.Train;

import java.util.List;

public interface TrainService {

    void addTrain(TrainCreateReqDto reqDto);

    void updateRoutesForExistingTrain(TrainUpdateReqDto reqDto);

    void deleteTrain(Long id);

    Train getTrainById(Long id);

    List<Train> getTrainListBySourceStationAndDestinationStation(String sourceStation, String destinationStation);


}
