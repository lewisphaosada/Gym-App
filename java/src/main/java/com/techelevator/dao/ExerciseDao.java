package com.techelevator.dao;

import com.techelevator.model.Exercise;

import java.util.List;

public interface ExerciseDao {

    Exercise createExercise();
    Exercise getExerciseByExerciseID();
    Exercise updateExerciseByExerciseID();
    void deleteExerciseByExerciseID();
    List<Exercise> getExerciseList();
}
