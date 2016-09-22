package com.footballcitymobileandroid.Entity.MeEntity;

import java.util.List;

/**
 * Created by smartlab on 16/5/23.
 */
public class ConfigInfo {
    private List<Countries> countries;
    private List<Position_l1s> position_l1s;

    public void setCountries(List<Countries> countries) {
         this.countries=countries;
     }
    public List<Countries> getCountries(){
        return countries;
    }
    public void setPosition_l1s (List<Position_l1s> position_l1s){
        this.position_l1s=position_l1s;
    }
    public List<Position_l1s> getPosition_l1s(){
        return position_l1s;
    }
}
