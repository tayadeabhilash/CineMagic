package com.scrumandcoke.movietheaterclub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieWithShowtimesDto {
    private Integer movieId;
    private String movieTitle;
    private List<ShowTimeDto> upcomingShowtimes;

}
