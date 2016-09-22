package com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1;

import java.io.Serializable;

/**
 * Created by smartlab on 16/6/4.
 */
public class VisitingTeam implements Serializable {
    private String clubID;
    private String clubName;
    private String clubLogo;

    public void setClubID(String clubID) {
        this.clubID = clubID;
    }

    public String getClubID() {
        return clubID;
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
