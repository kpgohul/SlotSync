package com.gohul.TrainService.dto.response;

import com.gohul.TrainService.entity.NearbyStation;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StationResponse {

    private Long id;
    private String name;
    private String address;
    private String city;
    private Integer zipcode;
    private String state;
    private String country;

    private List<NearbyStation> nearbyStationList;
}
