package com.scrumandcoke.movietheaterclub.repository;

import com.scrumandcoke.movietheaterclub.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
    Optional<MovieEntity> findByMovieName(String movieName);
    List<MovieEntity> findByReleaseDateAfter(Date date);
    List<MovieEntity> findByReleaseDateBetween(Date startDate, Date endDate);

}