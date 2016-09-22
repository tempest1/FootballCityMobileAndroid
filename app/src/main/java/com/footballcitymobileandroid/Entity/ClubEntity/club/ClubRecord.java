package com.footballcitymobileandroid.Entity.ClubEntity.club;

import java.io.Serializable;

/**
 * Created by smartlab on 16/5/24.
 */
public class ClubRecord implements Serializable{
    private String arena_Name;  //竞技场名称
    private String arena_SeasonName;//赛季名称
    private String point;   //积分

    private String sessions;
    private String wins;  //胜
    private String loses; //负
    private String draws;//平
    private String rankings;//排行

    public void setArena_Name(String arena_Name) {
        this.arena_Name = arena_Name;
    }

    public String getArena_Name() {
        return arena_Name;
    }

    public void setArena_SeasonName(String arena_SeasonName) {
        this.arena_SeasonName = arena_SeasonName;
    }

    public String getArena_SeasonName() {
        return arena_SeasonName;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPoint() {
        return point;
    }

    public void setSessions(String sessions) {
        this.sessions = sessions;
    }

    public String getSessions() {
        return sessions;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getWins() {
        return wins;
    }

    public void setLoses(String loses) {
        this.loses = loses;
    }

    public String getLoses() {
        return loses;
    }

    public void setDraws(String draws) {
        this.draws = draws;
    }

    public String getDraws() {
        return draws;
    }

    public void setRankings(String rankings) {
        this.rankings = rankings;
    }

    public String getRankings() {
        return rankings;
    }


}

