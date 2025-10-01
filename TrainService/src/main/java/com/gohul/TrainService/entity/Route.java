package com.gohul.TrainService.entity;


import com.vladmihalcea.hibernate.type.array.LongArrayType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Route extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long sourceStationId;
    private Long destinationStationId;
    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "bigint[]")
    private Long[] path;

}
