package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/
 */
public class ChallengeAlreadyInfoDeal extends Activity implements View.OnClickListener{

    private Intent intent;
    private RelativeLayout look_sign_people,sign,write_player,delete;
    private Button detail_back;

    TextView detail_title_center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_match_match_info_deal);
        init();
        initData();
    }

    private void init()
    {
        look_sign_people=(RelativeLayout)findViewById(R.id.look_sign_people);
        look_sign_people.setOnClickListener(this);

        sign=(RelativeLayout)findViewById(R.id.sign);
        sign.setOnClickListener(this);

        write_player=(RelativeLayout)findViewById(R.id.write_player);
        write_player.setOnClickListener(this);
        write_player.setVisibility(View.GONE);

        delete=(RelativeLayout)findViewById(R.id.delete);
        delete.setOnClickListener(this);
        delete.setVisibility(View.GONE);


        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);



        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("待开始比赛功能");

    }
    private void initData()
    {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.look_sign_people:                  //查看签到
                intent = new Intent();
                intent.setClass(this, ChallenageMatchReStartSet.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("club", data);
//                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.sign:                         //签到
                intent = new Intent();
                intent.setClass(this, ChallengeMatchSigns.class);//  NormalMatchSigns
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("club", data);
//                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;

            case R.id.delete:

                break;
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
