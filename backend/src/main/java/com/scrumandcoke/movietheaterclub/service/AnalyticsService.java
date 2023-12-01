package com.scrumandcoke.movietheaterclub.service;

import com.scrumandcoke.movietheaterclub.dto.TheaterOccupancySummaryByMoviesDto;

import java.util.List;

public interface AnalyticsService {

    List<TheaterOccupancySummaryByMoviesDto> getTheaterOccupancySummaryByMovies();


}
