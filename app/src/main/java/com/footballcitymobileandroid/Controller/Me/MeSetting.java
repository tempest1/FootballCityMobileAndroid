package com.footballcitymobileandroid.Controller.Me;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.MeEntity.App;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/23.
 */
public class MeSetting extends Activity implements View.OnClickListener,ActionCallBackListener<BaseEntity<App>> {

    TextView detail_title_center,checkup;
    Button detail_back;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_setting);
        init();
    }
    private void init()
    {

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("设置");

        checkup=(TextView)findViewById(R.id.checkup);
        checkup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
            case  R.id.checkup:      //检查更新
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
        appAction.fc_latestVersion(Params.fc_latestVersion,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<App> data) {
        App app;
        app= (App) data.getResponse().getApp();
        intent=new Intent();
        intent.setClass(this, MeUpdate.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("app",app);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
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
