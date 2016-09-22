package com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3;

import java.io.Serializable;

/**
 * Created by smartlab on 16/6/7.
 */
public class ClubRankings implements Serializable {
    private String clubID;
    private String clubLogo;
    private String clubName;
    private String point;            //价值
    private String rankings;          //排名

    public void setClubID(String clubID) {
        this.clubID = clubID;
    }

    public String getClubID() {
        return clubID;
    }

    public void setClubLogo(String clubLogo) {
        this.clubLogo = clubLogo;
    }

    public String getClubLogo() {
        return clubLogo;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPoint() {
        return point;
    }

    public void setRankings(String rankings) {
        this.rankings = rankings;
    }

    public String getRankings() {
        return rankings;
    }
}
