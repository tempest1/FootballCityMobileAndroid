package com.footballcitymobileandroid.Controller.TextActivity.MePostText;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.MeEntity.userInfo;
import com.footballcitymobileandroid.R;

/**
 * Created by smartlab on 16/5/16.
 */
public class UserReg extends Activity implements ActionCallBackListener<BaseEntity<com.footballcitymobileandroid.Entity.MeEntity.userInfo>>,View.OnClickListener {

    EditText editText1,editText2,editText3,editText4;
    public  BaseEntity<userInfo> data;
    public userInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userreg);
        init();
    }
    TextView textView;
    public void init()
    {
        editText1= (EditText) findViewById(R.id.phonenum);
        editText2= (EditText) findViewById(R.id.pass);
        editText3= (EditText) findViewById(R.id.c_pass);
        editText4= (EditText) findViewById(R.id.code);
        textView= (TextView) findViewById(R.id.yangxheng);
        textView.setOnClickListener(this);
        Button button= (Button) findViewById(R.id.queren);
        button.setOnClickListener(this);

    }

    public void doit()
    {
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_userReg(editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString(),editText4.getText().toString(), Params.fc_userReg,this );
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<userInfo> data) {
        Toast toast=Toast.makeText(this,"发送成功",Toast.LENGTH_LONG);
        toast.show();
        setdata(data);
    }

    public void setdata(BaseEntity<userInfo> data)
    {this.data=data;}
    /**
     * 请求失败
     * @param e_Type 错误码
     * @param e_Msg  错误详情
     */
    @Override
    public void onFailure(String e_Type, String e_Msg) {
        editText3.setText(e_Msg);
        Toast toast= Toast.makeText(this,e_Msg,Toast.LENGTH_LONG);
        toast.show();
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
            case R.id.queren :
                doit();
                break;

            case R.id.yangxheng:
                Log.i("sss",data.toString());
                userInfo = (userInfo)data.getResponse().getT();
                editText4.setText(userInfo.getPhoneNumb());

        }
    }
}

