package com.gohul.TrainService.service.impl;

import com.gohul.TrainService.dto.request.RouteCreateRequest;
import com.gohul.TrainService.dto.request.RouteUpdateRequest;
import com.gohul.TrainService.dto.response.RouteResponse;
import com.gohul.TrainService.dto.response.StationResponse;
import com.gohul.TrainService.entity.NearbyStation;
import com.gohul.TrainService.entity.Route;
import com.gohul.TrainService.exception.ResourceNotFoundException;
import com.gohul.TrainService.mapper.RouteMapper;
import com.gohul.TrainService.repo.RouteRepo;
import com.gohul.TrainService.service.RouteService;
import com.gohul.TrainService.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepo repo;
    private final RouteMapper mapper;
    private final StationService stationService;

    @Override
    public void createNewRoute(RouteCreateRequest request) {


        if(!request.getSourceStationId().equals(request.getPath()[0])){
            throw new RuntimeException("Source Station ID and Path List 1 argument must be same");
        }
        if(!request.getDestinationStationId().equals(request.getPath()[request.getPath().length - 1])){
            throw new RuntimeException("Destination Station ID and Path List last argument must be same");
        }
        Set<Long> set = new HashSet<>();
        Long[] paths = request.getPath();
        outer:
        for (int i=0; i< paths.length - 1; i++) {
            if (!set.add(paths[i])) {
                throw new IllegalArgumentException("Duplicate Station ID found in path: " + paths[i]);
            }
            StationResponse response = stationService.getStationById(paths[i]);
            for(NearbyStation nxtStation: response.getNearbyStationList()){
                if(nxtStation.getStationId().equals(paths[i + 1]))
                    continue outer;
            }
            throw new RuntimeException("There is no connection between stationID"+(i+1)+":: "+paths[i]+" and stationID"+(i+2)+":: "+paths[i+1]);

        }

        for(Long id: request.getPath()){
            stationService.getStationById(id);
        }
        Route route = mapper.toRoute(request);
        repo.save(route);


    }

    @Override
    public void updateRoute(RouteUpdateRequest request) {

        if(!request.getSourceStationId().equals(request.getPath()[0])){
            throw new RuntimeException("Source Station ID and Path List 1 argument must be same");
        }
        if(!request.getDestinationStationId().equals(request.getPath()[request.getPath().length - 1])){
            throw new RuntimeException("Destination Station ID and Path List last argument must be same");
        }

        Set<Long> set = new HashSet<>();
        Long[] paths = request.getPath();
        outer:
        for (int i=0; i< paths.length - 1; i++) {
            if (!set.add(paths[i])) {
                throw new IllegalArgumentException("Duplicate Station ID found in path: " + paths[i]);
            }
            StationResponse response = stationService.getStationById(paths[i]);
            for(NearbyStation nxtStation: response.getNearbyStationList()){
                if(nxtStation.getStationId().equals(paths[i + 1]))
                    continue outer;
            }
            throw new RuntimeException("There is no connection between stationID"+(i+1)+":: "+paths[i]+" and stationID"+(i+2)+":: "+paths[i+1]);

        }
        Route route = repo.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Route", "ID", request.getId().toString()));
        Route updRoute = mapper.toRoute(request);
        repo.save(updRoute);

    }

    @Override
    public void deleteRouteById(Long id) {

        Route route = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route", "ID", id.toString()));
        //TODO - need to validate some logics before going to deletion
        repo.deleteById(id);

    }

    @Override
    public RouteResponse getRouteBySourceAndDestination(Long source, Long destination) {

        Optional<Route> optionalRoute = repo.findBySourceStationIdAndDestinationStationId(source, destination);
        return optionalRoute.map(mapper::toRouteResponse).orElse(null);

    }

    @Override
    public RouteResponse getRouteById(Long id) {

        Optional<Route> optionalRoute = repo.findById(id);
        return optionalRoute.map(mapper::toRouteResponse).orElse(null);

    }

    @Override
    public List<RouteResponse> getAllRoutes(int page, int limit, String sort) {

        Sort.Direction direction = sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(direction, "createdAt"));
        Page<Route> routes = repo.findAll(pageable);
        return routes.stream()
                .map(mapper::toRouteResponse)
                .toList();

    }

}
