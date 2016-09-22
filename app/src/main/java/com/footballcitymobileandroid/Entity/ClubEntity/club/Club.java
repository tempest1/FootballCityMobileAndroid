package com.footballcitymobileandroid.Entity.ClubEntity.club;

import java.io.Serializable;

/**
 * Created by smartlab on 16/5/24.
 */
public class Club implements Serializable{
    private String clubID;
    private String logo;
    private String clubName;
    private String cityID;
    private String createTime;
    private String atyField;
    private String  atyTime;
    private int[] atyTimes;
    private String LeaderId;
    private String LeaderName;
    private String memberNumb;
    private String aveAge;
    private String clubWalfare;

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

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setAtyField(String atyField) {
        this.atyField = atyField;
    }

    public String getAtyField() {
        return atyField;
    }

    public void setLeaderId(String leaderId) {
        LeaderId = leaderId;
    }

    public String getLeaderId() {
        return LeaderId;
    }

    public void setLeaderName(String leaderName) {
        LeaderName = leaderName;
    }

    public String getLeaderName() {
        return LeaderName;
    }

    public void setAtyTime(String atyTime) {
        this.atyTime = atyTime;
    }

    public String getAtyTime() {
        return atyTime;
    }

    public void setAtyTimes(int[] atyTimes) {
        this.atyTimes = atyTimes;
    }

    public int[] getAtyTimes() {
        return atyTimes;
    }

    public void setMemberNumb(String memberNumb) {
        this.memberNumb = memberNumb;
    }

    public String getMemberNumb() {
        return memberNumb;
    }

    public void setAveAge(String aveAge) {
        this.aveAge = aveAge;
    }

    public String getAveAge() {
        return aveAge;
    }

    public void setClubWalfare(String clubWalfare) {
        this.clubWalfare = clubWalfare;
    }
    public String getClubWalfare(){
        return clubWalfare;
    }

    @Override
    public String toString() {
        return "Club{" +
                "clubID='" + clubID + '\'' +
//                ", logo='" + logo + '\'' +
                ", clubName='" + clubName + '\'' +
                ", cityID='" + cityID + '\'' +
                ", createTime='" + createTime + '\'' +
                ", atyField='" + atyField + '\'' +
                ", atyFieldID='" + atyField + '\'' +
                ", atyTime=" +atyTime +
                ", LeaderID='" + LeaderId + '\'' +
                ", memberNumb='" + memberNumb + '\'' +
                ", aveAge='" + aveAge + '\'' +
                ", clubWalfare='" + clubWalfare + '\'' +
                '}';
    }
}
