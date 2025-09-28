package com.gohul.TrainService.repo;

import com.gohul.TrainService.entity.Seat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepo extends JpaRepository<Seat, Long>{

    Page<Seat> findByScheduleId(Long id, Pageable pageable);

    Optional<Seat> findByScheduleIdAndNumber(Long scheduleId, Integer number);

    long countByScheduleId(Long id);

    @Query("SELECT MAX(s.number) FROM Seat s WHERE s.scheduleId = :scheduleId")
    Integer findMaxSeatNumberByScheduleId(@Param("scheduleId") Long scheduleId);

    void deletedByScheduleId(Long scheduleId);

}