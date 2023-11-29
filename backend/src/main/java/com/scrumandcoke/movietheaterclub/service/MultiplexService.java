package com.scrumandcoke.movietheaterclub.service;

import java.util.List;

import com.scrumandcoke.movietheaterclub.dto.MultiplexDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.model.enums.Location;

public interface MultiplexService {
	void addMultiplex(MultiplexDto multiplexDto) throws GlobalException;
	List<MultiplexDto> getAllMultiplex() throws GlobalException;
	List<MultiplexDto> getAllMultiplexByLocation(Location location) throws GlobalException;
	void updateMultiplex(MultiplexDto multiplexDto) throws GlobalException;
	void deleteMultiplex(Integer id) throws GlobalException;
    MultiplexDto getMultiplex(Integer id) throws GlobalException;
}
