package com.scrumandcoke.movietheaterclub.controller;


import com.scrumandcoke.movietheaterclub.annotation.LoginRequired;
import com.scrumandcoke.movietheaterclub.annotation.UserTypesAllowed;
import com.scrumandcoke.movietheaterclub.dto.TheaterOccupancySummaryByMoviesDto;
import com.scrumandcoke.movietheaterclub.enums.UserType;
import com.scrumandcoke.movietheaterclub.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v0/analytics")
public class AnalyticsController {

    @Autowired
    AnalyticsService analyticsService;

    @GetMapping("/theaterOccupanciesByMovie")
    @LoginRequired
    @UserTypesAllowed({UserType.THEATER_EMPLOYEE})
    public List<TheaterOccupancySummaryByMoviesDto> getTheaterOccupanciesByMovie() {
        return analyticsService.getTheaterOccupancySummaryByMovies();
    }
}
