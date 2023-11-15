package com.scrumandcoke.movietheaterclub.service;

import com.scrumandcoke.movietheaterclub.dto.ShowTimeDto;
import com.scrumandcoke.movietheaterclub.dto.UserDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;

import java.util.List;

public interface ShowTimeService {
    void addShowTime(ShowTimeDto showTimeDto) throws GlobalException;
    ShowTimeDto getShowTime(Integer id) throws GlobalException;
    List<ShowTimeDto> getShowTimesByMovie(Integer movieId) throws GlobalException;
    List<ShowTimeDto> getShowTimesByTheaterScreenId(Integer theaterScreenId) throws GlobalException;
    void updateShowTime(ShowTimeDto showTimeDto) throws GlobalException;
    void deleteShowTime(Integer id) throws GlobalException;
}
