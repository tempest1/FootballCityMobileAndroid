package com.footballcitymobileandroid.BLL.Interface;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.Base.exception;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Club;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubMemb;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubRecord;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Clubs;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Send;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Wrong;
import com.footballcitymobileandroid.Entity.ClubEntity.join.Invitations;
import com.footballcitymobileandroid.Entity.ClubEntity.join.JoinApplys;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.DeployDetail;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.MatchMemb;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.SportDetail;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.Condition;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.MatchingInfo;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.updateArena;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena2.AranaMatchMembs;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena2.DeployInfos;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.ClubRankings;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.GradeList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.NomiMembs;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.PlayerRankings;
import com.footballcitymobileandroid.Entity.MeEntity.App;
import com.footballcitymobileandroid.Entity.MeEntity.PlayerRecord;
import com.footballcitymobileandroid.Entity.MeEntity.Players;
import com.footballcitymobileandroid.Entity.MeEntity.userInfo;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by smartlab on 16/5/10.
 */
public class AppActionImpl implements AppAction {
    Context context;
    CommonImp commonImp=Factory.createCommonImp();
    SportImp sportImp = Factory.createSportImp();
    JoinImp joinImp = Factory.createJoinImp();
    ClubImp clubImp = Factory.createClubImp();
    Arena1Imp arena1Imp = Factory.createArena1Imp();
    Arena2Imp arena2Imp = Factory.createArena2Imp();
    Arena3Imp arena3Imp = Factory.createArena3Imp();
    public AppActionImpl(Context context){
        this.context=context;
    }

    //common

