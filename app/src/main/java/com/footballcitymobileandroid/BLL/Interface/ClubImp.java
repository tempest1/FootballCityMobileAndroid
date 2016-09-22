package com.footballcitymobileandroid.BLL.Interface;

import android.util.Log;

import com.footballcitymobileandroid.DAL.HttpEntity.BaseHttp.HttpBase;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Club;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubMemb;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubRecord;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Clubs;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Send;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Wrong;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smartlab on 16/5/30.
 */
public class ClubImp {
    private Gson gson = Factory.createGson();
    HttpBase httpBase = Factory.createHttpBase();

    /**
     * 1、创建俱乐部（fc_createClub）（登入：有，系统权限：无，业务权限：无）
     *
     * @param logo
     * @param clubName
     * @param cityID
     * @param createTime
     * @param atyFieldID
     * @param atyTime
     * @param url
     * @return
     */
    public BaseEntity<Club> fc_createClub(final String logo, final String clubName, final String cityID, final String createTime, final String atyFieldID, final int[] atyTime,final String welfare , final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("logo", logo);
        map2.put("clubName", clubName);
        map2.put("cityID", cityID);
        map2.put("createTime", createTime);
        map2.put("atyFieldID", atyFieldID);
        map2.put("atyTime", atyTime);
        map2.put("welfare",welfare);


        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_createClub");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<Club>>() {
        }.getType();
        String json = gson.toJson(map);
        try {
            return httpBase.postHandle(json, url, type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh", "服务异常");
            return null;
        }
    }

    /**
     * 2、修改俱乐部（fc_modifyClub）（登入：有，业务权限：领队)
     * "modifyType": "logo",//clubName,createTime,clubWelfare  //默认图标只是其中一项
     *
     * @param clubID
     * @param url
     * @return
     */
    public BaseEntity<Wrong> fc_modifyClub(final String clubID,final String type,final Object typevalue, final String url) {

        Map<String, Object> map3 = new HashMap<>();
        map3.put("modifyType", type);
        map3.put("value", typevalue);


        List<Map<String, Object>> modifyInfo = new ArrayList<>();
        modifyInfo.add(map3);


        Map<String, Object> map2 = new HashMap<>();
        map2.put("clubID", clubID);
        map2.put("modifyInfo", modifyInfo);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_modifyClub");
        map.put("request", map2);

        Type types = new TypeToken<BaseEntity<Wrong>>() {
        }.getType();
        String json = gson.toJson(map);
        try {
            return httpBase.postHandle(json, url, types);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh", "服务异常");
            return null;
        }
    }

    /**
     * 3、改变俱乐部领队（fc_changeLeader）（登入：有，业务权限：领队）
     *
     * @param clubID
     * @param clubMembID
     * @param url
     * @return
     */
    public BaseEntity<Void> fc_changeLeader(final String clubID, final String clubMembID, final String url) {

        Map<String, Object> map2 = new HashMap<>();
        map2.put("clubID", clubID);
        map2.put("clubMembID", clubMembID);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_changeLeader");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<Void>>() {
        }.getType();
        String json = gson.toJson(map);
        try {
            return httpBase.postHandle(json, url, type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh", "服务异常");
            return null;
        }
    }

