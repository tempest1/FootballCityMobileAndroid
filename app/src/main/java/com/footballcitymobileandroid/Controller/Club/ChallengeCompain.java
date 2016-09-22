package com.footballcitymobileandroid.Controller.Club;

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
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.NomiMembs;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/6/3.
 */
public class ChallengeCompain extends Activity implements View.OnClickListener ,ActionCallBackListener<BaseEntity<Void>> {
    TextView detail_title_center,detail_title;
    Button detail_back;
    Intent intent;

    List<NomiMembs> nomiMembs;
    ClubList clubList;
    AranaMatchs aranaMatchses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_complain );
        init();
    }

    private void  init()
    {

        nomiMembs= (List<NomiMembs>) getIntent().getSerializableExtra("nomiMembs");
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        aranaMatchses= (AranaMatchs) getIntent().getSerializableExtra("AranaMatchs");
        detail_title=(TextView)findViewById(R.id.detail_title);
        detail_title.setText("");

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("投诉");

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
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
        appAction.fc_voteNomiMemb(aranaMatchses.getAreanaMatchID(),clubList.getClubID(),nomiMembs.get(0).getClubMembID(), Params.fc_voteNomiMemb,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Void> data) {
        Toast toast = Toast.makeText(this, "投票成功", Toast.LENGTH_LONG);
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
