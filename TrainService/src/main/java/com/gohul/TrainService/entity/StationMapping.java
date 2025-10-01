package com.gohul.TrainService.entity;

import com.gohul.TrainService.converter.NearByStationConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.type.SqlTypes;


import java.util.List;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class StationMapping extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "station_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Station station;

//    @JdbcTypeCode(SqlTypes.JSON)
    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private List<NearbyStation> nearbyStationList;

}
