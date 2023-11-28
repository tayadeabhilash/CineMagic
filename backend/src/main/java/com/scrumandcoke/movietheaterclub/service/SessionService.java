package com.scrumandcoke.movietheaterclub.service;

import com.scrumandcoke.movietheaterclub.dto.CreateSessionRequest;
import com.scrumandcoke.movietheaterclub.model.SessionEntity;

public interface SessionService {
    void createSession(CreateSessionRequest createSessionRequest);
    SessionEntity validateSession(Integer id);
    void invalidateSession(Integer id);
}
