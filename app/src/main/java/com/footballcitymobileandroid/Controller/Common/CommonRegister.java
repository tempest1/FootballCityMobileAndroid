package com.footballcitymobileandroid.Controller.Common;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.Controller.Base.BaseCommonActivity;
import com.footballcitymobileandroid.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhoudi on 16/6/4.
 */
public class CommonRegister extends BaseCommonActivity implements View.OnClickListener {

    private Intent intent;
    private Button next,detail_back;
    private TextView detail_title_center;
    EditText editText;

    Pattern p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_register);
        initView();
    }



    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        editText = (EditText) findViewById(R.id.register_phones);

        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(this);
        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("注册");

        p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.next:
                Matcher m = p.matcher(editText.getText().toString());
                if (editText.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(this, "请输入手机号", Toast.LENGTH_LONG);
                    toast.show();
                } else if (TextUtils.isEmpty(editText.getText().toString())) {
                    Toast toast = Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    intent = new Intent();
                    intent.putExtra("phone", editText.getText().toString());
                    intent.setClass(this, CommonGetMessage.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                }
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
}
