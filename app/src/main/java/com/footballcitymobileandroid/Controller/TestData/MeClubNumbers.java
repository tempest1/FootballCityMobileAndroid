package com.footballcitymobileandroid.Controller.TestData;

import java.io.Serializable;

/**
 * Created by zhoudi on 16/5/26.
 */
public class MeClubNumbers implements Serializable {
    private String name;
    private String age;
    private String goodplace;
    private String time;
    private String money;

    public String getGoodplace() {
        return goodplace;
    }

    public void setGoodplace(String goodplace) {
        this.goodplace = goodplace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
