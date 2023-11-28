package com.scrumandcoke.movietheaterclub.service;

import com.scrumandcoke.movietheaterclub.dto.CreateSessionRequest;
import com.scrumandcoke.movietheaterclub.dto.SessionDto;
import lombok.NonNull;

public interface SessionService {
    SessionDto createSession(CreateSessionRequest createSessionRequest);
    SessionDto validateSession(@NonNull String sessionId);
    void invalidateSession(@NonNull String sessionId);
}
