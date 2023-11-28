package com.scrumandcoke.movietheaterclub.service.impl;

import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.model.Session;
import com.scrumandcoke.movietheaterclub.model.User;
import com.scrumandcoke.movietheaterclub.repository.SessionRepository;
import com.scrumandcoke.movietheaterclub.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);

    @Override
    public void addSession(Session session) throws GlobalException {
        if (session == null) {
            throw new GlobalException("Session cannot be null");
        }
        try {
            sessionRepository.save(session);
        } catch (Exception exception) {
            logger.error("Error saving session: {}", session.getId());
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public Session getSession(Integer id) throws GlobalException {
        if (id == null) {
            throw new GlobalException("Session ID cannot be null");
        }
        try {
            return sessionRepository.findById(id).get();
        } catch (Exception exception) {
            logger.error("Error getting session: {}", id);
            throw new GlobalException(exception.getMessage(), exception);
        }
    }

    @Override
    public void invalidateSession(Integer id) throws GlobalException {
        if (id == null) {
            throw new GlobalException("Session ID cannot be null");
        }
        try {
            Session session = getSession(id);
            session.setExpireAt(Date.from(Instant.now()));
            session.setLastUpdatedAt(Date.from(Instant.now()));
            sessionRepository.save(session);
        } catch (Exception exception) {
            logger.error("Error updating session: {}", id);
            throw new GlobalException(exception.getMessage(), exception);
        }
    }
}
