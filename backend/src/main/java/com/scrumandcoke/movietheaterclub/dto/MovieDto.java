package com.scrumandcoke.movietheaterclub.dto;

import com.scrumandcoke.movietheaterclub.entity.MovieEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
    private String posterUrl;
    private String genre;
    private String language;
//    private LocalDate releaseDate;
    private Date releaseDate;

    public static MovieDto fromEntity(MovieEntity movieEntity) {
        MovieDto movieDto = new MovieDto();
        movieDto.setMovieId(movieEntity.getMovieId());
        movieDto.setMovieName(movieEntity.getMovieName());
        movieDto.setSynopsis(movieEntity.getSynopsis());
        movieDto.setRunningTime(movieEntity.getRunningTime());

        movieDto.setPosterUrl(movieEntity.getPosterUrl());
        movieDto.setGenre(movieEntity.getGenre());
        movieDto.setLanguage(movieEntity.getLanguage());

        movieDto.setReleaseDate(movieEntity.getReleaseDate());


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

        movieEntity.setPosterUrl(movieDto.getPosterUrl());
        movieEntity.setGenre(movieDto.getGenre());
        movieEntity.setLanguage(movieDto.getLanguage());
        movieEntity.setReleaseDate(movieDto.getReleaseDate());

        return movieEntity;
    }
}
