package com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smartlab on 16/7/26.
 */
public class DeployInfos {
    private String clubMembID;
    private String is_main;
    private String coord_X;
    private String coord_Y;

    public static List<String> ISMAIN=new ArrayList<>();
    public static List<String> USERID=new ArrayList<>();



    public void setIs_main(String is_main) {
        this.is_main = is_main;
    }

    public String getIs_main() {
        return is_main;
    }

    public void setClubMembID(String clubMembID) {
        this.clubMembID = clubMembID;
    }

    public String getClubMembID() {
        return clubMembID;
    }

    public void setCoord_X(String coord_X) {
        this.coord_X = coord_X;
    }

    public String getCoord_X() {
        return coord_X;
    }

    public void setCoord_Y(String coord_Y) {
        this.coord_Y = coord_Y;
    }

    public String getCoord_Y() {
        return coord_Y;
    }
}
