package com.techelevator.dao;

import com.techelevator.model.Workout;

import java.util.List;

public interface WorkoutDao {
    List<Workout> getWorkoutsByUserId(int userId);
    Workout saveWorkout(Workout workout);
    Workout updateWorkout(Workout workout);
    void deleteWorkout(Long workoutId);
}