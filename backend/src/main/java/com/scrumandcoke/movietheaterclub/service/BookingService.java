package com.scrumandcoke.movietheaterclub.service;

import com.scrumandcoke.movietheaterclub.dto.BookingDto;
import com.scrumandcoke.movietheaterclub.dto.GetBookingResponse;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;

import java.util.List;

public interface BookingService {

    List<BookingDto> getAllBookings();
    BookingDto getBookingById(Integer id);
    void createBooking(BookingDto booking) throws GlobalException;
    void cancelBooking(Integer id) throws GlobalException;
    List<BookingDto> getAllBookingsByUserId(String id);
    List<GetBookingResponse> getAllUpcomingBookingsByUserId(String id) throws GlobalException;
    List<GetBookingResponse> getAllPastBookingsByUserId(String id) throws GlobalException;
    List<GetBookingResponse> getAllCancelledByUserId(String id) throws GlobalException;
}