    /**
     * 用户注册（fc_userReg）（登入：无，系统权限：无，业务权限：无）
     *
     */
    @Override
    public void fc_userReg(final String phoneNumb, final String passWord, final String c_passWord, final String code, final String url, final ActionCallBackListener<BaseEntity<userInfo>> listener) {
        try {

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(phoneNumb);
        if(listener==null){
            LogUtils.e("监听事件未注册");
        }
        else if(TextUtils.isEmpty(phoneNumb)) {
            LogUtils.e("phone is null");
            listener.onFailure(exception.PARAM_NULL, "手机号不能为空");
        }

        else if (!m.matches()&&!TextUtils.isEmpty(phoneNumb)) {
                listener.onFailure(exception.PARAM_ILLEGAL, "手机号格式不正确");

        }
        else if(TextUtils.isEmpty(passWord)){
            LogUtils.e("passWord is null");
            listener.onFailure(exception.PARAM_NULL,"密码不能为空");
        }
        else if(TextUtils.isEmpty(c_passWord)){
            LogUtils.e("c_passWord is null");
            listener.onFailure(exception.PARAM_NULL,"确认密码不能为空");
        }
        else if(TextUtils.isEmpty(code)){
            LogUtils.e("code is null");
            listener.onFailure(exception.PARAM_NULL,"验证码不能为空");
        }
        else {
            LogUtils.e("准备开始网络请求");

            new AsyncTask<Void, Void,BaseEntity<userInfo>>() {
                @Override
                protected BaseEntity<userInfo> doInBackground(Void... params) {
                    return commonImp.userReg(phoneNumb,passWord,c_passWord,code,url);
                }

                @Override
                protected void onPostExecute(BaseEntity<userInfo> baseEntityApiResponse) {
                    if(baseEntityApiResponse!=null)
                    {
                        try{
                            if(baseEntityApiResponse.getResponse().getResult().equals("true"))
                            {
                                listener.onSuccess(baseEntityApiResponse);
                            }else if(baseEntityApiResponse.getResponse().getResult().equals("false")){
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(),baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        }
                        catch (Exception  e){
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS,exception.ERROR_REQUEST_MSGS);
                        }
                    }else
                    {
                        listener.onFailure(exception.ERROR_SERVER,exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取手机验证码（fc_get_usercode）（登入：无，系统权限：无，业务权限：无）
     */
    public void fc_get_usercode(final String phoneNumb,final String url,final ActionCallBackListener<BaseEntity<userInfo>> listener)
    {
        try {

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(phoneNumb);
        if(listener==null){
            LogUtils.e("监听事件未注册");
        }
        else if(TextUtils.isEmpty(phoneNumb))
        {
            LogUtils.e("phone is null");
            listener.onFailure(exception.PARAM_NULL, "手机号不能为空");
        }
        else if (!m.matches()&&!TextUtils.isEmpty(phoneNumb)) {
            listener.onFailure(exception.PARAM_ILLEGAL, "手机号格式不正确");

        }
        else {
                LogUtils.e("准备开始网络请求");
                new AsyncTask<Void, Void,BaseEntity<userInfo>>() {
                    @Override
                    protected BaseEntity<userInfo> doInBackground(Void... params) {
                        return commonImp.getusercode(phoneNumb,url);
                    }
                    @Override
                    protected void onPostExecute(BaseEntity<userInfo> baseEntityApiResponse) {
                        if(baseEntityApiResponse!=null)
                        {
                            try{
                                if(baseEntityApiResponse.getResponse().getResult().equals("true"))
                                {
                                    listener.onSuccess(baseEntityApiResponse);
                                }else if(baseEntityApiResponse.getResponse().getResult().equals("false")){
                                    listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(),baseEntityApiResponse.getResponse().getException().getE_Msg());
                                }
                            }
                            catch (Exception e){
                                e.printStackTrace();
                                listener.onFailure(exception.ERROR_REQUESTS,exception.ERROR_REQUEST_MSGS);
                            }

                        }else
                        {
                            listener.onFailure(exception.ERROR_SERVER,exception.ERROR_SERVER_MSG);
                        }
                        super.onPostExecute(baseEntityApiResponse);
                    }
                }.execute();
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户登入（fc_userLogin）（登入：无，系统权限：无，业务权限：无）
     *
     */
    @Override
    public void fc_userLogin(final String phoneNumb, final String passWord, final String url,final ActionCallBackListener<BaseEntity<userInfo>> listener) {
        try {

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(phoneNumb);
        if(listener==null){
            LogUtils.e("监听事件未注册");
        }
        else if(TextUtils.isEmpty(phoneNumb))
        {
            LogUtils.e("phone is null");
            listener.onFailure(exception.PARAM_NULL, "手机号不能为空");
        }
        else if (!m.matches()&&!TextUtils.isEmpty(phoneNumb)) {
            listener.onFailure(exception.PARAM_ILLEGAL, "手机号格式不正确");

        }
        else if(TextUtils.isEmpty(passWord))
        {
            LogUtils.e("passWord is null");
            listener.onFailure(exception.PARAM_NULL, "密码不能为空");
        }
        else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void,BaseEntity<userInfo>>() {
                @Override
                protected BaseEntity<userInfo> doInBackground(Void... params) {
                    return commonImp.userLogin(phoneNumb,passWord,url);
                }
                @Override
                protected void onPostExecute(BaseEntity<userInfo> baseEntityApiResponse) {
                    if(baseEntityApiResponse!=null)
                    {
                        try {
                            if(baseEntityApiResponse.getResponse().getResult().equals("true"))
                            {
                                listener.onSuccess(baseEntityApiResponse);
                            }else if(baseEntityApiResponse.getResponse().getResult().equals("false")){
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(),baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        }
                       catch (Exception e){
                           e.printStackTrace();
                           listener.onFailure(exception.ERROR_REQUESTS,exception.ERROR_REQUEST_MSGS);
                       }
                    }else
                    {
                        listener.onFailure(exception.ERROR_SERVER,exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户退出（fc_userExit）（登入：是，系统权限：无，业务权限：无）
     *
     */
    @Override
    public void fc_userExit(final String phoneNumb, final String passWord, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {
        try {
            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
            Matcher m = p.matcher(phoneNumb);
        if(listener==null){
            LogUtils.e("监听事件未注册");
        }
        else if(TextUtils.isEmpty(phoneNumb))
        {
            LogUtils.e("phone is null");
            listener.onFailure(exception.PARAM_NULL, "手机号不能为空");
        }
        else if (!m.matches()&&!TextUtils.isEmpty(phoneNumb)) {
            listener.onFailure(exception.PARAM_ILLEGAL, "手机号格式不正确");

        }
        else if(TextUtils.isEmpty(passWord))
        {
            LogUtils.e("passWord is null");
            listener.onFailure(exception.PARAM_NULL, "密码不能为空");
        }
        else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return commonImp.userExit(phoneNumb,passWord,url);
                }
                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if(baseEntityApiResponse!=null)
                    {
                        try{
                            if(baseEntityApiResponse.getResponse().getResult().equals("true"))
                            {
                                listener.onSuccess(baseEntityApiResponse);
                            }else if(baseEntityApiResponse.getResponse().getResult().equals("false")){
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(),baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        }
                        catch (Exception e){
                            listener.onFailure(exception.ERROR_REQUESTS,exception.ERROR_REQUEST_MSGS);
                        }
                    }else
                    {
                        listener.onFailure(exception.ERROR_SERVER,exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
        } catch (Exception e) {
            if (listener != null) {
                listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
            }
        }
    }

    /**
     * 查询自己详细信息（fc_queryMyself）（登入：有，系统权限：无，业务权限：无）
     *
     */
    @Override
    public void fc_queryMyself(final String url,final ActionCallBackListener<BaseEntity<userInfo>> listener) {
        LogUtils.e("准备开始网络请求");
        new AsyncTask<Void, Void,BaseEntity<userInfo>>() {
            @Override
            protected BaseEntity<userInfo> doInBackground(Void... params) {
                return commonImp.fc_queryMyself(url);
            }
            @Override
            protected void onPostExecute(BaseEntity<userInfo> baseEntityApiResponse) {
                if(baseEntityApiResponse!=null)
                {
                    try{
                        if(baseEntityApiResponse.getResponse().getResult().equals("true"))
                        {
                            listener.onSuccess(baseEntityApiResponse);
                        }else if(baseEntityApiResponse.getResponse().getResult().equals("false")){
                            listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(),baseEntityApiResponse.getResponse().getException().getE_Msg());
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        listener.onFailure(exception.ERROR_REQUESTS,exception.ERROR_REQUEST_MSGS);
                    }
                }else
                {
                    listener.onFailure(exception.ERROR_SERVER,exception.ERROR_SERVER_MSG);
                }
                super.onPostExecute(baseEntityApiResponse);
            }
        }.execute();
    }

    /**
     * 修改自己详细信息（fc_modifyMyself）（登入：有，系统权限：无，业务权限：无）
     *
     */
    @Override
    public void fc_modifyMyself(final String name, final String namevalue, final int[] positionvalue, final String fieldID, final int[] atytimevalue, final String url, final ActionCallBackListener<BaseEntity<userInfo>> listener) {
        if(listener==null){
            LogUtils.e("监听事件未注册");
        }
        else if(TextUtils.isEmpty(name))
        {
            LogUtils.e("name is null");
            listener.onFailure(exception.PARAM_NULL, "姓名不能为空");
        }
        else if(TextUtils.isEmpty(namevalue))
        {
            LogUtils.e("namevalue is null");
            listener.onFailure(exception.PARAM_NULL, "数据不能为空");
        }
        else if(positionvalue[0]==0)
        {
            LogUtils.e("positionvalue is null");
            listener.onFailure(exception.PARAM_NULL, "位置不能为空");
        }
//        else if(TextUtils.isEmpty(fieldID))
//        {
//            LogUtils.e("namevalue is null");
//            listener.onFailure(exception.PARAM_NULL, "场地不能为空");
//        }
        else if(atytimevalue[0]==0)
        {
            LogUtils.e("atytimevalue is null");
            listener.onFailure(exception.PARAM_NULL, "活动时间不能为空");
        }
        else {
        LogUtils.e("准备开始网络请求");
        new AsyncTask<Void, Void,BaseEntity<userInfo>>() {
            @Override
            protected BaseEntity<userInfo> doInBackground(Void... params) {
                return commonImp.fc_modifyMyself(name,namevalue,positionvalue,fieldID,atytimevalue, Params.fc_modifyMyself);
            }
            @Override
            protected void onPostExecute(BaseEntity<userInfo> baseEntityApiResponse) {
                if(baseEntityApiResponse!=null)
                {
                    try{
                        if(baseEntityApiResponse.getResponse().getResult().equals("true"))
                        {
                            listener.onSuccess(baseEntityApiResponse);
                        }else if(baseEntityApiResponse.getResponse().getResult().equals("false")){
                            listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(),baseEntityApiResponse.getResponse().getException().getE_Msg());
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        listener.onFailure(exception.ERROR_REQUESTS,exception.ERROR_REQUEST_MSGS);
                    }
                }else
                {
                    listener.onFailure(exception.ERROR_SERVER,exception.ERROR_SERVER_MSG);
                }
                super.onPostExecute(baseEntityApiResponse);
            }
        }.execute();
        }
    }

    @Override
    public void fc_modifyMyselfs(final String name,final Object namevalue,final String url,final ActionCallBackListener<BaseEntity<userInfo>> listener) {
        if(listener==null){
            LogUtils.e("监听事件未注册");
        }
        else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void,BaseEntity<userInfo>>() {
                @Override
                protected BaseEntity<userInfo> doInBackground(Void... params) {
                    return commonImp.fc_modifyMyselfs(name,namevalue, Params.fc_modifyMyself);
                }
                @Override
                protected void onPostExecute(BaseEntity<userInfo> baseEntityApiResponse) {
                    if(baseEntityApiResponse!=null)
                    {
                        try{
                            if(baseEntityApiResponse.getResponse().getResult().equals("true"))
                            {
                                listener.onSuccess(baseEntityApiResponse);
                            }else if(baseEntityApiResponse.getResponse().getResult().equals("false")){
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(),baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS,exception.ERROR_REQUEST_MSGS);
                        }
                    }else
                    {
                        listener.onFailure(exception.ERROR_SERVER,exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 修改自己密码（fc_modifyPwd）（登入：有，系统权限：无，业务权限：无）
     *
     */
    @Override
    public void fc_modifyPwd(final String newPwd, final String code,final String url,final ActionCallBackListener<BaseEntity<userInfo>> listener) {

        if(listener==null){
            LogUtils.e("监听事件未注册");
        }
        else if(TextUtils.isEmpty(newPwd))
        {
            LogUtils.e("phone is null");
            listener.onFailure(exception.PARAM_NULL, "密码不能为空");
        }
        else if(TextUtils.isEmpty(code))
        {
            LogUtils.e("passWord is null");
            listener.onFailure(exception.PARAM_NULL, "验证码不能为空");
        }
        else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void,BaseEntity<userInfo>>() {
                @Override
                protected BaseEntity<userInfo> doInBackground(Void... params) {
                    return commonImp.fc_modifyPwd(newPwd,code,url);
                }
                @Override
                protected void onPostExecute(BaseEntity<userInfo> baseEntityApiResponse) {
                    if(baseEntityApiResponse!=null)
                    {
                        try{
                            if(baseEntityApiResponse.getResponse().getResult().equals("true"))
                            {
                                listener.onSuccess(baseEntityApiResponse);
                            }else if(baseEntityApiResponse.getResponse().getResult().equals("false")){
                                 listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(),baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS,exception.ERROR_REQUEST_MSGS);
                        }
                    }else
                    {
                        listener.onFailure(exception.ERROR_SERVER,exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 忘记自己密码（fc_modifyPwd）（登入：无，系统权限：无，业务权限：无）
     *
     */
    @Override
    public void fc_forgetPwd(final String phone,final String newPwd, final String code,final String url,final ActionCallBackListener<BaseEntity<userInfo>> listener) {

        if(listener==null){
            LogUtils.e("监听事件未注册");
        }
        else if(TextUtils.isEmpty(newPwd))
        {
            LogUtils.e("phone is null");
            listener.onFailure(exception.PARAM_NULL, "密码不能为空");
        }
        else if(TextUtils.isEmpty(code))
        {
            LogUtils.e("passWord is null");
            listener.onFailure(exception.PARAM_NULL, "验证码不能为空");
        }
        else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void,BaseEntity<userInfo>>() {
                @Override
                protected BaseEntity<userInfo> doInBackground(Void... params) {
                    return commonImp.fc_forgetPwd(phone,newPwd,code,url);
                }
                @Override
                protected void onPostExecute(BaseEntity<userInfo> baseEntityApiResponse) {
                    if(baseEntityApiResponse!=null)
                    {
                        try{
                            if(baseEntityApiResponse.getResponse().getResult().equals("true"))
                            {
                                listener.onSuccess(baseEntityApiResponse);
                            }else if(baseEntityApiResponse.getResponse().getResult().equals("false")){
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(),baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS,exception.ERROR_REQUEST_MSGS);
                        }
                    }else
                    {
                        listener.onFailure(exception.ERROR_SERVER,exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }
    /**
     * 修改自己手机号（fc_modifyPhone）（登入：有，系统权限：无，业务权限：无）
     *
     */
    @Override
    public void fc_modifyPhone(final String code1, final String newPhoneNumb,final String code2,final String passWord, final String url,final ActionCallBackListener<BaseEntity<userInfo>> listener) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(newPhoneNumb);
        if(listener==null){
            LogUtils.e("监听事件未注册");
        }
        else if(TextUtils.isEmpty(code1)){
            LogUtils.e("code1 is null");
            listener.onFailure(exception.PARAM_NULL, "原手机账号验证码不能为空");
        }
        else if(TextUtils.isEmpty(newPhoneNumb))
        {
            LogUtils.e("newPhoneNumb is null");
            listener.onFailure(exception.PARAM_NULL, "新手机号不能为空");
        }
        else if (!m.matches()&&!TextUtils.isEmpty(newPhoneNumb)) {
            listener.onFailure(exception.PARAM_ILLEGAL, "手机号格式不正确");

        }
        else if(TextUtils.isEmpty(code2))
        {
            LogUtils.e("code2 is null");
            listener.onFailure(exception.PARAM_NULL, "新手机号验证码不能为空");
        }
        else if(TextUtils.isEmpty(passWord))
        {
            LogUtils.e("passWord is null");
            listener.onFailure(exception.PARAM_NULL, "密码不能为空");
        }
        else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void,BaseEntity<userInfo>>() {
                @Override
                protected BaseEntity<userInfo> doInBackground(Void... params) {
                    return commonImp.fc_modifyPhone(code1,newPhoneNumb,code2,passWord,url);
                }
                @Override
                protected void onPostExecute(BaseEntity<userInfo> baseEntityApiResponse) {
                    if(baseEntityApiResponse!=null)
                    {
                        try{
                            if(baseEntityApiResponse.getResponse().getResult().equals("true"))
                            {
                                listener.onSuccess(baseEntityApiResponse);
                            }else if(baseEntityApiResponse.getResponse().getResult().equals("false")){
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(),baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS,exception.ERROR_REQUEST_MSGS);
                        }
                    }else
                    {
                        listener.onFailure(exception.ERROR_SERVER,exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 获取配置数据（fc_getConfig）（登入：有，系统权限：无，业务权限：无）
     */
    @Override
    public void fc_getConfig(final String url,final ActionCallBackListener<BaseEntity<userInfo>> listener) {
        LogUtils.e("准备开始网络请求");
        new AsyncTask<Void, Void,BaseEntity<userInfo>>() {
            @Override
            protected BaseEntity<userInfo> doInBackground(Void... params) {
                return commonImp.fc_getConfig(url);
            }
            @Override
            protected void onPostExecute(BaseEntity<userInfo> baseEntityApiResponse) {
                if(baseEntityApiResponse!=null)
                {
                    try{
                        if(baseEntityApiResponse.getResponse().getResult().equals("true"))
                        {
                            listener.onSuccess(baseEntityApiResponse);
                        }else if(baseEntityApiResponse.getResponse().getResult().equals("false")){
                            listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(),baseEntityApiResponse.getResponse().getException().getE_Msg());
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        listener.onFailure(exception.ERROR_REQUESTS,exception.ERROR_REQUEST_MSGS);
                    }
                }else
                {
                    listener.onFailure(exception.ERROR_SERVER,exception.ERROR_SERVER_MSG);
                }
                super.onPostExecute(baseEntityApiResponse);
            }
        }.execute();
    }

    /**
     * 10.10 查询球员信息(条件查询)（fc_queryPlayers）（登入：无，系统权限：无，业务权限：无）
     */
    @Override
    public void fc_queryPlayers(final String type,final Object value,final String url,final ActionCallBackListener<BaseEntity<Players>> listener) {

        if(listener==null){
            LogUtils.e("监听事件未注册");
        }
        else if(TextUtils.isEmpty(type)){
            LogUtils.e("type is null");
            listener.onFailure(exception.PARAM_NULL, "查找类型不能为空");
        }
        else if(value.equals(""))
        {
            LogUtils.e("value is null");
            listener.onFailure(exception.PARAM_NULL, "类型数据不能为空");
        }

        else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void,BaseEntity<Players>>() {
                @Override
                protected BaseEntity<Players> doInBackground(Void... params) {
                    return commonImp.fc_queryPlayers(type,value,url);
                }
                @Override
                protected void onPostExecute(BaseEntity<Players> baseEntityApiResponse) {
                    if(baseEntityApiResponse!=null)
                    {
                        try{
                            if(baseEntityApiResponse.getResponse().getResult().equals("true"))
                            {
                                listener.onSuccess(baseEntityApiResponse);
                            }else if(baseEntityApiResponse.getResponse().getResult().equals("false")){
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(),baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS,exception.ERROR_REQUEST_MSGS);
                        }
                    }else
                    {
                        listener.onFailure(exception.ERROR_SERVER,exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 11.查询球员信息（fc_queryPlayer）（登入：无，系统权限：无，业务权限：无）
     */
    @Override
    public void fc_queryPlayer(final String playerIDvalue,final String url,final ActionCallBackListener<BaseEntity<Players>> listener) {
        if(listener==null){
            LogUtils.e("监听事件未注册");
        }
        else if(TextUtils.isEmpty(playerIDvalue)){
            LogUtils.e("playerIDvalue is null");
            listener.onFailure(exception.PARAM_NULL, "球员ID不能为空");
        }
        else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void,BaseEntity<Players>>() {
                @Override
                protected BaseEntity<Players> doInBackground(Void... params) {
                    return commonImp.fc_queryPlayer(playerIDvalue,url);
                }
                @Override
                protected void onPostExecute(BaseEntity<Players> baseEntityApiResponse) {
                    if(baseEntityApiResponse!=null)
                    {
                        try{
                            if(baseEntityApiResponse.getResponse().getResult().equals("true"))
                            {
                                listener.onSuccess(baseEntityApiResponse);
                            }else if(baseEntityApiResponse.getResponse().getResult().equals("false")){
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(),baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS,exception.ERROR_REQUEST_MSGS);
                        }
                    }else
                    {
                        listener.onFailure(exception.ERROR_SERVER,exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 12.查询球员比赛信息（fc_queryPlayerCurRecord）（登入：无，系统权限：无，业务权限：无）
     *
     */
    @Override
    public void fc_queryPlayerCurRecord(final String playerIDvalue,final String url,final ActionCallBackListener<BaseEntity<PlayerRecord>> listener) {
        if(listener==null){
            LogUtils.e("监听事件未注册");
        }
        else if(TextUtils.isEmpty(playerIDvalue)){
            LogUtils.e("playerIDvalue is null");
            listener.onFailure(exception.PARAM_NULL, "球员ID不能为空");
        }
        else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void,BaseEntity<PlayerRecord>>() {
                @Override
                protected BaseEntity<PlayerRecord> doInBackground(Void... params) {
                    return commonImp.fc_queryPlayerCurRecord(playerIDvalue,url);
                }
                @Override
                protected void onPostExecute(BaseEntity<PlayerRecord> baseEntityApiResponse) {
                    if(baseEntityApiResponse!=null)
                    {
                        try{
                            if(baseEntityApiResponse.getResponse().getResult().equals("true"))
                            {
                                listener.onSuccess(baseEntityApiResponse);
                            }else if(baseEntityApiResponse.getResponse().getResult().equals("false")){
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(),baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS,exception.ERROR_REQUEST_MSGS);
                        }
                    }else
                    {
                        listener.onFailure(exception.ERROR_SERVER,exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 13.查询球员比赛信息[分页]（fc_queryPlayerRecords）（登入：无，系统权限：无，业务权限：无）
     *
     */
    @Override
    public void fc_queryPlayerRecords(final String page,final String playerIDvalue,final String url,final ActionCallBackListener<BaseEntity<PlayerRecord>> listener) {
        if(listener==null){
            LogUtils.e("监听事件未注册");
        }
        else if(TextUtils.isEmpty(playerIDvalue)){
            LogUtils.e("playerIDvalue is null");
            listener.onFailure(exception.PARAM_NULL, "球员ID不能为空");
        }
        else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void,BaseEntity<PlayerRecord>>() {
                @Override
                protected BaseEntity<PlayerRecord> doInBackground(Void... params) {
                    return commonImp.fc_queryPlayerRecords(page,playerIDvalue,url);
                }
                @Override
                protected void onPostExecute(BaseEntity<PlayerRecord> baseEntityApiResponse) {
                    if(baseEntityApiResponse!=null)
                    {
                        try{
                            if(baseEntityApiResponse.getResponse().getResult().equals("true"))
                            {
                                listener.onSuccess(baseEntityApiResponse);
                            }else if(baseEntityApiResponse.getResponse().getResult().equals("false")){
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(),baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS,exception.ERROR_REQUEST_MSGS);
                        }
                    }else
                    {
                        listener.onFailure(exception.ERROR_SERVER,exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 1.查询App最新版本 (fc_latestVersion) （登陆：无	权限：无	业务角色：无）
     *
     */
    @Override
    public void fc_latestVersion(final String url,final ActionCallBackListener<BaseEntity<App>> listener) {
        if(listener==null){
            LogUtils.e("监听事件未注册");
        }

        else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void,BaseEntity<App>>() {
                @Override
                protected BaseEntity<App> doInBackground(Void... params) {
                    return commonImp.fc_latestVersion(url);
                }
                @Override
                protected void onPostExecute(BaseEntity<App> baseEntityApiResponse) {
                    if(baseEntityApiResponse!=null)
                    {
                        try{
                            if(baseEntityApiResponse.getResponse().getResult().equals("true"))
                            {
                                listener.onSuccess(baseEntityApiResponse);
                            }else if(baseEntityApiResponse.getResponse().getResult().equals("false")){
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(),baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS,exception.ERROR_REQUEST_MSGS);
                        }
                    }else
                    {
                        listener.onFailure(exception.ERROR_SERVER,exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }


    //sport


    /**
     * 添加比赛信息 (fc_addClubSport) （登陆：有	权限：无	业务角色：俱乐部领队）
     *
     */
    @Override
    public void fc_addClubSport(final String clubID, final String startTime, final String endTime, final String visitingTeam, final String joinNum, final String sportField, final String sportState, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else if (TextUtils.isEmpty(clubID)) {
            LogUtils.e("clubID is null");
            listener.onFailure(exception.PARAM_NULL, "俱乐部ID不能为空");
        } else if (TextUtils.isEmpty(startTime)) {
            LogUtils.e("startTime is null");
            listener.onFailure(exception.PARAM_NULL, "开始时间不能为空");
        } else if (TextUtils.isEmpty(endTime)) {
            LogUtils.e("endTime is null");
            listener.onFailure(exception.PARAM_NULL, "结束时间不能为空");
        } else if (TextUtils.isEmpty(visitingTeam)) {
            LogUtils.e("visitingTeam is null");
            listener.onFailure(exception.PARAM_NULL, "访问球队不能为空");
        } else if (TextUtils.isEmpty(joinNum)) {
            LogUtils.e("joinNum is null");
            listener.onFailure(exception.PARAM_NULL, "参与人数不能为空");
        } else if (TextUtils.isEmpty(sportField)) {
            LogUtils.e("sportField is null");
            listener.onFailure(exception.PARAM_NULL, "比赛场地不能为空");
        } else if (TextUtils.isEmpty(sportState)) {
            LogUtils.e("sportState is null");
            listener.onFailure(exception.PARAM_NULL, "比赛状态不能为空");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return sportImp.fc_addClubSport(clubID, startTime, endTime, visitingTeam, joinNum, sportField, sportState, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 删除比赛信息 (fc_delClubSport) （登陆：有	权限：无	业务角色：俱乐部领队）
     *
     */
    @Override
    public void fc_delClubSport(final String clubID, final String sportID, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else if (TextUtils.isEmpty(clubID)) {
            LogUtils.e("clubID is null");
            listener.onFailure(exception.PARAM_NULL, "俱乐部ID不能为空");
        } else if (TextUtils.isEmpty(sportID)) {
            LogUtils.e("sportID is null");
            listener.onFailure(exception.PARAM_NULL, "比赛ID不能为空");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return sportImp.fc_delClubSport(clubID, sportID, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 查看比赛信息列表 (fc_checkSportDetail) （登陆：有	权限：无	业务角色：俱乐部成员）
     *
     */
    @Override
    public void fc_checkSportDetail(final String clubID, final String sportState, final String page, final String url, final ActionCallBackListener<BaseEntity<SportDetail>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else if (TextUtils.isEmpty(clubID)) {
            LogUtils.e("clubID is null");
            listener.onFailure(exception.PARAM_NULL, "俱乐部ID不能为空");
        } else if (TextUtils.isEmpty(sportState)) {
            LogUtils.e("sportState is null");
            listener.onFailure(exception.PARAM_NULL, "比赛状态不能为空");
        } else if (TextUtils.isEmpty(page)) {
            LogUtils.e("page is null");
            listener.onFailure(exception.PARAM_NULL, "页码不能为空");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<SportDetail>>() {
                @Override
                protected BaseEntity<SportDetail> doInBackground(Void... params) {
                    return sportImp.fc_checkSportDetail(clubID, sportState, page, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<SportDetail> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 录入比赛结果 (fc_editScore) （登陆：有	权限：无	业务角色：俱乐部领队）
     *
     */
    @Override
    public void fc_editScore(final String clubID, final String sportID, final String homeScore, final String visitingScore, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {

        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else if (TextUtils.isEmpty(clubID)) {
            LogUtils.e("clubID is null");
            listener.onFailure(exception.PARAM_NULL, "俱乐部ID不能为空");
        } else if (TextUtils.isEmpty(sportID)) {
            LogUtils.e("sportID is null");
            listener.onFailure(exception.PARAM_NULL, "比赛ID不能为空");
        } else if (TextUtils.isEmpty(homeScore)) {
            LogUtils.e("homeScore is null");
            listener.onFailure(exception.PARAM_NULL, "主场得分不能为空");
        } else if (TextUtils.isEmpty(visitingScore)) {
            LogUtils.e("visitingScore is null");
            listener.onFailure(exception.PARAM_NULL, "客场得分不能为空");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return sportImp.fc_editScore(clubID, sportID, homeScore, visitingScore, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }

    }

    /**
     * 签到 (fc_sportSign) （登陆：有	权限：无	业务角色：俱乐部成员）
     *
     */
    @Override
    public void fc_sportSign(final String clubID, final String sportID, final String sign, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else if (TextUtils.isEmpty(clubID)) {
            LogUtils.e("clubID is null");
            listener.onFailure(exception.PARAM_NULL, "俱乐部ID不能为空");
        } else if (TextUtils.isEmpty(sportID)) {
            LogUtils.e("sportID is null");
            listener.onFailure(exception.PARAM_NULL, "比赛ID不能为空");
        } else if (TextUtils.isEmpty(sign)) {
            LogUtils.e("sign is null");
            listener.onFailure(exception.PARAM_NULL, "是否签到不能为空");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return sportImp.fc_sportSign(clubID, sportID, sign, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }

    }

    /**
     * 查看签到人员 (fc_checkSportMemb) （登陆：有	权限：无	业务角色：俱乐部成员）
     */
    @Override
    public void fc_checkSportMemb(final String clubID, final String sportID, final String url, final ActionCallBackListener<BaseEntity<MatchMemb>> listener) {

        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else if (TextUtils.isEmpty(clubID)) {
            LogUtils.e("clubID is null");
            listener.onFailure(exception.PARAM_NULL, "俱乐部ID不能为空");
        } else if (TextUtils.isEmpty(sportID)) {
            LogUtils.e("sportID is null");
            listener.onFailure(exception.PARAM_NULL, "比赛ID不能为空");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<MatchMemb>>() {
                @Override
                protected BaseEntity<MatchMemb> doInBackground(Void... params) {
                    return sportImp.fc_checkSportMemb(clubID, sportID, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<MatchMemb> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 布置比赛人员	 (fc_deploySport) （登陆：有	权限：无	业务角色：俱乐部领队）
     *
     */
    @Override
    public void fc_deploySport(final String clubID, final String sportID, final List<DeployDetail> deployDetail, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else if (TextUtils.isEmpty(clubID)) {
            LogUtils.e("clubID is null");
            listener.onFailure(exception.PARAM_NULL, "俱乐部ID不能为空");
        } else if (TextUtils.isEmpty(sportID)) {
            LogUtils.e("sportID is null");
            listener.onFailure(exception.PARAM_NULL, "比赛ID不能为空");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return sportImp.fc_deploySport(clubID, sportID, deployDetail, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }


    /**
     * join
     * 球员申请加盟（fc_createJoinApply）（需要登录，系统权限：无，业务权限：现阶段无）
     *
     */
    @Override
    public void fc_createJoinApply( final String receiver, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else if (TextUtils.isEmpty(receiver)) {
            LogUtils.e("receiver is null");
            listener.onFailure(exception.PARAM_NULL, "俱乐部ID不能为空");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return joinImp.f_createJoinApply( receiver, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 俱乐部邀请球员加盟（fc_createInvitation）（需要登录，需要是领队，业务权限：现阶段无
     *
     */
    @Override
    public void fc_createInvitation( final String receiver, final String play, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        }  else if (TextUtils.isEmpty(receiver)) {
            LogUtils.e("receiver is null");
            listener.onFailure(exception.PARAM_NULL, "俱乐部ID不能为空");
        } else if (TextUtils.isEmpty(play)) {
            LogUtils.e("play is null");
            listener.onFailure(exception.PARAM_NULL, "被邀请者ID不能为空");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return joinImp.fc_createInvitation(receiver, play, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 俱乐部领队处理加盟信息（fc_dealJoinApply）（需要登录，需要是领队，业务权限：现阶段无）
     *
     */
    @Override
    public void fc_dealJoinApply( final String play, final String receiver, final String dealResult, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        }  else if (TextUtils.isEmpty(play)) {
            LogUtils.e("play is null");
            listener.onFailure(exception.PARAM_NULL, "球员ID不能为空");
        } else if (TextUtils.isEmpty(receiver)) {
            LogUtils.e("receiver is null");
            listener.onFailure(exception.PARAM_NULL, "俱乐部ID不能为空");
        } else if (TextUtils.isEmpty(dealResult)) {
            LogUtils.e("dealResult is null");
            listener.onFailure(exception.PARAM_NULL, "处理结果不能为空");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return joinImp.fc_dealJoinApply( play, receiver, dealResult, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 球员处理受邀请信息（fc_dealInvitation）（需要登录，业务权限：现阶段无）
     *
     */
    @Override
    public void fc_dealInvitation( final String receiver, final String dealResult, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        }  else if (TextUtils.isEmpty(receiver)) {
            LogUtils.e("receiver is null");
            listener.onFailure(exception.PARAM_NULL, "俱乐部ID不能为空");
        } else if (TextUtils.isEmpty(dealResult)) {
            LogUtils.e("dealResult is null");
            listener.onFailure(exception.PARAM_NULL, "处理结果不能为空");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return joinImp.fc_dealInvitation( receiver, dealResult, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 球员查询被邀请记录（fc_checkInvitation）（需要登录，系统权限：无，业务权限：现阶段无）
     *
     */
    @Override
    public void fc_checkInvitation( final String page, final String url, final ActionCallBackListener<BaseEntity<Invitations>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        }  else if (TextUtils.isEmpty(page)) {
            LogUtils.e("page is null");
            listener.onFailure(exception.PARAM_NULL, "页数不能为空");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Invitations>>() {
                @Override
                protected BaseEntity<Invitations> doInBackground(Void... params) {
                    return joinImp.fc_checkInvitation( page, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Invitations> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 俱乐部查询申请加盟记录（fc_checkJoinApply）（需要登录，系统权限：领队，业务权限：现阶段无）
     *final String sender,
     */
    @Override
    public void fc_checkJoinApply( final String receiver, final String page, final String url, final ActionCallBackListener<BaseEntity<JoinApplys>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else if (TextUtils.isEmpty(receiver)) {
            LogUtils.e("receiver is null");
            listener.onFailure(exception.PARAM_NULL, "俱乐部ID不能为空");
        } else if (TextUtils.isEmpty(page)) {
            LogUtils.e("page is null");
            listener.onFailure(exception.PARAM_NULL, "页数不能为空");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<JoinApplys>>() {
                @Override
                protected BaseEntity<JoinApplys> doInBackground(Void... params) {
                    return joinImp.fc_checkJoinApply( receiver, page, url);//sender,
                }

                @Override
                protected void onPostExecute(BaseEntity<JoinApplys> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 1、创建俱乐部（fc_createClub）（登入：有，系统权限：无，业务权限：无）
     *
     */
    @Override
    public void fc_createClub(final String logo, final String clubName, final String cityID, final String createTime, final String atyFieldID, final int[] atyTime,final String welfare, final String url, final ActionCallBackListener<BaseEntity<Club>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else if (TextUtils.isEmpty(logo)) {
            LogUtils.e("logo is null");
            listener.onFailure(exception.PARAM_NULL, "俱乐部图标不能为空");
        } else if (TextUtils.isEmpty(clubName)) {
            LogUtils.e("clubName is null");
            listener.onFailure(exception.PARAM_NULL, "俱乐部姓名不能为空");
        } else if (TextUtils.isEmpty(cityID)) {
            LogUtils.e("cityID is null");//通过城市找到城市id
            listener.onFailure(exception.PARAM_NULL, "城市不能为空");
        } else if (TextUtils.isEmpty(createTime)) {
            LogUtils.e("createTime is null");//通过城市找到城市id
            listener.onFailure(exception.PARAM_NULL, "创建时间不能为空");
        } else if (TextUtils.isEmpty(atyFieldID)) {
            LogUtils.e("atyFieldID is null");//通过活动场地得到id
            listener.onFailure(exception.PARAM_NULL, "活动场地不能为空");
        }
//        else if (atyTime[0] == 0) {
//            LogUtils.e("atyTime is null");
//            listener.onFailure(exception.PARAM_NULL, "活动时间不能为空");
//        }
        else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Club>>() {
                @Override
                protected BaseEntity<Club> doInBackground(Void... params) {
                    return clubImp.fc_createClub(logo, clubName, cityID, createTime, atyFieldID, atyTime,welfare, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Club> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 2、修改俱乐部（fc_modifyClub）（登入：有，业务权限：领队)
     * "modifyType": "logo",//clubName,createTime,clubWelfare  //默认图标只是其中一项
     *
     */
    @Override
    public void fc_modifyClub(final String clubID,final String type,final Object typevalue, final String url, final ActionCallBackListener<BaseEntity<Wrong>> listener) {

        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else if (TextUtils.isEmpty(type)) {
            LogUtils.e("type is null");
            listener.onFailure(exception.PARAM_NULL, "修改选项不能为空");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Wrong>>() {
                @Override
                protected BaseEntity<Wrong> doInBackground(Void... params) {
                    return clubImp.fc_modifyClub(clubID,type, typevalue, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Wrong> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 3、改变俱乐部领队（fc_changeLeader）（登入：有，业务权限：领队）
     *
     */
    @Override
    public void fc_changeLeader(final String clubID, final String clubMembID, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {

        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else if (TextUtils.isEmpty(clubMembID)) {
            LogUtils.e("clubMembID is null");
            listener.onFailure(exception.PARAM_NULL, "俱乐部成员不能为空");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return clubImp.fc_changeLeader(clubID, clubMembID, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 4.获取用户俱乐部列表(fc_getClubList)
     * "msg":"leader/user";//final String msg,
     *
     */
    @Override
    public void fc_getClubList(final String url, final ActionCallBackListener<BaseEntity<ClubList>> listener) {

        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<ClubList>>() {
                @Override
                protected BaseEntity<ClubList> doInBackground(Void... params) {
                    return clubImp.fc_getClubList( url);//msg,
                }

                @Override
                protected void onPostExecute(BaseEntity<ClubList> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 5、解散俱乐部（fc_fireClub）
     *
     */
    @Override
    public void fc_fireClub(final String clubID, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return clubImp.fc_fireClub(clubID, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
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
     */
    @Override
    public void fc_queryClubs(final String page, final String clubName, final String  age, final int[] aty_Time, final String aty_Field, final String url, final ActionCallBackListener<BaseEntity<Clubs>> listener) {

        if (TextUtils.isEmpty(page)) {
            LogUtils.e("page is null");
//            listener.onFailure(exception.PARAM_NULL, "页面为" + page);
        }
//        if (TextUtils.isEmpty(order)) {
//            LogUtils.e("order is null");
////            listener.onFailure(exception.PARAM_NULL, "排序不能为空");
//        }
        if (TextUtils.isEmpty(clubName)) {
            LogUtils.e("clubName is null");
//            listener.onFailure(exception.PARAM_NULL, "俱乐部名字不能为空");
        }
//        if (TextUtils.isEmpty(age)) {
//            LogUtils.e("age is null");
//            listener.onFailure(exception.PARAM_NULL, "俱乐部年限不能为空");
//        }
//        if (TextUtils.isEmpty(aty_Time)) {
//            LogUtils.e("aty_Time is null");
////            listener.onFailure(exception.PARAM_NULL, "俱乐部活动时间不能为空");
//        }
        if (TextUtils.isEmpty(aty_Field)) {
            LogUtils.e("aty_Field is null");
//            listener.onFailure(exception.PARAM_NULL, "俱乐部活动场地不能为空");
        }
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        }
        else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Clubs>>() {
                @Override
                protected BaseEntity<Clubs> doInBackground(Void... params) {
                    return clubImp.fc_queryClubs(page, clubName, age, aty_Time, aty_Field, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Clubs> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 7、获取俱乐部详细信息（fc_queryClubDetail）
     *

     */
    @Override
    public void fc_queryClubDetail(final String clubID, final String url, final ActionCallBackListener<BaseEntity<Club>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Club>>() {
                @Override
                protected BaseEntity<Club> doInBackground(Void... params) {
                    return clubImp.fc_queryClubDetail(clubID, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Club> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 8、获取俱乐部比赛记录（fc_queryClubCurRecord）
     *
     */
    @Override
    public void fc_queryClubRecord(final String clubID, final String url, final ActionCallBackListener<BaseEntity<ClubRecord>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<ClubRecord>>() {
                @Override
                protected BaseEntity<ClubRecord> doInBackground(Void... params) {
                    return clubImp.fc_queryClubRecord(clubID, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<ClubRecord> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 9、获取俱乐部比赛记录（分页）（fc_queryClubRecords）
     * "page":1
     *
     */
    @Override
    public void fc_queryClubRecords(final String clubID, final String page, final String url, final ActionCallBackListener<BaseEntity<ClubRecord>> listener) {

        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<ClubRecord>>() {
                @Override
                protected BaseEntity<ClubRecord> doInBackground(Void... params) {
                    return clubImp.fc_queryClubRecords(clubID, page, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<ClubRecord> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 10、分配球员场上位置（fc_setPosition)  针对一个球员进行一次操作,因为人数未定故不可一次操作所有
     *
     */
    @Override
    public void fc_setPosition(final String clubID, final List<Send> sends, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return clubImp.fc_setPosition(clubID, sends, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 11、解雇俱乐部成员（fc_fireClubMemb）
     *
     */
    @Override
    public void fc_fireClubMemb(final String clubID, final String clubMembID, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return clubImp.fc_fireClubMemb(clubID, clubMembID, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 12、检查俱乐部球员信息（fc_checkClubMemb）
     *
     */
    @Override
    public void fc_checkClubMemb(final String clubID, final String url, final ActionCallBackListener<BaseEntity<ClubMemb>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<ClubMemb>>() {
                @Override
                protected BaseEntity<ClubMemb> doInBackground(Void... params) {
                    return clubImp.fc_checkClubMemb(clubID, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<ClubMemb> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 13、退出俱乐部（fc_exitClub）
     *
     */
    @Override
    public void fc_exitClub(final String clubID, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {

        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return clubImp.fc_exitClub(clubID, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 1.查询竞技场（fc_queryArena）（无需登录，系统权限：无，业务权限：无）
     *
     */
    @Override
    public void fc_queryArena(final String cityID, final String arenaID, final String seasonID, final String updateTag, final String url, final ActionCallBackListener<BaseEntity<updateArena>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<updateArena>>() {
                @Override
                protected BaseEntity<updateArena> doInBackground(Void... params) {
                    return arena1Imp.fc_queryArena(cityID, arenaID, seasonID, updateTag, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<updateArena> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 2.获取匹配条件（fc_getMatchingCondition）（需登录，需要是领队，业务权限：需要有一家俱乐部）
     */
    @Override
    public void fc_getMatchingCondition(final String url, final ActionCallBackListener<BaseEntity<Condition>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Condition>>() {
                @Override
                protected BaseEntity<Condition> doInBackground(Void... params) {
                    return arena1Imp.fc_getMatchingCondition(url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Condition> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }

    }

    /**
     * 3得到能匹配的俱乐部（fc_getMatchingClub）（无需登录，系统权限：无，业务权限：无）
     *
     */
    @Override
    public void fc_getMatchingClub(final String page, final String fieldID, final String matchingDate, final String url, final ActionCallBackListener<BaseEntity<Clubs>> listener) {

        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Clubs>>() {
                @Override
                protected BaseEntity<Clubs> doInBackground(Void... params) {
                    return arena1Imp.fc_getMatchingClub(page, fieldID, matchingDate, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Clubs> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 4.发送匹配请求（fc_sendMatchingMsg）（需登录，系统权限：无，业务权限：无）
     *
     */
    @Override
    public void fc_sendMatchingMsg(final String sender, final String season_id, final String matchingDate, final String matchingTime, final String matchRule, final String costModeKey, final List<String> receiver, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {

        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return arena1Imp.fc_sendMatchingMsg(sender, season_id, matchingDate, matchingTime, matchRule, costModeKey, receiver, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 5查看匹配信息（fc_checkMatchingMsg） （需登录，系统权限：无，业务权限：无）
     *
     */
    @Override
    public void fc_checkMatchingMsg(final String getType,final String page, final String clubID, final String url, final ActionCallBackListener<BaseEntity<MatchingInfo>> listener) {

        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<MatchingInfo>>() {
                @Override
                protected BaseEntity<MatchingInfo> doInBackground(Void... params) {
                    return arena1Imp.fc_checkMatchingMsg(getType, page, clubID, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<MatchingInfo> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 6.查看竞技挑战赛（fc_checkArenaMatch）（需登录，系统权限：无，业务权限：无）
     * '挑战赛状态：1-待开始；2-退赛中；3-已开始；4-强制退赛结束；5-正常退赛结束；6-正常比赛结束',
     *

     */
    @Override
    public void fc_checkArenaMatch(final String arenaMatchState, final String page, final String clubID, final String url, final ActionCallBackListener<BaseEntity<AranaMatchs>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<AranaMatchs>>() {
                @Override
                protected BaseEntity<AranaMatchs> doInBackground(Void... params) {
                    return arena1Imp.fc_checkArenaMatch(arenaMatchState, page, clubID, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<AranaMatchs> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 7.处理匹配请求（fc_dealMatchingMsg）（需登录，系统权限：无，业务权限：无）
     *

     */
    @Override
    public void fc_dealMatchingMsg(final String arenaMatchID, final String clubID, final String signIn, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {

        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return arena1Imp.fc_dealMatchingMsg(arenaMatchID, clubID, signIn, url);
                }
                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * arena2
     * 6.8.1/2 签到(fc_arenaSign)（登陆：有 权限：无 业务角色：俱乐部成员）
     */
    @Override
    public void fc_arenaSign(final String arenaMatchID, final String clubID, final String signIn, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {

        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return arena2Imp.fc_arenaSign(arenaMatchID, clubID, signIn, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 6.8.3 查询竞技场签到成员(fc_checkArenaMatchMemb)（登陆：有 权限：无 业务角色：俱乐部成员）
     */
    @Override
    public void fc_checkArenaMatchMemb(final String arenaMatchID, final String clubID, final String url, final ActionCallBackListener<BaseEntity<AranaMatchMembs>> listener) {

        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<AranaMatchMembs>>() {
                @Override
                protected BaseEntity<AranaMatchMembs> doInBackground(Void... params) {
                    return arena2Imp.fc_checkArenaMatchMemb(arenaMatchID, clubID, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<AranaMatchMembs> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 6.9.1 布置竞技场比赛人员(fc_deployArenaMatch)（登陆：有 权限：无 业务角色：俱乐部领队）
     */
    @Override
    public void fc_deployArenaMatch(final String arenaMatchID, final String clubID, final List<DeployInfos> deployInfosList, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return arena2Imp.fc_deployArenaMatch(arenaMatchID, clubID, deployInfosList, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 6.10.1 退赛申请(fc_quitApply)（登陆：有 权限：无 业务角色：俱乐部领队）
     */
    @Override
    public void fc_quitApply(final String arenaMatchID, final String clubID, final String applyDesc, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return arena2Imp.fc_quitApply(arenaMatchID, clubID, applyDesc, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 6.10.2/3 处理退赛申请(fc_dealQuitApply)（登陆：有 权限：无 业务角色：俱乐部领队）
     */
    @Override
    public void fc_dealQuitApply(final String arenaMatchID, final String clubID, final String dealResult, final String applyDesc, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {

        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return arena2Imp.fc_dealQuitApply(arenaMatchID, clubID, dealResult, applyDesc, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 6.10.4 强行退赛申请(fc_forcedQuit)（登陆：有 权限：无 业务角色：俱乐部领队）
     */
    @Override
    public void fc_forcedQuit(final String arenaMatchID, final String clubID, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return arena2Imp.fc_forcedQuit(arenaMatchID, clubID, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * arena3
     * 14、编辑进球数（fc_editGoals）
     */
    @Override
    public void fc_editGoals(final String arenaMatchID, final String clubID, final String goals, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return arena3Imp.fc_editGoals(arenaMatchID, clubID, goals, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 15、提交提名名单（fc_gradeMatchMemb）
     */
    @Override
    public void fc_gradeMatchMemb(final String arenaMatchID, final String clubID, final List<GradeList> gradeList, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {

        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return arena3Imp.fc_gradeMatchMemb(arenaMatchID, clubID, gradeList, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 16、检查提名名单（fc_checkNominationMemb）
     */
    @Override
    public void fc_checkNominationMemb(final String arenaMatchID, final String clubID, final String url, final ActionCallBackListener<BaseEntity<NomiMembs>> listener) {

        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<NomiMembs>>() {
                @Override
                protected BaseEntity<NomiMembs> doInBackground(Void... params) {
                    return arena3Imp.fc_checkNominationMemb(arenaMatchID, clubID, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<NomiMembs> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 17，投票（fc_voteNomiMemb）
     */
    @Override
    public void fc_voteNomiMemb(final String arenaMatchID, final String clubID, final String voteTo, final String url, final ActionCallBackListener<BaseEntity<Void>> listener) {

        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<Void>>() {
                @Override
                protected BaseEntity<Void> doInBackground(Void... params) {
                    return arena3Imp.fc_voteNomiMemb(arenaMatchID, clubID, voteTo, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<Void> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 18、检查竞技场选手排行(fc_checkArenaPlayerRankings)
     */
    @Override
    public void fc_checkArenaPlayerRankings(final String seasonID, final String url, final ActionCallBackListener<BaseEntity<PlayerRankings>> listener) {

        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<PlayerRankings>>() {
                @Override
                protected BaseEntity<PlayerRankings> doInBackground(Void... params) {
                    return arena3Imp.fc_checkArenaPlayerRankings(seasonID, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<PlayerRankings> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }

    /**
     * 19、检查竞技场俱乐部排行(fc_checkArenaClubRankings)
     */
    @Override
    public void fc_checkArenaClubRankings(final String seasonID, final String url, final ActionCallBackListener<BaseEntity<ClubRankings>> listener) {
        if (listener == null) {
            LogUtils.e("监听事件未注册");
        } else {
            LogUtils.e("准备开始网络请求");
            new AsyncTask<Void, Void, BaseEntity<ClubRankings>>() {
                @Override
                protected BaseEntity<ClubRankings> doInBackground(Void... params) {
                    return arena3Imp.fc_checkArenaClubRankings(seasonID, url);
                }

                @Override
                protected void onPostExecute(BaseEntity<ClubRankings> baseEntityApiResponse) {
                    if (baseEntityApiResponse != null) {
                        try {
                            if (baseEntityApiResponse.getResponse().getResult().equals("true")) {
                                listener.onSuccess(baseEntityApiResponse);
                            } else if (baseEntityApiResponse.getResponse().getResult().equals("false")) {
                                listener.onFailure(baseEntityApiResponse.getResponse().getException().getE_Type(), baseEntityApiResponse.getResponse().getException().getE_Msg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFailure(exception.ERROR_REQUESTS, exception.ERROR_REQUEST_MSGS);
                        }
                    } else {
                        listener.onFailure(exception.ERROR_SERVER, exception.ERROR_SERVER_MSG);
                    }
                    super.onPostExecute(baseEntityApiResponse);
                }
            }.execute();
        }
    }
}