    /**
     * 4.获取用户俱乐部列表(fc_getClubList)
     * "msg":"leader/user";
     *
//     * @param msg
     * @param url
     * @return
     */
    public BaseEntity<ClubList> fc_getClubList(final String url) {//final String msg,

        Map<String, Object> map2 = new HashMap<>();
//        map2.put("msg", msg);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_getClubList");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<ClubList>>() {}.getType();
        String json = gson.toJson(map);
        try {
            return httpBase.postHandle(json, url, type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh", "服务异常");
            return null;
        }
    }

    /**
     * 5、解散俱乐部（fc_fireClub）
     *
     * @param clubID
     * @param url
     * @return
     */
    public BaseEntity<Void> fc_fireClub(final String clubID, final String url) {

        Map<String, Object> map2 = new HashMap<>();
        map2.put("clubID", clubID);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_fireClub");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<Void>>() {
        }.getType();
        String json = gson.toJson(map);
        try {
            return httpBase.postHandle(json, url, type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh", "服异常");
            return null;
        }
    }

    /**
     * 6、fc_queryClubs待完善
     * order:"prev/next",//表示升序或降序
     * condition:条件对象，当为null时，则是查找所有clubs
     * condition包括：
     * {key：俱乐部名称；value：}
     * {key：年龄；value：}
     * {key：活动时间；value：}
     * {key:aty_Field;value:}
     *
     * @param page
     * @param clubName
     * @param age
     * @param aty_Time
     * @param aty_Field
     * @param url
     * @return
     */
    public BaseEntity<Clubs> fc_queryClubs(final String page, final String clubName, final String  age, final int[] aty_Time, final String aty_Field, final String url) {

//        Map<String, Object> map3 = new HashMap<>();
//        map3.put("clubName", clubName);
//        Map<String, Object> map4 = new HashMap<>();
//        map4.put("age", age);
//        Map<String, Object> map5 = new HashMap<>();
//        map5.put("aty_Time", aty_Time);
//        Map<String, Object> map6 = new HashMap<>();
//        map6.put("aty_Field", aty_Field);

        Map<String, Object> condition = new HashMap<>();
        condition.put("clubName", clubName);
        condition.put("age", age);
        condition.put("aty_Time", aty_Time);
        condition.put("aty_Field", aty_Field);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("page", page);
//        map2.put("order", order);
        map2.put("condition", condition);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_queryClubs");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<Clubs>>() {
        }.getType();
        String json = gson.toJson(map);
        try {
            return httpBase.postHandle(json, url, type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh", "服务异常");
            return null;
        }
    }

    /**
     * 7、获取俱乐部详细信息（fc_queryClubDetail）
     *
     * @param clubID
     * @param url
     * @return
     */
    public BaseEntity<Club> fc_queryClubDetail(final String clubID, final String url) {

        Map<String, Object> map2 = new HashMap<>();
        map2.put("clubID", clubID);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_queryClubDetail");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<Club>>() {
        }.getType();
        String json = gson.toJson(map);
        try {
            return httpBase.postHandle(json, url, type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh", "服异常");
            return null;
        }
    }

    /**
     * 8、获取俱乐部比赛记录（fc_queryClubCurRecord）
     *
     * @param clubID
     * @param url
     * @return
     */
    public BaseEntity<ClubRecord> fc_queryClubRecord(final String clubID, final String url) {

        Map<String, Object> map2 = new HashMap<>();
        map2.put("clubID", clubID);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_queryClubCurRecord");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<ClubRecord>>() {
        }.getType();
        String json = gson.toJson(map);
        try {
            return httpBase.postHandle(json, url, type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh", "服异常");
            return null;
        }
    }

    /**
     * 9、获取俱乐部比赛记录（分页）（fc_queryClubRecords）
     * "page":1
     *
     * @param clubID
     * @param page
     * @param url
     * @return
     */
    public BaseEntity<ClubRecord> fc_queryClubRecords(final String clubID, final String page, final String url) {

        Map<String, Object> map2 = new HashMap<>();
        map2.put("clubID", clubID);
        map2.put("page", page);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_queryClubRecords");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<ClubRecord>>() {
        }.getType();
        String json = gson.toJson(map);
        try {
            return httpBase.postHandle(json, url, type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh", "服异常");
            return null;
        }
    }

    /**
     * 10、分配球员场上位置（fc_setPosition)  针对一个球员进行一次操作,因为人数未定故不可一次操作所有
     *
     * @param clubID
//     * @param clubMembID
//     * @param positionID
     * @param url
     * @return
     */
    public BaseEntity<Void> fc_setPosition(final String clubID, final List<Send> sends, final String url) {


//        Map<String, Object> map3 = new HashMap<>();
//        map3.put("clubMembID", clubMembID);
//        map3.put("positionID", positionID);
//
//        List<Map<String, Object>> clubMembPosition = new ArrayList<>();
//        clubMembPosition.add(map3);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("clubID", clubID);
        map2.put("clubMembPosition", sends);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_setPosition");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<Void>>() {
        }.getType();
        String json = gson.toJson(map);
        try {
            return httpBase.postHandle(json, url, type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh", "服异常");
            return null;
        }
    }

    /**
     * 11、解雇俱乐部成员（fc_fireClubMemb）
     *
     * @param clubID
     * @param clubMembID
     * @param url
     * @return
     */
    public BaseEntity<Void> fc_fireClubMemb(final String clubID, final String clubMembID, final String url) {

        Map<String, Object> map2 = new HashMap<>();
        map2.put("clubID", clubID);
        map2.put("clubMembID", clubMembID);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_fireClubMemb");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<Void>>() {
        }.getType();
        String json = gson.toJson(map);
        try {
            return httpBase.postHandle(json, url, type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh", "服异常");
            return null;
        }
    }

    /**
     * 12、检查俱乐部球员信息（fc_checkClubMemb）
     *
     * @param clubID
     * @param url
     * @return
     */
    public BaseEntity<ClubMemb> fc_checkClubMemb(final String clubID, final String url) {

        Map<String, Object> map2 = new HashMap<>();
        map2.put("clubID", clubID);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_checkClubMemb");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<ClubMemb>>() {
        }.getType();
        String json = gson.toJson(map);
        try {
            return httpBase.postHandle(json, url, type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh", "服异常");
            return null;
        }
    }

    /**
     * 13、退出俱乐部（fc_exitClub）
     *
     * @param clubID
     * @param url
     * @return
     */
    public BaseEntity<Void> fc_exitClub(final String clubID, final String url) {

        Map<String, Object> map2 = new HashMap<>();
        map2.put("clubId", clubID);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_exitClub");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<Void>>() {
        }.getType();
        String json = gson.toJson(map);
        try {
            return httpBase.postHandle(json, url, type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh", "服异常");
            return null;
        }
    }


}
