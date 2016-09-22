package com.footballcitymobileandroid.Controller.Common;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.mobileim.channel.event.IWxCallback;
import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.CustomView.LoadingDialog;
import com.footballcitymobileandroid.Controller.Base.MainActivity;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Controller.TestData.MessageInfo;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.MeEntity.PlayerRecord;
import com.footballcitymobileandroid.Entity.MeEntity.userInfo;
import com.footballcitymobileandroid.R;
import com.footballcitymobileandroid.openimui.common.Notification;
import com.footballcitymobileandroid.openimui.sample.LoginSampleHelper;
import com.footballcitymobileandroid.openimui.sample.NotificationInitSampleHelper;
import com.footballcitymobileandroid.openimui.sample.UserProfileSampleHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户登录
 * Created by zhoudi on 16/6/4.
 */
public class CommonLogin extends Activity implements View.OnClickListener, ActionCallBackListener<BaseEntity<userInfo>> {
    public static String username;
    public static String password;
    private LoginSampleHelper loginHelper;


    Button login;
    TextView forget, register;
    Intent intent;
    EditText editText1, editText2;
    BaseEntity<userInfo> userInfoBaseEntity;
    userInfo userInfo;
    //    Button detail_back_login;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_login);
        initView();
    }


    protected void initView() {
        loginHelper = LoginSampleHelper.getInstance();

        MessageInfo.listInfo = new ArrayList<>();
        loadingDialog = new LoadingDialog(this,R.drawable.loading);

        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(this);

        forget=(TextView)findViewById(R.id.forget);
        forget.setOnClickListener(this);

        register=(TextView)findViewById(R.id.register);
        register.setOnClickListener(this);

        editText1 = (EditText) findViewById(R.id.username);
        editText2 = (EditText) findViewById(R.id.password);
//        detail_back_login=(Button)findViewById(R.id.detail_back_login);
//        detail_back_login.setVisibility(View.GONE);
        // 方法1 Android获得屏幕的宽和高
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int screenWidth  = display.getWidth();
        int screenHeight  = display.getHeight();
        MainApplication.setHeight(screenHeight);
        MainApplication.setWidth(screenWidth);


        MainApplication.sharedPreferences= getSharedPreferences("test",
                Activity.MODE_PRIVATE);
        if ( MainApplication.sharedPreferences!=null) {
            editText1.setText(MainApplication.sharedPreferences.getString("username", ""));
            editText2.setText(MainApplication.sharedPreferences.getString("password", ""));

        }

    }



    private void doit() {
        // loadingDialog.show();
        AppAction appAction = Factory.createAppActionImpl(this);
        appAction.fc_userLogin(editText1.getText().toString(), editText2.getText().toString(), Params.fc_userLogin, this);
//        MainApplication.getUserInfo().setPhoneNumb(editText1.getText().toString());
//        MainApplication.getUserInfo().setPassWord(editText2.getText().toString());


//        do_querymyself();
//        do_queryclublist();
    }

    private void do_querymyself() {
        AppAction appAction = Factory.createAppActionImpl(getApplicationContext());
        appAction.fc_queryMyself(Params.fc_queryMyself, listener);
    }

    private void doset(BaseEntity<userInfo> userInfoBaseEntity) {
        MainApplication.setUserInfo((com.footballcitymobileandroid.Entity.MeEntity.userInfo) userInfoBaseEntity.getResponse().getT());          //////////////
        userInfo = MainApplication.getUserInfo();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login:
                doit();
                break;
            case R.id.forget:
                forget();
                break;
            case R.id.register:
                register();
                break;
        }
    }

    private   void login()
    {
        //足球城登陆成功后，进行云旺的初始化和登录:
        username = editText1.getText().toString();
        password = editText2.getText().toString();


        init(CommonLogin.username,"23417173");
        loginHelper.login_Sample(username, password, "23417173", new IWxCallback() {

            @Override
            public void onSuccess(Object... arg0) {
                //saveLoginInfoToLocal(userId.toString(), password.toString(), appKey.toString());
                Log.e("infoo","userID:"+CommonLogin.username+"密码"+CommonLogin.password);
                //        loginButton.setClickable(true);
                //progressBar.setVisibility(View.GONE);
                Toast.makeText(CommonLogin.this, "登录成功",
                        Toast.LENGTH_SHORT).show();
                /*YWLog.i(TAG, "login success!");
                        *//*Intent intent = new Intent(LoginActivity.this, FragmentTabs.class);
                        intent.putExtra(FragmentTabs.LOGIN_SUCCESS, "loginSuccess");
                        LoginActivity.this.startActivity(intent);*//*
                Log.e("infoo","准备进入调用Actvity.finish（）！！！");
                LoginActivity.this.finish();*/

//						YWIMKit mKit = LoginSampleHelper.getInstance().getIMKit();
//						EServiceContact contact = new EServiceContact("qn店铺测试账号001:找鱼");
//						LoginActivity.this.startActivity(mKit.getChattingActivityIntent(contact));
//                        mockConversationForDemo();

                SharedPreferences mySharedPreferences= getSharedPreferences("test",
                        Activity.MODE_PRIVATE);
                //实例化SharedPreferences.Editor对象（第二步）
                SharedPreferences.Editor editor = mySharedPreferences.edit();
                //用putString的方法保存数据
                editor.putString("username",username);
                editor.putString("password", password);
                //提交当前数据
                editor.commit();
                //使用toast信息提示框提示成功写入数据
            }

            @Override
            public void onProgress(int arg0) {

            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                //progressBar.setVisibility(View.GONE);
                //if (errorCode == YWLoginCode.LOGON_FAIL_INVALIDUSER) { //若用户不存在，则提示使用游客方式登陆
                //     showDialog(GUEST);
                //} else {
                //loginButton.setClickable(true);
                //YWLog.w(TAG, "登录失败，错误码：" + errorCode + "  错误信息：" + errorMessage);
                Notification.showToastMsg(CommonLogin.this, errorMessage);
                // }
            }
        });


        intent = new Intent();
        intent.setClass(this, MainActivity.class);
        intent.putExtra("type", "login");
        startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
    }

    private   void register()
    {
        intent = new Intent();
        intent.setClass(this, CommonRegister.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
    }

    private void init(String userId, String appKey){
        //初始化imkit
        LoginSampleHelper.getInstance().initIMKit(userId, appKey);
        //自定义头像和昵称回调初始化(如果不需要自定义头像和昵称，则可以省去)
        UserProfileSampleHelper.initProfileCallback();
        //通知栏相关的初始化
        NotificationInitSampleHelper.init();

    }


    private   void forget()
    {
        intent = new Intent();
        intent.setClass(this, CommonForget.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);

    }


    public ActionCallBackListener<BaseEntity<userInfo>> listener = new ActionCallBackListener<BaseEntity<userInfo>>() {
        @Override
        public void onSuccess(BaseEntity<userInfo> data) {
            Toast toast = Toast.makeText(getApplicationContext(), "获取成功", Toast.LENGTH_LONG);
            toast.show();
            doset(data);
            userInfo.setPhoneNumb(editText1.getText().toString());
            userInfo.setPassWord(editText2.getText().toString());
            Log.e("atyfild",userInfo.getAtyField()+"");
            Log.e("atyfild",userInfo.getName()+"");
            do_queryclublist();

        }

        @Override
        public void onFailure(String e_Type, String e_Msg) {
            Toast toast = Toast.makeText(getApplicationContext(), e_Msg, Toast.LENGTH_LONG);
            toast.show();
            loadingDialog.dismiss();
        }
    };


    private void do_queryclublist() {
        AppAction appAction = Factory.createAppActionImpl(getApplicationContext());
        appAction.fc_getClubList(Params.fc_getClubList, clublistener);//"leader",
    }

    public ActionCallBackListener<BaseEntity<ClubList>> clublistener=new ActionCallBackListener<BaseEntity<ClubList>>(){

        /**
         * 处理成功
         *
         * @param data 返回数据
         */
        @Override
        public void onSuccess(BaseEntity<ClubList> data) {
            MainApplication.setClubList((List<com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList>) data.getResponse().getClubList());                      //////////////
            Toast toast = Toast.makeText(getApplicationContext(), "俱乐部获取成功", Toast.LENGTH_LONG);
            toast.show();
            do_queryPlayerCurRecord();
//            login();
//            loadingDialog.dismiss();

        }

        /**
         * 请求失败
         *
         * @param e_Type 错误码
         * @param e_Msg  错误详情
         */
        @Override
        public void onFailure(String e_Type, String e_Msg) {
            Toast toast = Toast.makeText(getApplicationContext(), "俱乐部"+e_Msg, Toast.LENGTH_LONG);
            toast.show();
            loadingDialog.dismiss();
        }
    };

    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<userInfo> data) {
        loadingDialog.show();

        Toast toast = Toast.makeText(this, "登录成功", Toast.LENGTH_LONG);
        toast.show();
        //login();

        do_querymyself();
    }

    /**
     * 请求失败
     *
     * @param e_Type 错误码
     * @param e_Msg  错误详情
     */
    @Override
    public void onFailure(String e_Type, String e_Msg) {
        loadingDialog.show();

        Toast toast = Toast.makeText(this, e_Msg, Toast.LENGTH_LONG);
        toast.show();
        loadingDialog.dismiss();

    }

    private void do_queryPlayerCurRecord() {
        AppAction appAction = Factory.createAppActionImpl(this);
        appAction.fc_queryPlayerCurRecord("user",Params.fc_queryPlayerCurRecord, numberListener);
    }
    public ActionCallBackListener<BaseEntity<PlayerRecord>> numberListener=new ActionCallBackListener<BaseEntity<PlayerRecord>>(){

        /**
         * 处理成功
         *fc_queryPlayerCurRecord
         * @param data 返回数据
         */
        @Override
        public void onSuccess(BaseEntity<PlayerRecord> data) {
            showToast("个人战绩获得成功");
            List<PlayerRecord> playerRecord=data.getResponse().getPlayerRecord();
//            loadingDialog.dismiss();
            if (playerRecord!=null) {
                for (int i = 0; i < playerRecord.size(); i++) {

                    MainApplication.PLAYERPRICE = playerRecord.get(i).getWorth();
                    MainApplication.SEASON_NAME = playerRecord.get(i).getArena_SeasonName();
                }
            }

            login();
            loadingDialog.dismiss();
        }

        /**
         * 请求失败
         *
         * @param e_Type 错误码
         * @param e_Msg  错误详情
         */
        @Override
        public void onFailure(String e_Type, String e_Msg) {
            //showToast("个人战绩获得");
            loadingDialog.dismiss();
        }
    };

    public void showToast(String str)
    {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }


}

