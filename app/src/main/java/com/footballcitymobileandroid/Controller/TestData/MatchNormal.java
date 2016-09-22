package com.footballcitymobileandroid.Controller.TestData;

import java.io.Serializable;

/**
 * Created by zhoudi on 16/5/30.
 */
public class MatchNormal implements Serializable {
    private String MeClub;
    private String YouClub;
    private String Time;
    private String type;

    public String getMeClub() {
        return MeClub;
    }

    public void setMeClub(String meClub) {
        MeClub = meClub;
    }

    public String getYouClub() {
        return YouClub;
    }

    public void setYouClub(String youClub) {
        YouClub = youClub;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MatchNormal{" +
                "MeClub='" + MeClub + '\'' +
                ", YouClub='" + YouClub + '\'' +
                ", Time='" + Time + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
