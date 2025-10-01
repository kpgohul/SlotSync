package com.gohul.TrainService.mapper;

import com.gohul.TrainService.dto.request.TrainCreateRequest;
import com.gohul.TrainService.dto.request.TrainUpdateRequest;
import com.gohul.TrainService.dto.response.TrainResponse;
import com.gohul.TrainService.entity.Train;
import org.springframework.stereotype.Component;

@Component
public class TrainMapper {

    public TrainResponse toTrainResponse(Train train){
        return TrainResponse.builder()
                .id(train.getId())
                .name(train.getName())
                .number(train.getNumber())
                .type(train.getType())
                .build();
    }

    public Train toTrain(TrainCreateRequest request){
        return Train.builder()
                .number(request.getNumber())
                .name(request.getName())
                .type(request.getType())
                .build();
    }

    public Train toTrain(TrainUpdateRequest request){
        return Train.builder()
                .id(request.getId())
                .number(request.getNumber())
                .name(request.getName())
                .type(request.getType())
                .build();
    }
}
