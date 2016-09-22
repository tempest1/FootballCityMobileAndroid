package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/31.
 */
public class NormalMatchAfterPlaySetting extends Activity implements View.OnClickListener{

    private Button detail_back;
    TextView detail_title_center;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_match_after_play_setting);
        init();
    }

    private  void init()
    {
        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("赛前部署");
    }

    /*
     *
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }
}



