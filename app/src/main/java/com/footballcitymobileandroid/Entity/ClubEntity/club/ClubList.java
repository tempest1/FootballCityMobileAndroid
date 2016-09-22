package com.footballcitymobileandroid.Entity.ClubEntity.club;

import java.io.Serializable;

/**
 * Created by smartlab on 16/5/24.
 */
public class ClubList implements Serializable {

//    private Club club;
////    private Exception error;
//public void setClub(Club club) {
//    this.club = club;
//}
//
//    public Club getClub() {
//        return club;
//    }

    private String clubID;
    private String logo;
    private String clubName;
    private String cityID;
    private String createTime;
    private String atyField;//
//    private String atyFieldID;
    private String atyTime;
//    private String LeaderID;
    private String isLeader;
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

//    public void setAtyFieldID(String atyFieldID) {
//        this.atyFieldID = atyFieldID;
//    }
//
//    public String getAtyFieldID() {
//        return atyFieldID;
//    }

    public void setAtyTime(String atyTime) {
        this.atyTime = atyTime;
    }

    public String getAtyTime() {
        return atyTime;
    }

//    public void setLeaderID(String LeaderID) {
//        this.LeaderID = LeaderID;
//    }
//
//    public String getLeaderID() {
//        return LeaderID;
//    }

    public void setIsLeader(String isLeader) {
        this.isLeader = isLeader;
    }

    public String getIsLeader() {
        return isLeader;
    }

    public void setLeaderName(String leaderName) {
        LeaderName = leaderName;
    }

    public String getLeaderName() {
        return LeaderName;
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
        return "ClubList{" +
                "clubID='" + clubID + '\'' +
                ", clubName='" + clubName + '\'' +
                ", cityID='" + cityID + '\'' +
                ", createTime='" + createTime + '\'' +
                ", atyField='" + atyField + '\'' +
//                ", atyFieldID='" + atyFieldID + '\'' +
                ", atyTime='" + atyTime + '\'' +
//                ", LeaderID='" + LeaderID + '\'' +
                ", LeaderName='" + LeaderName + '\'' +
                ", memberNumb='" + memberNumb + '\'' +
                ", aveAge='" + aveAge + '\'' +
                ", clubWalfare='" + clubWalfare + '\'' +
                '}';
    }
}
