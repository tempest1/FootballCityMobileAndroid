 package com.footballcitymobileandroid.Controller.Common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.TimeCount;
import com.footballcitymobileandroid.Controller.Base.BaseCommonActivity;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/6/4.
 */
public class CommonForgetGetMessage extends BaseCommonActivity implements View.OnClickListener {

    private Intent intent;
    private Button me_register2_send;
    private TextView detail_title_center;
    private Button req_msg_code;
    private TimeCount time;


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
        me_register2_send=(Button)findViewById(R.id.me_register2_send);
        me_register2_send.setOnClickListener(this);
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("找回密码");
        req_msg_code = (Button) findViewById(R.id.req_msg_code);
        time=new TimeCount(90000,1000,req_msg_code);
        req_msg_code.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.me_register2_send:
                intent = new Intent();
                intent.setClass(this, CommonForgetSetPassword.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;

            case R.id.req_msg_code:
                time.start();
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }
}
