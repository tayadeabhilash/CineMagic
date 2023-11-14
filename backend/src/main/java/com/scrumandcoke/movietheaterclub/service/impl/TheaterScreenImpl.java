package com.scrumandcoke.movietheaterclub.service.impl;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrumandcoke.movietheaterclub.dto.MultiplexDto;
import com.scrumandcoke.movietheaterclub.dto.TheaterScreenDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.model.Multiplex;
import com.scrumandcoke.movietheaterclub.model.TheaterScreen;
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
            TheaterScreen theaterScreen = new TheaterScreen();
            theaterScreen.setName(theaterScreenDto.getName());
            theaterScreen.setSeatingCapacity(theaterScreenDto.getSeatingCapacity());

            Multiplex multiplex = multiplexRepository.findById(theaterScreenDto.getMultiplex().getId())
                    .orElseThrow(() -> new RuntimeException("Multiplex not found"));
            theaterScreen.setMultiplex(multiplex);

            theaterScreenRepository.save(theaterScreen);
        } catch (Exception exception) {
            logger.error("Error adding theater screen", exception);
            throw new GlobalException("Error adding theater screen: " + exception.getMessage(), exception);
        }
    }

    @Override
    public TheaterScreenDto getTheaterScreenById(int id) throws GlobalException {
        try {
            Optional<TheaterScreen> theaterScreenOptional = theaterScreenRepository.findById(id);


            TheaterScreen theaterScreen = theaterScreenOptional.get();
            TheaterScreenDto theaterScreenDto = new TheaterScreenDto();
            theaterScreenDto.setId(theaterScreen.getId());
            theaterScreenDto.setName(theaterScreen.getName());
            theaterScreenDto.setSeatingCapacity(theaterScreen.getSeatingCapacity());

            MultiplexDto multiplexDto = new MultiplexDto();
            multiplexDto.setId(theaterScreen.getMultiplex().getId());
            multiplexDto.setName(theaterScreen.getMultiplex().getName());
            multiplexDto.setLocation(theaterScreen.getMultiplex().getLocation());
            multiplexDto.setTheaterScreenCount(theaterScreen.getSeatingCapacity());

            theaterScreenDto.setMultiplex(multiplexDto);

            return theaterScreenDto;
        } catch (Exception e) {
            logger.error("Error retrieving theater screen with ID: " + id, e);
            throw new GlobalException("Error retrieving theater screen: " + e.getMessage(), e);
        }
    }

    @Override
    public TheaterScreenDto updateTheaterScreen(int id, TheaterScreenDto theaterScreenDto) throws GlobalException {
        try {
            Optional<TheaterScreen> theaterScreenOptional = theaterScreenRepository.findById(id);

            TheaterScreen theaterScreen = theaterScreenOptional.get();
            theaterScreen.setName(theaterScreenDto.getName());
            theaterScreen.setSeatingCapacity(theaterScreenDto.getSeatingCapacity());

            if (theaterScreenDto.getMultiplex() != null && theaterScreenDto.getMultiplex().getId() != 0) {
                Multiplex multiplex = multiplexRepository.findById(theaterScreenDto.getMultiplex().getId())
                        .orElseThrow(() -> new GlobalException("Multiplex not found"));
                theaterScreen.setMultiplex(multiplex);
            }

            TheaterScreen updatedTheaterScreen = theaterScreenRepository.save(theaterScreen);

            TheaterScreenDto updatedDto = new TheaterScreenDto();
            updatedDto.setId(updatedTheaterScreen.getId());
            updatedDto.setName(updatedTheaterScreen.getName());
            updatedDto.setSeatingCapacity(updatedTheaterScreen.getSeatingCapacity());

            MultiplexDto multiplexDto = new MultiplexDto();
            multiplexDto.setId(updatedTheaterScreen.getMultiplex().getId());
            multiplexDto.setLocation(updatedTheaterScreen.getMultiplex().getLocation());
            multiplexDto.setName(updatedTheaterScreen.getMultiplex().getName());
            multiplexDto.setTheaterScreenCount(updatedTheaterScreen.getMultiplex().getTheaterScreenCount());
            
            updatedDto.setMultiplex(multiplexDto);

            return updatedDto;
        } catch (Exception e) {
            logger.error("Error updating theater screen with ID: " + id, e);
            throw new GlobalException("Error updating theater screen: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteTheaterScreen(int id) throws GlobalException {
        try {
            Optional<TheaterScreen> theaterScreenOptional = theaterScreenRepository.findById(id);

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
            List<TheaterScreen> theaterScreens = theaterScreenRepository.findAll();
            List<TheaterScreenDto> theaterScreenDtos = new ArrayList<>();

            for (TheaterScreen theaterScreen : theaterScreens) {
                TheaterScreenDto dto = new TheaterScreenDto();
                dto.setId(theaterScreen.getId());
                dto.setName(theaterScreen.getName());
                dto.setSeatingCapacity(theaterScreen.getSeatingCapacity());

                MultiplexDto multiplexDto = new MultiplexDto();
                multiplexDto.setId(theaterScreen.getMultiplex().getId());
                multiplexDto.setName(theaterScreen.getMultiplex().getName());
                multiplexDto.setLocation(theaterScreen.getMultiplex().getLocation());
                multiplexDto.setTheaterScreenCount(theaterScreen.getMultiplex().getTheaterScreenCount());
                dto.setMultiplex(multiplexDto);

                theaterScreenDtos.add(dto);
            }

            return theaterScreenDtos;
        } catch (Exception e) {
            logger.error("Error retrieving all theater screens", e);
            throw new GlobalException("Error retrieving theater screens", e);
        }
    }

}
