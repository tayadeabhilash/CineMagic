package com.scrumandcoke.movietheaterclub.service;

import com.scrumandcoke.movietheaterclub.dto.CreateUserRequest;
import com.scrumandcoke.movietheaterclub.dto.UserDto;
import lombok.NonNull;

import java.util.List;

public interface UserService {
    UserDto createUser(@NonNull CreateUserRequest userDto);

    UserDto validateLoginCredentials(@NonNull String email, @NonNull String password);

    UserDto getUserByEmail(@NonNull String email);

    UserDto getUserByUserId(@NonNull String userId);

    List<UserDto> getUsers();

    void updateUser(@NonNull UserDto userDto);

    void deleteUser(@NonNull Integer id);
}
