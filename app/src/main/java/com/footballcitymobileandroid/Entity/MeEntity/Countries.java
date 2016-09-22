package com.footballcitymobileandroid.Entity.MeEntity;

import java.util.List;

/**
 * Created by smartlab on 16/5/23.
 */
public class Countries {
    private String country_id;
    private String country_name;
    private List<City> cities;
    public void setCountry_id(String country_id){
        this.country_id=country_id;
    }
    public String getCountry_id(){
        return country_id;
    }
    public void setCountry_name(String country_name){
        this.country_name=country_name;
    }
    public String getCountry_name(){
        return country_name;
    }
    public void setCities(List<City> cities){
        this.cities=cities;
    }
    public List<City> getCities(){
        return cities;
    }
}
