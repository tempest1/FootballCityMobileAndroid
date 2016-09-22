package com.footballcitymobileandroid.Controller.Club;

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
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Wrong;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/31.
 */
public class UpdateClubGreat extends Activity implements View.OnClickListener ,ActionCallBackListener<BaseEntity<Wrong>> {

    Button detail_back,detail_title;
    ClubList clubList;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_club_great);
        init();
    }

    private void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title=(Button) findViewById(R.id.detail_title);
        detail_title.setOnClickListener(this);
        editText= (EditText) findViewById(R.id.clubWelfare);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_title:
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
        appAction.fc_modifyClub(clubList.getClubID(),"clubWelfare",editText.getText().toString(), Params.fc_modifyClub,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Wrong> data) {
        Toast toast = Toast.makeText(this, "修改福利", Toast.LENGTH_LONG);
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
