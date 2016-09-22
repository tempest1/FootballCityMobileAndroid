package com.footballcitymobileandroid.Controller.Me;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Adapter.MeAdapter.MyPlayRecordAdpter;
import com.footballcitymobileandroid.BLL.Util.Pull.PullToRefreshLayout;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Controller.Club.ChallengeEndMatchInfoMessage;
import com.footballcitymobileandroid.Controller.TestData.PlayRecord;
import com.footballcitymobileandroid.Controller.TestData.PlayRecordInfo;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubRecord;
import com.footballcitymobileandroid.Entity.MeEntity.PlayerRecord;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/23.
 */
public class MePlayerRecord extends Activity implements View.OnClickListener,PullToRefreshLayout.OnRefreshListener {

    MyPlayRecordAdpter adapter;

    Button detail_back;
    TextView detail_title_center;

    ListView list;

    List<ClubRecord> clubRecord;

    List<PlayerRecord> playerRecord;
    private PullToRefreshLayout layout;
    private static int currentState = PullToRefreshLayout.PULL_REFRESH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_player_record);
        init();

    }


    private AdapterView.OnItemClickListener info_click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

          //  showInfoDetail(position);
        }
    };
    private void showInfoDetail(int data) {
        Intent intent = new Intent();
        intent.setClass(this, ChallengeEndMatchInfoMessage.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("club", data);
//        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
    }
    private void init()
    {
        playerRecord= (List<PlayerRecord>) getIntent().getSerializableExtra("playerRecord");  //个人战绩数据
        clubRecord= (List<ClubRecord>) getIntent().getSerializableExtra("clubRecord");
        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("个人战绩");

        list=(ListView)findViewById(R.id.pull_list);
        if (playerRecord!=null) {
            adapter = new MyPlayRecordAdpter(this, playerRecord);
            list.setAdapter(adapter);
        }



        list.setOnItemClickListener(info_click);

        layout = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        layout.setOnRefreshListener(this);
        MainApplication.runDelay(layout);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
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

    /**
     * 刷新操作
     *
     * @param pullToRefreshLayout
     */
    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        currentState = PullToRefreshLayout.PULL_REFRESH;
//        appAction.loadCard(CMD_CARD_LIST, pagination, pageNum, frunm.getTimestamp(), Params.CARD_LIST_URL, this);
        layout.loadmoreFinish(PullToRefreshLayout.SUCCEED);

    }

    /**
     * 加载操作
     *
     * @param pullToRefreshLayout
     */
    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        currentState = PullToRefreshLayout.PULL_LOAD;
//        pagination ++;
//        appAction.loadCard(CMD_CARD_LIST, pagination, pageNum, frunm.getTimestamp(), Params.CARD_LIST_URL, this);

        //layout.loadmoreFinish(PullToRefreshLayout.FAIL);
        layout.loadmoreFinish(PullToRefreshLayout.SUCCEED);

    }
}
