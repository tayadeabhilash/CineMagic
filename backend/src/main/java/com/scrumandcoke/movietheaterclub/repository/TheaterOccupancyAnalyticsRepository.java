package com.scrumandcoke.movietheaterclub.repository;

import com.scrumandcoke.movietheaterclub.dto.TheaterOccupancySummaryByMoviesDto;
import com.scrumandcoke.movietheaterclub.mapper.TheaterOccupancySummaryByMoviesDtoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TheaterOccupancyAnalyticsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<TheaterOccupancySummaryByMoviesDto> findOccupancyForTheaters() {

        String sql = "SELECT " +
                "theater_screens.id AS theater_id, " +
                "theater_screens.name AS theater_name, " +
                "showtimes.movie_id, " +
                "SUM(CASE WHEN DATEDIFF(CURRENT_DATE, showtimes.time) <= 30 " +
                "THEN theater_screens.seating_capacity - showtimes.available_seats ELSE 0 END) AS occupancy_last_30_days, " +
                "SUM(CASE WHEN DATEDIFF(CURRENT_DATE, showtimes.time) <= 60 " +
                "THEN theater_screens.seating_capacity - showtimes.available_seats ELSE 0 END) AS occupancy_last_60_days, " +
                "SUM(CASE WHEN DATEDIFF(CURRENT_DATE, showtimes.time) <= 90 " +
                "THEN theater_screens.seating_capacity - showtimes.available_seats ELSE 0 END) AS occupancy_last_90_days " +
                "FROM showtimes " +
                "JOIN theater_screens ON showtimes.theater_screen_id = theater_screens.id " +
                "WHERE showtimes.time >= DATE_SUB(CURRENT_DATE, INTERVAL 90 DAY) " +
                "GROUP BY theater_screens.id, showtimes.movie_id";


        return jdbcTemplate.query(
                sql,
                new TheaterOccupancySummaryByMoviesDtoRowMapper()
        );
    }
}
