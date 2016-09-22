package com.footballcitymobileandroid.Controller.TestData;

import java.io.Serializable;

/**
 * Created by zhoudi on 16/5/19.
 */
public class ClubTest implements Serializable{
    private String clubname;
    private  int paice;

    public String getClubname() {
        return clubname;
    }

    public void setClubname(String clubname) {
        this.clubname = clubname;
    }

    public int getPaice() {
        return paice;
    }

    public void setPaice(int paice) {
        this.paice = paice;
    }
}
