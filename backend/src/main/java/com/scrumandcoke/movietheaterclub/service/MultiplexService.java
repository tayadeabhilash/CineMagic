package com.scrumandcoke.movietheaterclub.service;

import java.util.List;

import com.scrumandcoke.movietheaterclub.dto.MultiplexDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;

public interface MultiplexService {
	void addMultiplex(MultiplexDto multiplexDto) throws GlobalException;
	List<MultiplexDto> getAllMultiplex() throws GlobalException;
	void updateMultiplex(MultiplexDto multiplexDto) throws GlobalException;
	void deleteMultiplex(Integer id) throws GlobalException;
}
