package com.scrumandcoke.movietheaterclub.service;

import java.util.List;

import com.scrumandcoke.movietheaterclub.dto.LocationDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.enums.Location;

public interface MultiplexService {
	void addMultiplex(LocationDto locationDto) throws GlobalException;
	List<LocationDto> getAllMultiplex() throws GlobalException;
	List<LocationDto> getAllMultiplexByLocation(Location location) throws GlobalException;
	void updateMultiplex(Integer id, LocationDto locationDto) throws GlobalException;
	void deleteMultiplex(Integer id) throws GlobalException;
    LocationDto getMultiplex(Integer id) throws GlobalException;
}
