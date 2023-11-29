package com.scrumandcoke.movietheaterclub.dto;

import com.scrumandcoke.movietheaterclub.enums.BookingStatus;
import com.scrumandcoke.movietheaterclub.enums.PaymentMethod;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private Integer bookingId;
    private String userId;
    @Nonnull
    private Integer showtimeId;
    @Nonnull
    private Integer seatsBooked;
    private Double totalAmount;
    private Double onlineServiceFee;
    @Nonnull
    private PaymentMethod paymentMethod;
    private BookingStatus bookingStatus;
    private Date bookingDate;
}
