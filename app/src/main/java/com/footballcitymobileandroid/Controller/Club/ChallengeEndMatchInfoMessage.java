package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/6/3.
 */
public class ChallengeEndMatchInfoMessage extends Activity implements View.OnClickListener {
    ClubList clubList;
    AranaMatchs aranaMatchses;
    TextView detail_title_center;
    Button detail_back;
    Intent intent;
    private TextView tv_me,time,tv_your,ways,place,me_point,you_point,me_club,you_club;
    private CircleButton image_my,image_yours;
    private CircleButton me_iamge1,me_iamge2,me_iamge3,you_iamge1,you_iamge2,you_iamge3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_end_match_info);
        init();
    }

    private void  init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        aranaMatchses= (AranaMatchs) getIntent().getSerializableExtra("AranaMatchs");
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("结束比赛详情");

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        tv_me=(TextView)findViewById(R.id.tv_me);
        tv_your=(TextView)findViewById(R.id.tv_your);
        time=(TextView)findViewById(R.id.time);
        ways=(TextView)findViewById(R.id.ways);
        place=(TextView)findViewById(R.id.way);
        me_point=(TextView)findViewById(R.id.me_point);
        you_point=(TextView)findViewById(R.id.you_point);
        me_club=(TextView)findViewById(R.id.me_club);
        you_club=(TextView)findViewById(R.id.you_club);

        image_my=(CircleButton)findViewById(R.id.image_my);
        image_yours=(CircleButton)findViewById(R.id.image_yours);

        me_iamge1=(CircleButton)findViewById(R.id.me_iamge1);
        me_iamge2=(CircleButton)findViewById(R.id.me_iamge2);
        me_iamge3=(CircleButton)findViewById(R.id.me_iamge3);
        you_iamge1=(CircleButton)findViewById(R.id.you_iamge1);
        you_iamge2=(CircleButton)findViewById(R.id.you_iamge2);
        you_iamge3=(CircleButton)findViewById(R.id.you_iamge3);


        tv_me.setText(clubList.getClubName());


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
}
