package com.scrumandcoke.movietheaterclub.entity;

import com.scrumandcoke.movietheaterclub.enums.BookingStatus;
import com.scrumandcoke.movietheaterclub.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "bookings")
@Data
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;

    @Column(name = "user_id")
    private String userId;

    @ManyToOne
    @JoinColumn(name = "showtime_id")
    private ShowTimeEntity showtime;

    @Column(name = "seats_booked")
    private Integer seatsBooked;
    @Column(name = "total_amount")
    private Double totalAmount;
    @Column(name = "cash_amount")
    private Double cashAmount;
    @Column(name = "points_amount")
    private Double pointsAmount;
    @Column(name = "online_service_fee")
    private Double onlineServiceFee;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status")
    private BookingStatus bookingStatus;
    @Column(name = "booking_date")
    private Date bookingDate;
}
