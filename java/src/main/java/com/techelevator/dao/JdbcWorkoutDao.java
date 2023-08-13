package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Workout;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcWorkoutDao implements WorkoutDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcWorkoutDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Workout> getWorkoutsByUserId(int userId) {
        List<Workout> workouts = new ArrayList<>();
        String sql = "SELECT * FROM workout WHERE user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Workout workout = mapRowToWorkout(results);
                workouts.add(workout);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the server or database", e);
        }
        return workouts;
    }


    private Workout mapRowToWorkout(SqlRowSet rowSet) {
        Workout workout = new Workout();
        workout.setWorkoutId(rowSet.getLong("workout_id"));
        workout.setSessionId(rowSet.getLong("session_id"));
        workout.setUserId(rowSet.getLong("user_id"));
        workout.setExerciseId(rowSet.getLong("exercise_id"));

        // Use getTimestamp to retrieve a java.sql.Timestamp
        java.sql.Timestamp durationTimestamp = rowSet.getTimestamp("duration");
        if (durationTimestamp != null) {
            workout.setDuration(durationTimestamp.toLocalDateTime().toLocalTime());
        }

        workout.setWeight(rowSet.getBigDecimal("weight"));
        workout.setSets(rowSet.getInt("sets"));
        workout.setReps(rowSet.getInt("reps"));
        return workout;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Workout saveWorkout(@RequestBody Workout workout) {
        String sql = "INSERT INTO workout (user_id, exercise_id, sets, reps, weight, duration) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(
                    sql,
                    workout.getUserId(),
                    workout.getExerciseId(),
                    workout.getSets(),
                    workout.getReps(),
                    workout.getWeight(),
                    workout.getDuration()
            );

            return workout;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the server or database", e);
        }
    }



    @Override
    public Workout updateWorkout(Workout workout) {
        String sql = "UPDATE workout SET session_id=?, user_id=?, exercise_id=?, duration=?, weight=?, sets=?, reps=? " +
                "WHERE workout_id=?";
        try {
            jdbcTemplate.update(
                    sql,
                    workout.getSessionId(),
                    workout.getUserId(),
                    workout.getExerciseId(),
                    workout.getDuration(),
                    workout.getWeight(),
                    workout.getSets(),
                    workout.getReps(),
                    workout.getWorkoutId()
            );
            return workout;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the server or database", e);
        }
    }
    @Override
    public void deleteWorkout(Long workoutId) {
        String sql = "DELETE FROM workout WHERE workout_id=?";
        try {
            jdbcTemplate.update(sql, workoutId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the server or database", e);
        }
    }
}


