package com.scrumandcoke.movietheaterclub.repository;

import com.scrumandcoke.movietheaterclub.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
    List<MovieEntity> findByReleaseDateAfter(LocalDate date);
    List<MovieEntity> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate);

}