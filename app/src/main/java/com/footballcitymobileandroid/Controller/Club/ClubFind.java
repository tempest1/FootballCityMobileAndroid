package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.LoadingDialog;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/24.
 */
public class ClubFind extends Activity implements View.OnClickListener{


    RelativeLayout findclub,if_findclub;
    private LoadingDialog loadingDialog;

    private Button detail_back;
    Intent intent;

    TextView detail_title_center;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_club);
        init();
        inData();
    }

    private void init()
    {

        findclub=(RelativeLayout)findViewById(R.id.findclub);
        findclub.setOnClickListener(this);

        if_findclub=(RelativeLayout)findViewById(R.id.if_findclub);
        if_findclub.setOnClickListener(this);

        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);

        detail_title_center.setText("找俱乐部");
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
            case R.id.findclub:          //姓名查找
                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                intent.setClass(this , ClubFindNormal.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.if_findclub:         //条件查找
                intent = new Intent();
		Bundle bundle=new Bundle();
		bundle.putString("type", "me");
		intent.putExtras(bundle);
                intent.setClass(this, ClubFindIf.class);
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
