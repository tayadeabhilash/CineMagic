package com.scrumandcoke.movietheaterclub.repository;

import com.scrumandcoke.movietheaterclub.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
    List<BookingEntity> findByUserId(String id);
}
