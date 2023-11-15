package com.scrumandcoke.movietheaterclub.service.impl;

import com.scrumandcoke.movietheaterclub.dto.ShowTimeDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.repository.ShowTimeRepository;
import com.scrumandcoke.movietheaterclub.service.ShowTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowTimeServiceImpl implements ShowTimeService {

    @Autowired
    ShowTimeRepository showTimeRepository;

    Logger logger = LoggerFactory.getLogger(ShowTimeService.class);


    @Override
    public void addShowTime(ShowTimeDto showTimeDto) throws GlobalException {
        try {
           showTimeRepository.save(ShowTimeDto.toEntity(showTimeDto));
        } catch (Exception exception) {
            logger.error("Error saving showtime: {}", showTimeDto.getId());
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public ShowTimeDto getShowTime(Integer id) throws GlobalException {
        try {
            return ShowTimeDto.fromEntity(showTimeRepository.findById(id).get());
        } catch (Exception exception) {
            logger.error("Error getting showtime: {}", id);
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public List<ShowTimeDto> getShowTimesByMovie(Integer id) throws GlobalException {
        try {
            return ShowTimeDto.fromEntityList(showTimeRepository.findByMovie_MovieId(id));
        } catch (Exception exception) {
            logger.error("Error getting showtime with movieId: {}", id);
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public List<ShowTimeDto> getShowTimesByTheaterScreenId(Integer id) throws GlobalException {
        try {
            return ShowTimeDto.fromEntityList(showTimeRepository.findByTheaterScreen_Id(id));
        } catch (Exception exception) {
            logger.error("Error getting showtime with theaterScreenId: {}", id);
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public void updateShowTime(ShowTimeDto showTimeDto) throws GlobalException {
        try {
            showTimeRepository.save(ShowTimeDto.toEntity(showTimeDto));
        } catch (Exception exception) {
            logger.error("Error saving showtime: {}", showTimeDto.getId());
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
}
