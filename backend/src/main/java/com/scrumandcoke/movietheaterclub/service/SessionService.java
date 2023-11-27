package com.scrumandcoke.movietheaterclub.service;

import com.scrumandcoke.movietheaterclub.dto.CreateSessionRequest;
import com.scrumandcoke.movietheaterclub.model.Session;

public interface SessionService {
    void createSession(CreateSessionRequest createSessionRequest);
    Session validateSession(Integer id);
    void invalidateSession(Integer id);
}
