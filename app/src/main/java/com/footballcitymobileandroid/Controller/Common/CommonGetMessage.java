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
import com.footballcitymobileandroid.BLL.Util.CustomView.TimeCount;
import com.footballcitymobileandroid.Controller.Base.BaseCommonActivity;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.MeEntity.userInfo;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/6/4.
 */
public class CommonGetMessage extends BaseCommonActivity implements View.OnClickListener, ActionCallBackListener<BaseEntity<userInfo>> {

    Intent intent;
    Button me_register2_send;
    TextView detail_title_center;
    Button req_msg_code;
    private TimeCount time;
    EditText editText;
    String getphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_get_message);

        initView();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        editText = (EditText) findViewById(R.id.edit_msg_code);
        me_register2_send=(Button)findViewById(R.id.me_register2_send);
        me_register2_send.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("注册");

        req_msg_code = (Button) findViewById(R.id.req_msg_code);
        time = new TimeCount(90000, 1000, req_msg_code, me_register2_send);
        req_msg_code.setOnClickListener(this);
        Intent intent = getIntent();
        getphone = intent.getStringExtra("phone");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.me_register2_send:

                if (!editText.getText().toString().equals("")) {
                    intent = new Intent();
                    intent.putExtra("getphone", getphone);
                    intent.putExtra("getcode", editText.getText().toString());
                    intent.setClass(this, CommonSetPassword.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                } else {
                    Toast toast = Toast.makeText(this, "请输入验证码", Toast.LENGTH_LONG);
                    toast.show();
                }
                break;
            case R.id.req_msg_code:
                doit();
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
        appAction.fc_get_usercode(getphone, Params.fc_get_usercode, this);
    }

    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<userInfo> data) {
//        Toast toast = Toast.makeText(this, "修改成功", Toast.LENGTH_LONG);
//        toast.show();
        time.start();
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
