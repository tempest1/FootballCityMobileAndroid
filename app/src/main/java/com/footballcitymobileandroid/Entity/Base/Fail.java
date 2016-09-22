package com.footballcitymobileandroid.Entity.Base;

import java.io.Serializable;

/**
 * Created by smartlab on 16/5/21.
 */
public class Fail implements Serializable {
    private int[] atyTime;
    private String e_Type;    //错误类型编码
    private String e_Msg;     //错误说明
    public void setAtyTime(int[] atyTime){
        this.atyTime=atyTime;
    }
    public int[] getAtyTime(){
        return atyTime;
    }
    public void setE_Type(String e_Type)
    {
        this.e_Type=e_Type;
    }
    public String getE_Type()
    {
        return e_Type;
    }
    public void setE_Msg(String e_Msg)
    {
        this.e_Msg=e_Msg;
    }
    public String getE_Msg()
    {
        return e_Msg;
    }
}
