package com.techelevator.controller;

import com.techelevator.dao.ExerciseDao;
import com.techelevator.model.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
public class ExerciseController {


    private ExerciseDao exerciseDao;
    @Autowired
    public ExerciseController(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    @RequestMapping(path = "/exercises/{exerciseId}", method = RequestMethod.GET)
    public ResponseEntity<Exercise> getExerciseById(@PathVariable int exerciseId) {
        Exercise exercise = exerciseDao.getExerciseByExerciseID(exerciseId);

        if (exercise != null) {
            return ResponseEntity.ok(exercise);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(path = "/exercises", method = RequestMethod.GET)
    public List<Exercise> getExerciseList() {
        return exerciseDao.getExerciseList();
    }

    @RequestMapping(path = "/session/{id}", method = RequestMethod.GET)
    public String getExerciseNameByExerciseId(@PathVariable Long id) {
        return exerciseDao.getExerciseNameByExerciseId(id);
    }
}
