package com.footballcitymobileandroid.BLL.Interface;

import android.util.Log;

import com.footballcitymobileandroid.DAL.HttpEntity.BaseHttp.HttpBase;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena2.AranaMatchMembs;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena2.DeployInfos;
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
public class Arena2Imp {
    private Gson gson = Factory.createGson();
    HttpBase httpBase = Factory.createHttpBase();

    /**
     * 6.8.1/2 签到(fc_arenaSign)（登陆：有 权限：无 业务角色：俱乐部成员）
     */
    public BaseEntity<Void> fc_arenaSign(final String arenaMatchID, final String clubID, final String signIn, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("arenaMatchID", arenaMatchID);
        map2.put("clubID", clubID);
        map2.put("sign", signIn);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_arenaSign");
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
     * 6.8.3 查询竞技场签到成员(fc_checkArenaMatchMemb)（登陆：有 权限：无 业务角色：俱乐部成员）
     */
    public BaseEntity<AranaMatchMembs> fc_checkArenaMatchMemb(final String arenaMatchID, final String clubID, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("arenaMatchID", arenaMatchID);
        map2.put("clubID", clubID);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_checkArenaMatchMemb");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<AranaMatchMembs>>() {
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
     * 6.9.1 布置竞技场比赛人员(fc_deployArenaMatch)（登陆：有 权限：无 业务角色：俱乐部领队）
     */
    public BaseEntity<Void> fc_deployArenaMatch(final String arenaMatchID, final String clubID, final List<DeployInfos> deployInfosList, final String url) {
//        Map<String, Object> map3 = new HashMap<>();
//        map3.put("clubMembID", clubMembID);
//        map3.put("is_main", is_main);
//        map3.put("coord_X", coord_X);
//        map3.put("coord_Y", coord_Y);

//        List<Map<String, Object>> map4 = new ArrayList<>();
//        map4.add(map3);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("arenaMatchID", arenaMatchID);
        map2.put("clubID", clubID);
        map2.put("deployInfos", deployInfosList);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_deployArenaMatch");
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
     * 6.10.1 退赛申请(fc_quitApply)（登陆：有 权限：无 业务角色：俱乐部领队）
     */
    public BaseEntity<Void> fc_quitApply(final String arenaMatchID, final String clubID, final String applyDesc, final String url) {

        Map<String, Object> map2 = new HashMap<>();
        map2.put("arenaMatchID", arenaMatchID);
        map2.put("clubID", clubID);
        map2.put("applyDesc", applyDesc);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_quitApply");
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
     * 6.10.2/3 处理退赛申请(fc_dealQuitApply)（登陆：有 权限：无 业务角色：俱乐部领队）
     */
    public BaseEntity<Void> fc_dealQuitApply(final String arenaMatchID, final String clubID, final String dealResult, final String applyDesc, final String url) {

        Map<String, Object> map2 = new HashMap<>();
        map2.put("arenaMatchID", arenaMatchID);
        map2.put("clubID", clubID);
        map2.put("dealResult", dealResult);
        map2.put("applyDesc", applyDesc);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_dealQuitApply");
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
     * 6.10.4 强行退赛申请(fc_forcedQuit)（登陆：有 权限：无 业务角色：俱乐部领队）
     */
    public BaseEntity<Void> fc_forcedQuit(final String arenaMatchID, final String clubID, final String url) {

        Map<String, Object> map2 = new HashMap<>();
        map2.put("arenaMatchID", arenaMatchID);
        map2.put("clubID", clubID);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_forcedQuit");
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
