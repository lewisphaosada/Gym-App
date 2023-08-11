package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Session;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcSessionDao implements SessionDao {
    private JdbcTemplate jdbcTemplate;
    private SessionDao sessionDao;

    public JdbcSessionDao(JdbcTemplate jdbcTemplate, SessionDao sessionDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.sessionDao = sessionDao;
    }

    @Override
    public List<Session> getSessions() {
        List<Session> sessions = null;
        String sql = "SELECT * FROM session";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            if(results.next()) {
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
        String sql = "SELECT * FROM session WHERE session_id = ?";
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
        List<Session> sessions = null;
        String sql = "SELECT * FROM session WHERE user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            if(results.next()) {
                sessions.add(mapRowToSession(results));
            }
        } catch(CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return sessions;
    };

    @Override
    public Session updateSessionBySessionId(int id, Session updatedSession) {
        String sql = "UPDATE session SET duration = ?, date = ? WHERE session_id = ?";
        try {
            jdbcTemplate.update(sql, updatedSession.getDuration(), updatedSession.getDate(), updatedSession.getSessionId());
            return getSessionBySessionId(id);
        } catch(CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    };

    @Override
    public void deleteSessionBySessionId(int id) {
        String sql = "DELETE FROM session WHERE session_id = ?";
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
