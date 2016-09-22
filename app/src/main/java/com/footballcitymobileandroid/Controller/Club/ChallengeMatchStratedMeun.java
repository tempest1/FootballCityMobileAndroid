package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.MatchMemb;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhoudi on 16/6/1.
 */
public class ChallengeMatchStratedMeun extends Activity implements View.OnClickListener {
    TextView club_info,detail_title,write_point,detail_title_center;
    Button detail_back;
    Intent intent;
    List<AranaMatchs> aranaMatchses;
    ClubList clubList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_start_match_meun);
        init();
    }

    private void  init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        aranaMatchses= (List<AranaMatchs>) getIntent().getSerializableExtra("AranaMatchs");
        detail_title=(TextView)findViewById(R.id.detail_title);
        detail_title.setText("");

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        club_info=(TextView)findViewById(R.id.club_info);
        club_info.setOnClickListener(this);

        write_point=(TextView)findViewById(R.id.write_point);
        write_point.setOnClickListener(this);


        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("设置进行中的比赛");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.club_info:        //比赛详情
                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                intent.setClass(this, ChallengeStartedMatchInfoNew.class);
                Bundle bundle=new Bundle() ;
                bundle.putSerializable("AranaMatchs", (Serializable) aranaMatchses);
                bundle.putSerializable("clublist",clubList);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);

                break;
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
            case R.id.write_point:   //录入比赛得分

                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                intent.setClass(this, ChallenagelMatchWriteEnd.class);
                Bundle bundles=new Bundle() ;
                bundles.putSerializable("AranaMatchs", (Serializable) aranaMatchses);
                bundles.putSerializable("clublist",clubList);
                intent.putExtras(bundles);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;


        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }
}
/*

 */