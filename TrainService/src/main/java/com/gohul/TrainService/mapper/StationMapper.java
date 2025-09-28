package com.gohul.TrainService.mapper;

import com.gohul.TrainService.dto.request.StationCreateRequest;
import com.gohul.TrainService.dto.request.StationUpdateRequest;
import com.gohul.TrainService.dto.response.StationResponse;
import com.gohul.TrainService.entity.NearbyStation;
import com.gohul.TrainService.entity.Station;
import com.gohul.TrainService.entity.StationMapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StationMapper {

    public Station toStation(StationCreateRequest request){

        return Station.builder()
                .name(request.getName())
                .address(request.getAddress())
                .city(request.getCity())
                .zipcode(request.getZipcode())
                .country(request.getCountry())
                .build();

    }

    public Station toStation(StationUpdateRequest request){

        return Station.builder()
                .id(request.getId())
                .name(request.getName())
                .address(request.getAddress())
                .city(request.getCity())
                .zipcode(request.getZipcode())
                .country(request.getCountry())
                .build();

    }

    public StationMapping toStationMapping(Station station, List<NearbyStation> nearbyStationList){

        return StationMapping.builder()
                .station(station)
                .nearbyStationList(nearbyStationList)
                .build();

    }

    public StationResponse toStationResponse(Station station){

        return StationResponse.builder()
                .id(station.getId())
                .address(station.getAddress())
                .city(station.getCity())
                .state(station.getState())
                .zipcode(station.getZipcode())
                .country(station.getCountry())
                .nearbyStationList(
                        station.getStationMapping().getNearbyStationList()
                )
                .build();
    }

}
