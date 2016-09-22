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
 * Created by zhoudi on 16/7/29.
 */
public class CommonReSet extends BaseCommonActivity implements View.OnClickListener, ActionCallBackListener<BaseEntity<userInfo>> {
    Intent intent;
    Button next, detail_back;
    TextView detail_title_center;
    Button req_msg_code;
    EditText editText1, editText2;
    private TimeCount time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_forget);
        initView();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        editText1 = (EditText) findViewById(R.id.common_phones);
        editText2 = (EditText) findViewById(R.id.common_code);

        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(this);
        next.setEnabled(false);
        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("修改密码");

        req_msg_code = (Button) findViewById(R.id.req_msg_code);
        time = new TimeCount(90000, 1000, req_msg_code, next);
        req_msg_code.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.next:
                if (!editText2.getText().toString().equals("")) {
                    intent = new Intent();
                    intent.putExtra("code", editText2.getText().toString());
                    intent.setClass(this, CommonReSetPassword.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                } else {
                    Toast toast = Toast.makeText(this, "请输入验证码", Toast.LENGTH_LONG);
                    toast.show();
                }
                break;
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
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
        appAction.fc_get_usercode(editText1.getText().toString(), Params.fc_get_usercode, this);
    }

    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<userInfo> data) {
        Toast toast = Toast.makeText(this, "发送成功", Toast.LENGTH_LONG);
        toast.show();
        time.start();
        next.setEnabled(true);
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
