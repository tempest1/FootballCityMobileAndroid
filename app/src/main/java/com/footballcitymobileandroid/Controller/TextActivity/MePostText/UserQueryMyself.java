package com.footballcitymobileandroid.Controller.TextActivity.MePostText;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
public class UserQueryMyself extends Activity implements View.OnClickListener,ActionCallBackListener<BaseEntity<userInfo>>{

   public  BaseEntity<userInfo> userInfoBaseEntity;
    public userInfo userInfo;

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userquerymyself);
        init();
    }
    private void init(){
        textView1= (TextView) findViewById(R.id.queryphoto);
        textView2= (TextView) findViewById(R.id.queryname);
        textView3= (TextView) findViewById(R.id.querysex);
        textView4= (TextView) findViewById(R.id.querybirthdy);
        textView5= (TextView) findViewById(R.id.queryheight);
        textView6= (TextView) findViewById(R.id.queryweight);
        textView7= (TextView) findViewById(R.id.queryposition);
        textView8= (TextView) findViewById(R.id.queryatytime);
        textView9= (TextView) findViewById(R.id.queryatyfield);
        button= (Button) findViewById(R.id.fc_queryMyself);
        button.setOnClickListener(this);
    }

    private void doit(){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_queryMyself(Params.fc_queryMyself,this);

    }

    private void setdata(BaseEntity<userInfo> userInfoBaseEntity){
        this.userInfoBaseEntity=userInfoBaseEntity;
    }

    private void settext(){
        userInfo= (com.footballcitymobileandroid.Entity.MeEntity.userInfo) userInfoBaseEntity.getResponse().getT();
        textView1.setText(userInfo.getPhoto());
        textView2.setText(userInfo.getName());
        textView3.setText(userInfo.getSex());
        textView4.setText(userInfo.getBrithday());
        textView5.setText(userInfo.getHeight());
        textView6.setText(userInfo.getWeight());
//        textView7.setText((String)userInfo.getPosition().get(0));
        textView8.setText(String.valueOf(userInfo.getAtyTime()[0]));
        textView9.setText(userInfo.getAtyField());
        Log.i("birthday", userInfo.getBrithday());
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
            case R.id.fc_queryMyself:
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
        Toast toast=Toast.makeText(this,"查询成功",Toast.LENGTH_LONG);
        toast.show();
        setdata(data);
        settext();
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
