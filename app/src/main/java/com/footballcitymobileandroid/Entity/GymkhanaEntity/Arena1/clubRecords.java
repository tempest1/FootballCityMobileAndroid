package com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1;

import java.io.Serializable;

/**
 * Created by smartlab on 16/6/4.
 */
public class clubRecords implements Serializable{
    private String arena_Name;
    private String arena_Season;
    private String integral;
    private String ranking;

    public void setArena_Name(String arena_Name) {
        this.arena_Name = arena_Name;
    }

    public String getArena_Name() {
        return arena_Name;
    }

    public void setArena_Season(String arena_Season) {
        this.arena_Season = arena_Season;
    }

    public String getArena_Season() {
        return arena_Season;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getIntegral() {
        return integral;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getRanking() {
        return ranking;
    }

}
