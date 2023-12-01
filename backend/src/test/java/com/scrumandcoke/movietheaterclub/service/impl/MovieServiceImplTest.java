package com.scrumandcoke.movietheaterclub.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.scrumandcoke.movietheaterclub.dto.MovieDto;
import com.scrumandcoke.movietheaterclub.entity.MovieEntity;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.repository.MovieRepository;

import java.text.SimpleDateFormat;
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

@ContextConfiguration(classes = {MovieServiceImpl.class})
@ExtendWith(SpringExtension.class)
class MovieServiceImplTest {
  @MockBean
  private MovieRepository movieRepository;

  @Autowired
  private MovieServiceImpl movieServiceImpl;

  @Test
  void testAddMovie() throws GlobalException {
    MovieEntity movieEntity = new MovieEntity();
    movieEntity.setGenre("Genre");
    movieEntity.setLanguage("en");
    movieEntity.setMovieId(1);
    movieEntity.setMovieName("Movie Name");
    movieEntity.setPosterUrl("https://example.org/example");
    movieEntity.setReleaseDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    movieEntity.setRunningTime(1);
    movieEntity.setSynopsis("Synopsis");
    when(movieRepository.save(Mockito.<MovieEntity>any())).thenReturn(movieEntity);
    Optional<MovieEntity> emptyResult = Optional.empty();
    when(movieRepository.findByMovieName(Mockito.<String>any())).thenReturn(emptyResult);
    movieServiceImpl.addMovie(new MovieDto());
    verify(movieRepository).findByMovieName(Mockito.<String>any());
    verify(movieRepository).save(Mockito.<MovieEntity>any());
  }

  @Test
  void testAddMovieFailure() throws GlobalException {
    MovieEntity movieEntity = new MovieEntity();
    movieEntity.setGenre("Genre");
    movieEntity.setLanguage("en");
    movieEntity.setMovieId(1);
    movieEntity.setMovieName("Movie Name");
    movieEntity.setPosterUrl("https://example.org/example");
    movieEntity.setReleaseDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    movieEntity.setRunningTime(1);
    movieEntity.setSynopsis("Synopsis");
    Optional<MovieEntity> ofResult = Optional.of(movieEntity);
    when(movieRepository.findByMovieName(Mockito.<String>any())).thenReturn(ofResult);
    MovieDto movieDto = mock(MovieDto.class);
    when(movieDto.getMovieName()).thenReturn("Movie Name");
    assertThrows(GlobalException.class, () -> movieServiceImpl.addMovie(movieDto));
    verify(movieDto, atLeast(1)).getMovieName();
    verify(movieRepository).findByMovieName(Mockito.<String>any());
  }

