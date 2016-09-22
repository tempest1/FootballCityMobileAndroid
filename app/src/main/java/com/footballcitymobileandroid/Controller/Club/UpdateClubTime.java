package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.graphics.Color;
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
import com.footballcitymobileandroid.Entity.ClubEntity.club.Wrong;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/31.
 */
public class UpdateClubTime extends Activity implements View.OnClickListener ,ActionCallBackListener<BaseEntity<Wrong>> {
     Button detail_back,detail_title;
    private TextView week1,week2,week3,week4,week5,week6,week7;
    int[] week= new int[]{0,0,0,0,0,0,0};
    ClubList clubList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_club_time);
        init();
    }

    private void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);
        week1=(TextView)findViewById(R.id.week1);
        week2=(TextView)findViewById(R.id.week2);
        week3=(TextView)findViewById(R.id.week3);
        week4=(TextView)findViewById(R.id.week4);
        week5=(TextView)findViewById(R.id.week5);
        week6=(TextView)findViewById(R.id.week6);
        week7=(TextView)findViewById(R.id.week7);
        week1.setOnClickListener(this);
        week2.setOnClickListener(this);
        week3.setOnClickListener(this);
        week4.setOnClickListener(this);
        week5.setOnClickListener(this);
        week6.setOnClickListener(this);
        week7.setOnClickListener(this);

        detail_title=(Button) findViewById(R.id.detail_title);
        detail_title.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
            case R.id.week1:
                setColor(week1, 0);
                break;
            case R.id.week2:
                setColor(week2,1);
                break;
            case R.id.week3:
                setColor(week3,2);

                break;
            case R.id.week4:
                setColor(week4,3);

                break;
            case R.id.week5:
                setColor(week5,4);

                break;

            case R.id.week6:
                setColor(week6,5);

                break;
            case R.id.week7:
                setColor(week7,6);

                break;
            case R.id.detail_title:
                doit();
                break;
        }
    }
    int [] atytime=new int[]{0,0,0,0,0,0,0};
    private void setColor(TextView weeks,int x)
    {
        if (week[x]==0) {
            week[x]=1;
            weeks.setTextColor(Color.RED);
            atytime[x]=x+1;
//            MainApplication.getClub().setAtyTime(atytime);
//            Log.i("time",String.valueOf(MainApplication.getClub().getAtyTime()[x])+x);

        }else {
            week[x]=0;
            weeks.setTextColor(Color.BLACK);
            atytime[x]=0;
//            MainApplication.getClub().setAtyTime(atytime);
//            Log.i("time", String.valueOf(MainApplication.getClub().getAtyTime()[x])+x);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

    private void doit(){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_modifyClub(clubList.getClubID(),"atyTime",atytime, Params.fc_modifyClub,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Wrong> data) {
        Toast toast = Toast.makeText(this, "修改活动时间成功", Toast.LENGTH_LONG);
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
