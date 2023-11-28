package com.scrumandcoke.movietheaterclub.service.impl;

import com.scrumandcoke.movietheaterclub.dto.*;
import com.scrumandcoke.movietheaterclub.mapper.UserSessionDetailMapper;
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

    public UserSessionDetail signUp(@NonNull CreateUserRequest createUserRequest) {
        UserDto userDto = userService.createUser(createUserRequest);

        SessionDto sessionDto = sessionService.createSession(CreateSessionRequest.builder()
                .userId(userDto.getUserId())
                .sessionDuration(DEFAULT_SESSION_DURATION)
                .build());

        return UserSessionDetailMapper.INSTANCE.toUserSessionDetail(userDto, sessionDto);
    }


    public UserSessionDetail signIn(@NonNull String email, @NonNull String password) {

        UserDto userDto = userService.validateLoginCredentials(email, password);

        SessionDto sessionDto = sessionService.createSession(CreateSessionRequest.builder()
                .userId(userDto.getUserId())
                .sessionDuration(DEFAULT_SESSION_DURATION)
                .build());

        return UserSessionDetailMapper.INSTANCE.toUserSessionDetail(userDto, sessionDto);
    }

    @Override
    public UserSessionDetail isAuthenticated(@NonNull String sessionId) {
        SessionDto sessionDto = sessionService.validateSession(sessionId);
        UserDto userDto = userService.getUserByEmail(sessionDto.getUserId());

        return UserSessionDetailMapper.INSTANCE.toUserSessionDetail(userDto, sessionDto);
    }

    @Override
    public void logout() {
        sessionService.invalidateSession(String.valueOf(1));
    }
}
