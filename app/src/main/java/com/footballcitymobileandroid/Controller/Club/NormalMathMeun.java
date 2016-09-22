package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/30.
 */
public class NormalMathMeun extends Activity implements View.OnClickListener{

    RelativeLayout add_match,find_matchifo;

     Button detail_back;
    TextView detail_title_center;
    Intent intent;
    ClubList clubList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_math_meun);
        init();
        inData();
    }

    private void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        add_match=(RelativeLayout)findViewById(R.id.add_match);
        add_match.setOnClickListener(this);

        find_matchifo=(RelativeLayout)findViewById(R.id.find_matchifo);
        find_matchifo.setOnClickListener(this);

        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("普通比赛选择");
    }

    private void inData()
    {
        intent = this.getIntent();
        MainApplication.FindClub= intent.getStringExtra("type");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.add_match:             //添加比赛信息
                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                intent.setClass(this , ClubPlayWrite.class);
                Bundle bundless=new Bundle();
                bundless.putSerializable("clublist",clubList);
                intent.putExtras(bundless);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.find_matchifo:
                intent = new Intent();         //查看比赛
//                Bundle bundle=new Bundle();
//                bundle.putString("type", "me");
//                intent.putExtras(bundle);
//                intent.setClass(this, NormalMacthInfoList.class);NormalMatchInfo
                intent.setClass(this, NormalMatchInfo.class);
                Bundle bundles=new Bundle();
                bundles.putSerializable("clublist",clubList);
                intent.putExtras(bundles);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
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
