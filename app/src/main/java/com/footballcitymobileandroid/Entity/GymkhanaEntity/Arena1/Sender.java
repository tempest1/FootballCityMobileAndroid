package com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1;

import java.io.Serializable;

/**
 * Created by smartlab on 16/6/4.
 */
public class Sender implements Serializable{
    private String clubKey;
    private String clubName;
    private String clubLogo;

    public void setClubKey(String clubKey) {
        this.clubKey = clubKey;
    }

    public String getClubKey() {
        return clubKey;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubLogo(String clubLogo) {
        this.clubLogo = clubLogo;
    }

    public String getClubLogo() {
        return clubLogo;
    }
}
