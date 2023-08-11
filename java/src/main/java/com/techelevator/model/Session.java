package com.techelevator.model;

public class Session {
    private int sessionId;
    private int userId;
    private long duration;
    private String date;

    public Session() {

    }

    public Session(int user_id, long duration, String date) {
        this.userId = user_id;
        this.duration = duration;
        this.date = date;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
