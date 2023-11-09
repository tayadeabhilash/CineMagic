package com.scrumandcoke.movietheaterclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scrumandcoke.movietheaterclub.model.Multiplex;

public interface MultiplexRepository extends JpaRepository<Multiplex, Integer> {
}
