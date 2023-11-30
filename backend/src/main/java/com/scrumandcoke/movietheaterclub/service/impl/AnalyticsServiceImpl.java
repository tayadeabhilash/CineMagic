package com.scrumandcoke.movietheaterclub.service.impl;

import com.scrumandcoke.movietheaterclub.dto.TheaterOccupancySummaryByMoviesDto;
import com.scrumandcoke.movietheaterclub.repository.TheaterOccupancyAnalyticsRepository;
import com.scrumandcoke.movietheaterclub.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    @Autowired
    TheaterOccupancyAnalyticsRepository theaterOccupancyAnalyticsRepository;

    @Override
    public List<TheaterOccupancySummaryByMoviesDto> getTheaterOccupancySummaryByMovies() {
        return theaterOccupancyAnalyticsRepository.findOccupancyForTheaters();
    }
}
