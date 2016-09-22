package com.footballcitymobileandroid.Controller.Me;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.CustomView.ActivityCollector;
import com.footballcitymobileandroid.Controller.Base.BaseChangeActivity;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.MeEntity.userInfo;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/6/22.
 */
public class ChangePhone2  extends BaseChangeActivity implements View.OnClickListener,ActionCallBackListener<BaseEntity<userInfo>> {
    Button detail_back,next,req_msg_code;
    TextView detail_title_center;
    EditText common_code,common_phones;
    String code,psd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_forget);
        init();
    }

    private void init()
    {
        code=getIntent().getStringExtra("code");
        psd=getIntent().getStringExtra("psd");
        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("修改手机号");

        common_phones= (EditText) findViewById(R.id.common_phones);
        common_code=(EditText)findViewById(R.id.common_code);
        next.setText("完成");
        req_msg_code= (Button) findViewById(R.id.req_msg_code);
        req_msg_code.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
            case R.id.next:


                do_modifyPhone();
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
    private void doit(){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_get_usercode(common_phones.getText().toString(), Params.fc_get_usercode,this);
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

    private void do_modifyPhone() {
        AppAction appAction = Factory.createAppActionImpl(getApplicationContext());
        if(!common_code.getText().toString().equals("")){
            appAction.fc_modifyPhone(code,common_phones.getText().toString(),common_code.getText().toString(),psd,Params.fc_modifyPhone, modifyPhone);//"leader",

        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "验证码不能为空", Toast.LENGTH_LONG);
            toast.show();
        }
          }

    public ActionCallBackListener<BaseEntity<userInfo>> modifyPhone=new ActionCallBackListener<BaseEntity<userInfo>>(){

        /**
         * 处理成功
         *
         * @param data 返回数据
         */
        @Override
        public void onSuccess(BaseEntity<userInfo> data) {
//            MainApplication.setClubList((List<Void>) data.getResponse().getClubList());                      //////////////
            Toast toast = Toast.makeText(getApplicationContext(), "修改手机号成功", Toast.LENGTH_LONG);
            toast.show();
            ActivityCollector.finishAll();
//            login();
//            loadingDialog.dismiss();

        }

        /**
         * 请求失败
         *
         * @param e_Type 错误码
         * @param e_Msg  错误详情
         */
        @Override
        public void onFailure(String e_Type, String e_Msg) {
            Toast toast = Toast.makeText(getApplicationContext(), e_Msg, Toast.LENGTH_LONG);
            toast.show();
//            loadingDialog.dismiss();
        }
    };

}
