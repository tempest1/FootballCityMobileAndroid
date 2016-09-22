package com.footballcitymobileandroid.Entity.MeEntity;

import java.io.Serializable;

/**
 * Created by smartlab on 16/5/7.
 * 用户数据 可能需要持久化保存
 */
public class userInfo  implements Serializable {
    private String phoneNumb;    //手机号码
    private String passWord;    //用户密码
    private String  photo;      //用户头像  byte[]的十六进制字符串编码
    private String name;      //用户姓名
    private String sex;         //用户性别 0:男,1:女  强制转换成int
    private String birthday;   //用户生日  "yyyy-mm-dd"
    private String height;   //用户身高  整型cm
    private String weight;   //用户体重  整型kg
    private int[] position; //用户位置,前锋中锋等  Position_level2的"id_name"值
    private int[] atyTime; //用户活动时间，整型数组，1-7，表示周一到周日
    private String atyField;   //用户活动场地  Field的"id_name"值
    private String myCity;  //用户所在城市  city的"id_name"值

    public void setPhoneNumb(String phoneNumb) {
        this.phoneNumb = phoneNumb;
    }

    public String getPhoneNumb() {
        return phoneNumb;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPhoto(String  photo) {
        this.photo = photo;
    }

    public String  getPhoto() {
        return photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setBrithday(String brithday) {
        this.birthday = brithday;
    }

    public String  getBrithday() {
        return birthday;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHeight() {
        return height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeight() {
        return weight;
    }

    public void setPosition(int[] position)
    {
        this.position=position;
    }
    public int[] getPosition()
    {
        return position;
    }
    public void setAtyTime(int[] atyTime)
    {
        this.atyTime=atyTime;
    }
    public int[] getAtyTime()
    {
        return atyTime;
    }
    public void setAtyField(String  atyField)
    {
        this.atyField=atyField;
    }
    public String getAtyField()
    {
        return atyField;
    }
    public void setMyCity(String myCity)
    {
        this.myCity=myCity;
    }
    public String getMyCity()
    {
        return myCity;
    }

    public String toString(){
        return "phonenum: "+getPhoneNumb()+"passWord: "+getPassWord()+"name:"+getName()+"sex:"+getSex()+"birthday:"+getBrithday()+"height:"+getHeight()+
                "weight:"+getWeight();
    }
}
