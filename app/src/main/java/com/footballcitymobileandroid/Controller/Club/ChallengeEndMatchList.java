package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.AlreadyMatchAdapter;
import com.footballcitymobileandroid.BLL.Util.Pull.PullToRefreshLayout;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Controller.TestData.MatchInfo;
import com.footballcitymobileandroid.Controller.TestData.MatchNormal;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/6/1.
 */
public class ChallengeEndMatchList extends Activity implements View.OnClickListener,PullToRefreshLayout.OnRefreshListener {

    private Button detail_back;
    private ListView list;
    private AlreadyMatchAdapter adapter;
    private List<MatchNormal> data=new ArrayList<>();
    TextView detail_title_center;

    ClubList clubList;
    List<AranaMatchs> aranaMatchses;
    private PullToRefreshLayout layout;
    private static int currentState = PullToRefreshLayout.PULL_REFRESH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matchinfo_list);
        init();
    }
    private void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        aranaMatchses= (List<AranaMatchs>) getIntent().getSerializableExtra("AranaMatchs");
        list=(ListView)findViewById(R.id.pull_list);
        data= MatchInfo.InvestInfoDate();

        if (aranaMatchses!=null) {
            adapter = new AlreadyMatchAdapter(this, aranaMatchses,clubList);
            list.setAdapter(adapter);
            list.setOnItemClickListener(info_click);
        }



        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("已结束比赛列表");

        layout = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        layout.setOnRefreshListener(this);
        MainApplication.runDelay(layout);
    }

    private AdapterView.OnItemClickListener info_click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            AranaMatchs aranaMatchs=(AranaMatchs) adapterView.getAdapter().getItem(position);
            //  LogUtils.e(clubTest.getClubname());
            showInfoDetail(aranaMatchs);
        }
    };
    private void showInfoDetail(AranaMatchs data) {
        Intent intent=new Intent();
        intent.setClass(this, ChallengeEndMatchInfo.class);
        //  Bundle bundle = new Bundle();

//        Intent intent = new Intent();
//        intent.setClass(this, ChallengeMatchEndMeun.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("club", data);
        //  intent.putExtras(bundle);
        Bundle bundle=new Bundle();
        bundle.putSerializable("AranaMatchs", data);
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
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
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
