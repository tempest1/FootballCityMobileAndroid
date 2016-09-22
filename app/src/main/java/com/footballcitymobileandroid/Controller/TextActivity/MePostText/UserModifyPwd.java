package com.footballcitymobileandroid.Controller.TextActivity.MePostText;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
public class UserModifyPwd extends Activity implements View.OnClickListener,ActionCallBackListener<BaseEntity<userInfo>>{

    EditText editText1;
    EditText editText2;
    EditText editText3;
    Button button1;
    Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usermodifypwd);
        init();

    }

private void init(){
    editText1= (EditText) findViewById(R.id.modifyphone);
    editText2= (EditText) findViewById(R.id.modifynewPwd);
    editText3= (EditText) findViewById(R.id.modifycode);
    button1= (Button) findViewById(R.id.modifygetcode);
    button1.setOnClickListener(this);
    button2= (Button) findViewById(R.id.modifypwdqueren);
    button2.setOnClickListener(this);
}

    private void doit(){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_modifyPwd(editText2.getText().toString(),editText3.getText().toString(), Params.fc_modifyPwd,this);
        }
    private void doit2(){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_get_usercode(editText1.getText().toString(),Params.fc_get_usercode,this);
        }
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.modifygetcode:
                doit2();
                break;
            case R.id.modifypwdqueren:
                doit();
                break;
        }
    }

    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<userInfo> data) {
        Toast toast=Toast.makeText(this,"修改成功",Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * 请求失败
     *
     * @param e_Type 错误码
     * @param e_Msg  错误详情
     */
    @Override
    public void onFailure(String e_Type, String e_Msg) {
        Toast toast=Toast.makeText(this,e_Msg,Toast.LENGTH_LONG);
        toast.show();
    }
}
