package com.gohul.TrainService.repo;

import com.gohul.TrainService.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainRepo extends JpaRepository<Train, Long> {

    List<Train> findBySourceStationAndDestinationStation(String sourceStation, String destinationStation);

    Optional<Train> findByName(String trainName);

}
