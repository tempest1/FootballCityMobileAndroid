package com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1;

import java.util.List;

/**
 * Created by smartlab on 16/6/4.
 */
public class updateArena {
    private String arenaID;
    private String arenaName;
    private String seasonID;
    private String seasonName;
    private String seasonClubNumb;
    private String fieldID;
    private String fieldName;
    private List<String> seasonImgs;
    private String updateTag;

    public void setArenaID(String arenaID) {
        this.arenaID = arenaID;
    }

    public String getArenaID() {
        return arenaID;
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public String getArenaName() {
        return arenaName;
    }

    public void setSeasonID(String seasonID) {
        this.seasonID = seasonID;
    }

    public String getSeasonID() {
        return seasonID;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonClubNumb(String seasonClubNumb) {
        this.seasonClubNumb = seasonClubNumb;
    }

    public String getSeasonClubNumb() {
        return seasonClubNumb;
    }

    public void setFieldID(String fieldID) {
        this.fieldID = fieldID;
    }

    public String getFieldID() {
        return fieldID;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setSeasonImgs(List<String> seasonImgs) {
        this.seasonImgs = seasonImgs;
    }

    public List<String> getSeasonImgs() {
        return seasonImgs;
    }

    public void setUpdateTag(String updateTag) {
        this.updateTag = updateTag;
    }

    public String getUpdateTag() {
        return updateTag;
    }
}
