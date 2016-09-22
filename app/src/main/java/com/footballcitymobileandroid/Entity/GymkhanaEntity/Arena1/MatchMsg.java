package com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1;

/**
 * Created by smartlab on 16/7/22.
 */
public class MatchMsg {
    public static String sender;    //clubid
    public static String season_id;   //赛季id
    public static String matchingDate;
    public static String matchingTime="00:00:00";
    public static String matchRule;       //赛制
    public static String costModeKey;     //0主场支付，1aa制
    public static String[] receiver;       //邀请俱乐部数组最对3个
    public static String fieldID;        //场地ID,在发送匹配时未使用
}
