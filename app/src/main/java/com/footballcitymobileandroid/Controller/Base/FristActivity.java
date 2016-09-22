package com.footballcitymobileandroid.Controller.Base;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.footballcitymobileandroid.Controller.Common.CommonLogin;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/7/14.
 */
public class FristActivity extends Activity {
    private SharedPreferences mpre;
    private Intent intent;
    int loginTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frist);
        LogUtils.i("app--Restart");
        /**
         * 应用程序启动之后，
         * 1、判断是否是第一次启动 若是则进入应用引导页，
         * 2、否则判断用户是否设置了自动登录，否则 进入登录页面
         **/
        mpre = MainApplication.getPreferences();
        loginTime = mpre.getInt("logintime", 0);
        Log.e("是否首次登录", "" + loginTime);
      //  ScreenUtils.initScreen(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PackageInfo info;
                try {
                    info = getPackageManager().getPackageInfo("com.footballcitymobileandroid", 0);
                    int currentVersion = info.versionCode;
                    int lastVersion = mpre.getInt("VERSION_KEY", 0);
                    if (currentVersion > lastVersion) {
                        //如果当前版本大于上次版本，该版本属于第一次启动
                        //......
                        //将当前版本写入preference中，则下次启动的时候，据此判断，不再为首次启动
                        mpre.edit().putInt("VERSION_KEY", currentVersion).apply();
                        Log.e("当前版本的信息", "" + mpre.getInt("VERSION_KEY", 0));
                        intent = new Intent(FristActivity.this,CommonLogin.class);//应用程序首次启动时，将启动引导进入欢迎页面
                        startActivity(intent);
                        FristActivity. this.finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    } else {
                        intent = new Intent(FristActivity.this,CommonLogin.class);//应用程序首次启动时，将启动引导进入欢迎页面
                        startActivity(intent);
                        FristActivity. this.finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }, 2000);

    }
}
