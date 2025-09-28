package com.gohul.TrainService.entity;

import com.gohul.TrainService.converter.NearByStationConverter;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class StationMapping extends BaseEntity{

    @Id
    @OneToOne
    @JoinColumn(name = "station_id", referencedColumnName = "id", nullable = false)
    private Station station;

    @Column(columnDefinition = "jsonb")
    @Convert(converter = NearByStationConverter.class)
    private List<NearbyStation> nearbyStationList;

}
