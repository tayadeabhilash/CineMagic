package com.scrumandcoke.movietheaterclub.controller;

import com.scrumandcoke.movietheaterclub.dto.BookingDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping
    public List<BookingDto> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/user/{id}")
    public List<BookingDto> getAllBookingsByUserId(@PathVariable String id) {
        return bookingService.getAllBookingsByUserId(id);
    }

    @GetMapping("/{id}")
    public BookingDto getBookingById(@PathVariable Integer id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping
    public void createBooking(@RequestBody BookingDto booking) throws GlobalException {
        bookingService.createBooking(booking);
    }

    @DeleteMapping("/{id}")
    public void cancelBooking(@PathVariable Integer id) throws GlobalException {
        bookingService.cancelBooking(id);
    }
}
