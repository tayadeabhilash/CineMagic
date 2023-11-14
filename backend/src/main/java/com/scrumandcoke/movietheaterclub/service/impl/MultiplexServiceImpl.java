package com.scrumandcoke.movietheaterclub.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrumandcoke.movietheaterclub.dto.MultiplexDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.model.Multiplex;
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
		try {
			Multiplex multiplex = new Multiplex();
			multiplex.setName(multiplexDto.getName());
			multiplex.setLocation(multiplexDto.getLocation());
			multiplex.setTheaterScreenCount(multiplexDto.getTheaterScreenCount());
			multiplexRepository.save(multiplex);
		}
		catch(Exception exception){
			logger.error("Error adding user");
			throw new GlobalException(exception.getMessage(),exception);
		}
	}

	@Override
	public List<MultiplexDto> getAllMultiplex() throws GlobalException {
	    try {
	        List<Multiplex> multiplexes = multiplexRepository.findAll();
	        List<MultiplexDto> allMultiplex = new ArrayList<>();
	        for (Multiplex multiplex : multiplexes) {
	            MultiplexDto dto = new MultiplexDto(multiplex.getId(), multiplex.getName(), multiplex.getLocation(), multiplex.getTheaterScreenCount());
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
	    try {
	        Integer multiplexId = multiplexDto.getId();
	        Multiplex multiplex = multiplexRepository.findById(multiplexId)
	                                  .orElseThrow(() -> new EntityNotFoundException("Multiplex not found with id: " + multiplexId));
	        multiplex.setName(multiplexDto.getName());
	        multiplex.setLocation(multiplexDto.getLocation());
	        multiplex.setTheaterScreenCount(multiplexDto.getTheaterScreenCount());
	        multiplexRepository.save(multiplex);
	    } catch (Exception e) {
	        logger.error("Error updating multiplex with id: " + multiplexDto.getId(), e);
	        throw new GlobalException("Error updating multiplex: " + e.getMessage(), e);
	    }
	}
	
	@Override
	public void deleteMultiplex(Integer id) throws GlobalException {
		try {
			multiplexRepository.deleteById(id);
		} catch(Exception exception) {
			logger.error("Error deleting multiplex: {}", id);
            throw new GlobalException(exception.getMessage(), exception);
		}
	}

}
