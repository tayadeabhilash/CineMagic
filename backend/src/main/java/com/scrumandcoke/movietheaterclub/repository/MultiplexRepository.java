package com.scrumandcoke.movietheaterclub.repository;

import com.scrumandcoke.movietheaterclub.enums.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scrumandcoke.movietheaterclub.entity.LocationEntity;

import java.util.List;

public interface MultiplexRepository extends JpaRepository<LocationEntity, Integer> {
    List<LocationEntity> findByLocation(Location location);
}
