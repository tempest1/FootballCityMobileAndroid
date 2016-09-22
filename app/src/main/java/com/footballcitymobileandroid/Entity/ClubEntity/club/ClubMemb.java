package com.footballcitymobileandroid.Entity.ClubEntity.club;

import java.io.Serializable;
import java.util.List;

/**
 * Created by smartlab on 16/5/24.
 */
public class ClubMemb implements Serializable{

//    private List<ClubMembs> clubMembs;
//
//
//
//    public void setClubMembs(List<ClubMembs> clubMembs) {
//        this.clubMembs = clubMembs;
//    }
//
//    public List<ClubMembs> getClubMembs() {
//        return clubMembs;
//    }
    private String clubPosition;
    private String playerID;
    private String ClubMembID;//
    private String photo;
    private String Name;
    private List<Worth> worth;//
    public void setClubPosition(String clubPosition) {
        this.clubPosition = clubPosition;
    }

    public String getClubPosition() {
        return clubPosition;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setClubMembID(String clubMembID) {
        ClubMembID = clubMembID;
    }

    public String getClubMembID() {
        return ClubMembID;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setWorth(List<Worth> worth) {
        this.worth = worth;
    }

    public List<Worth> getWorth() {
        return worth;
    }

    @Override
    public String toString() {
        return "ClubMemb{" +
                "clubPosition='" + clubPosition + '\'' +
                ", playerID='" + playerID + '\'' +
                ", ClubMembID='" + ClubMembID + '\'' +
                ", Name='" + Name + '\'' +
                ", worth=" + worth +
                '}';
    }
}
