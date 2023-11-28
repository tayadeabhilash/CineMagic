package com.scrumandcoke.movietheaterclub.service;

import com.scrumandcoke.movietheaterclub.dto.CreateSessionRequest;
import com.scrumandcoke.movietheaterclub.dto.SessionDto;
import com.scrumandcoke.movietheaterclub.model.SessionEntity;

public interface SessionService {
    SessionDto createSession(CreateSessionRequest createSessionRequest);
    SessionEntity validateSession(Integer id);
    void invalidateSession(Integer id);
}
