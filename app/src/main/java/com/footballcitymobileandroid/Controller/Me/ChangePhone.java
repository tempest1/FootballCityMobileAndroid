package com.footballcitymobileandroid.Controller.Me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.Controller.Base.BaseChangeActivity;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.MeEntity.userInfo;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/6/22.
 */
public class ChangePhone extends BaseChangeActivity implements View.OnClickListener,ActionCallBackListener<BaseEntity<userInfo>>{

    Button detail_back,next,req_msg_code;
    TextView detail_title_center;
    EditText common_code,psd,common_phones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_phone);
        init();
    }

    private void init()
    {
        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("修改手机号");

        common_code=(EditText)findViewById(R.id.common_code);//验证码
        psd=(EditText)findViewById(R.id.psd);       //密码
        psd.setVisibility(View.VISIBLE);
        common_phones= (EditText) findViewById(R.id.common_phones);
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
                if(!common_code.getText().toString().equals("")){
                    Intent intent = new Intent();
                    intent.setClass(this, ChangePhone2.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("club", data);
//        intent.putExtras(bundle);
                    intent.putExtra("psd",psd.getText().toString());
                    intent.putExtra("code",common_code.getText().toString());
                    startActivity(intent);
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                }else{
                    Toast toast = Toast.makeText(this, "验证码不能为空!", Toast.LENGTH_LONG);
                    toast.show();
                }


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
}
