package com.footballcitymobileandroid.Entity.ClubEntity.club;

import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.clubRecords;

import java.io.Serializable;
import java.util.List;

/**
 * Created by smartlab on 16/5/24.
 */
public class Clubs implements Serializable{
    private String clubID;
    private String logo;
    private String clubName;
    private String atyTime;
    private String atyField;
    private String lastMatchTime;
    private List<ClubRecord> clubRecord;
    private clubRecords clubRecords;

    public void setClubID(String clubID) {
        this.clubID = clubID;
    }

    public String getClubID() {
        return clubID;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogo() {
        return logo;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setAtyTime(String atyTime) {
        this.atyTime = atyTime;
    }

    public String getAtyTime() {
        return atyTime;
    }

    public void setAtyField(String atyField) {
        this.atyField = atyField;
    }

    public String getAtyField() {
        return atyField;
    }

    public void setLastMatchTime(String lastMatchTime) {
        this.lastMatchTime = lastMatchTime;
    }

    public String getLastMatchTime() {
        return lastMatchTime;
    }

    public void setClubRecord(List<ClubRecord> clubRecord) {
        this.clubRecord = clubRecord;
    }

    public List<ClubRecord> getClubRecord() {
        return clubRecord;
    }

    public void setClubRecords(clubRecords clubRecords) {
        this.clubRecords = clubRecords;
    }

    public clubRecords getClubRecords() {
        return clubRecords;
    }
}
