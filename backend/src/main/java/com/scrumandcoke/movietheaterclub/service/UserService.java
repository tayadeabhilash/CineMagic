package com.scrumandcoke.movietheaterclub.service;

import com.scrumandcoke.movietheaterclub.dto.CreateUserRequest;
import com.scrumandcoke.movietheaterclub.dto.UserDto;
import com.scrumandcoke.movietheaterclub.enums.MemberType;
import com.scrumandcoke.movietheaterclub.enums.UserType;
import lombok.NonNull;

import java.util.List;

public interface UserService {
    UserDto createUser(@NonNull CreateUserRequest userDto, @NonNull UserType userType);

    UserDto createUser(@NonNull CreateUserRequest createUserRequest);

    UserDto validateLoginCredentials(@NonNull String email, @NonNull String password);


    UserDto updateMemberType(@NonNull String userId, @NonNull MemberType newMemberType);

    UserDto getUserByEmail(@NonNull String email);

    UserDto getUserByUserId(@NonNull String userId);

    List<UserDto> getUsers();

    void updateUser(@NonNull UserDto userDto);

    void deleteUser(@NonNull Integer id);
}
