package com.gohul.TrainService.repo;

import com.gohul.TrainService.entity.StationMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationMappingRepo extends JpaRepository<StationMapping, Long> {

}
