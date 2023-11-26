package com.scrumandcoke.movietheaterclub.service;

import com.scrumandcoke.movietheaterclub.dto.BookingDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.model.BookingEntity;
import com.scrumandcoke.movietheaterclub.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface BookingService {

    List<BookingDto> getAllBookings();
    BookingDto getBookingById(Integer id);
    void createBooking(BookingDto booking) throws GlobalException;
    void cancelBooking(Integer id) throws GlobalException;
    List<BookingDto> getAllBookingsByUserId(Integer id);
}
