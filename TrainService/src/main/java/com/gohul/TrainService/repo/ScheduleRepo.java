package com.gohul.TrainService.repo;

import com.gohul.TrainService.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Long>
{

    List<Schedule> findByTrainIdIn(List<Long> ids);

}