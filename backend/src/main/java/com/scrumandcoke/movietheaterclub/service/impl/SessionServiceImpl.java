package com.scrumandcoke.movietheaterclub.service.impl;

import com.scrumandcoke.movietheaterclub.dto.CreateSessionRequest;
import com.scrumandcoke.movietheaterclub.dto.SessionDto;
import com.scrumandcoke.movietheaterclub.mapper.SessionMapper;
import com.scrumandcoke.movietheaterclub.model.SessionEntity;
import com.scrumandcoke.movietheaterclub.repository.SessionRepository;
import com.scrumandcoke.movietheaterclub.service.SessionService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public SessionDto createSession(@NonNull CreateSessionRequest createSessionRequest) {
        SessionEntity sessionEntity = new SessionEntity();

        sessionEntity.setSessionId(UUID.randomUUID().toString());
        sessionEntity.setUserId(createSessionRequest.getUserId());
        sessionEntity.setExpireAt(Date.from(Instant.now().plus(createSessionRequest.getSessionDuration())));

        sessionRepository.save(sessionEntity);

        return SessionMapper.INSTANCE.entityToDto(sessionEntity);
    }

    @Override
    public SessionEntity validateSession(Integer id) {
        return sessionRepository.findById(id).get();
    }

    @Override
    public void invalidateSession(Integer id) {
        SessionEntity sessionEntity = validateSession(id);
        sessionEntity.setExpireAt(Date.from(Instant.now()));
        sessionEntity.setLastUpdatedAt(Date.from(Instant.now()));
        sessionRepository.save(sessionEntity);
    }
}
