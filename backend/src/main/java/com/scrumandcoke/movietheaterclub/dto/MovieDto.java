package com.scrumandcoke.movietheaterclub.dto;

import com.scrumandcoke.movietheaterclub.model.MovieEntity;
import com.scrumandcoke.movietheaterclub.model.MovieEntity;
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
    private String poster;
    private String genre;
    private String language;
    private String release_date;
    public static MovieDto fromEntity(MovieEntity movieEntity) {
        MovieDto movieDto = new MovieDto();
        movieDto.setMovieId(movieEntity.getMovieId());
        movieDto.setMovieName(movieEntity.getMovieName());
        movieDto.setSynopsis(movieEntity.getSynopsis());
        movieDto.setRunningTime(movieEntity.getRunningTime());

        movieDto.setPoster(movieEntity.getPoster());
        movieDto.setGenre(movieEntity.getGenre());
        movieDto.setLanguage(movieEntity.getLanguage());
        movieDto.setRelease_date(movieDto.getRelease_date());

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

        movieEntity.setPoster(movieDto.getPoster());
        movieEntity.setGenre(movieDto.getGenre());
        movieEntity.setLanguage(movieDto.getLanguage());
        movieEntity.setRelease_date(movieDto.getRelease_date());

        return movieEntity;
    }
}
