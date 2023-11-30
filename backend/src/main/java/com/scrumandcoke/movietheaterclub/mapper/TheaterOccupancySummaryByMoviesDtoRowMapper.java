package com.scrumandcoke.movietheaterclub.mapper;

import com.scrumandcoke.movietheaterclub.dto.TheaterOccupancySummaryByMoviesDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TheaterOccupancySummaryByMoviesDtoRowMapper implements RowMapper<TheaterOccupancySummaryByMoviesDto> {
    @Override
    public TheaterOccupancySummaryByMoviesDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TheaterOccupancySummaryByMoviesDto(
                rs.getInt("theater_id"),
                rs.getString("theater_name"),
                rs.getInt("movie_id"),
                rs.getInt("occupancy_last_30_days"),
                rs.getInt("occupancy_last_60_days"),
                rs.getInt("occupancy_last_90_days")
        );
    }
}
