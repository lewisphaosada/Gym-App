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
    public Exercise getExerciseByExerciseID(int exerciseId) {
        String sql = "SELECT * FROM exercise WHERE exercise_id = ?";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, exerciseId);
            if (rowSet.next()) {
                return mapRowToExercise(rowSet);
            } else {
                return null;
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
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
    public void insertExercises(List<Exercise> exercises) {
        for (Exercise exercise : exercises) {
            String sql = "INSERT INTO exercise (name, photo, description, gif) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, exercise.getName(), exercise.getPhoto(), exercise.getDescription(), exercise.getGif());
        }
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
        exercise.setExercise_id(rowSet.getInt("exercise_id"));
        exercise.setName(rowSet.getString("name"));
        exercise.setDescription(rowSet.getString("description"));
        exercise.setPhoto(rowSet.getString("photo"));
        exercise.setGif(rowSet.getString("gif"));

        return exercise;
    }


}
