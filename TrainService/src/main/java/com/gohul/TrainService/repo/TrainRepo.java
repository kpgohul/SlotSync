package com.gohul.TrainService.repo;

import com.gohul.TrainService.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainRepo extends JpaRepository<Train, Long> {

    Optional<Train> findByNumber(String number);
    Optional<List<Train>> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
