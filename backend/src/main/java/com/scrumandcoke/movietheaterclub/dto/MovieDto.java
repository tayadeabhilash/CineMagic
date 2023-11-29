package com.scrumandcoke.movietheaterclub.dto;

import com.scrumandcoke.movietheaterclub.entity.MovieEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Integer movieId;
    private String movieName;
    private String synopsis;
    private Integer runningTime;

    public static MovieDto fromEntity(MovieEntity movieEntity) {
        MovieDto movieDto = new MovieDto();
        movieDto.setMovieId(movieEntity.getMovieId());
        movieDto.setMovieName(movieEntity.getMovieName());
        movieDto.setSynopsis(movieEntity.getSynopsis());
        movieDto.setRunningTime(movieEntity.getRunningTime());
        return movieDto;
    }

    public static List<MovieDto> fromEntityList(List<MovieEntity> showTimeEntities) {
        List<MovieDto> movieDtoList = new ArrayList<>();
        for (MovieEntity showTime : showTimeEntities) {
            movieDtoList.add(fromEntity(showTime));
        }
        return movieDtoList;
    }

    public static MovieEntity toEntity(MovieDto movieDto) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setMovieId(movieDto.getMovieId());
        movieEntity.setMovieName(movieDto.getMovieName());
        movieEntity.setSynopsis(movieDto.getSynopsis());
        movieEntity.setRunningTime(movieDto.getRunningTime());
        return movieEntity;
    }
}
