package com.scrumandcoke.movietheaterclub.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.scrumandcoke.movietheaterclub.enums.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrumandcoke.movietheaterclub.dto.MultiplexDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.entity.MultiplexEntity;
import com.scrumandcoke.movietheaterclub.repository.MultiplexRepository;
import com.scrumandcoke.movietheaterclub.service.MultiplexService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MultiplexServiceImpl implements MultiplexService {

    @Autowired
    MultiplexRepository multiplexRepository;

    Logger logger = LoggerFactory.getLogger(MultiplexServiceImpl.class);


    @Override
    public void addMultiplex(MultiplexDto multiplexDto) throws GlobalException {
        if (multiplexDto == null) {
            throw new GlobalException("Multiplex data cannot be null");
        }
        if (multiplexDto.getName() == null || multiplexDto.getName().trim().isEmpty()) {
            throw new GlobalException("Multiplex name cannot be null or empty");
        }
        if (multiplexDto.getLocation() == null) {
            throw new GlobalException("Location cannot be null");
        }
        if (multiplexDto.getTheaterScreenCount() < 1) {
            throw new GlobalException("Theater screen count must be at least 1");
        }
        if (multiplexRepository.existsByName(multiplexDto.getName())) {
            throw new GlobalException("A multiplex with the same name already exists");
        }

        try {
            MultiplexEntity multiplexEntity = new MultiplexEntity();
            multiplexEntity.setName(multiplexDto.getName());
            multiplexEntity.setLocation(multiplexDto.getLocation());
            multiplexEntity.setTheaterScreenCount(multiplexDto.getTheaterScreenCount());
            multiplexRepository.save(multiplexEntity);
        } catch (Exception exception) {
            logger.error("Error adding multiplex");
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public List<MultiplexDto> getAllMultiplex() throws GlobalException {
        try {
            List<MultiplexEntity> multiplexEntities = multiplexRepository.findAll();
            List<MultiplexDto> allMultiplex = new ArrayList<>();
            for (MultiplexEntity multiplexEntity : multiplexEntities) {
                MultiplexDto dto = new MultiplexDto(multiplexEntity.getId(), multiplexEntity.getName(), multiplexEntity.getLocation(), multiplexEntity.getTheaterScreenCount());
                allMultiplex.add(dto);
            }
            return allMultiplex;
        } catch (Exception exception) {
            logger.error("Error retrieving all multiplexes", exception);
            throw new GlobalException("Error retrieving multiplex data: " + exception.getMessage(), exception);
        }
    }

    @Override
    public List<MultiplexDto> getAllMultiplexByLocation(Location location) throws GlobalException {
        try {
            List<MultiplexEntity> multiplexEntities = multiplexRepository.findByLocation(location);
            List<MultiplexDto> allMultiplex = new ArrayList<>();
            for (MultiplexEntity multiplexEntity : multiplexEntities) {
                MultiplexDto dto = new MultiplexDto(multiplexEntity.getId(), multiplexEntity.getName(),
                        multiplexEntity.getLocation(), multiplexEntity.getTheaterScreenCount());
                allMultiplex.add(dto);
            }
            return allMultiplex;
        } catch (Exception exception) {
            logger.error("Error retrieving all multiplexes", exception);
            throw new GlobalException("Error retrieving multiplex data: " + exception.getMessage(), exception);
        }
    }

    @Override
    public void updateMultiplex(MultiplexDto multiplexDto) throws GlobalException {

        if (multiplexDto == null) {
            throw new GlobalException("Multiplex data cannot be null");
        }
        if (multiplexDto.getId() == 0) {
            throw new GlobalException("Invalid multiplex ID");
        }
        if (multiplexDto.getName() == null || multiplexDto.getName().trim().isEmpty()) {
            throw new GlobalException("Multiplex name cannot be null or empty");
        }
        if (multiplexDto.getLocation() == null) {
            throw new GlobalException("Location cannot be null");
        }
        if (multiplexDto.getTheaterScreenCount() < 1) {
            throw new GlobalException("Theater screen count must be at least 1");
        }
        // Check for duplicate name, excluding the current multiplex
        boolean nameExists = multiplexRepository.existsByNameAndIdNot(multiplexDto.getName(), multiplexDto.getId());
        if (nameExists) {
            throw new GlobalException("Another multiplex with this name already exists");
        }

        try {
            Integer multiplexId = multiplexDto.getId();
            MultiplexEntity multiplexEntity = multiplexRepository.findById(multiplexId)
                    .orElseThrow(() -> new EntityNotFoundException("Multiplex not found with id: " + multiplexId));
            multiplexEntity.setName(multiplexDto.getName());
            multiplexEntity.setLocation(multiplexDto.getLocation());
            multiplexEntity.setTheaterScreenCount(multiplexDto.getTheaterScreenCount());
            multiplexRepository.save(multiplexEntity);
        } catch (Exception e) {
            logger.error("Error updating multiplex with id: " + multiplexDto.getId(), e);
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
    public MultiplexDto getMultiplex(Integer id) throws GlobalException {
        try {
            MultiplexEntity multiplexEntity = multiplexRepository.findById(id).get();
            MultiplexDto dto = new MultiplexDto(multiplexEntity.getId(), multiplexEntity.getName(), multiplexEntity.getLocation(), multiplexEntity.getTheaterScreenCount());
            return dto;
        } catch (Exception exception) {
            logger.error("Error retrieving multiplex {}", id);
            throw new GlobalException("Error retrieving multiplex data: " + exception.getMessage(), exception);
        }
    }

}
