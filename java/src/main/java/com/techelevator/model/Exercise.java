package com.techelevator.model;

public class Exercise {

    private int exercise_id;
    private String name;
    private String photo;
    private String description;
    private String gif;

    public int getExercise_id() {
        return exercise_id;
    }

    public Exercise() {

    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public Exercise(int exercise_id, String name, String photo, String description, String gif) {
        this.exercise_id = exercise_id;
        this.name = name;
        this.photo = photo;
        this.description = description;
        this.gif = gif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGif() {
        return gif;
    }

    public void setGif(String gif) {
        this.gif = gif;
    }
}
