package com.scrumandcoke.movietheaterclub.service;

import com.scrumandcoke.movietheaterclub.dto.CreateUserRequest;
import com.scrumandcoke.movietheaterclub.dto.UserDto;
import com.scrumandcoke.movietheaterclub.dto.UserSessionDetail;
import lombok.NonNull;

public interface IAMService {

    UserSessionDetail signUp(CreateUserRequest createUserRequest);

    UserSessionDetail signIn(@NonNull String email, @NonNull String password);

    UserSessionDetail isAuthenticated(@NonNull String sessionId);

    void logout();
}
