package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/6/3.
 */
public class ChallengeStartedMatchInfo extends Activity implements View.OnClickListener {
    TextView detail_title_center,detail_title,you_club_number,my_club_number;
    Button detail_back,btn_set;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_started_match_info);
        init();
    }

    private void  init()
    {

        detail_title=(TextView)findViewById(R.id.detail_title);
        detail_title.setText("");

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("结束比赛详情");

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        btn_set=(Button)findViewById(R.id.btn_set);
        btn_set.setOnClickListener(this);

        you_club_number=(TextView)findViewById(R.id.you_club_number);
        you_club_number.setOnClickListener(this);

        my_club_number=(TextView)findViewById(R.id.my_club_number);
        my_club_number.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;

            case R.id.btn_set:

                intent = new Intent();
                intent.setClass(this, NormalMatchAfterPlaySetting.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("club", data);
      //          intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.my_club_number:
                intent = new Intent();
                intent.setClass(this, ClubNumberList.class);//MeClubNumberList
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("club", data);
//        intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.you_club_number:
                intent = new Intent();
                intent.setClass(this, ClubNumberList.class);//MeClubNumberList
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("club", data);
//        intent.putExtras(bundle);
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
