package com.techelevator.controller;

import com.techelevator.dao.ProfileDao;
import com.techelevator.model.Profile;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/profile")
public class ProfileController {

    private ProfileDao profileDao;

    public ProfileController(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Profile getProfileByUserID(@PathVariable int id) {
        return profileDao.getProfileByUserID(id);
    }

    @RequestMapping(path = "/{id}/edit", method = RequestMethod.PUT)
    public Profile updateProfileByUserID(@PathVariable int id, @RequestBody Profile updatedProfile) {
        return profileDao.updateProfileByUserID(id, updatedProfile);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteProfileByUserID(@PathVariable int id) {
        profileDao.deleteProfileByUserID(id);
    }
}
