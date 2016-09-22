package com.footballcitymobileandroid.Controller.TestData;

import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by zhoudi on 16/5/23.
 */
public class PlayRecord implements Serializable {
    private int point_me;
    private int pint_you;
    private String club_me;
    private String club_you;
    private String payways;
    private String place;
    private String date;

    public int getPoint_me() {
        return point_me;
    }

    public void setPoint_me(int point_me) {
        this.point_me = point_me;
    }

    public int getPint_you() {
        return pint_you;
    }

    public void setPint_you(int pint_you) {
        this.pint_you = pint_you;
    }

    public String getClub_me() {
        return club_me;
    }

    public void setClub_me(String club_me) {
        this.club_me = club_me;
    }

    public String getClub_you() {
        return club_you;
    }

    public void setClub_you(String club_you) {
        this.club_you = club_you;
    }

    public String getPayways() {
        return payways;
    }

    public void setPayways(String payways) {
        this.payways = payways;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
