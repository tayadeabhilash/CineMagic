package com.scrumandcoke.movietheaterclub.service;

import com.scrumandcoke.movietheaterclub.dto.CreateUserRequest;
import com.scrumandcoke.movietheaterclub.dto.UserDto;
import lombok.NonNull;

public interface IAMService {

    UserDto signUp(CreateUserRequest createUserRequest);

    UserDto signIn(@NonNull String email, @NonNull String password);

    UserDto isAuthenticated(String sessionId);

    void logout();
}
