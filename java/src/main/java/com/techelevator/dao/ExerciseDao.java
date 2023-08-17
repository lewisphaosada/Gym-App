package com.techelevator.dao;

import com.techelevator.model.Exercise;

import java.util.List;

public interface ExerciseDao {


    Exercise createExercise();

    Exercise getExerciseByExerciseID(int id);

    Exercise updateExerciseByExerciseID();

    void deleteExerciseByExerciseID();

    List<Exercise> getExerciseList();

    void insertExercises(List<Exercise> exercises);
    public String getExerciseNameByExerciseId(Long exerciseId);
}
