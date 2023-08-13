package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Exercise;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcExerciseDao implements ExerciseDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcExerciseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Exercise createExercise() {
        Exercise exercise = new Exercise();

        return exercise;
    }

    @Override
    public Exercise getExerciseByExerciseID() {
        Exercise exercise = new Exercise();

        return exercise;
    }

    @Override
    public Exercise updateExerciseByExerciseID() {
        Exercise exercise = new Exercise();

        return exercise;
    }

    @Override
    public void deleteExerciseByExerciseID() {

    }

    @Override
    public List<Exercise> getExerciseList() {
        List<Exercise> exercises = new ArrayList<>();
        String sql = "SELECT * FROM exercise";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()) {
                exercises.add(mapRowToExercise(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return exercises;
    }

    private Exercise mapRowToExercise(SqlRowSet rowSet) {
        Exercise exercise = new Exercise();

        exercise.setName(rowSet.getString("name"));
        exercise.setDescription(rowSet.getString("description"));
        exercise.setPhoto(rowSet.getString("photo"));
        exercise.setGif(rowSet.getString("gif"));

        return exercise;
    }
}
