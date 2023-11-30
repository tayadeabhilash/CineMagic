package com.scrumandcoke.movietheaterclub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterOccupancySummaryByMoviesDto {

    @NonNull
    private Integer theaterId;

    @NonNull
    private String theaterName;

    @NonNull
    private Integer movieId;

    @NonNull
    private Integer occupancyLast30Days;

    @NonNull
    private Integer occupancyLast60Days;

    @NonNull
    private Integer occupancyLast90Days;

}

