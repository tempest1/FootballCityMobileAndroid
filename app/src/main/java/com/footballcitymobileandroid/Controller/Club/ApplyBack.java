package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.os.Bundle;
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
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/7/26.
 */
public class ApplyBack extends Activity implements View.OnClickListener,ActionCallBackListener<BaseEntity<Void>> {

    Button detail_back,btn_send;
    TextView detail_title_center;

    EditText edit;

    List<AranaMatchs> aranaMatchses;//
    ClubList clubList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_back);
        init();
    }

    private void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        aranaMatchses= (List<AranaMatchs>) getIntent().getSerializableExtra("matchingInfoList");
        detail_back=(Button)findViewById(R.id.detail_back);
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("申请退赛");

        edit=(EditText)findViewById(R.id.edit);
        btn_send=(Button)findViewById(R.id.btn_send);
        detail_back.setOnClickListener(this);
        btn_send.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_send:
                doit();

                break;
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
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
        appAction.fc_quitApply(aranaMatchses.get(0).getAreanaMatchID(),clubList.getClubID(),edit.getText().toString(), Params.fc_quitApply,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Void> data) {
        Toast toast = Toast.makeText(this, "申请成功", Toast.LENGTH_LONG);
        toast.show();
        this.finish();
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
