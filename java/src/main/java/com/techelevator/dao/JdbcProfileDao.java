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

    public JdbcProfileDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Profile getProfileByUserID(int id) {
        Profile profile = null;
        String sql = "SELECT name, password_hash, username, sex, email, photo, weight, height, bmi FROM users WHERE user_id = ?";
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

        String deleteUserSql = "DELETE FROM users WHERE user_id = ?";

        try {
            jdbcTemplate.update(deleteUserSql, id);


        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }
    // Check out ORM (Object Relational Mapping) updateUserName(Username), updateUsernameAndPAssowrd()
    // Build a query string based off of non-null poperties in updatedProfile
    // if password_hash != null { concatentate password_hash = ? to query string }
    // Since JDBC functions require arguments to match placeholders
    // You can sterilize input, so SQL injection attacks are thwarted, and then add clean values to query string
    // You can use regular expressions (that checks for only alpha numeric values) to detect SQL
    // SQL attacks often contain, ;, --, DROP TABLE, SELECT *.
    @Override
    public Profile updateProfileByUserID(int id, Profile updatedProfile) {
        String sql = "UPDATE users SET name = ?, password_hash = ?, username = ?, sex = ?, email = ?, photo = ?, " +
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
        Profile profile = new Profile();
        profile.setName(rowSet.getString("name"));
        profile.setPassword(rowSet.getString("password_hash"));
        profile.setUsername(rowSet.getString("username"));
        profile.setSex(rowSet.getString("sex"));
        profile.setEmail(rowSet.getString("email"));
        profile.setPhoto(rowSet.getString("photo"));
        profile.setWeight(rowSet.getBigDecimal("weight"));
        profile.setHeight(rowSet.getBigDecimal("height"));
        profile.setBmi(rowSet.getBigDecimal("bmi"));
        return profile;
    }

}
