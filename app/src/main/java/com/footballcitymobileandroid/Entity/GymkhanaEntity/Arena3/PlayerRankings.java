package com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3;

import java.io.Serializable;

/**
 * Created by smartlab on 16/6/7.
 */
public class PlayerRankings implements Serializable {
    private String playerID;
    private String playerPhoto;
    private String playerName;
    private String worth;
    private String rankings;   //第18与19公用

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerPhoto(String playerPhoto) {
        this.playerPhoto = playerPhoto;
    }

    public String getPlayerPhoto() {
        return playerPhoto;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setWorth(String worth) {
        this.worth = worth;
    }

    public String getWorth() {
        return worth;
    }

    public void setRankings(String rankings) {
        this.rankings = rankings;
    }

    public String getRankings() {
        return rankings;
    }


}
