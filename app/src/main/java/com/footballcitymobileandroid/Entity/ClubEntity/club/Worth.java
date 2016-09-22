package com.footballcitymobileandroid.Entity.ClubEntity.club;

import java.io.Serializable;

/**
 * Created by smartlab on 16/5/30.
 */
public class Worth implements Serializable{
    private String arena_Name;
    private String arena_worth;

    public void setArena_Name(String arena_Name) {
        this.arena_Name = arena_Name;
    }

    public String getArena_Name() {
        return arena_Name;
    }

    public void setArena_worth(String arena_worth) {
        this.arena_worth = arena_worth;
    }

    public String getArena_worth() {
        return arena_worth;
    }
}
