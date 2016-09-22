package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/28.
 */
public class PlayerFind extends Activity implements View.OnClickListener{
    private RelativeLayout find_player,if_find_player;

    Button detail_back;
    TextView detail_title_center;
    Intent intent;
    ClubList clubList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_find);
        init();


    }

    private void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        find_player=(RelativeLayout)findViewById(R.id.find_player);
        find_player.setOnClickListener(this);

        if_find_player=(RelativeLayout)findViewById(R.id.if_find_player);
        if_find_player.setOnClickListener(this);

        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("球员查找");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.find_player:         //普通查找
                intent = new Intent();
                Bundle bundleb=new Bundle();
                bundleb.putSerializable("clublist",clubList);
                intent.putExtras(bundleb);
                intent.setClass(this , PlayerFindNomal.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.if_find_player:     //条件查找
                intent = new Intent();
                Bundle bundle=new Bundle();
                bundle.putSerializable("clublist",clubList);
                intent.putExtras(bundle);
                intent.setClass(this , PlayerFindIf.class);
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

