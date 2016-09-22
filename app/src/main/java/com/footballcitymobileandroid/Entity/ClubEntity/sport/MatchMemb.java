package com.footballcitymobileandroid.Entity.ClubEntity.sport;

import java.io.Serializable;

/**
 * Created by smartlab on 16/5/23.
 */
public class MatchMemb implements Serializable{
    private String clubMembID;
    private String name;
    private String photo;
    private String isMain;
    private String coordX;
    private String coordY;

    public void setClubMembID(String clubMembID) {
        this.clubMembID = clubMembID;
    }

    public String getClubMembID() {
        return clubMembID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setIsMain(String isMain) {
        this.isMain = isMain;
    }

    public String getIsMain() {
        return isMain;
    }

    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public String getCoordX() {
        return coordX;
    }

    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }

    public String getCoordY() {
        return coordY;
    }
}
