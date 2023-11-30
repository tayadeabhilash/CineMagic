package com.scrumandcoke.movietheaterclub.service.impl;

import com.scrumandcoke.movietheaterclub.dto.MovieWithShowtimesDto;
import com.scrumandcoke.movietheaterclub.dto.ShowTimeDto;
import com.scrumandcoke.movietheaterclub.entity.MovieEntity;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.mapper.ShowTimeMapper;
import com.scrumandcoke.movietheaterclub.entity.ShowTimeEntity;
import com.scrumandcoke.movietheaterclub.enums.Location;
import com.scrumandcoke.movietheaterclub.repository.MovieRepository;
import com.scrumandcoke.movietheaterclub.repository.ShowTimeRepository;
import com.scrumandcoke.movietheaterclub.service.ShowTimeService;
import com.scrumandcoke.movietheaterclub.service.TheaterScreenService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShowTimeServiceImpl implements ShowTimeService {

    @Autowired
    ShowTimeRepository showTimeRepository;

    @Autowired
    TheaterScreenService theaterScreenService;

    @Autowired
    MovieRepository movieRepository;

    @Value("${showtime.discount.percent:10}")
    private double discount;

    @Autowired
    ShowTimeMapper showTimeMapper;

    Logger logger = LoggerFactory.getLogger(ShowTimeService.class);


    @Override
    public void addShowTime(ShowTimeDto showTimeDto) throws GlobalException {
        try {
            showTimeDto.setAvailableSeats(theaterScreenService.getTheaterScreenById(
                    showTimeDto.getTheaterScreenId()).getSeatingCapacity());
            showTimeDto.setPrice(this.getDiscountedPrice(showTimeDto.getPrice(), showTimeDto.getTime()));
            ShowTimeEntity showTimeEntity = showTimeMapper.toEntity(showTimeDto);
            showTimeRepository.save(showTimeEntity);
        } catch (Exception exception) {
            logger.error("Error saving showtime: {}", showTimeDto.getId());
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public ShowTimeDto getShowTime(Integer id) throws GlobalException {
        try {
            return showTimeMapper.toDto(showTimeRepository.findById(id).get());
        } catch (Exception exception) {
            logger.error("Error getting showtime: {}", id);
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public List<ShowTimeDto> getShowTimesByMovie(Integer id) throws GlobalException {
        try {
            return showTimeMapper.toDto(showTimeRepository.findByMovie_MovieId(id));
        } catch (Exception exception) {
            logger.error("Error getting showtime with movieId: {}", id);
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public List<ShowTimeDto> getShowTimesByTheaterScreenId(Integer id) throws GlobalException {
        try {
            return showTimeMapper.toDto(showTimeRepository.findByTheaterScreen_Id(id));
        } catch (Exception exception) {
            logger.error("Error getting showtime with theaterScreenId: {}", id);
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public List<ShowTimeDto> getShowTimesByLocation(Location location) throws GlobalException {
        try {
            return showTimeMapper.toDto(showTimeRepository.findByTheaterScreen_MultiplexEntity_Location(location));
        } catch (Exception exception) {
            logger.error("Error getting showtime with location: {}", location);
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public List<ShowTimeDto> getShowTimesByTheaterScreenIdAndMultiplexId(Integer theaterScreenId, Integer multiplexId) throws GlobalException {
        try {
            return showTimeMapper.toDto(showTimeRepository
                    .findByTheaterScreen_IdAndTheaterScreen_MultiplexEntity_Id(theaterScreenId, multiplexId));
        } catch (Exception exception) {
            logger.error("Error getting showtime with theaterScreenId: {} and multiplexId: {}", theaterScreenId,
                    multiplexId);
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public void updateShowTime(ShowTimeDto showTimeDto) throws GlobalException {
        try {
            ShowTimeEntity entity = showTimeRepository.findById(showTimeDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Showtime not found with id: " + showTimeDto.getId()));
            showTimeMapper.updateCustomerFromDto(showTimeDto, entity);
            showTimeRepository.save(entity);
        } catch (Exception exception) {
            logger.error("Error updating showtime: {}", showTimeDto.getId());
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public void deleteShowTime(Integer id) throws GlobalException {
        try {
            showTimeRepository.deleteById(id);
        } catch (Exception exception) {
            logger.error("Error deleting showtime: {}", id);
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public Double getDiscountedPrice(Double price, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if (dayOfWeek == Calendar.TUESDAY || (hourOfDay >= 8 && hourOfDay < 18)) {
            double discountFactor = 1.0 - (discount / 100.0);
            price = price * discountFactor;
        }
        return price;
    }

    @Override
    public List<MovieWithShowtimesDto> getMoviesWithUpcomingShowtimes(int daysAhead) {
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DATE, daysAhead);
        Date endDate = c.getTime();

        List<ShowTimeEntity> showTimeEntities = showTimeRepository.findByTimeBetween(today, endDate);
        List<ShowTimeDto> showTimeDtos = showTimeMapper.toDto(showTimeEntities);

        // Grouping showtimes by movieId
        Map<Integer, List<ShowTimeDto>> groupedShowTimes = showTimeDtos.stream()
                .collect(Collectors.groupingBy(ShowTimeDto::getMovieId));

        // Creating a list of MovieWithShowtimesDto
        List<MovieWithShowtimesDto> moviesWithShowtimes = new ArrayList<>();
        for (Map.Entry<Integer, List<ShowTimeDto>> entry : groupedShowTimes.entrySet()) {
            Integer movieId = entry.getKey();
            List<ShowTimeDto> showTimes = entry.getValue();

            MovieWithShowtimesDto movieWithShowtimesDto = new MovieWithShowtimesDto();
            movieWithShowtimesDto.setMovieId(movieId);
            movieWithShowtimesDto.setUpcomingShowtimes(showTimes);

            // Assuming you have a method to fetch movie details by movieId
            MovieEntity movie = movieRepository.findById(movieId).orElse(null);
            if (movie != null) {
                movieWithShowtimesDto.setMovieTitle(movie.getMovieName());
                // Set other movie details as needed
            }

            moviesWithShowtimes.add(movieWithShowtimesDto);
        }

        return moviesWithShowtimes;
    }

}
