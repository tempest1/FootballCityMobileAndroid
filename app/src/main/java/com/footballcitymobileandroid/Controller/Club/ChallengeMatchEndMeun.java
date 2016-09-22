package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/6/1.
 */
public class ChallengeMatchEndMeun extends Activity implements View.OnClickListener {
    TextView club_info,detail_title,evaluate,complain,detail_title_center;
    Button detail_back;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_end_match_meun);
        init();

    }

    private void  init()
    {

        detail_title=(TextView)findViewById(R.id.detail_title);
        detail_title.setText("");

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        club_info=(TextView)findViewById(R.id.club_info);
        club_info.setOnClickListener(this);

        evaluate=(TextView)findViewById(R.id.evaluate);
        evaluate.setOnClickListener(this);

        complain=(TextView)findViewById(R.id.complain);
        complain.setOnClickListener(this);
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);

        detail_title_center.setText("设置已结束的比赛 ");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.club_info:
                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                intent.setClass(this, ChallengeEndMatchInfo.class); //ChallengeEndMatchInfoMessage
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);

                break;
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
            case R.id.evaluate:

                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                intent.setClass(this, ChallengeEvaluate.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.complain:
                    intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                intent.setClass(this, ChallengeCompain.class);
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
