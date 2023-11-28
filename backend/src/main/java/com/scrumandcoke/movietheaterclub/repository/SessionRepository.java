package com.scrumandcoke.movietheaterclub.repository;

import com.scrumandcoke.movietheaterclub.model.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Integer> {

}
