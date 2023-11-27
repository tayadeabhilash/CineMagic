package com.scrumandcoke.movietheaterclub.service.impl;

import com.scrumandcoke.movietheaterclub.dto.CreateSessionRequest;
import com.scrumandcoke.movietheaterclub.dto.CreateUserRequest;
import com.scrumandcoke.movietheaterclub.dto.UserDto;
import com.scrumandcoke.movietheaterclub.service.IAMService;
import com.scrumandcoke.movietheaterclub.service.SessionService;
import com.scrumandcoke.movietheaterclub.service.UserService;
import lombok.NonNull;

import java.time.Duration;

public class IAMServiceImpl implements IAMService {
    UserService userService;
    SessionService sessionService;

    public UserDto signUp(@NonNull CreateUserRequest createUserRequest) {
        UserDto userDto = userService.createUser(createUserRequest);

        sessionService.createSession(CreateSessionRequest.builder()
                .userId(userDto.getUserId())
                .sessionDuration(Duration.ofDays(7))
                .build());

        return userDto;
    }


    public UserDto signIn() {

        UserDto userDto = userService.validateLoginCredentials(null, null);

        sessionService.createSession(CreateSessionRequest.builder()
                .userId(userDto.getUserId())
                .sessionDuration(Duration.ofDays(7))
                .build());

        return userDto;
    }

    @Override
    public UserDto isAuthenticated(@NonNull String sessionId) {
        return null;
    }

    @Override
    public void logout() {
        sessionService.invalidateSession(1);
    }
}
