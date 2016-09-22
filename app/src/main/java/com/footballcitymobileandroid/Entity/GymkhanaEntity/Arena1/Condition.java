package com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1;

import java.util.List;

/**
 * Created by smartlab on 16/6/4.
 */
public class Condition {
    private List<clubObjs> clubObjs;
    private List<String> datetime;
    private List<Integer> rules;
    private List<costMode> costMode;

    public void setClubObjs(List<clubObjs> clubObjs) {
        this.clubObjs = clubObjs;
    }

    public List<clubObjs> getClubObjs() {
        return clubObjs;
    }

    public void setDatetime(List<String> datetime) {
        this.datetime = datetime;
    }

    public List<String> getDatetime() {
        return datetime;
    }

    public void setRules(List<Integer> rules) {
        this.rules = rules;
    }

    public List<Integer> getRules() {
        return rules;
    }

    public void setCostMode(List<costMode> costMode) {
        this.costMode = costMode;
    }

    public List<costMode> getCostMode() {
        return costMode;
    }
}
