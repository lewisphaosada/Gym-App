package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Workout {

    private Long id;
    private Long sessionId;
    private Long userId;
    private Long exerciseId;
    private Long duration;
    private BigDecimal weight;
    private Integer sets;
    private Integer reps;

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

<<<<<<< HEAD
    public Long getDuration() {
=======

    public LocalTime getDuration() {
>>>>>>> d6af3960b4fb62adb197e481721e3c3fb441fa94
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }
}
