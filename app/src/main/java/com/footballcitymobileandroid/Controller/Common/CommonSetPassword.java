package com.footballcitymobileandroid.Controller.Common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.CustomView.ActivityCollector;
import com.footballcitymobileandroid.Controller.Base.BaseCommonActivity;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.MeEntity.userInfo;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/6/4.
 */
public class CommonSetPassword extends BaseCommonActivity implements View.OnClickListener, ActionCallBackListener<BaseEntity<userInfo>> {

    Button setPassword,detail_back;
    TextView detail_title_center;
    String getphone, getcode;
    EditText editText1, editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_set_password);
        initView();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        editText1 = (EditText) findViewById(R.id.modifypwd1);
        editText2 = (EditText) findViewById(R.id.modifypwd2);
        setPassword=(Button)findViewById(R.id.setPassword);
        setPassword.setOnClickListener(this);

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("注册");
        Intent intent = getIntent();
        getphone = intent.getStringExtra("getphone");
        getcode = intent.getStringExtra("getcode");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.setPassword:
                doset();
                break;
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

    private void doset() {
        if (editText1.getText().toString().equals("") || editText2.getText().toString().equals("")) {
            Toast toast = Toast.makeText(this, "密码输入不能为空", Toast.LENGTH_LONG);
            toast.show();
        } else if (editText1.getText().toString().equals(editText2.getText().toString())) {
            doit();
        } else {
            Toast toast = Toast.makeText(this, "密码输入不一致", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void doit() {
        AppAction appAction = Factory.createAppActionImpl(this);
        appAction.fc_userReg(getphone, editText1.getText().toString(), editText2.getText().toString(), getcode, Params.fc_userReg, this);
    }

    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<userInfo> data) {
        Toast toast = Toast.makeText(this, "注册成功", Toast.LENGTH_LONG);
        toast.show();
        ActivityCollector.finishAllInvestment();
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
    }
}
