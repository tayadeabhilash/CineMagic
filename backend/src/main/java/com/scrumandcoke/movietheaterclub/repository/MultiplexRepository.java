package com.scrumandcoke.movietheaterclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scrumandcoke.movietheaterclub.model.MultiplexEntity;

public interface MultiplexRepository extends JpaRepository<MultiplexEntity, Integer> {
}
