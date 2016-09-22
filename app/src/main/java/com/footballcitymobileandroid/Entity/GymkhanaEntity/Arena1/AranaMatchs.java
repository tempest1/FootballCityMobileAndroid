package com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1;

import java.io.Serializable;

/**
 * Created by smartlab on 16/6/4.
 */
public class AranaMatchs implements Serializable     {
    private String myClub; //主队
    private HomeTeam homeTeam; // \
    private VisitingTeam visitingTeam;// 客队
    private String arenaName;//竞技场名
    private String seasonName;//赛季名
    private String arenaMatchID;//竞技场比赛id
    private String matchDate;//比赛日期
    private String matchTime;//比赛时间
    private String matchRule;//比赛规则
    private String costMode;//支付方式

    public void setMyClub(String myClub) {
        this.myClub = myClub;
    }

    public String getMyClub() {
        return myClub;
    }

    public void setHomeTeam(HomeTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    public void setVisitingTeam(VisitingTeam visitingTeam) {
        this.visitingTeam = visitingTeam;
    }

    public VisitingTeam getVisitingTeam() {
        return visitingTeam;
    }

    public void setAranaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public String getAranaName() {
        return arenaName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setAreanaMatchID(String areanaMatchID) {
        this.arenaMatchID = arenaMatchID;
    }

    public String getAreanaMatchID() {
        return arenaMatchID;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchRule(String matchRule) {
        this.matchRule = matchRule;
    }

    public String getMatchRule() {
        return matchRule;
    }

    public void setCostMode(String costMode) {
        this.costMode = costMode;
    }

    public String getCostMode() {
        return costMode;
    }

    @Override
    public String toString() {
        return "AranaMatchs{" +
                "myClub='" + myClub + '\'' +
                ", homeTeam=" + homeTeam +
                ", visitingTeam=" + visitingTeam +
                ", arenaName='" + arenaName + '\'' +
                ", seasonName='" + seasonName + '\'' +
                ", areanaMatchID='" + arenaMatchID + '\'' +
                ", matchDate='" + matchDate + '\'' +
                ", matchTime='" + matchTime + '\'' +
                ", matchRule='" + matchRule + '\'' +
                ", costMode='" + costMode + '\'' +
                '}';
    }
}
