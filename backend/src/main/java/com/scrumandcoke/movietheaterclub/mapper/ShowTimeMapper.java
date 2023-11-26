package com.scrumandcoke.movietheaterclub.mapper;

import com.scrumandcoke.movietheaterclub.dto.BookingDto;
import com.scrumandcoke.movietheaterclub.dto.ShowTimeDto;
import com.scrumandcoke.movietheaterclub.model.BookingEntity;
import com.scrumandcoke.movietheaterclub.model.MovieEntity;
import com.scrumandcoke.movietheaterclub.model.ShowTimeEntity;
import com.scrumandcoke.movietheaterclub.model.TheaterScreenEntity;
import com.scrumandcoke.movietheaterclub.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.StringUtils;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShowTimeMapper {
    @Mapping(target = "movieId", source = "showTimeEntity.movie.movieId")
    @Mapping(target = "theaterScreenId", source = "showTimeEntity.theaterScreen.id")
    ShowTimeDto toDto(ShowTimeEntity showTimeEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movie", source = "movieId", qualifiedByName = "movieIdToMovieEntity")
    @Mapping(target = "theaterScreen", source = "theaterScreenId", qualifiedByName = "theaterScreenIdToScreenEntity")
    ShowTimeEntity toEntity(ShowTimeDto showTimeDto);

    @Mapping(target = "movieId", source = "showTimeEntity.movie.movieId")
    @Mapping(target = "theaterScreenId", source = "showTimeEntity.theaterScreen.id")
    List<ShowTimeDto> toDto(List<ShowTimeEntity> showTimeEntities);

    @Named("movieIdToMovieEntity")
    public static MovieEntity movieIdToMovieEntity(Integer movieId) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setMovieId(movieId);
        return movieEntity;
    }

    @Named("theaterScreenIdToScreenEntity")
    public static TheaterScreenEntity theaterScreenIdToScreennEntity(Integer screenId) {
        TheaterScreenEntity theaterScreen = new TheaterScreenEntity();
        theaterScreen.setId(screenId);
        return theaterScreen;
    }
}
