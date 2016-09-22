package com.footballcitymobileandroid.Controller.TestData;

import java.io.Serializable;

/**
 * Created by zhoudi on 16/5/31.
 */
public class ClubChallenge implements Serializable {
    private String meclub;
    private String youclub;

    public String getYouclub() {
        return youclub;
    }

    public void setYouclub(String youclub) {
        this.youclub = youclub;
    }

    public String getMeclub() {
        return meclub;
    }

    public void setMeclub(String meclub) {
        this.meclub = meclub;
    }
}
