package com.footballcitymobileandroid.Entity.ClubEntity.join;

import java.io.Serializable;

/**
 * Created by smartlab on 16/5/23.
 */
public class SenderInfo implements Serializable{
    private String clubID;
    private String clubLogo;
    private String clubName;

    private String playerID;
    private String photo;
    private String name;

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

}
