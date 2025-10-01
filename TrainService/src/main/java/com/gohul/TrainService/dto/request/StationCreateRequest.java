package com.gohul.TrainService.dto.request;

import com.gohul.TrainService.entity.NearbyStation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationCreateRequest {

    @NotBlank(message = "Station name must not be blank")
    private String name;

    @NotBlank(message = "Address must not be blank")
    private String address;

    @NotBlank(message = "City must not be blank")
    private String city;

    @NotNull(message = "Zipcode must not be null")
    @Min(value = 100000, message = "Zipcode must be a valid 6-digit number")
    @Max(value = 999999, message = "Zipcode must be a valid 6-digit number")
    private Integer zipcode;

    @NotBlank(message = "State must not be blank")
    private String state;

    @NotBlank(message = "Country must not be blank")
    private String country;

    @NotNull(message = "NearByStation should not be null")
    private List<NearbyStation> nearbyStationList;

}
