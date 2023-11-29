package com.scrumandcoke.movietheaterclub.service.impl;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrumandcoke.movietheaterclub.dto.TheaterScreenDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.entity.MultiplexEntity;
import com.scrumandcoke.movietheaterclub.entity.TheaterScreenEntity;
import com.scrumandcoke.movietheaterclub.repository.MultiplexRepository;
import com.scrumandcoke.movietheaterclub.repository.TheaterScreenRepository;
import com.scrumandcoke.movietheaterclub.service.TheaterScreenService;

import jakarta.transaction.Transactional;

@Service
public class TheaterScreenImpl implements TheaterScreenService {

    @Autowired
    TheaterScreenRepository theaterScreenRepository;

    @Autowired
    MultiplexRepository multiplexRepository;

    Logger logger = LoggerFactory.getLogger(MultiplexServiceImpl.class);

    @Override
    @Transactional
    public void createTheaterScreen(TheaterScreenDto theaterScreenDto) throws GlobalException {
        try {
            TheaterScreenEntity theaterScreenEntity = new TheaterScreenEntity();
            theaterScreenEntity.setName(theaterScreenDto.getName());
            theaterScreenEntity.setSeatingCapacity(theaterScreenDto.getSeatingCapacity());

            MultiplexEntity multiplexEntity = multiplexRepository.findById(theaterScreenDto.getMultiplexId())
                    .orElseThrow(() -> new GlobalException("Multiplex not found"));
            theaterScreenEntity.setMultiplexEntity(multiplexEntity);

            theaterScreenRepository.save(theaterScreenEntity);
        } catch (Exception exception) {
            logger.error("Error adding theater screen", exception);
            throw new GlobalException("Error adding theater screen: " + exception.getMessage(), exception);
        }
    }

    @Override
    public TheaterScreenDto getTheaterScreenById(int id) throws GlobalException {
        try {
            Optional<TheaterScreenEntity> theaterScreenOptional = theaterScreenRepository.findById(id);


            TheaterScreenEntity theaterScreenEntity = theaterScreenOptional.get();
            TheaterScreenDto theaterScreenDto = new TheaterScreenDto();
            theaterScreenDto.setId(theaterScreenEntity.getId());
            theaterScreenDto.setName(theaterScreenEntity.getName());
            theaterScreenDto.setSeatingCapacity(theaterScreenEntity.getSeatingCapacity());
            theaterScreenDto.setMultiplexId(theaterScreenEntity.getMultiplexEntity().getId());

            return theaterScreenDto;
        } catch (Exception e) {
            logger.error("Error retrieving theater screen with ID: " + id, e);
            throw new GlobalException("Error retrieving theater screen: " + e.getMessage(), e);
        }
    }

    @Override
    public TheaterScreenDto updateTheaterScreen(int id, TheaterScreenDto theaterScreenDto) throws GlobalException {
        try {
            Optional<TheaterScreenEntity> theaterScreenOptional = theaterScreenRepository.findById(id);

            TheaterScreenEntity theaterScreenEntity = theaterScreenOptional.get();
            theaterScreenEntity.setName(theaterScreenDto.getName());
            theaterScreenEntity.setSeatingCapacity(theaterScreenDto.getSeatingCapacity());

            if (theaterScreenDto.getMultiplexId() != null && theaterScreenDto.getMultiplexId() != 0) {
                MultiplexEntity multiplexEntity = multiplexRepository.findById(theaterScreenDto.getMultiplexId())
                        .orElseThrow(() -> new GlobalException("Multiplex not found"));
                theaterScreenEntity.setMultiplexEntity(multiplexEntity);
            }

            TheaterScreenEntity updatedTheaterScreenEntity = theaterScreenRepository.save(theaterScreenEntity);

            TheaterScreenDto updatedDto = new TheaterScreenDto();
            updatedDto.setId(updatedTheaterScreenEntity.getId());
            updatedDto.setName(updatedTheaterScreenEntity.getName());
            updatedDto.setSeatingCapacity(updatedTheaterScreenEntity.getSeatingCapacity());
            updatedDto.setMultiplexId(updatedTheaterScreenEntity.getMultiplexEntity().getId());

            return updatedDto;
        } catch (Exception e) {
            logger.error("Error updating theater screen with ID: " + id, e);
            throw new GlobalException("Error updating theater screen: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteTheaterScreen(int id) throws GlobalException {
        try {
            Optional<TheaterScreenEntity> theaterScreenOptional = theaterScreenRepository.findById(id);

            if (!theaterScreenOptional.isPresent()) {
                throw new GlobalException("Theater screen not found for ID: " + id);
            }

            theaterScreenRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting theater screen with ID: " + id, e);
            throw new GlobalException("Error deleting theater screen: " + e.getMessage(), e);
        }
    }

    @Override
    public List<TheaterScreenDto> getAllTheaterScreens() throws GlobalException {
        try {
            List<TheaterScreenEntity> theaterScreenEntities = theaterScreenRepository.findAll();
            List<TheaterScreenDto> theaterScreenDtos = getTheaterScreenDtos(theaterScreenEntities);

            return theaterScreenDtos;
        } catch (Exception e) {
            logger.error("Error retrieving all theater screens", e);
            throw new GlobalException("Error retrieving theater screens", e);
        }
    }

    private static List<TheaterScreenDto> getTheaterScreenDtos(List<TheaterScreenEntity> theaterScreenEntities) {
        List<TheaterScreenDto> theaterScreenDtos = new ArrayList<>();

        for (TheaterScreenEntity theaterScreenEntity : theaterScreenEntities) {
            TheaterScreenDto dto = new TheaterScreenDto();
            dto.setId(theaterScreenEntity.getId());
            dto.setName(theaterScreenEntity.getName());
            dto.setSeatingCapacity(theaterScreenEntity.getSeatingCapacity());
            dto.setMultiplexId(theaterScreenEntity.getMultiplexEntity().getId());
            theaterScreenDtos.add(dto);
        }
        return theaterScreenDtos;
    }

}
