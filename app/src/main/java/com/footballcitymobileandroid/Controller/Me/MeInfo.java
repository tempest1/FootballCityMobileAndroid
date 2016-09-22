package com.footballcitymobileandroid.Controller.Me;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.CustomView.ActivityCollector;
import com.footballcitymobileandroid.BLL.Util.CustomView.LoadingDialog;
import com.footballcitymobileandroid.Controller.Base.BaseActivity;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Controller.Common.CommonLogin;
import com.footballcitymobileandroid.Controller.Common.CommonReSet;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.MeEntity.userInfo;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/6/4.
 */
public class MeInfo extends BaseActivity implements View.OnClickListener, ActionCallBackListener<BaseEntity<Void>> {

    Button detail_back,exit;
    TextView user, update, detail_title_center,update_password;
    private Intent intent;
    userInfo userInfo;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_info);
        init();
    }

    private void init()
    {
        loadingDialog = new LoadingDialog(this,R.drawable.loading);
        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        user=(TextView)findViewById(R.id.user);
        user.setOnClickListener(this);

        update=(TextView)findViewById(R.id.update);
        update.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("个人信息");

        update_password=(TextView)findViewById(R.id.update_password);
        update_password.setOnClickListener(this);

        exit=(Button) findViewById(R.id.exit);
        exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
            case R.id.user:                     //个人信息
                intent = new Intent();
                intent.setClass(this, MeMyInfo.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.update:                   //修改账户
                intent = new Intent();
                intent.setClass(this, ChangePhone.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.exit:
                doit();
                break;//CommonReSet
            case R.id.update_password: // 修改密码
                intent = new Intent();
                intent.setClass(this, CommonReSet.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;

        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

    private void doit() {
        AppAction appAction = Factory.createAppActionImpl(this);
        if (MainApplication.getUserInfo() != null) {
            appAction.fc_userExit(MainApplication.getUserInfo().getPhoneNumb(), MainApplication.getUserInfo().getPassWord(), Params.fc_userExit, this);
            loadingDialog.show();

        } else {
            Toast toast = Toast.makeText(this, "未登录", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Void> data) {
        Toast toast = Toast.makeText(this, "退出成功", Toast.LENGTH_LONG);
        toast.show();
        ActivityCollector.finishAll();
        intent = new Intent();
        intent.setClass(this, CommonLogin.class);
        startActivity(intent);

        MainApplication.sharedPreferences.edit().clear().commit();
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
        Toast toast = Toast.makeText(this, e_Msg, Toast.LENGTH_LONG);
        toast.show();
        loadingDialog.dismiss();

    }
}