  @Test
  void testGetMovie() throws GlobalException {
    MovieEntity movieEntity = new MovieEntity();
    movieEntity.setGenre("Genre");
    movieEntity.setLanguage("en");
    movieEntity.setMovieId(1);
    movieEntity.setMovieName("Movie Name");
    movieEntity.setPosterUrl("https://example.org/example");
    movieEntity.setReleaseDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    movieEntity.setRunningTime(1);
    movieEntity.setSynopsis("Synopsis");
    Optional<MovieEntity> ofResult = Optional.of(movieEntity);
    when(movieRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
    MovieDto actualMovie = movieServiceImpl.getMovie(1);
    verify(movieRepository).findById(Mockito.<Integer>any());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    assertEquals("1969-12-31", simpleDateFormat.format(actualMovie.getReleaseDate()));
    assertEquals("Genre", actualMovie.getGenre());
    assertEquals("Movie Name", actualMovie.getMovieName());
    assertEquals("Synopsis", actualMovie.getSynopsis());
    assertEquals("en", actualMovie.getLanguage());
    assertEquals("https://example.org/example", actualMovie.getPosterUrl());
    assertEquals(1, actualMovie.getMovieId().intValue());
    assertEquals(1, actualMovie.getRunningTime().intValue());
  }

  @Test
  void testGetMovieFailure() throws GlobalException {
    Optional<MovieEntity> emptyResult = Optional.empty();
    when(movieRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
    assertThrows(GlobalException.class, () -> movieServiceImpl.getMovie(1));
    verify(movieRepository).findById(Mockito.<Integer>any());
  }

  @Test
  void testUpdateMovie() throws GlobalException {
    MovieEntity movieEntity = new MovieEntity();
    movieEntity.setGenre("Genre");
    movieEntity.setLanguage("en");
    movieEntity.setMovieId(1);
    movieEntity.setMovieName("Movie Name");
    movieEntity.setPosterUrl("https://example.org/example");
    movieEntity.setReleaseDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    movieEntity.setRunningTime(1);
    movieEntity.setSynopsis("Synopsis");
    Optional<MovieEntity> ofResult = Optional.of(movieEntity);

    MovieEntity movieEntity2 = new MovieEntity();
    movieEntity2.setGenre("Genre");
    movieEntity2.setLanguage("en");
    movieEntity2.setMovieId(1);
    movieEntity2.setMovieName("Movie Name");
    movieEntity2.setPosterUrl("https://example.org/example");
    movieEntity2.setReleaseDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    movieEntity2.setRunningTime(1);
    movieEntity2.setSynopsis("Synopsis");
    when(movieRepository.save(Mockito.<MovieEntity>any())).thenReturn(movieEntity2);
    when(movieRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
    movieServiceImpl.updateMovie(1, new MovieDto());
    verify(movieRepository, atLeast(1)).findById(Mockito.<Integer>any());
    verify(movieRepository).save(Mockito.<MovieEntity>any());
  }

  @Test
  void testUpdateMovieFailure() throws GlobalException {
    Optional<MovieEntity> emptyResult = Optional.empty();
    when(movieRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
    assertThrows(GlobalException.class, () -> movieServiceImpl.updateMovie(1, new MovieDto()));
    verify(movieRepository).findById(Mockito.<Integer>any());
  }

  @Test
  void testUpdateMovie2() throws GlobalException {
    MovieEntity movieEntity = new MovieEntity();
    movieEntity.setGenre("Genre");
    movieEntity.setLanguage("en");
    movieEntity.setMovieId(1);
    movieEntity.setMovieName("Movie Name");
    movieEntity.setPosterUrl("https://example.org/example");
    movieEntity.setReleaseDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    movieEntity.setRunningTime(1);
    movieEntity.setSynopsis("Synopsis");
    Optional<MovieEntity> ofResult = Optional.of(movieEntity);

    MovieEntity movieEntity2 = new MovieEntity();
    movieEntity2.setGenre("Genre");
    movieEntity2.setLanguage("en");
    movieEntity2.setMovieId(1);
    movieEntity2.setMovieName("Movie Name");
    movieEntity2.setPosterUrl("https://example.org/example");
    movieEntity2.setReleaseDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    movieEntity2.setRunningTime(1);
    movieEntity2.setSynopsis("Synopsis");
    when(movieRepository.save(Mockito.<MovieEntity>any())).thenReturn(movieEntity2);
    when(movieRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
    MovieDto movieDto = mock(MovieDto.class);
    when(movieDto.getRunningTime()).thenReturn(1);
    when(movieDto.getGenre()).thenReturn("Genre");
    when(movieDto.getLanguage()).thenReturn("en");
    when(movieDto.getMovieName()).thenReturn("Movie Name");
    when(movieDto.getPosterUrl()).thenReturn("https://example.org/example");
    when(movieDto.getSynopsis()).thenReturn("Synopsis");
    when(movieDto.getReleaseDate())
            .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    movieServiceImpl.updateMovie(1, movieDto);
    verify(movieDto).getGenre();
    verify(movieDto).getLanguage();
    verify(movieDto).getMovieName();
    verify(movieDto).getPosterUrl();
    verify(movieDto).getReleaseDate();
    verify(movieDto).getRunningTime();
    verify(movieDto).getSynopsis();
    verify(movieRepository, atLeast(1)).findById(Mockito.<Integer>any());
    verify(movieRepository).save(Mockito.<MovieEntity>any());
  }

  @Test
  void testDeleteMovie() throws GlobalException {
    MovieEntity movieEntity = new MovieEntity();
    movieEntity.setGenre("Genre");
    movieEntity.setLanguage("en");
    movieEntity.setMovieId(1);
    movieEntity.setMovieName("Movie Name");
    movieEntity.setPosterUrl("https://example.org/example");
    movieEntity.setReleaseDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
    movieEntity.setRunningTime(1);
    movieEntity.setSynopsis("Synopsis");
    Optional<MovieEntity> ofResult = Optional.of(movieEntity);
    doNothing().when(movieRepository).deleteById(Mockito.<Integer>any());
    when(movieRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
    movieServiceImpl.deleteMovie(1);
    verify(movieRepository).deleteById(Mockito.<Integer>any());
    verify(movieRepository).findById(Mockito.<Integer>any());
    List<MovieDto> currentMovies = movieServiceImpl.getCurrentMovies();
    assertEquals(currentMovies, movieServiceImpl.getMovies());
    assertEquals(currentMovies, movieServiceImpl.getUpcomingMovies());
  }

  @Test
  void testDeleteMovieFailure() throws GlobalException {
    Optional<MovieEntity> emptyResult = Optional.empty();
    when(movieRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
    assertThrows(GlobalException.class, () -> movieServiceImpl.deleteMovie(1));
    verify(movieRepository).findById(Mockito.<Integer>any());
  }
}
