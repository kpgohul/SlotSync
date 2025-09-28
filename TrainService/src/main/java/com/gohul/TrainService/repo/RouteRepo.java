package com.gohul.TrainService.repo;

import com.gohul.TrainService.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepo extends JpaRepository<Route, Long> {


    Optional<Route> findBySourceStationIdAndDestinationStationId(Long id1, Long id2);
}
