package com.scrumandcoke.movietheaterclub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowTimeDto {
    private Integer id;

    private Date time;

    private Integer movieId;

    private Integer theaterScreenId;

    private Double price;

    private Integer availableSeats;

    private Double discountedPrice;
}
