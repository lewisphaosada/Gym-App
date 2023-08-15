package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Workout {

    private Long id;
    private Long session_id;
    private Long user_id;
    private Long exercise_id;
    private LocalTime duration;
    private BigDecimal weight;
    private Integer sets;
    private Integer reps;

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setExercise_id(Long exercise_id) {
        this.exercise_id = exercise_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public Long getExercise_id() {
        return exercise_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
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
