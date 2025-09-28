package com.gohul.TrainService.repo;

import com.gohul.TrainService.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s " +
            "WHERE s.trainId = :trainId " +
            "AND s.arrivalTime < :newDeparture " +
            "AND s.departureTime > :newArrival")
    List<Schedule> findConflictingSchedules(
            @Param("trainId") Long trainId,
            @Param("newArrival") Instant newArrival,
            @Param("newDeparture") Instant newDeparture);

    @Query("SELECT s FROM Schedule s " +
            "WHERE s.arrivalTime < :newDeparture " +
            "AND s.departureTime > :newArrival")
    Page<Schedule> findScheduleWithGivenTimingRange(
            @Param("newArrival") Instant newArrival,
            @Param("newDeparture") Instant newDeparture,
            Pageable pageable);




}
