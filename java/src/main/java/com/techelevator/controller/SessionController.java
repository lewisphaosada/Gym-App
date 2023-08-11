package com.techelevator.controller;

import com.techelevator.dao.SessionDao;
import com.techelevator.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/sessions")
@PreAuthorize("isAuthenticated()")
public class SessionController {
    @Autowired
    private SessionDao sessionDao;
    public SessionController(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<Session> getSessionsByUserId(@PathVariable int id) {
        return sessionDao.getSessionsByUserId(id);
    }
}
