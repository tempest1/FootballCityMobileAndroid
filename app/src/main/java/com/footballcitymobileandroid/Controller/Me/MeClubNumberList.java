package com.footballcitymobileandroid.Controller.Me;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Adapter.MeAdapter.MeClubNumberAdapter;
import com.footballcitymobileandroid.Controller.TestData.MeClubNumberInfo;
import com.footballcitymobileandroid.Controller.TestData.MeClubNumbers;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.MeEntity.Players;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/6/7.
 */
public class MeClubNumberList extends Activity implements View.OnClickListener {

    private ListView list;
    private MeClubNumberAdapter adapter;
    private Button detail_back;

    ClubList clubList;
    List<Players> players;
    TextView detail_title_center;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_club_number_list);
        init();
    }

    private void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        players= (List<Players>) getIntent().getSerializableExtra("player");

        list=(ListView)findViewById(R.id.pull_list);

        if (players!=null) {
            adapter = new MeClubNumberAdapter(this, players);
            list.setAdapter(adapter);
        }else {

        }
        list.setOnItemClickListener(info_click);

        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("俱乐部查询列表");
    }

    private AdapterView.OnItemClickListener info_click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            Players players=(Players) adapterView.getAdapter().getItem(position);
            //  LogUtils.e(clubTest.getClubname());
            showInfoDetail(players);
        }
    };
    private void showInfoDetail(Players data) {
        Intent intent = new Intent();
        intent.setClass(this, MeClubNumberListInfo.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("player",  data); //改成i
        bundle.putSerializable("clublist",clubList);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
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
