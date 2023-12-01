package com.scrumandcoke.movietheaterclub.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.scrumandcoke.movietheaterclub.enums.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrumandcoke.movietheaterclub.dto.LocationDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.entity.LocationEntity;
import com.scrumandcoke.movietheaterclub.repository.MultiplexRepository;
import com.scrumandcoke.movietheaterclub.service.MultiplexService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MultiplexServiceImpl implements MultiplexService {

    @Autowired
    MultiplexRepository multiplexRepository;

    Logger logger = LoggerFactory.getLogger(MultiplexServiceImpl.class);


    @Override
    public void addMultiplex(LocationDto locationDto) throws GlobalException {
        try {
            LocationEntity locationEntity = new LocationEntity();
            locationEntity.setName(locationDto.getName());
            locationEntity.setLocation(locationDto.getLocation());
            locationEntity.setTheaterScreenCount(locationDto.getTheaterScreenCount());
            multiplexRepository.save(locationEntity);
        } catch (Exception exception) {
            logger.error("Error adding multiplex");
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public List<LocationDto> getAllMultiplex() throws GlobalException {
        try {
            List<LocationEntity> multiplexEntities = multiplexRepository.findAll();
            List<LocationDto> allMultiplex = new ArrayList<>();
            for (LocationEntity locationEntity : multiplexEntities) {
                LocationDto dto = new LocationDto(locationEntity.getId(), locationEntity.getName(), locationEntity.getLocation(), locationEntity.getTheaterScreenCount());
                allMultiplex.add(dto);
            }
            return allMultiplex;
        } catch (Exception exception) {
            logger.error("Error retrieving all multiplexes", exception);
            throw new GlobalException("Error retrieving multiplex data: " + exception.getMessage(), exception);
        }
    }

    @Override
    public List<LocationDto> getAllMultiplexByLocation(Location location) throws GlobalException {
        try {
            List<LocationEntity> multiplexEntities = multiplexRepository.findByLocation(location);
            List<LocationDto> allMultiplex = new ArrayList<>();
            for (LocationEntity locationEntity : multiplexEntities) {
                LocationDto dto = new LocationDto(locationEntity.getId(), locationEntity.getName(),
                        locationEntity.getLocation(), locationEntity.getTheaterScreenCount());
                allMultiplex.add(dto);
            }
            return allMultiplex;
        } catch (Exception exception) {
            logger.error("Error retrieving all multiplexes", exception);
            throw new GlobalException("Error retrieving multiplex data: " + exception.getMessage(), exception);
        }
    }

    @Override
    public void updateMultiplex(LocationDto locationDto) throws GlobalException {
        try {
            Integer multiplexId = locationDto.getId();
            LocationEntity locationEntity = multiplexRepository.findById(multiplexId)
                    .orElseThrow(() -> new EntityNotFoundException("Multiplex not found with id: " + multiplexId));
            locationEntity.setName(locationDto.getName());
            locationEntity.setLocation(locationDto.getLocation());
            locationEntity.setTheaterScreenCount(locationDto.getTheaterScreenCount());
            multiplexRepository.save(locationEntity);
        } catch (Exception e) {
            logger.error("Error updating multiplex with id: " + locationDto.getId(), e);
            throw new GlobalException("Error updating multiplex: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteMultiplex(Integer id) throws GlobalException {
        try {
            multiplexRepository.deleteById(id);
        } catch (Exception exception) {
            logger.error("Error deleting multiplex: {}", id);
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public LocationDto getMultiplex(Integer id) throws GlobalException {
        try {
            LocationEntity locationEntity = multiplexRepository.findById(id).get();
            LocationDto dto = new LocationDto(locationEntity.getId(), locationEntity.getName(), locationEntity.getLocation(), locationEntity.getTheaterScreenCount());
            return dto;
        } catch (Exception exception) {
            logger.error("Error retrieving multiplex {}", id);
            throw new GlobalException("Error retrieving multiplex data: " + exception.getMessage(), exception);
        }
    }

}
