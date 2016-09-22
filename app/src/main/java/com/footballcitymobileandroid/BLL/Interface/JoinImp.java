package com.footballcitymobileandroid.BLL.Interface;

import android.util.Log;

import com.footballcitymobileandroid.DAL.HttpEntity.BaseHttp.HttpBase;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.join.Invitations;
import com.footballcitymobileandroid.Entity.ClubEntity.join.JoinApplys;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by smartlab on 16/5/23.
 */
public class JoinImp {
    private Gson gson = Factory.createGson();
    HttpBase httpBase = Factory.createHttpBase();

    public BaseEntity<Void> f_createJoinApply( final String receiver, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("receiver", receiver);   //俱乐部id


        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_createJoinApply");
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

    public BaseEntity<Void> fc_createInvitation( final String receiver, final String play, final String url) {
        Map<String, Object> map2 = new HashMap<>();
        map2.put("receiver", receiver);   //俱乐部id
        map2.put("play", play);           //被邀请者id

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_createInvitation");
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

    public BaseEntity<Void> fc_dealJoinApply(final String play, final String receiver, final String dealResult, final String url) {
        Map<String, Object> map2 = new HashMap<>();
//        map2.put("sender", sender);       //邀请者id
        map2.put("play", play);           //被邀请者id
        map2.put("receiver", receiver);   //俱乐部id
        map2.put("dealResult", dealResult);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_dealJoinApply");
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

    public BaseEntity<Void> fc_dealInvitation(final String receiver, final String dealResult, final String url) {
        Map<String, Object> map2 = new HashMap<>();
//        map2.put("sender", sender);       //邀请者id
        map2.put("receiver", receiver);   //俱乐部id
        map2.put("dealResult", dealResult);

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_dealInvitation");
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

    public BaseEntity<Invitations> fc_checkInvitation( final String page, final String url) {
        Map<String, Object> map2 = new HashMap<>();
//        map2.put("sender", sender);       //发送者id（球员）
        map2.put("page", page);   //页数，现默认1页5个记录

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_checkInvitation");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<Invitations>>() {
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

    public BaseEntity<JoinApplys> fc_checkJoinApply( final String receiver, final String page, final String url) {
        Map<String, Object> map2 = new HashMap<>();
//        map2.put("sender", sender);           //发送者id（领队）
        map2.put("receiver", receiver);       //邀请者id
        map2.put("page", page);   //页码

        Map<String, Object> map = new HashMap<>();
        map.put("type", "request");
        map.put("cmd", "fc_checkJoinApply");
        map.put("request", map2);

        Type type = new TypeToken<BaseEntity<JoinApplys>>() {
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
