package com.footballcitymobileandroid.BLL.Interface;

import android.util.Log;

import com.footballcitymobileandroid.DAL.HttpEntity.BaseHttp.HttpBase;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.DeployDetail;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.MatchMemb;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.SportDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smartlab on 16/5/23.
 */
public class SportImp {

    private Gson gson = Factory.createGson();
    HttpBase httpBase = Factory.createHttpBase();

    public BaseEntity<Void> fc_addClubSport(final String clubID, final String startTime, final String endTime, final String visitingTeam, final String joinNum, final String sportField, final String sportState, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("clubID", clubID);
        map2.put("startTime", startTime);   //包含完整的年月日时分秒
        map2.put("endTime", endTime);       //包含完整的年月日时分秒
        map2.put("visitingTeam", visitingTeam);
        map2.put("joinNum", joinNum);
        map2.put("sportField", sportField);
        map2.put("sportState", sportState);  //比赛状态：1. 未开始 2. 已开始 3. 已结束

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_addClubSport");
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

    public BaseEntity<Void> fc_delClubSport(final String clubID, final String sportID, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("clubID", clubID);
        map2.put("sportID", sportID);


        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_delClubSport");
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

    public BaseEntity<SportDetail> fc_checkSportDetail(final String clubID, final String sportState, final String page, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("clubID", clubID);
        map2.put("sportState", sportState);
        map2.put("page", page);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_checkSportDetail");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<SportDetail>>() {
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

    public BaseEntity<Void> fc_editScore(final String clubID, final String sportID, final String homeScore, final String visitingScore, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("clubID", clubID);
        map2.put("sportID", sportID);
        map2.put("homeScore", homeScore);
        map2.put("visitingScore", visitingScore);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_editScore");
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

    public BaseEntity<Void> fc_sportSign(final String clubID, final String sportID, final String sign, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("clubID", clubID);
        map2.put("sportID", sportID);
        map2.put("sign", sign);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_sportSign");
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

    public BaseEntity<MatchMemb> fc_checkSportMemb(final String clubID, final String sportID, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("clubID", clubID);
        map2.put("sportID", sportID);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_checkSportMemb");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<MatchMemb>>() {
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

    public BaseEntity<Void> fc_deploySport(final String clubID, final String sportID, final List<DeployDetail> deployDetail, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("clubID", clubID);
        map2.put("sportID", sportID);
        map2.put("deployDetail", deployDetail);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_deploySport");
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