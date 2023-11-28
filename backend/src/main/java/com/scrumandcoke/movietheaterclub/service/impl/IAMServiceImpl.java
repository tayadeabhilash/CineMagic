package com.scrumandcoke.movietheaterclub.service.impl;

import com.scrumandcoke.movietheaterclub.dto.CreateSessionRequest;
import com.scrumandcoke.movietheaterclub.dto.CreateUserRequest;
import com.scrumandcoke.movietheaterclub.dto.UserDto;
import com.scrumandcoke.movietheaterclub.service.IAMService;
import com.scrumandcoke.movietheaterclub.service.SessionService;
import com.scrumandcoke.movietheaterclub.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class IAMServiceImpl implements IAMService {

    private static final Duration DEFAULT_SESSION_DURATION = Duration.ofDays(7);

    @Autowired
    UserService userService;

    @Autowired
    SessionService sessionService;

    public UserDto signUp(@NonNull CreateUserRequest createUserRequest) {
        UserDto userDto = userService.createUser(createUserRequest);

        sessionService.createSession(CreateSessionRequest.builder()
                .userId(userDto.getUserId())
                .sessionDuration(DEFAULT_SESSION_DURATION)
                .build());

        return userDto;
    }


    public UserDto signIn(@NonNull String email, @NonNull String password) {

        UserDto userDto = userService.validateLoginCredentials(email, password);

        sessionService.createSession(CreateSessionRequest.builder()
                .userId(userDto.getUserId())
                .sessionDuration(DEFAULT_SESSION_DURATION)
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
