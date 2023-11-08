package com.scrumandcoke.movietheaterclub.service;

import com.scrumandcoke.movietheaterclub.exception.GlobalException;
import com.scrumandcoke.movietheaterclub.model.Session;

public interface SessionService {
    void addSession(Session session) throws GlobalException;
    Session getSession(Integer id) throws GlobalException;
    void invalidateSession(Integer id) throws GlobalException;
}
