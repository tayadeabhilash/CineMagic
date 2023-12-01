package com.scrumandcoke.movietheaterclub.service.impl;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.scrumandcoke.movietheaterclub.dto.TheaterOccupancySummaryByMoviesDto;
import com.scrumandcoke.movietheaterclub.repository.TheaterOccupancyAnalyticsRepository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AnalyticsServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AnalyticsServiceImplTest {
    @Autowired
    private AnalyticsServiceImpl analyticsServiceImpl;

    @MockBean
    private TheaterOccupancyAnalyticsRepository theaterOccupancyAnalyticsRepository;

    @Test
    void testGetTheaterOccupancySummaryByMovies() {
        when(theaterOccupancyAnalyticsRepository.findOccupancyForTheaters()).thenReturn(null);
        List<TheaterOccupancySummaryByMoviesDto> actualTheaterOccupancySummaryByMovies = analyticsServiceImpl
                .getTheaterOccupancySummaryByMovies();
        verify(theaterOccupancyAnalyticsRepository).findOccupancyForTheaters();
        assertNull(actualTheaterOccupancySummaryByMovies);
    }
}