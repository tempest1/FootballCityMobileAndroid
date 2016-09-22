package com.footballcitymobileandroid.Entity.ClubEntity.club;

/**
 * Created by smartlab on 16/7/27.
 */
public class Send {
    private String positionID;
    private String clubMembID;

    public String getPositionID() {
        return positionID;
    }

    public void setPositionID(String positionID) {
        this.positionID = positionID;
    }

    public String getClubMembID() {
        return clubMembID;
    }

    public void setClubMembID(String clubMembID) {
        this.clubMembID = clubMembID;
    }

    @Override
    public String toString() {
        return "Send{" +
                "positionID='" + positionID + '\'' +
                ", clubMembID='" + clubMembID + '\'' +
                '}';
    }
}
