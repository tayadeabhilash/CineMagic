package com.scrumandcoke.movietheaterclub.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "showtimes")
@Data
public class ShowTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private Date time;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name = "theater_screen_id")
    private TheaterScreenEntity theaterScreen;

    private Double price;

    @Column(name = "discounted_price")
    private Double discountedPrice;

    @Column(name = "available_seats")
    private Integer availableSeats;
}
