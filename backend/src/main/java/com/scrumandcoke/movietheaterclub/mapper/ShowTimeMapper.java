package com.scrumandcoke.movietheaterclub.mapper;

import com.scrumandcoke.movietheaterclub.dto.ShowTimeDto;
import com.scrumandcoke.movietheaterclub.entity.MovieEntity;
import com.scrumandcoke.movietheaterclub.entity.ShowTimeEntity;
import com.scrumandcoke.movietheaterclub.entity.TheaterScreenEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShowTimeMapper {
    @Mapping(target = "movieId", source = "showTimeEntity.movie.movieId")
    @Mapping(target = "theaterScreenId", source = "showTimeEntity.theaterScreen.id")
    ShowTimeDto toDto(ShowTimeEntity showTimeEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "availableSeats", ignore = true)
    @Mapping(target = "movie", source = "movieId", qualifiedByName = "movieIdToMovieEntity")
    @Mapping(target = "theaterScreen", source = "theaterScreenId", qualifiedByName = "theaterScreenIdToScreenEntity")
    ShowTimeEntity toEntity(ShowTimeDto showTimeDto);

    @Mapping(target = "movieId", source = "showTimeEntity.movie.movieId")
    @Mapping(target = "theaterScreenId", source = "showTimeEntity.theaterScreen.id")
    List<ShowTimeDto> toDto(List<ShowTimeEntity> showTimeEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movie", source = "movieId", qualifiedByName = "movieIdToMovieEntity")
    @Mapping(target = "theaterScreen", source = "theaterScreenId", qualifiedByName = "theaterScreenIdToScreenEntity")
    void updateCustomerFromDto(ShowTimeDto dto, @MappingTarget ShowTimeEntity entity);

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
