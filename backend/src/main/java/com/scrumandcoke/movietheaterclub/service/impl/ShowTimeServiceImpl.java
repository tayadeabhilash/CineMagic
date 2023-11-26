package com.scrumandcoke.movietheaterclub.service.impl;

import com.scrumandcoke.movietheaterclub.dto.ShowTimeDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.mapper.ShowTimeMapper;
import com.scrumandcoke.movietheaterclub.model.MultiplexEntity;
import com.scrumandcoke.movietheaterclub.model.ShowTimeEntity;
import com.scrumandcoke.movietheaterclub.model.enums.Location;
import com.scrumandcoke.movietheaterclub.repository.ShowTimeRepository;
import com.scrumandcoke.movietheaterclub.service.MovieService;
import com.scrumandcoke.movietheaterclub.service.ShowTimeService;
import com.scrumandcoke.movietheaterclub.service.TheaterScreenService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ShowTimeServiceImpl implements ShowTimeService {

    @Autowired
    ShowTimeRepository showTimeRepository;

    @Autowired
    TheaterScreenService theaterScreenService;

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
            showTimeRepository.findById(showTimeDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Showtime not found with id: " + showTimeDto.getId()));
            showTimeRepository.save(showTimeMapper.toEntity(showTimeDto));
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
}
