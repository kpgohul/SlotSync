package com.gohul.TrainService.entity;


import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Route extends BaseEntity{

    private Long id;
    private Long sourceStationId;
    private Long destinationId;
    @ElementCollection
    @Column(columnDefinition = "text[]")
    private List<Long> path;

}
