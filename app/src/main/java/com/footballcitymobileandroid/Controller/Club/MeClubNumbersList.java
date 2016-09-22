package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.MeClubNumbersListAdapter;
import com.footballcitymobileandroid.BLL.Adapter.GymkhanaAdapter.GymPlayerPirceAdapter;
import com.footballcitymobileandroid.BLL.Util.Pull.PullToRefreshLayout;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Controller.TestData.PlayerInfo;
import com.footballcitymobileandroid.Controller.TestData.PlayerTest;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubMemb;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/28.
 */
public class MeClubNumbersList extends Activity implements View.OnClickListener,PullToRefreshLayout.OnRefreshListener {

    private ListView list;

    ClubList clubList;
    List<ClubMemb> clubMemb;
    private MeClubNumbersListAdapter adapter;
    private List<PlayerTest> data=new ArrayList<>();

    TextView detail_title_center;

    Button detail_back;

    private PullToRefreshLayout layout;
    private static int currentState = PullToRefreshLayout.PULL_REFRESH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_number_list);
        init();
    }

    private void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");   //本俱乐部信息
        clubMemb= (List<ClubMemb>) getIntent().getSerializableExtra("clubmemb");  //成员信息


        data= PlayerInfo.ClubInfoData();
        list=(ListView)findViewById(R.id.pull_list);
        adapter=new MeClubNumbersListAdapter(this,clubMemb);

        list.setAdapter(adapter);

        list.setOnItemClickListener(info_click);
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("俱乐部成员列表");

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        layout = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        layout.setOnRefreshListener(this);
        MainApplication.runDelay(layout);
    }

    private AdapterView.OnItemClickListener info_click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            ClubMemb clubMemb=(ClubMemb) adapterView.getAdapter().getItem(position);
            //  LogUtils.e(clubTest.getClubname());
            showInfoDetail(clubMemb);
        }
    };
    private void showInfoDetail(ClubMemb data) {
        Intent intent = new Intent();
        intent.setClass(this, MeClubNumberInfo.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("clubnumber", data);
        bundle.putSerializable("clubList", clubList);

        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
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
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);

                this.finish();
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
