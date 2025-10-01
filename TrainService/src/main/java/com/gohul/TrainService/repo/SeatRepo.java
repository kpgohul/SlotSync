package com.gohul.TrainService.repo;

import com.gohul.TrainService.entity.Seat;
import com.gohul.TrainService.entity.composite.SeatId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepo extends JpaRepository<Seat, SeatId>{

    Page<Seat> findByIdScheduleId(Long id, Pageable pageable);

    Optional<Seat> findByIdScheduleIdAndIdNumber(Long scheduleId, Integer number);

    Long countByIdScheduleId(Long id);

    @Query("SELECT MAX(s.id.number) FROM Seat s WHERE s.id.scheduleId = :scheduleId")
    Integer findMaxSeatNumberByScheduleId(@Param("scheduleId") Long scheduleId);

    void deleteByIdScheduleId(Long scheduleId);


}