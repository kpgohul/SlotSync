package com.gohul.TrainService.service.impl;

import com.gohul.TrainService.dto.request.TrainCreateRequest;
import com.gohul.TrainService.dto.request.TrainUpdateRequest;
import com.gohul.TrainService.dto.response.TrainResponse;
import com.gohul.TrainService.entity.Train;
import com.gohul.TrainService.exception.ResourceAlreadyExistException;
import com.gohul.TrainService.exception.ResourceNotFoundException;
import com.gohul.TrainService.mapper.TrainMapper;
import com.gohul.TrainService.repo.TrainRepo;
import com.gohul.TrainService.service.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainServiceImpl implements TrainService {

    private final TrainRepo repo;
    private final TrainMapper mapper;

    @Override
    public void createTrain(TrainCreateRequest request) {

        Optional<Train> trainOption = repo.findByNumber(request.getNumber());
        if(trainOption.isPresent())
            throw new ResourceAlreadyExistException("Train", "Number", request.getNumber());
        repo.save(mapper.toTrain(request));

    }

    @Override
    public void updateTrain(TrainUpdateRequest request) {

        Optional<Train> optionalTrain = repo.findById(request.getId());
        if(optionalTrain.isEmpty()) throw new ResourceNotFoundException("Train", "ID", request.getId().toString());
        repo.save(mapper.toTrain(request));

    }

    @Override
    public void deleteTrain(Long id) {

        Optional<Train> optionalTrain = repo.findById(id);
        if(optionalTrain.isEmpty()) throw new ResourceNotFoundException("Train", "ID", id.toString());

        // TODO - need to execute the logic before deleting it!

        repo.deleteById(id);

    }

    @Override
    public TrainResponse getTrainById(Long id) {

        Optional<Train> optionalTrain = repo.findById(id);
        if(optionalTrain.isEmpty()) throw new ResourceNotFoundException("Train", "ID", id.toString());
        return mapper.toTrainResponse(optionalTrain.get());

    }

    @Override
    public TrainResponse getTrainByNumber(String number) {

        Optional<Train> optionalTrain = repo.findByNumber(number);
        if(optionalTrain.isEmpty()) throw new ResourceNotFoundException("Train", "Number", number);
        return mapper.toTrainResponse(optionalTrain.get());
    }

    @Override
    public List<TrainResponse> getTrainsByName(int page, int limit, String sort, String name) {

        Sort.Direction direction = sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(direction, "createdAt"));
        Optional<List<Train>> optionalTrain = repo.findByNameContainingIgnoreCase(name, pageable);
        return optionalTrain.map(trains -> trains.stream()
                .map(mapper::toTrainResponse)
                .toList()).orElseGet(List::of);

    }

    @Override
    public List<TrainResponse> getAllTheTrains(int page, int limit, String sort) {

        Sort.Direction direction = sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(direction, "name"));
        Page<Train> trainPage = repo.findAll(pageable);
        return trainPage.stream()
                .map(mapper::toTrainResponse)
                .toList();

    }

}
