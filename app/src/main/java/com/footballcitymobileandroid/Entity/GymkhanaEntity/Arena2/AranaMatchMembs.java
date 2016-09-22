package com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena2;

import android.net.wifi.p2p.WifiP2pManager;

import java.io.Serializable;

/**
 * Created by smartlab on 16/6/7.
 */
public class AranaMatchMembs implements Serializable {
    private String clubMembID;
    private String playerPhoto;
    private String playerName;
    private String coord;
    private String coord_X;
    private String coord_Y;

    public void setClubMembID(String clubMembID) {
        this.clubMembID = clubMembID;
    }

    public String getClubMembID() {
        return clubMembID;
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

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public String getCoord() {
        return coord;
    }

    public void setCoord_X(String coord_X) {
        this.coord_X = coord_X;
    }

    public String getCoord_X() {
        return coord_X;
    }

    public void setCoord_Y(String coord_Y) {
        this.coord_Y = coord_Y;
    }

    public String getCoord_Y() {
        return coord_Y;
    }

}
