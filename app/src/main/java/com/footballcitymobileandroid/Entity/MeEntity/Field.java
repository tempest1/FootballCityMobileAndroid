package com.footballcitymobileandroid.Entity.MeEntity;

/**
 * Created by smartlab on 16/5/23.
 */
public class Field {
    private String field_id;
    private String field_name;
    private String field_address;
    public void setField_id(String field_id){
        this.field_id=field_id;
    }
    public String getField_id(){
        return field_id;
    }
    public void setField_name(String field_name){
        this.field_name=field_name;
    }
    public String getField_name(){
        return field_name;
    }
    public void setField_address(String field_address){
        this.field_address=field_address;
    }
    public String getField_address(){
        return field_address;
    }
}
