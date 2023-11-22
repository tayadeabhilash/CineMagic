package com.scrumandcoke.movietheaterclub.service;

import com.scrumandcoke.movietheaterclub.dto.CreateUserRequest;
import com.scrumandcoke.movietheaterclub.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(CreateUserRequest userDto);

    UserDto getUserByEmail(String email);

    List<UserDto> getUsers();

    void updateUser(UserDto userDto);

    void deleteUser(Integer id);
}
