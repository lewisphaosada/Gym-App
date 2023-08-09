package com.techelevator.model;

public class Profile {

    private String name;
    private String password;
    private String username;
    private String sex;
    private String email;
    private String photo;
    private int id;
    private int weight;
    private int height;
    private int bmi;


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public String getUsername(){
        return username;
    }

    public String getSex(){
        return sex;
    }

    public String getEmail(){
        return email;
    }

    public int getId(){
        return id;
    }

    public int getWeight(){
        return weight;
    }

    public int getHeight(){
        return height;
    }
    public int getBmi(){
        return bmi;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public void setSex(String sex){
        this.sex = sex;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void setBmi(int bmi){
        this.bmi = bmi;
    }
}
