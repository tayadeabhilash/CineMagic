package com.scrumandcoke.movietheaterclub.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.scrumandcoke.movietheaterclub.dto.ShowTimeDto;
import com.scrumandcoke.movietheaterclub.dto.TheaterScreenDto;
import com.scrumandcoke.movietheaterclub.entity.LocationEntity;
import com.scrumandcoke.movietheaterclub.entity.MovieEntity;
import com.scrumandcoke.movietheaterclub.entity.ShowTimeEntity;
import com.scrumandcoke.movietheaterclub.entity.TheaterScreenEntity;
import com.scrumandcoke.movietheaterclub.enums.Location;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.mapper.ShowTimeMapper;
import com.scrumandcoke.movietheaterclub.repository.ShowTimeRepository;
import com.scrumandcoke.movietheaterclub.service.TheaterScreenService;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ShowTimeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ShowTimeServiceImplTest {
    @MockBean
    private ShowTimeMapper showTimeMapper;

    @MockBean
    private ShowTimeRepository showTimeRepository;

    @Autowired
    private ShowTimeServiceImpl showTimeServiceImpl;

    @MockBean
    private TheaterScreenService theaterScreenService;

    @Test
    void testAddShowTime() throws GlobalException {
        assertThrows(GlobalException.class, () -> showTimeServiceImpl.addShowTime(new ShowTimeDto()));
        assertThrows(GlobalException.class, () -> showTimeServiceImpl.addShowTime(null));
        assertThrows(GlobalException.class, () -> showTimeServiceImpl.addShowTime(new ShowTimeDto(1,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), 1, 1, 10.0d, 1, 10.0d)));
    }

    @Test
    void testAddShowTimeNullTime() throws GlobalException {
        ShowTimeDto showTimeDto = mock(ShowTimeDto.class);
        when(showTimeDto.getTime()).thenThrow(new EntityNotFoundException("An error occurred"));
        assertThrows(EntityNotFoundException.class, () -> showTimeServiceImpl.addShowTime(showTimeDto));
        verify(showTimeDto).getTime();
    }

    @Test
    void testAddShowTimeDiscountValidation() throws GlobalException {
        ShowTimeDto showTimeDto = mock(ShowTimeDto.class);
        when(showTimeDto.getDiscountedPrice()).thenReturn(10.0d);
        when(showTimeDto.getPrice()).thenReturn(10.0d);
        when(showTimeDto.getId()).thenReturn(1);
        when(showTimeDto.getTime()).thenReturn(new Date(9223372036854775807L));
        assertThrows(GlobalException.class, () -> showTimeServiceImpl.addShowTime(showTimeDto));
        verify(showTimeDto).getDiscountedPrice();
        verify(showTimeDto).getId();
        verify(showTimeDto).getPrice();
        verify(showTimeDto, atLeast(1)).getTime();
    }

    @Test
    void testAddShowTimeTheaterNotFound() throws GlobalException {
        MovieEntity movie = new MovieEntity();
        movie.setGenre("Genre");
        movie.setLanguage("en");
        movie.setMovieId(1);
        movie.setMovieName("Movie Name");
        movie.setPosterUrl("https://example.org/example");
        movie.setReleaseDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        movie.setRunningTime(1);
        movie.setSynopsis("Synopsis");

        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setId(1);
        locationEntity.setLocation(Location.CAMPBELL);
        locationEntity.setName("Name");
        locationEntity.setTheaterScreenCount(3);

        TheaterScreenEntity theaterScreen = new TheaterScreenEntity();
        theaterScreen.setAddress("42 Main St");
        theaterScreen.setEmail("jane.doe@example.org");
        theaterScreen.setId(1);
        theaterScreen.setLocationEntity(locationEntity);
        theaterScreen.setName("Name");
        theaterScreen.setPhone("6625550144");
        theaterScreen.setSeatingCapacity(1);

        ShowTimeEntity showTimeEntity = new ShowTimeEntity();
        showTimeEntity.setAvailableSeats(1);
        showTimeEntity.setDiscountedPrice(10.0d);
        showTimeEntity.setId(1);
        showTimeEntity.setMovie(movie);
        showTimeEntity.setPrice(10.0d);
        showTimeEntity.setTheaterScreen(theaterScreen);
        showTimeEntity.setTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(showTimeMapper.toEntity(Mockito.<ShowTimeDto>any())).thenReturn(showTimeEntity);
        ShowTimeDto showTimeDto = mock(ShowTimeDto.class);
        when(showTimeDto.getTheaterScreenId()).thenThrow(new EntityNotFoundException("An error occurred"));
        when(showTimeDto.getDiscountedPrice()).thenReturn(null);
        when(showTimeDto.getPrice()).thenReturn(10.0d);
        when(showTimeDto.getId()).thenReturn(1);
        when(showTimeDto.getTime()).thenReturn(new Date(9223372036854775807L));
        assertThrows(GlobalException.class, () -> showTimeServiceImpl.addShowTime(showTimeDto));
        verify(showTimeDto).getDiscountedPrice();
        verify(showTimeDto).getId();
        verify(showTimeDto).getPrice();
        verify(showTimeDto).getTheaterScreenId();
        verify(showTimeDto, atLeast(1)).getTime();
        verify(showTimeMapper).toEntity(Mockito.<ShowTimeDto>any());
    }

    @Test
    void testGetShowTime() throws GlobalException {
        ShowTimeDto showTimeDto = new ShowTimeDto();
        when(showTimeMapper.toDto(Mockito.<ShowTimeEntity>any())).thenReturn(showTimeDto);

        MovieEntity movie = new MovieEntity();
        movie.setGenre("Genre");
        movie.setLanguage("en");
        movie.setMovieId(1);
        movie.setMovieName("Movie Name");
        movie.setPosterUrl("https://example.org/example");
        movie.setReleaseDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        movie.setRunningTime(1);
        movie.setSynopsis("Synopsis");

        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setId(1);
        locationEntity.setLocation(Location.CAMPBELL);
        locationEntity.setName("Name");
        locationEntity.setTheaterScreenCount(3);

        TheaterScreenEntity theaterScreen = new TheaterScreenEntity();
        theaterScreen.setAddress("42 Main St");
        theaterScreen.setEmail("jane.doe@example.org");
        theaterScreen.setId(1);
        theaterScreen.setLocationEntity(locationEntity);
        theaterScreen.setName("Name");
        theaterScreen.setPhone("6625550144");
        theaterScreen.setSeatingCapacity(1);

        ShowTimeEntity showTimeEntity = new ShowTimeEntity();
        showTimeEntity.setAvailableSeats(1);
        showTimeEntity.setDiscountedPrice(10.0d);
        showTimeEntity.setId(1);
        showTimeEntity.setMovie(movie);
        showTimeEntity.setPrice(10.0d);
        showTimeEntity.setTheaterScreen(theaterScreen);
        showTimeEntity.setTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        Optional<ShowTimeEntity> ofResult = Optional.of(showTimeEntity);
        when(showTimeRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        ShowTimeDto actualShowTime = showTimeServiceImpl.getShowTime(1);
        verify(showTimeMapper).toDto(Mockito.<ShowTimeEntity>any());
        verify(showTimeRepository).findById(Mockito.<Integer>any());
        assertSame(showTimeDto, actualShowTime);
    }

    @Test
    void testGetShowTimeNotFound() throws GlobalException {
        Optional<ShowTimeEntity> emptyResult = Optional.empty();
        when(showTimeRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        assertThrows(GlobalException.class, () -> showTimeServiceImpl.getShowTime(1));
        verify(showTimeRepository).findById(Mockito.<Integer>any());
    }

    @Test
    void testGetShowTimeNullId() throws GlobalException {
        assertThrows(GlobalException.class, () -> showTimeServiceImpl.getShowTime(null));
    }

    @Test
    void testGetAllMoviesByTheaterScreenIdNotFound() throws GlobalException {
        when(showTimeRepository.findByTheaterScreen_Id(Mockito.<Integer>any()))
                .thenThrow(new EntityNotFoundException("An error occurred"));
        assertThrows(GlobalException.class, () -> showTimeServiceImpl.getAllMoviesByTheaterScreenId(1));
        verify(showTimeRepository).findByTheaterScreen_Id(Mockito.<Integer>any());
    }

    @Test
    void testGetShowTimesByLocation() throws GlobalException {
        when(showTimeMapper.toDto(Mockito.<List<ShowTimeEntity>>any())).thenReturn(null);
        when(showTimeRepository.findByTheaterScreen_LocationEntity_Location(Mockito.<Location>any())).thenReturn(null);
        List<ShowTimeDto> actualShowTimesByLocation = showTimeServiceImpl.getShowTimesByLocation(Location.CAMPBELL);
        verify(showTimeMapper).toDto(Mockito.<List<ShowTimeEntity>>any());
        verify(showTimeRepository).findByTheaterScreen_LocationEntity_Location(Mockito.<Location>any());
        assertNull(actualShowTimesByLocation);
    }

    @Test
    void testGetShowTimesByLocationNotFound() throws GlobalException {
        when(showTimeRepository.findByTheaterScreen_LocationEntity_Location(Mockito.<Location>any()))
                .thenThrow(new EntityNotFoundException("An error occurred"));
        assertThrows(GlobalException.class, () -> showTimeServiceImpl.getShowTimesByLocation(Location.CAMPBELL));
        verify(showTimeRepository).findByTheaterScreen_LocationEntity_Location(Mockito.<Location>any());
    }

    @Test
    void testDeleteShowTime() throws GlobalException {
        doNothing().when(showTimeRepository).deleteById(Mockito.<Integer>any());
        when(showTimeRepository.existsById(Mockito.<Integer>any())).thenReturn(true);
        showTimeServiceImpl.deleteShowTime(1);
        verify(showTimeRepository).deleteById(Mockito.<Integer>any());
        verify(showTimeRepository).existsById(Mockito.<Integer>any());
        assertTrue(showTimeServiceImpl.logger instanceof ch.qos.logback.classic.Logger);
    }

    @Test
    void testDeleteShowTimeIdNotFound() throws GlobalException {
        doThrow(new EntityNotFoundException("An error occurred")).when(showTimeRepository)
                .deleteById(Mockito.<Integer>any());
        when(showTimeRepository.existsById(Mockito.<Integer>any())).thenReturn(true);
        assertThrows(GlobalException.class, () -> showTimeServiceImpl.deleteShowTime(1));
        verify(showTimeRepository).deleteById(Mockito.<Integer>any());
        verify(showTimeRepository).existsById(Mockito.<Integer>any());
    }

    @Test
    void testDeleteShowTimeNullCheck() throws GlobalException {
        assertThrows(GlobalException.class, () -> showTimeServiceImpl.deleteShowTime(null));
    }
}
