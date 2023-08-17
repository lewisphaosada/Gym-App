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

<<<<<<< HEAD

    private Workout mapRowToWorkout(SqlRowSet rowSet) {
        Workout workout = new Workout();
        workout.setWorkoutId(rowSet.getLong("workout_id"));
        workout.setSessionId(rowSet.getLong("session_id"));
        workout.setUserId(rowSet.getLong("user_id"));
        workout.setExerciseId(rowSet.getLong("exercise_id"));
        workout.setDuration(rowSet.getLong("duration"));
        workout.setWeight(rowSet.getBigDecimal("weight"));
        workout.setSets(rowSet.getInt("sets"));
        workout.setReps(rowSet.getInt("reps"));
        return workout;
=======
    public List<Workout> getWorkoutsBySessionId(int sessionId) {
        List<Workout> workouts = new ArrayList<>();
        String sql = "SELECT * FROM workout WHERE session_id = ?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, sessionId);
            while (results.next()) {
                Workout workout = mapRowToWorkout(results);
                workouts.add(workout);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to serve or database");
        }
        return workouts;
>>>>>>> b93241e5911e79d110407c3796f2c40253533f3e
    }

    public Workout saveWorkout( Workout workout) {
        Workout newWorkout = null;
        String sql = "INSERT INTO workout (session_id, user_id, exercise_id, sets, reps, weight, duration) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING workout_id";
        try {
            int newWorkoutId = jdbcTemplate.queryForObject(
                    sql, int.class,
                    workout.getSessionId(),
                    workout.getUserId(),
                    workout.getExerciseId(),
                    workout.getSets(),
                    workout.getReps(),
                    workout.getWeight(),
                    workout.getDuration()
            );
            newWorkout = getWorkoutByWorkoutId(newWorkoutId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the server or database", e);
        }
        return newWorkout;
    }

    public Workout getWorkoutByWorkoutId(int workoutId) {
        Workout workout = null;
        String sql = "SELECT * FROM workout WHERE workout_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, workoutId);
            if(results.next()) {
                workout = mapRowToWorkout(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database");
        }
        return workout;
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

    private Workout mapRowToWorkout(SqlRowSet rowSet) {
        Workout workout = new Workout();
        workout.setId(rowSet.getLong("workout_id"));
        workout.setSessionId(rowSet.getLong("session_id"));
        workout.setUserId(rowSet.getLong("user_id"));
        workout.setExerciseId(rowSet.getLong("exercise_id"));
        workout.setDuration(rowSet.getLong("duration"));
        workout.setWeight(rowSet.getBigDecimal("weight"));
        workout.setSets(rowSet.getInt("sets"));
        workout.setReps(rowSet.getInt("reps"));
        return workout;
    }
}


