package com.footballcitymobileandroid.Controller.TextActivity.MePostText;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.footballcitymobileandroid.R;

public class MainActivity extends Activity implements View.OnClickListener {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_main_user);
        init();
    }
    public void init()
    {
        button1= (Button) findViewById(R.id.fc_userReg);
        button1.setOnClickListener(this);
        button2= (Button) findViewById(R.id.fc_get_usercode);
        button2.setOnClickListener(this);
        button3= (Button) findViewById(R.id.fc_userLogin);
        button3.setOnClickListener(this);
        button4= (Button) findViewById(R.id.fc_userExit);
        button4.setOnClickListener(this);
        button5= (Button) findViewById(R.id.fc_queryMyself);
        button5.setOnClickListener(this);
        button6= (Button) findViewById(R.id.fc_modifyMyself);
        button6.setOnClickListener(this);
        button7= (Button) findViewById(R.id.fc_modifyPwd);
        button7.setOnClickListener(this);
        button8= (Button) findViewById(R.id.fc_modifyPhone);
        button8.setOnClickListener(this);
        button9= (Button) findViewById(R.id.fc_getConfig);
        button9.setOnClickListener(this);
    }
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fc_userReg :
                intent=new Intent(this,UserReg.class);
                startActivity(intent);
                break;
            case R.id.fc_get_usercode:
                intent=new Intent(this,GetUserCode.class);
                startActivity(intent);
                break;
            case R.id.fc_userLogin:
                intent=new Intent(this,UserLogin.class);
                startActivity(intent);
                break;
            case R.id.fc_userExit:
                intent=new Intent(this,UserExit.class);
                startActivity(intent);
                break;
            case R.id.fc_queryMyself:
                intent=new Intent(this,UserQueryMyself.class);
                startActivity(intent);
                break;
            case R.id.fc_modifyMyself:
                intent=new Intent(this,UserModifyMyself.class);
                startActivity(intent);
                break;
            case R.id.fc_modifyPwd:
                intent=new Intent(this,UserModifyPwd.class);
                startActivity(intent);
                break;
            case R.id.fc_modifyPhone:
                intent=new Intent(this,UserModifyPhone.class);
                startActivity(intent);
                break;
            case R.id.fc_getConfig:
                intent=new Intent(this,UserGetConfig.class);
                startActivity(intent);
                break;
        }
    }
}
