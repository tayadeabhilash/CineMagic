package com.scrumandcoke.movietheaterclub.repository;

import com.scrumandcoke.movietheaterclub.enums.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scrumandcoke.movietheaterclub.entity.MultiplexEntity;

import java.util.List;

public interface MultiplexRepository extends JpaRepository<MultiplexEntity, Integer> {
    List<MultiplexEntity> findByLocation(Location location);
}
