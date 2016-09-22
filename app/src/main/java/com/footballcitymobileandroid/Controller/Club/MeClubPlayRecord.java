package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Adapter.GymkhanaAdapter.GymRankAdapter;
import com.footballcitymobileandroid.BLL.Adapter.MeAdapter.MyClubPlayRecordAdpter;
import com.footballcitymobileandroid.Controller.Gymkhana.GymPkDealAccept;
import com.footballcitymobileandroid.Controller.Gymkhana.GymPkDealDeliver;
import com.footballcitymobileandroid.Controller.TestData.PlayRecord;
import com.footballcitymobileandroid.Controller.TestData.PlayRecordInfo;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/23.
 */
public class MeClubPlayRecord extends Activity implements View.OnClickListener{

    List<PlayRecord> playRecords=new ArrayList<>();

    MyClubPlayRecordAdpter adapter;
    private RadioButton radio_play;
    private RadioButton radio_normal;

    TextView detail_title_center;

    private Button detail_back;
    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkId) {

            switch (checkId) {
                case R.id.radio_play:
                    setTabSelection(0);
                    break;
                case R.id.radio_normal:
                    setTabSelection(1);
                    break;

            }
        }
    };

    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_club_play_record);
        init();

    }


    private void  setTabSelection(int Index)
    {
        switch (Index) {
            case 0:

                LogUtils.e("0");
                radio_play.setChecked(true);

                break;
            case 1:
                LogUtils.e("1");

                radio_normal.setChecked(true);
                break;
        }
    }

    private void init()
    {

        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("赛事纪录");

        radio_play = (RadioButton) findViewById(R.id.radio_play);
        radio_normal = (RadioButton) findViewById(R.id.radio_normal);
        playRecords= PlayRecordInfo.PlayRecordInfoData();
        LogUtils.e(playRecords.get(0).getClub_me());
        LogUtils.e(playRecords.get(1).getClub_me());
        LogUtils.e(playRecords.get(2).getClub_me());
        list=(ListView)findViewById(R.id.list);

        adapter = new MyClubPlayRecordAdpter(this, playRecords);
        list.setAdapter(adapter);
        RadioGroup radio_tab = (RadioGroup) findViewById(R.id.radio_tab);

        radio_tab.setOnCheckedChangeListener(listener);

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
