package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.DAL.Data.LogUtil.StringUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/7/28.
 */
public class ChallengeExitMatchInfo extends Activity implements View.OnClickListener,ActionCallBackListener<BaseEntity<Void>>{

    private Button refuse,accept;
    private TextView place,money,days,time,way,detail_title_center;
    AranaMatchs aranaMatchses;
    ClubList clubList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_match_exiting);
        init();
    }
    private void init(){
        aranaMatchses= (AranaMatchs) getIntent().getSerializableExtra("AranaMatchs");
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        refuse=(Button)findViewById(R.id.refuse);
        refuse.setOnClickListener(this);
        accept=(Button)findViewById(R.id.accept);
        accept.setOnClickListener(this);

        place=(TextView)findViewById(R.id.way);
        money=(TextView)findViewById(R.id.money);
        days=(TextView)findViewById(R.id.days);
        time=(TextView)findViewById(R.id.time);
        way=(TextView)findViewById(R.id.way);
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);

        place.setText(aranaMatchses.getAranaName());
        money.setText(StringUtils.costMode(aranaMatchses.getCostMode()));
        days.setText(aranaMatchses.getMatchDate());
        time.setText(aranaMatchses.getMatchTime());
        way.setText(aranaMatchses.getMatchRule()+"v"+aranaMatchses.getMatchRule());
        detail_title_center.setText("申请退赛");
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.accept:
                doit("true");
                break;
            case R.id.refuse:
                doit("false");
                break;
        }
    }

    private void doit(String bool){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_dealQuitApply(aranaMatchses.getAreanaMatchID(),clubList.getClubID(), bool,bool,Params.fc_dealQuitApply,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Void> data) {
        Toast toast = Toast.makeText(this, "处理成功", Toast.LENGTH_LONG);
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
