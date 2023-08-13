package com.techelevator.controller;

import com.techelevator.dao.SessionDao;
import com.techelevator.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
//@RequestMapping("/sessions")
@PreAuthorize("isAuthenticated()")
public class SessionController {
    @Autowired
    private SessionDao sessionDao;
    public SessionController(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    @RequestMapping(path = "/sessions/{id}", method = RequestMethod.GET)
    public List<Session> getSessionsByUserId(@PathVariable int id) {
        return sessionDao.getSessionsByUserId(id);
    }

    @RequestMapping(path = "sessions/create-session", method = RequestMethod.POST)
    public Session createSession(@RequestBody Session session) {
        return sessionDao.createSession(session);
    }

    @RequestMapping(path = "/session/{id}", method = RequestMethod.PUT)
    public Session updateSessionBySessionId(@PathVariable int id, @RequestBody Session session) {
        return sessionDao.updateSessionBySessionId(id, session);
    }
}
