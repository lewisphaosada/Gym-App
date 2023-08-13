package com.techelevator.model;

public class Exercise {
    private String name;
    private String photo;
    private String description;
    private String gif;

    public Exercise() {
    }

    public Exercise(String name, String photo, String description, String gif) {
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
