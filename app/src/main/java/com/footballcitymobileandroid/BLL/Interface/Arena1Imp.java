package com.footballcitymobileandroid.BLL.Interface;

import android.util.Log;

import com.footballcitymobileandroid.DAL.HttpEntity.BaseHttp.HttpBase;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Clubs;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.Condition;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.MatchingInfo;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.updateArena;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smartlab on 16/6/4.
 */
public class Arena1Imp {
    private Gson gson = Factory.createGson();
    HttpBase httpBase = Factory.createHttpBase();

    /**
     * 1.查询竞技场（fc_queryArena）（无需登录，系统权限：无，业务权限：无）
     *
     * @param cityID
     * @param arenaID
     * @param seasonID
     * @param updateTag
     * @param url
     * @return
     */
    public BaseEntity<updateArena> fc_queryArena(final String cityID, final String arenaID, final String seasonID, final String updateTag, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("arenaID", arenaID);
        map2.put("seasonID", seasonID);
        map2.put("updateTag", updateTag);

        List<Map<String, Object>> obj = new ArrayList<>();
        obj.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("cityID", cityID);
        map3.put("arenaObjs", obj);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_queryArena");
        map.put("request", map3);

        Type type = new TypeToken<BaseEntity<updateArena>>() {
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
     * 2.获取匹配条件（fc_getMatchingCondition）（需登录，需要是领队，业务权限：需要有一家俱乐部）
     *
     * @param url
     * @return
     */
    public BaseEntity<Condition> fc_getMatchingCondition(final String url) {
        Map<String, Object> map2 = new HashMap<>();


        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_getMatchingCondition");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<Condition>>() {
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
     * 3得到能匹配的俱乐部（fc_getMatchingClub）（无需登录，系统权限：无，业务权限：无）
     *
     * @param page
     * @param fieldID
     * @param matchingDate
     * @param url
     * @return
     */
    public BaseEntity<Clubs> fc_getMatchingClub(final String page, final String fieldID, final String matchingDate, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("page", page);
        map2.put("fieldID", fieldID);
        map2.put("matchingDate", matchingDate);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_getMatchingClub");
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
     * 4.发送匹配请求（fc_sendMatchingMsg）（需登录，系统权限：无，业务权限：无）
     * "receiver":[
     * "clubID",
     * "clubID",
     * "clubID"
     * ]
     *
     * @param sender
     * @param season_id
     * @param matchingDate
     * @param matchingTime
     * @param matchRule
     * @param costModeKey
     * @param receiver
     * @param url
     * @return
     */
    public BaseEntity<Void> fc_sendMatchingMsg(final String sender, final String season_id, final String matchingDate, final String matchingTime, final String matchRule, final String costModeKey, final List<String> receiver, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("sender", sender);
        map2.put("season_id", season_id);
        map2.put("matchingDate", matchingDate);
        map2.put("matchingTime", matchingTime);
        map2.put("matchRule", matchRule);
        map2.put("costModeKey", costModeKey);
        map2.put("receiver", receiver);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_sendMatchingMsg");
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
     * 5查看匹配信息（fc_checkMatchingMsg） （需登录，系统权限：无，业务权限：无） getType 区分sender与reciver
     *
     * @param page
     * @param clubID
     * @param url
     * @return
     */
    public BaseEntity<MatchingInfo> fc_checkMatchingMsg(final String getType, final String page, final String clubID, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("getType", getType);
        map2.put("page", page);
        map2.put("clubID", clubID);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_checkMatchingMsg");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<MatchingInfo>>() {
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
     * 6.查看竞技挑战赛（fc_checkArenaMatch）（需登录，系统权限：无，业务权限：无）6种状态
     * '挑战赛状态：1-待开始；2-退赛中；3-已开始；4-强制退赛结束；5-正常退赛结束；6-正常比赛结束',
     *
     * @param arenaMatchState
     * @param page
     * @param clubID
     * @param url
     * @return
     */
    public BaseEntity<AranaMatchs> fc_checkArenaMatch(final String arenaMatchState, final String page, final String clubID, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("arenaMatchState", arenaMatchState);
        map2.put("page", page);
        map2.put("clubID", clubID);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_checkArenaMatch");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<AranaMatchs>>() {
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
     * 7.处理匹配请求（fc_dealMatchingMsg）（需登录，系统权限：无，业务权限：无）
     *
     * @param arenaMatchID
     * @param clubID
     * @param signIn
     * @param url
     * @return
     */
    public BaseEntity<Void> fc_dealMatchingMsg(final String arenaMatchID, final String clubID, final String signIn, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("arenaMatchID", arenaMatchID);
        map2.put("clubID", clubID);
        map2.put("signIn", signIn);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_dealMatchingMsg");
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

}
