package com.footballcitymobileandroid.Entity.MeEntity;

import java.io.Serializable;

/**
 * Created by smartlab on 16/7/20.
 */
public class PlayerRecord implements Serializable{
    private String arena_Name; //竞技场
    private String arena_SeasonName;//赛季
    private String worth;   //声价
    private String sessions;
    private String wins;    //胜
    private String loses;   //败
    private String draws;   //平

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

    public void setWorth(String worth) {
        this.worth = worth;
    }

    public String getWorth() {
        return worth;
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
}
