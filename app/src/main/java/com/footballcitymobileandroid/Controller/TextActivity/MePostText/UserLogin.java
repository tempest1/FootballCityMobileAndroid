package com.footballcitymobileandroid.Controller.TextActivity.MePostText;

import android.app.Activity;
import android.content.SharedPreferences;
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
public class UserLogin extends Activity implements View.OnClickListener ,ActionCallBackListener<BaseEntity<userInfo>>{

    EditText editText;
    EditText editText2;
    Button button;
    Button button2;
    Button button3;

    String phone;
    String password;

    BaseEntity<userInfo> userInfoBaseEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlogin);
        init();
    }

    private void init(){
        editText= (EditText) findViewById(R.id.userloginphone);
        editText2= (EditText) findViewById(R.id.userloginpassword);
        button= (Button) findViewById(R.id.userloginqueren);
        button.setOnClickListener(this);
        button2= (Button) findViewById(R.id.userexittuichu);
        button2.setOnClickListener(this);
        button3= (Button) findViewById(R.id.chaxunxinxi);
        button3.setOnClickListener(this);

    }

    private void doit(){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_userLogin(editText.getText().toString(),editText2.getText().toString(), Params.fc_userLogin,this);
    }

    private void doittuichu(){
        duqu();
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_userExit(phone, password, Params.fc_userExit, listener);
    }

    private void setdata(BaseEntity<userInfo> userInfoBaseEntity){
        this.userInfoBaseEntity=userInfoBaseEntity;
    }

    private void baocun()
    {
        //实例化SharedPreferences对象（第一步）
        SharedPreferences mySharedPreferences= getSharedPreferences("test",
                Activity.MODE_PRIVATE);
         //实例化SharedPreferences.Editor对象（第二步）
        SharedPreferences.Editor editor = mySharedPreferences.edit();
         //用putString的方法保存数据
        editor.putString("phone", editText.getText().toString());
        editor.putString("password", editText2.getText().toString());
        //提交当前数据
        editor.commit();
         //使用toast信息提示框提示成功写入数据
        Toast.makeText(this, "数据成功写入SharedPreferences！"+"phone: "+editText.getText().toString(), Toast.LENGTH_LONG).show();
    }

    private void duqu(){
        //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences= getSharedPreferences("test",
                Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        phone =sharedPreferences.getString("phone", "");
        password =sharedPreferences.getString("password", "");
        //使用toast信息提示框显示信息
        Toast.makeText(this, "读取数据如下："+"\n"+"phone：" + phone + "\n" + "password：" + password,
                Toast.LENGTH_LONG).show();
    }
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.userloginqueren:
                doit();
                break;
            case R.id.userexittuichu:
                doittuichu();
                break;
            case R.id.chaxunxinxi:
                AppAction appAction= Factory.createAppActionImpl(this);
                appAction.fc_queryMyself(Params.fc_queryMyself,this);
//                Intent intent=new Intent(this,UserQueryMyself.class);
//                startActivity(intent);
                break;
        }
    }

    private ActionCallBackListener<BaseEntity<Void>> listener = new ActionCallBackListener<BaseEntity<Void>>() {
        @Override
        public void onSuccess(BaseEntity<Void> data) {
            Toast toast = Toast.makeText(getApplicationContext(), "退出成功", Toast.LENGTH_LONG);
            toast.show();
        }

        @Override
        public void onFailure(String e_Type, String e_Msg) {
            Toast toast = Toast.makeText(getApplicationContext(), e_Msg, Toast.LENGTH_LONG);
            toast.show();
        }
    };
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<userInfo> data) {

        Toast toast=Toast.makeText(this,"登录成功",Toast.LENGTH_LONG);
        toast.show();
        setdata(data);
        baocun();
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
