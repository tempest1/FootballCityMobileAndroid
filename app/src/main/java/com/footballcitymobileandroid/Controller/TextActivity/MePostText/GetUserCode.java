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
public class GetUserCode extends Activity implements View.OnClickListener ,ActionCallBackListener<BaseEntity<userInfo>>{

    EditText editText;
    Button button;
    BaseEntity<userInfo> userInfoBaseEntity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usercode);
        init();
    
    }
    private void init(){
        editText= (EditText) findViewById(R.id.usercodephones);
        button= (Button) findViewById(R.id.usercodequeren);
        button.setOnClickListener(this);
    }

    public void doit()
    {
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_get_usercode(editText.getText().toString(),Params.fc_get_usercode,this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.usercodequeren:
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
        Toast toast= Toast.makeText(this,"发送成功",Toast.LENGTH_LONG);
        toast.show();
        setdata(data);
    }

    private void setdata(BaseEntity<userInfo> userInfoBaseEntity){
        this.userInfoBaseEntity=userInfoBaseEntity;
    }
    /**
     * 请求失败
     *
     * @param e_Type 错误码
     * @param e_Msg  错误详情
     */
    @Override
    public void onFailure(String e_Type, String e_Msg) {

    }
}
