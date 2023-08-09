package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Profile;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcProfileDao implements ProfileDao {

    private JdbcTemplate jdbcTemplate;

    private UserDao userDao;

    public JdbcProfileDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;

    }

    @Override
    public Profile getProfileByUserID(int id) {
        Profile profile = null;
        String sql = "SELECT name, password, username, sex, email, photo, weight, height, bmi FROM user WHERE user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                profile = mapRowToProfile(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return profile;
    }

    @Override
    public void deleteProfileByUserID(int id) {

        String deleteProfileSql = "DELETE FROM profile WHERE userID = ?";
        String deleteUserSql = "DELETE FROM user WHERE userID = ?";

        try {
            jdbcTemplate.update(deleteProfileSql, id);
            jdbcTemplate.update(deleteUserSql, id);


        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }
    @Override
    public Profile updateProfileByUserID(int id, Profile updatedProfile) {
        String sql = "UPDATE user SET name = ?, password = ?, username = ?, sex = ?, email = ?, photo = ?" +
                "weight = ?, height = ?, bmi = ? WHERE user_id = ?";
        try {
            jdbcTemplate.update(sql,
                    updatedProfile.getName(),
                    updatedProfile.getPassword(),
                    updatedProfile.getUsername(),
                    updatedProfile.getSex(),
                    updatedProfile.getEmail(),
                    updatedProfile.getPhoto(),
                    updatedProfile.getWeight(),
                    updatedProfile.getHeight(),
                    updatedProfile.getBmi(),
                    id
            );

            return getProfileByUserID(id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    private Profile mapRowToProfile(SqlRowSet rowSet) {
        Profile profile = new Profile(
                rowSet.getString("name"),
                rowSet.getString("password"),
                rowSet.getString("username"),
                rowSet.getString("sex"),
                rowSet.getString("email"),
                rowSet.getString("photo"),
                rowSet.getInt("id"),
                rowSet.getInt("weight"),
                rowSet.getInt("height"),
                rowSet.getInt("bmi")
        );
        return profile;
    }
}
