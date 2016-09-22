package com.footballcitymobileandroid.Entity.ClubEntity.sport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smartlab on 16/5/23.
 */
public class DeployDetail {
    private String user_id;
    private String is_main;
    private String coordX;
    private String coordY;

    public static List<String> ISMAIN=new ArrayList<>();
    public static List<String> USERID=new ArrayList<>();

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setIs_main(String is_main) {
        this.is_main = is_main;
    }

    public String getIs_main() {
        return is_main;
    }

    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public String getCoordX() {
        return coordX;
    }

    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }

    public String getCoordY() {
        return coordY;
    }

}
