package com.techelevator.controller;

import com.techelevator.dao.WorkoutDao;
import com.techelevator.model.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/workouts")
//@PreAuthorize("isAuthenticated()")
public class WorkoutController {

    private WorkoutDao workoutDao;
    @Autowired
    public WorkoutController(WorkoutDao workoutDao) {
        this.workoutDao = workoutDao;
    }
    @RequestMapping(path = "/user/{userId}", method = RequestMethod.GET)
    public List<Workout> getWorkoutsByUserId(@PathVariable int userId) {
        return workoutDao.getWorkoutsByUserId(userId);
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public Workout saveWorkout(@RequestBody Workout workout) {
        return workoutDao.saveWorkout(workout);
    }

    @RequestMapping(path = "/{workoutId}", method = RequestMethod.PUT)
    public Workout updateWorkout(@PathVariable Long workoutId, @RequestBody Workout workout) {
        workout.setWorkoutId(workoutId);
        return workoutDao.updateWorkout(workout);
    }

    @RequestMapping(path = "/{workoutId}", method = RequestMethod.DELETE)
    public void deleteWorkout(@PathVariable Long workoutId) {
        workoutDao.deleteWorkout(workoutId);
    }

}

