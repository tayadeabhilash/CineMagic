package com.scrumandcoke.movietheaterclub.dto;

import com.scrumandcoke.movietheaterclub.model.MovieEntity;
import com.scrumandcoke.movietheaterclub.model.ShowTimeEntity;
import com.scrumandcoke.movietheaterclub.model.TheaterScreenEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
