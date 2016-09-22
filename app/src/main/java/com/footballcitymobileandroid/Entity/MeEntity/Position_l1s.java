package com.footballcitymobileandroid.Entity.MeEntity;

import java.util.List;

/**
 * Created by smartlab on 16/5/23.
 */
public class Position_l1s {
    private String position_l1_id;
    private String position_l1_name;
    private List<Position_l2s> position_l2s;
    public void setPosition_l1_id(String position_l1_id){
        this.position_l1_id=position_l1_id;
    }
    public String getPosition_l1_id(){
        return position_l1_id;
    }
    public void setPosition_l1_name(String position_l1_name){
        this.position_l1_name=position_l1_name;
    }
    public String getPosition_l1_name(){
        return position_l1_name;
    }
    public void setPosition_l2s(List<Position_l2s> position_l2s){
        this.position_l2s=position_l2s;
    }
    public List<Position_l2s> getPosition_l2s(){
        return position_l2s;
    }
}
