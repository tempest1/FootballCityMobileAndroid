package com.footballcitymobileandroid.Controller.TextActivity.MePostText;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.AppActionImpl;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.MeEntity.userInfo;
import com.footballcitymobileandroid.R;

/**
 * Created by smartlab on 16/5/16.
 */
public class UserGetConfig extends Activity implements View.OnClickListener,ActionCallBackListener<BaseEntity<userInfo>>{
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usergetconfig);
        init();
    }

    private void init(){
        button= (Button) findViewById(R.id.configqueren);
        button.setOnClickListener(this);
    }
    private void doit(){
        AppAction appAction=new AppActionImpl(this);
        appAction.fc_getConfig(Params.fc_getConfig,this);
    }
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.configqueren:
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
        Toast toast=Toast.makeText(this,"登录成功",Toast.LENGTH_LONG);
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
