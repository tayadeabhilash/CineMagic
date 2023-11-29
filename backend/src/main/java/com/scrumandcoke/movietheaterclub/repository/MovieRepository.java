package com.scrumandcoke.movietheaterclub.repository;

import com.scrumandcoke.movietheaterclub.model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
}