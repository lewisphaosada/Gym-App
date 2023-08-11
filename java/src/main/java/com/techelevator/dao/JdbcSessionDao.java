package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSessionDao implements SessionDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcSessionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Session> getSessions() {
        List<Session> sessions = new ArrayList<>();
        String sql = "SELECT * FROM sessions";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()) {
                sessions.add(mapRowToSession(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return sessions;
    }

    @Override
    public Session getSessionBySessionId(int sessionId) {
        Session session = null;
        String sql = "SELECT * FROM sessions WHERE session_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, sessionId);
            session = mapRowToSession(results);
        } catch(CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return session;
    }

    @Override
    public List<Session> getSessionsByUserId(int userId) {
        List<Session> sessions = new ArrayList<>();
        String sql = "SELECT * FROM sessions WHERE user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while(results.next()) {
                sessions.add(mapRowToSession(results));
            }
        } catch(CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return sessions;
    };

    @Override
    public Session updateSessionBySessionId(int id, Session updatedSession) {
        String sql = "UPDATE sessions SET duration = ?, date = ? WHERE session_id = ?";
        try {
            jdbcTemplate.update(sql, updatedSession.getDuration(), updatedSession.getDate(), updatedSession.getSessionId());
            return getSessionBySessionId(id);
        } catch(CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    };

    @Override
    public void deleteSessionBySessionId(int id) {
        String sql = "DELETE FROM sessions WHERE session_id = ?";
        try {
            jdbcTemplate.update(sql, id);
        } catch(CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    };

    private Session mapRowToSession(SqlRowSet rowSet) {
        Session session = new Session();
        session.setSessionId(Integer.parseInt(rowSet.getString("session_id")));
        session.setUserId(Integer.parseInt(rowSet.getString("user_id")));
        session.setDuration(Long.parseLong(rowSet.getString("duration")));
        session.setDate(rowSet.getString("date"));
        return session;
    }
}
