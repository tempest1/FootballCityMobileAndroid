package com.footballcitymobileandroid.Entity.MeEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by smartlab on 16/7/20.
 */
public class Players implements Serializable{
    private String playerID;
    private String photo;
    private String name;
    private String sex;
    private String age;
    private String height;
    private String weight;
    private List<String> position;
    private List<String> aty_Time;
    private String acy_Field;
    private List<Clubs> Clubs;

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHeight() {
        return height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeight() {
        return weight;
    }

    public void setPosition(List<String> position) {
        this.position = position;
    }

    public List<String> getPosition() {
        return position;
    }

    public void setAty_Time(List<String> aty_Time) {
        this.aty_Time = aty_Time;
    }

    public List<String> getAty_Time() {
        return aty_Time;
    }

    public void setAcy_Field(String acy_Field) {
        this.acy_Field = acy_Field;
    }

    public String getAcy_Field() {
        return acy_Field;
    }

    public void setClubs(List<com.footballcitymobileandroid.Entity.MeEntity.Clubs> clubs) {
        Clubs = clubs;
    }

    public List<com.footballcitymobileandroid.Entity.MeEntity.Clubs> getClubs() {
        return Clubs;
    }
}
