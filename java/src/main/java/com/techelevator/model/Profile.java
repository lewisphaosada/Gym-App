package com.techelevator.model;

import java.math.BigDecimal;

public class Profile {

    private String name;
    private String password;
    private String username;
    private String sex;
    private String email;
    private String photo;
    private int id;
    private BigDecimal weight;
    private BigDecimal height;
    private BigDecimal bmi;


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

    public BigDecimal getWeight(){
        return weight;
    }

    public BigDecimal getHeight(){
        return height;
    }
    public BigDecimal getBmi(){
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
    public void setWeight(BigDecimal weight){
        this.weight = weight;
    }
    public void setHeight(BigDecimal height){
        this.height = height;
    }
    public void setBmi(BigDecimal bmi){
        this.bmi = bmi;
    }
}
