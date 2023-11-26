package com.scrumandcoke.movietheaterclub.repository;

import com.scrumandcoke.movietheaterclub.model.enums.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scrumandcoke.movietheaterclub.model.MultiplexEntity;

import java.util.List;

public interface MultiplexRepository extends JpaRepository<MultiplexEntity, Integer> {
    List<MultiplexEntity> findByLocation(Location location);
}
