package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.ClubRecordAdapter;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubRecord;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public class ClubRecords extends  Activity implements View.OnClickListener{

    Button detail_back;
    TextView detail_title_center;
    List<ClubRecord> clubRecord;
    ClubRecordAdapter adapter;

    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_record);
        init();
    }

    private void init()
    {

        TextView tip=(TextView)findViewById(R.id.tip);
        clubRecord= (List<ClubRecord>) getIntent().getSerializableExtra("clubRecord");
        detail_back = (Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center = (TextView) findViewById(R.id.detail_title_center);
        detail_title_center.setText("历史战绩");
        list = (ListView) findViewById(R.id.list);

        if(clubRecord==null){
            tip.setVisibility(View.VISIBLE);
        }else {
            tip.setVisibility(View.GONE);


            adapter = new ClubRecordAdapter(this, clubRecord);
            list.setAdapter(adapter);
        }


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
