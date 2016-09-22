package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.ClubAdapter;
import com.footballcitymobileandroid.Controller.TestData.ClubInfo;
import com.footballcitymobileandroid.Controller.TestData.ClubTest;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/30.
 */
public class NormalMathClubList  extends Activity implements View.OnClickListener {

    private ListView list;
    private List<ClubTest> data=new ArrayList<>();
    private Button detail_back;

    private TextView detail_title_center;

    private ClubAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_find_list);
        init();
    }

    private void init()
    {
        data= ClubInfo.ClubInfoData();
        list=(ListView)findViewById(R.id.pull_list);
        //adapter=new ClubAdapter(this,data);

       // list.setAdapter(adapter);

        list.setOnItemClickListener(info_click);

        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("我的俱乐部列表");

    }

    private AdapterView.OnItemClickListener info_click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            ClubTest clubTest=(ClubTest) adapterView.getAdapter().getItem(position);
            //  LogUtils.e(clubTest.getClubname());
            showInfoDetail(clubTest);
        }
    };
    private void showInfoDetail(ClubTest data) {
        Intent intent = new Intent();
        intent.setClass(this, NormalMathMeun.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("club", data);
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
