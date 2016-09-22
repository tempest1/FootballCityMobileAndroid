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
public class ChallengeEvaluate extends Activity implements View.OnClickListener   {
    TextView detail_title_center,detail_title;
    Button detail_back;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_evaluate);
        init();
    }

    private void  init()
    {

        detail_title=(TextView)findViewById(R.id.detail_title);
        detail_title.setText("");

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("评价");

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
}
