package com.footballcitymobileandroid.BLL.Interface;


import android.util.Log;

import com.footballcitymobileandroid.DAL.HttpEntity.BaseHttp.HttpBase;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.MeEntity.App;
import com.footballcitymobileandroid.Entity.MeEntity.PlayerRecord;
import com.footballcitymobileandroid.Entity.MeEntity.Players;
import com.footballcitymobileandroid.Entity.MeEntity.userInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**post数据请求参数
 * Created by smartlab on 16/5/9.
 */
public class CommonImp {

    private Gson gson = Factory.createGson();
    HttpBase httpBase=Factory.createHttpBase();
//    exception exceptions=new exception();
    /**
     * 用户注册
     * @param phoneNumb  手机号
     * @param passWord   密码
     * @param c_passWord  确认密码
     * @param code        手机验证码
     */
    public BaseEntity<userInfo> userReg(String phoneNumb, String passWord, String c_passWord, String code, String url) {

        Map<String ,String > map2 =new HashMap<>();
        map2.put("phoneNumb",phoneNumb);
        map2.put("passWord",passWord);
        map2.put("c_passWord",c_passWord);
        map2.put("code",code);


        Map<String ,Object > map =new HashMap<>();
        map.put("type","request");
        map.put("cmd","fc_userReg");
        map.put("request",map2);

        Type type =new TypeToken<BaseEntity<userInfo>>(){}.getType();
        String json=gson.toJson(map);
        try {return httpBase.postHandle(json,url,type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh","服务异常");
//            listener.onFailure(exception.ERROR_SERVERS,exception.ERROR_SERVER_MSGS);
            return null;

        }
    }

    /**
     * 获取手机验证码（fc_get_usercode）（登入：无，系统权限：无，业务权限：无）
     * @param phone
     * @param url
     * @return
     */
    public BaseEntity<userInfo> getusercode(String phone,String url)
    {
        Map<String ,String > map2=new HashMap<>();
        map2.put("phoneNumb",phone);

        Map<String ,Object> map=new HashMap<>();
        map.put("type","request");
        map.put("cmd","fc_get_usercode");
        map.put("request",map2);

        Type type =new TypeToken<BaseEntity<userInfo>>(){}.getType();
        String json=gson.toJson(map);
        try {return httpBase.postHandle(json,url,type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh","服务异常");
            return null;

        }
    }

    /**
     * 用户登录
     * @param phone
     * @param password
     * @param url
     * @return
     */
    public BaseEntity<userInfo> userLogin(String phone,String password,String url)
    {
        Map<String ,String > map2=new HashMap<>();
        map2.put("phoneNumb",phone);
        map2.put("passWord",password);

        Map<String ,Object> map=new HashMap<>();
        map.put("type","request");
        map.put("cmd","fc_userLogin");
        map.put("request",map2);

        Type type =new TypeToken<BaseEntity<userInfo>>(){}.getType();
        String json=gson.toJson(map);
        try {return httpBase.postHandle(json,url,type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh","服务异常");
            return null;

        }
    }

    /**
     * 用户退出
     * @param phone
     * @param password
     * @param url
     * @return
     */
    public BaseEntity<Void> userExit(String phone, String password, String url)
    {
        Map<String ,String > map2=new HashMap<>();
        map2.put("phoneNumb",phone);
        map2.put("passWord",password);

        Map<String ,Object> map=new HashMap<>();
        map.put("type","request");
        map.put("cmd","fc_userExit");
        map.put("request",map2);

        Type type = new TypeToken<BaseEntity<Void>>() {
        }.getType();
        String json=gson.toJson(map);
        try {return httpBase.postHandle(json,url,type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh","服务异常");
            return null;

        }
    }

    /**
     * 查询自己详细信息
     * @param url
     * @return
     */
    public BaseEntity<userInfo> fc_queryMyself( String url){
        Map<String ,String> map2=new HashMap<>();

        Map<String ,Object> map=new HashMap<>();
        map.put("type","request");
        map.put("cmd","fc_queryMyself");
        map.put("request",map2);

        Type type =new TypeToken<BaseEntity<userInfo>>(){}.getType();
        String json=gson.toJson(map);
        try {return httpBase.postHandle(json,url,type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh","服务异常");
            return null;

        }
    }

    /**
     * 修改自己详细信息
     *     "modifyType":"name",//sex,birthday,height,weight，photo //默认姓名只是其中一项
     * @param name
     * @param namevalue
     * @param positionvalue
     * @param fieldID
     * @param atytimevalue
     * @param url
     * @return
     */
    public BaseEntity<userInfo> fc_modifyMyself(final String name,final String namevalue,final int[] positionvalue,final String fieldID,final int[] atytimevalue ,final String url)
    {
        Map<String ,Object> map2=new HashMap<>();
        map2.put("modifyType",name);
        map2.put("value",namevalue);
        Map<String ,Object> map3=new HashMap<>();
        map3.put("modifyType","position");
        map3.put("value",positionvalue);
        Map<String ,Object> map4=new HashMap<>();
        map4.put("modifyType","atyField");
        map4.put("value",fieldID);
        Map<String ,Object> map5=new HashMap<>();
        map5.put("modifyType","atyTime");
        map5.put("value",atytimevalue);

        List<Map<String ,Object>> modifyInfo=new ArrayList<>();
        modifyInfo.add(map2);
        modifyInfo.add(map3);
        modifyInfo.add(map4);
        modifyInfo.add(map5);

        Map<String ,Object> map6=new HashMap<>();
        map6.put("modifyInfo",modifyInfo);

        Map<String ,Object> map=new HashMap<>();
        map.put("type","request");
        map.put("cmd","fc_modifyMyself");
        map.put("request",map6);

        Type type =new TypeToken<BaseEntity<userInfo>>(){}.getType();
        String json=gson.toJson(map);
        try {return httpBase.postHandle(json,url,type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh","服务异常");
            return null;
        }
    }
    public BaseEntity<userInfo> fc_modifyMyselfs(final String name,final Object namevalue,final String url)
    {
        Map<String ,Object> map2=new HashMap<>();
        map2.put("modifyType",name);
        map2.put("value",namevalue);

        List<Map<String ,Object>> modifyInfo=new ArrayList<>();
        modifyInfo.add(map2);

        Map<String ,Object> map6=new HashMap<>();
        map6.put("modifyInfo",modifyInfo);

        Map<String ,Object> map=new HashMap<>();
        map.put("type","request");
        map.put("cmd","fc_modifyMyself");
        map.put("request",map6);

        Type type =new TypeToken<BaseEntity<userInfo>>(){}.getType();
        String json=gson.toJson(map);
        try {return httpBase.postHandle(json,url,type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh","服务异常");
            return null;
        }
    }
    public BaseEntity<userInfo> fc_modifyPwd(final String newpwd,final String code,final String url)
    {

        Map<String ,Object> map6=new HashMap<>();
        map6.put("newPwd",newpwd);
        map6.put("code",code);

        Map<String ,Object> map=new HashMap<>();
        map.put("type","request");
        map.put("cmd","fc_modifyPwd");
        map.put("request",map6);

        Type type =new TypeToken<BaseEntity<userInfo>>(){}.getType();
        String json=gson.toJson(map);
        try {return httpBase.postHandle(json,url,type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh","服务异常");
            return null;
        }
    }
    public BaseEntity<userInfo> fc_forgetPwd(final String phone,final String newpwd,final String code,final String url)
    {

        Map<String ,Object> map6=new HashMap<>();
        map6.put("phone",phone);
        map6.put("newPwd",newpwd);
        map6.put("code",code);

        Map<String ,Object> map=new HashMap<>();
        map.put("type","request");
        map.put("cmd","fc_forgetPwd");
        map.put("request",map6);

        Type type =new TypeToken<BaseEntity<userInfo>>(){}.getType();
        String json=gson.toJson(map);
        try {return httpBase.postHandle(json,url,type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh","服务异常");
            return null;
        }
    }
    public BaseEntity<userInfo> fc_modifyPhone(final String code1,final String newPhoneNumb,final String code2,final String passWord,final String url)
    {

        Map<String ,Object> map6=new HashMap<>();
        map6.put("code1",code1);
        map6.put("newPhoneNumb",newPhoneNumb);
        map6.put("code2",code2);
        map6.put("passWord",passWord);

        Map<String ,Object> map=new HashMap<>();
        map.put("type","request");
        map.put("cmd","fc_modifyPhone");
        map.put("request",map6);

        Type type =new TypeToken<BaseEntity<userInfo>>(){}.getType();
        String json=gson.toJson(map);
        try {return httpBase.postHandle(json,url,type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh","服务异常");
            return null;
        }
    }
    public BaseEntity<userInfo> fc_getConfig(final String url)
    {

        Map<String ,Object> map6=new HashMap<>();

        Map<String ,Object> map=new HashMap<>();
        map.put("type","request");
        map.put("cmd","fc_getConfig");
        map.put("request",map6);

        Type type =new TypeToken<BaseEntity<userInfo>>(){}.getType();
        String json=gson.toJson(map);
        try {return httpBase.postHandle(json,url,type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh","服务异常");
            return null;
        }
    }

    /**
     * 10.10 查询球员信息(条件查询)（fc_queryPlayers）（登入：无，系统权限：无，业务权限：无）
     */
    public BaseEntity<Players> fc_queryPlayers(final String type,final Object value,final String url)
    {

        Map<String ,Object> map5=new HashMap<>();
        map5.put(type,value);
        Map<String ,Object> map6=new HashMap<>();
        map6.put("page","1");
        map6.put("condition",map5);

        Map<String ,Object> map=new HashMap<>();
        map.put("type","request");
        map.put("cmd","fc_queryPlayers");
        map.put("request",map6);

        Type types =new TypeToken<BaseEntity<Players>>(){}.getType();
        String json=gson.toJson(map);
        try {return httpBase.postHandle(json,url,types);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh","服务异常");
            return null;
        }
    }

    /**
     * 11.查询球员信息（fc_queryPlayer）（登入：无，系统权限：无，业务权限：无）
     */
    public BaseEntity<Players> fc_queryPlayer(final String playerIDvalue,final String url)
    {

        Map<String ,Object> map6=new HashMap<>();
        map6.put("playerID",playerIDvalue);

        Map<String ,Object> map=new HashMap<>();
        map.put("type","request");
        map.put("cmd","fc_queryPlayer");
        map.put("request",map6);

        Type types =new TypeToken<BaseEntity<Players>>(){}.getType();
        String json=gson.toJson(map);
        try {return httpBase.postHandle(json,url,types);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh","服务异常");
            return null;
        }
    }

    /**
     * 12.查询球员比赛信息（fc_queryPlayerCurRecord）（登入：无，系统权限：无，业务权限：无）
     */
    public BaseEntity<PlayerRecord> fc_queryPlayerCurRecord(final String playerIDvalue, final String url)
    {

        Map<String ,Object> map6=new HashMap<>();
        map6.put("playerID",playerIDvalue);

        Map<String ,Object> map=new HashMap<>();
        map.put("type","request");
        map.put("cmd","fc_queryPlayerCurRecord");
        map.put("request",map6);

        Type types =new TypeToken<BaseEntity<PlayerRecord>>(){}.getType();
        String json=gson.toJson(map);
        try {return httpBase.postHandle(json,url,types);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh","服务异常");
            return null;
        }
    }

    /**
     * 13.查询球员比赛信息[分页]（fc_queryPlayerRecords）（登入：无，系统权限：无，业务权限：无）
     */
    public BaseEntity<PlayerRecord> fc_queryPlayerRecords(final String page,final String playerIDvalue, final String url)
    {

        Map<String ,Object> map6=new HashMap<>();
        map6.put("page",page);
        map6.put("playerID",playerIDvalue);

        Map<String ,Object> map=new HashMap<>();
        map.put("type","request");
        map.put("cmd","fc_queryPlayerRecords");
        map.put("request",map6);

        Type types =new TypeToken<BaseEntity<PlayerRecord>>(){}.getType();
        String json=gson.toJson(map);
        try {return httpBase.postHandle(json,url,types);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh","服务异常");
            return null;
        }
    }

    /**
     *1.查询App最新版本 (fc_latestVersion) （登陆：无	权限：无	业务角色：无）
     */
    public BaseEntity<App> fc_latestVersion( final String url)
    {

        Map<String ,Object> map6=new HashMap<>();

        Map<String ,Object> map=new HashMap<>();
        map.put("type","request");
        map.put("cmd","fc_latestVersion");
        map.put("request",map6);

        Type types =new TypeToken<BaseEntity<App>>(){}.getType();
        String json=gson.toJson(map);
        try {return httpBase.postHandle(json,url,types);
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("sssh","服务异常");
            return null;
        }
    }

}
