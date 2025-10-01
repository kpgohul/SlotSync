package com.gohul.TrainService.repo;

import com.gohul.TrainService.entity.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StationRepo extends JpaRepository<Station, Long> {

    Optional<Station> findByName(String name);

    Page<Station> findByCityContainingIgnoreCase(String name, Pageable pageable);



}
