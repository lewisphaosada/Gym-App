package com.techelevator.dao;

import com.techelevator.model.Session;

import java.util.List;

public interface SessionDao {
    List<Session> getSessions();
    Session getSessionBySessionId(int sessionId);
    List<Session> getSessionsByUserId(int id);
    Session createSession(Session session);
    Session updateSessionBySessionId(int id, Session updatedSession);
    void deleteSessionBySessionId(int id);
}
