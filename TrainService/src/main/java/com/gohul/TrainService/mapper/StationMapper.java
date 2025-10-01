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

    public Station toStation(StationCreateRequest request, Station station) {
        // Set or override station fields
        station.setName(request.getName());
        station.setAddress(request.getAddress());
        station.setCity(request.getCity());
        station.setZipcode(request.getZipcode());
        station.setState(request.getState());
        station.setCountry(request.getCountry());

        // Handle StationMapping
        StationMapping mapping = station.getStationMapping();
        if (mapping == null) {
            mapping = new StationMapping();
            mapping.setStation(station);
            station.setStationMapping(mapping);
        }
        mapping.setNearbyStationList(request.getNearbyStationList());
        mapping.setStation(station); // ensure bidirectional mapping

        return station;
    }


    public Station toStation(StationUpdateRequest request, Station station) {
        // Set or override station fields
//        station.setId(request.getId()); // Optional, Since u r sending the actual station object
        station.setName(request.getName());
        station.setAddress(request.getAddress());
        station.setCity(request.getCity());
        station.setZipcode(request.getZipcode());
        station.setState(request.getState());
        station.setCountry(request.getCountry());

        // Handle StationMapping
        StationMapping mapping = station.getStationMapping();
        if (mapping == null) {
            mapping = new StationMapping();
            mapping.setStation(station);
            station.setStationMapping(mapping);
        }
        mapping.setNearbyStationList(request.getNearbyStationList());
        mapping.setStation(station); // ensure bidirectional mapping

        return station;
    }


    public StationResponse toStationResponse(Station station){

        return StationResponse.builder()
                .id(station.getId())
                .name(station.getName())
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
