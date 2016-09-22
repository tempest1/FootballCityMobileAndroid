package com.footballcitymobileandroid.Controller.TestData;

import java.io.Serializable;

/**
 * Created by zhoudi on 16/5/19.
 */
public class PlayerTest implements Serializable{
    private String playname;
    private  int paice;

    public String getPlayname() {
        return playname;
    }

    public void setPlayname(String playname) {
        this.playname = playname;
    }

    public int getPaice() {
        return paice;
    }

    public void setPaice(int paice) {
        this.paice = paice;
    }
}
