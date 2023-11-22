package com.scrumandcoke.movietheaterclub.service.impl;

import com.scrumandcoke.movietheaterclub.model.Session;
import com.scrumandcoke.movietheaterclub.repository.SessionRepository;
import com.scrumandcoke.movietheaterclub.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public void addSession(Session session) {
        sessionRepository.save(session);
    }

    @Override
    public Session getSession(Integer id) {
        return sessionRepository.findById(id).get();
    }

    @Override
    public void invalidateSession(Integer id) {
        Session session = getSession(id);
        session.setExpireAt(Date.from(Instant.now()));
        session.setLastUpdatedAt(Date.from(Instant.now()));
        sessionRepository.save(session);
    }
}
