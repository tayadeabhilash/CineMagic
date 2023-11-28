package com.scrumandcoke.movietheaterclub.service.impl;

import com.scrumandcoke.movietheaterclub.dto.MovieDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.model.MovieEntity;
import com.scrumandcoke.movietheaterclub.repository.MovieRepository;
import com.scrumandcoke.movietheaterclub.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Override
    public void addMovie(MovieDto movieDto) throws GlobalException {
        try {
            validateMovieDto(movieDto); //new validation call
            ensureUniqueMovieTitle(movieDto.getMovieName()); //new validation call
            movieRepository.save(MovieDto.toEntity(movieDto));
        } catch (Exception exception) {
            logger.error("Error saving movie: {}", movieDto.getMovieName());
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public MovieDto getMovie(Integer movieId) throws GlobalException {
        try {
            MovieEntity movieEntity = movieRepository.findById(movieId).get();
            return MovieDto.fromEntity(movieEntity);
        } catch (Exception exception) {
            logger.error("Error getting movie: {}", movieId);
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public List<MovieDto> getMovies() throws GlobalException {
        try {
            List<MovieEntity> movieEntities = movieRepository.findAll();
            return MovieDto.fromEntityList(movieEntities);
        } catch (Exception exception) {
            logger.error("Error getting movies");
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public void updateMovie(MovieDto movieDto) throws GlobalException {
        try {
            validateMovieDto(movieDto); //new call
            MovieEntity movie = getValidatedMovieEntity(movieDto.getMovieId()); //new call
            MovieEntity movieEntity = movieRepository.findById(movieDto.getMovieId()).get();
            movieEntity.setMovieName(movieDto.getMovieName());
            movieEntity.setSynopsis(movieDto.getSynopsis());
            movieEntity.setRunningTime(movieDto.getRunningTime());
            movieRepository.save(movieEntity);
        } catch (Exception exception) {
            logger.error("Error updating movie: {}", movieDto.getMovieName());
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public void deleteMovie(Integer id) throws GlobalException {
        try {
            getValidatedMovieEntity(id); // Check if movie exists + new call
            movieRepository.deleteById(id);
        } catch (Exception exception) {
            logger.error("Error deleting movie: {}", id);
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

//edge cases + validations
    private void validateMovieDto(MovieDto movieDto) throws GlobalException {
        if (movieDto == null) {
            throw new GlobalException("Movie details cannot be null");
        }
    }

    private void ensureUniqueMovieTitle(String movieTitle) throws GlobalException {
        if (movieRepository.findByMovieName(movieTitle).isPresent()) {
            throw new GlobalException("Movie title already exists");
        }
    }

    private MovieEntity getValidatedMovieEntity(Integer movieId) throws GlobalException {
        return movieRepository.findById(movieId).orElseThrow(() ->
                new GlobalException("Movie with the specified ID does not exist"));
    }



}

