package com.scrumandcoke.movietheaterclub.service;

import com.scrumandcoke.movietheaterclub.dto.UserDto;
import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.model.User;

import java.util.List;

public interface UserService {
    void addUser(UserDto userDto) throws GlobalException;
    UserDto getUserByEmail(String email) throws GlobalException;
    List<UserDto> getUsers() throws GlobalException;
    void updateUser(UserDto userDto) throws GlobalException;
    void deleteUser(Integer id) throws GlobalException;
}
