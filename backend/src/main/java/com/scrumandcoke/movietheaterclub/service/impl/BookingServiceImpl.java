package com.scrumandcoke.movietheaterclub.service.impl;

import com.scrumandcoke.movietheaterclub.dto.BookingDto;
import com.scrumandcoke.movietheaterclub.dto.ShowTimeDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.mapper.BookingMapper;
import com.scrumandcoke.movietheaterclub.model.BookingEntity;
import com.scrumandcoke.movietheaterclub.model.ShowTimeEntity;
import com.scrumandcoke.movietheaterclub.model.enums.BookingStatus;
import com.scrumandcoke.movietheaterclub.model.enums.PaymentMethod;
import com.scrumandcoke.movietheaterclub.repository.BookingRepository;
import com.scrumandcoke.movietheaterclub.service.BookingService;
import com.scrumandcoke.movietheaterclub.service.ShowTimeService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingMapper bookingMapper;

    @Autowired
    ShowTimeService showTimeService;

    @Value("${booking.service.charge:1.5}")
    private Double serviceCharge;

    Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Override
    public List<BookingDto> getAllBookings() {
        return bookingMapper.toDto(bookingRepository.findAll());
    }

    @Override
    public BookingDto getBookingById(Integer id) {
        return bookingMapper.toDto(bookingRepository.findById(id).get());
    }

    @Override
    @Transactional
    public void createBooking(BookingDto booking) throws GlobalException {
        try {
            BookingEntity bookingEntity = bookingMapper.toEntity(booking);
            validateShowtime(booking.getShowtimeId());
            reduceAvailableSeats(booking.getShowtimeId(), booking.getSeatsBooked());
            bookingEntity.setOnlineServiceFee(calculateServiceFee(booking.getUserId()));
            bookingEntity.setTotalAmount(calculateTotalPrice(booking.getSeatsBooked(), bookingEntity.getOnlineServiceFee(),
                    booking.getShowtimeId()));
            bookingEntity.setBookingDate(Date.from(Instant.now()));
            processPayment(booking.getPaymentMethod(), booking.getTotalAmount());
            bookingEntity.setBookingStatus(BookingStatus.CONFIRMED);
            bookingRepository.save(bookingEntity);
        } catch (Exception exception) {
            logger.error("Error saving booking");
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public void cancelBooking(Integer id) throws GlobalException {
        BookingEntity bookingEntity = bookingRepository.findById(id).get();
        checkCancelEligibility(bookingEntity.getShowtime(), bookingEntity.getBookingStatus());
        bookingEntity.setBookingStatus(BookingStatus.CANCELLED);
        processRefund(bookingEntity.getTotalAmount(), bookingEntity.getOnlineServiceFee(), bookingEntity.getPaymentMethod());
        bookingRepository.save(bookingEntity);
    }

    @Override
    public List<BookingDto> getAllBookingsByUserId(Integer userId) {
        return bookingMapper.toDto(bookingRepository.findByUser_Id(userId));
    }

    private void validateShowtime(Integer showTimeId) throws GlobalException {
        ShowTimeDto showtime = showTimeService.getShowTime(showTimeId);

        // Check if the showtime is in the future
        Date currentDateTime = Date.from(Instant.now());
        Date showtimeDateTime = showtime.getTime();
        if (showtimeDateTime.before(currentDateTime)) {
            throw new GlobalException("Showtime must be in the future");
        }
    }

    private void reduceAvailableSeats(Integer showtimeId, int seatsBooked) throws GlobalException {
        ShowTimeDto showtime = showTimeService.getShowTime(showtimeId);
        int availableSeats = showtime.getAvailableSeats();

        if (seatsBooked > 8 || seatsBooked < 1)
            throw new GlobalException("Number of seats should be between 1 to 8");

        if (seatsBooked > availableSeats) {
            throw new GlobalException("Not enough available seats for the booking");
        }

        // Reduce available seats
        showtime.setAvailableSeats(availableSeats - seatsBooked);

        // Save the updated Showtime entity
        showTimeService.updateShowTime(showtime);
    }

    private Double calculateServiceFee(Integer userId) {
        if (userId == null)
            return serviceCharge;

        // TODO: Add Logic to retrieve member type and return service charge accordingly
        return serviceCharge;
    }

    private Double calculateTotalPrice(Integer seatsBooked, Double serviceCharge, Integer showTimeId) throws GlobalException {
        Double showPrice = showTimeService.getShowTime(showTimeId).getPrice();
        return (showPrice * seatsBooked) + serviceCharge;
    }

    private void processPayment(PaymentMethod paymentMethod, Double totalAmount) {
        if (paymentMethod.equals(PaymentMethod.POINTS)) {
            // TODO: Add logic to check and reduce points
        } else {
            // TODO: Add points to users account
        }
    }

    private void checkCancelEligibility(ShowTimeEntity showtime, BookingStatus bookingStatus) throws GlobalException {
        if (bookingStatus.equals(BookingStatus.CANCELLED))
            throw new GlobalException("Booking already cancelled");

        // Checking if current datetime is past showtime
        if (Date.from(Instant.now()).after(showtime.getTime()))
            throw new GlobalException("Booking not eligible for cancellation");
    }

    private void processRefund(Double totalAmount, Double onlineServiceFee, PaymentMethod paymentMethod) {
        if (paymentMethod.equals(PaymentMethod.POINTS)) {
            // TODO: Add points back to user's account
        }
    }
}
