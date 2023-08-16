package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Profile;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
//@RequestMapping("/profile")
@PreAuthorize("isAuthenticated()")
public class ProfileController {

    private ProfileDao profileDao;
    private UserDao userDao;



    public ProfileController(ProfileDao profileDao, UserDao userDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/profile/{id}", method = RequestMethod.GET)
    public Profile getProfileByUserID(@PathVariable int id) {
        return profileDao.getProfileByUserID(id);
    }

    @RequestMapping(path = "/profile/{id}/edit", method = RequestMethod.PUT)
    public Profile updateProfileByUserID(@PathVariable int id, @RequestBody Profile updatedProfile) {
        return profileDao.updateProfileByUserID(id, updatedProfile);
    }

    @RequestMapping(path = "/profile/{id}", method = RequestMethod.DELETE)
    public void deleteProfileByUserID(@PathVariable int id) {
        profileDao.deleteProfileByUserID(id);
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userDao.getUsers();
    }
}
