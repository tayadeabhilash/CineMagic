package com.scrumandcoke.movietheaterclub.repository;

import com.scrumandcoke.movietheaterclub.model.ShowTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTimeEntity, Integer> {
    List<ShowTimeEntity> findByMovie_MovieId(Integer id);
    List<ShowTimeEntity> findByTheaterScreen_Id(Integer id);
}
