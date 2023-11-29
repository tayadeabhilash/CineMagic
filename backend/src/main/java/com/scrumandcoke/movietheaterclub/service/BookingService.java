package com.scrumandcoke.movietheaterclub.service;

import com.scrumandcoke.movietheaterclub.dto.BookingDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;

import java.util.List;

public interface BookingService {

    List<BookingDto> getAllBookings();
    BookingDto getBookingById(Integer id);
    void createBooking(BookingDto booking) throws GlobalException;
    void cancelBooking(Integer id) throws GlobalException;
    List<BookingDto> getAllBookingsByUserId(String id);
}
