package com.footballcitymobileandroid.BLL.Interface;

import android.util.Log;

import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.HttpEntity.BaseHttp.HttpBase;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.ClubRankings;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.GradeList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.NomiMembs;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.PlayerRankings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smartlab on 16/6/4.
 */
public class Arena3Imp {
    private Gson gson = Factory.createGson();
    HttpBase httpBase = Factory.createHttpBase();

    /**
     * 14、编辑进球数（fc_editGoals）
     */
    public BaseEntity<Void> fc_editGoals(final String arenaMatchID, final String clubID, final String goals, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("arenaMatchID", arenaMatchID);
        map2.put("clubID", clubID);
        map2.put("goals", goals);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_editGoals");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<Void>>() {
        }.getType();
        String json = gson.toJson(map);
        LogUtils.e("json="+json);
        try {
            return httpBase.postHandle(json, url, type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh", "服务异常");
            return null;
        }
    }

    /**
     * 15、提交提名名单（fc_gradeMatchMemb）
     */
    public BaseEntity<Void> fc_gradeMatchMemb(final String arenaMatchID, final String clubID, final List<GradeList> gradeList, final String url) {

//        Map<String, Object> map3 = new HashMap<>();
//        map3.put("clubMembID", clubMembID);
//        map3.put("score", score);
//        map3.put("nomination", nomination);
//
//        List<Map<String, Object>> map4 = new ArrayList<>();
//        map4.add(map3);


        Map<String, Object> map2 = new HashMap<>();
        map2.put("arenaMatchID", arenaMatchID);
        map2.put("clubID", clubID);
        map2.put("gradeList", gradeList);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_gradeMatchMemb");
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
     * 16、检查提名名单（fc_checkNominationMemb）
     */
    public BaseEntity<NomiMembs> fc_checkNominationMemb(final String arenaMatchID, final String clubID, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("arenaMatchID", arenaMatchID);
        map2.put("clubID", clubID);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_checkNominationMemb");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<NomiMembs>>() {
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
     * 17，投票（fc_voteNomiMemb）
     */
    public BaseEntity<Void> fc_voteNomiMemb(final String arenaMatchID, final String clubID, final String voteTo, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("arenaMatchID", arenaMatchID);
        map2.put("clubID", clubID);
        map2.put("voteTo", voteTo);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_voteNomiMemb");
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
     * 18、检查竞技场选手排行(fc_checkArenaPlayerRankings)
     */
    public BaseEntity<PlayerRankings> fc_checkArenaPlayerRankings(final String seasonID, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("seasonID", seasonID);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_checkArenaPlayerRankings");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<PlayerRankings>>() {
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
     * 19、检查竞技场俱乐部排行(fc_checkArenaClubRankings)
     */
    public BaseEntity<ClubRankings> fc_checkArenaClubRankings(final String seasonID, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("seasonID", seasonID);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_checkArenaClubRankings");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<ClubRankings>>() {
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

}
