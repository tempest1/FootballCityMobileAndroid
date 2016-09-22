package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/31.
 */
public class UpdateClub extends Activity implements View.OnClickListener{

    private Button detail_back;

    private TextView update_image,update_clubname,update_time,updte_message,updte_great;

    private Intent intent;
    ClubList clubList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.up_date_club);
        init();
    }

    private void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        Log.i("clubnamecccc",clubList.getClubName());
        update_image=(TextView)findViewById(R.id.update_image);
        update_image.setOnClickListener(this);

        update_clubname=(TextView)findViewById(R.id.update_clubname);
        update_clubname.setOnClickListener(this);

        update_time=(TextView)findViewById(R.id.update_time);
        update_time.setOnClickListener(this);

        updte_message=(TextView)findViewById(R.id.updte_message);
        updte_message.setOnClickListener(this);

        updte_great=(TextView)findViewById(R.id.updte_great);
        updte_great.setOnClickListener(this);

        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.update_image:           //修改图片
                intent=new Intent();
                intent.setClass(this,UpdateClubImage.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("clublist",clubList);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;

            case R.id.update_clubname:       //修改俱乐部名称
                intent=new Intent();
                intent.setClass(this,UpdateClubName.class);
                Bundle bundlea=new Bundle();
                bundlea.putSerializable("clublist",clubList);
                intent.putExtras(bundlea);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;

            case R.id.update_time:         //修改活动时间
                intent=new Intent();
                intent.setClass(this,UpdateClubTime.class);
                Bundle bundleb=new Bundle();
                bundleb.putSerializable("clublist",clubList);
                intent.putExtras(bundleb);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;

            case R.id.updte_message:       //修改相关信息
                intent=new Intent();
                intent.setClass(this,UpdateClubMessage.class);
                Bundle bundlec=new Bundle();
                bundlec.putSerializable("clublist",clubList);
                intent.putExtras(bundlec);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;

            case R.id.updte_great:        //修改福利
                intent=new Intent();
                intent.setClass(this,UpdateClubGreat.class);
                Bundle bundled=new Bundle();
                bundled.putSerializable("clublist",clubList);
                intent.putExtras(bundled);
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
