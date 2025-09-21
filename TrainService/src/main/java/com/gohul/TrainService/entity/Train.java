package com.gohul.TrainService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "train",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"source_station", "destination_station"})
        }
)
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(name = "source_station", length = 100)
    private String sourceStation;
    @Column(name = "destination_station", length = 100)
    private String destinationStation;
    @Column(nullable = false)
    private Integer totalSeats;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;
    @CreatedBy
    @Column(updatable = false, nullable = false)
    private Long createdBy;
    @LastModifiedBy
    @Column(insertable = false)
    private Long updatedBy;


    @OneToMany(
            mappedBy = "train",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            targetEntity = Schedule.class
    )
    private List<Schedule> scheduleList;

}
