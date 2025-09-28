package com.gohul.TrainService.service.impl;

import com.gohul.TrainService.dto.request.StationCreateRequest;
import com.gohul.TrainService.dto.request.StationUpdateRequest;
import com.gohul.TrainService.dto.response.StationResponse;
import com.gohul.TrainService.entity.Station;
import com.gohul.TrainService.entity.StationMapping;
import com.gohul.TrainService.exception.ResourceAlreadyExistException;
import com.gohul.TrainService.exception.ResourceNotFoundException;
import com.gohul.TrainService.mapper.StationMapper;
import com.gohul.TrainService.repo.StationRepo;
import com.gohul.TrainService.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {

    private final StationRepo repo;
    private final StationMapper mapper;


    @Override
    public void createStation(StationCreateRequest request) {

        Optional<Station> optionalStation = repo.findByName(request.getName());
        if(optionalStation.isPresent())
            throw new ResourceAlreadyExistException("Station", "Name", request.getName());
        Station station = mapper.toStation(request);
        StationMapping mapping = mapper.toStationMapping(station, request.getNearbyStationList());
        station.setStationMapping(mapping);
        repo.save(station);

    }

    @Override
    public void updateStation(StationUpdateRequest request) {

        Optional<Station> optionalStation = repo.findById(request.getId());
        if(optionalStation.isEmpty())
            throw new ResourceNotFoundException("Station", "ID", request.getId().toString());
        Station station = mapper.toStation(request);
        StationMapping mapping = mapper.toStationMapping(station, request.getNearbyStationList());
        station.setStationMapping(mapping);
        repo.save(station);

    }

    @Override
    public void deleteStationById(Long id) {

        Optional<Station> optionalStation = repo.findById(id);
        if(optionalStation.isEmpty())
            throw new ResourceNotFoundException("Station", "ID", id.toString());

        // TODO - need to execute some logic before deleting it

        repo.deleteById(id);

    }

    @Override
    public StationResponse getStationById(Long id) {

        Optional<Station> optionalStation = repo.findById(id);
        if(optionalStation.isEmpty())
            throw new ResourceNotFoundException("Station", "ID", id.toString());
        return mapper.toStationResponse(optionalStation.get());

    }

    @Override
    public List<StationResponse> getStationByName(int page, int limit, String sort, String name) {

        Sort.Direction direction = sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(direction, "name"));
        Optional<List<Station>> optionalStations = repo.findByNameContainingIgnoreCase(name, pageable);
        return optionalStations.map(stations -> stations.stream()
                .map(mapper::toStationResponse)
                .toList()).orElseGet(List::of);

    }

    @Override
    public List<StationResponse> getAllStations(int page, int limit, String sort) {

        Sort.Direction direction = sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(direction, "name"));
        Page<Station> stations = repo.findAll(pageable);
        return stations.stream()
                .map(mapper::toStationResponse)
                .toList();

    }
}
