package com.scrumandcoke.movietheaterclub.repository;

import com.scrumandcoke.movietheaterclub.entity.BookingEntity;
import com.scrumandcoke.movietheaterclub.entity.ShowTimeEntity;
import com.scrumandcoke.movietheaterclub.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
    List<BookingEntity> findByUserId(String id);
    List<BookingEntity> findByUserIdAndShowtime_TimeAfterAndBookingStatus(String userId, Date showtime_time, BookingStatus bookingStatus);
    List<BookingEntity> findByUserIdAndShowtime_TimeBeforeAndBookingStatus(String userId, Date showtime_time, BookingStatus bookingStatus);
    List<BookingEntity> findByUserIdAndBookingStatus(String userId, BookingStatus bookingStatus);
}
