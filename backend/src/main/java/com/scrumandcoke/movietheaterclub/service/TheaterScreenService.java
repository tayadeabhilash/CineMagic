package com.scrumandcoke.movietheaterclub.service;

import java.util.List;

import com.scrumandcoke.movietheaterclub.dto.TheaterScreenDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;

public interface TheaterScreenService {
    void createTheaterScreen(TheaterScreenDto theaterScreenDto)throws GlobalException;
    TheaterScreenDto getTheaterScreenById(int id)throws GlobalException;
    List<TheaterScreenDto> getAllTheaterScreens()throws GlobalException;
    TheaterScreenDto updateTheaterScreen(int id, TheaterScreenDto theaterScreenDto) throws GlobalException;
    void deleteTheaterScreen(int id)throws GlobalException;
}
