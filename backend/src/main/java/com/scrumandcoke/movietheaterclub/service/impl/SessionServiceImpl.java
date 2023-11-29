package com.scrumandcoke.movietheaterclub.service.impl;

import com.scrumandcoke.movietheaterclub.dto.CreateSessionRequest;
import com.scrumandcoke.movietheaterclub.dto.SessionDto;
import com.scrumandcoke.movietheaterclub.mapper.SessionMapper;
import com.scrumandcoke.movietheaterclub.model.SessionEntity;
import com.scrumandcoke.movietheaterclub.repository.SessionRepository;
import com.scrumandcoke.movietheaterclub.service.SessionService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
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
    public SessionDto validateSession(@NonNull String sessionId) {
        Optional<SessionEntity> sessionEntity = sessionRepository.findById(sessionId);
        if (sessionEntity.isEmpty() || Date.from(Instant.now()).after(sessionEntity.get().getExpireAt())) {
            throw new AuthorizationServiceException("Invalid or no session found with the session ID");
        }

        return SessionMapper.INSTANCE.entityToDto(sessionEntity.get());
    }

    @Override
    public void invalidateSession(@NonNull String sessionId) {
        return;
//        SessionEntity sessionEntity = validateSession(sessionId);
//        sessionEntity.setExpireAt(Date.from(Instant.now()));
//        sessionEntity.setLastUpdatedAt(Date.from(Instant.now()));
//        sessionRepository.save(sessionEntity);
    }
}
