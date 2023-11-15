package com.scrumandcoke.movietheaterclub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Integer movieId;
    private String movieName;
    private String synopsis;
    private Integer runningTime;
}
