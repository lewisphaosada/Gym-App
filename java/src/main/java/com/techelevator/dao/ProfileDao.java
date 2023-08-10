package com.techelevator.dao;

import com.techelevator.model.Profile;

public interface ProfileDao {

    Profile getProfileByUserID(int id);
    void deleteProfileByUserID(int id);
    Profile updateProfileByUserID(int id, Profile updatedProfile);

    }
