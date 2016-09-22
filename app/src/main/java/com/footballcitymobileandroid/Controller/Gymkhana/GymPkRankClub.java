package com.footballcitymobileandroid.Controller.Gymkhana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.ClubAdapter;
import com.footballcitymobileandroid.BLL.Util.Pull.PullToRefreshLayout;
import com.footballcitymobileandroid.Controller.Base.InvestmentActivity;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Controller.TestData.ClubInfo;
import com.footballcitymobileandroid.Controller.TestData.ClubTest;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Clubs;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/16.
 */
public class GymPkRankClub extends InvestmentActivity implements View.OnClickListener,PullToRefreshLayout.OnRefreshListener {


    private ListView info_list;
    private ClubAdapter adapter;
    private Button detail_back;
    private TextView detail_title_center;

    List<Clubs> clubs;
    ClubList clubList;
    private PullToRefreshLayout layout;
    private static int currentState = PullToRefreshLayout.PULL_REFRESH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gym_rank_club);
        init();
    }
    private void init()
    {
        clubs= (List<Clubs>) getIntent().getSerializableExtra("Clubs");
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("匹配的俱乐部");

        detail_back=(Button)findViewById(R.id.detail_back);
        info_list = (ListView)findViewById(R.id.pull_list);

        if (clubs!=null) {
            for (int i=0;i<clubs.size();i++) {
                if (clubs.get(i).getClubID().equals(clubList.getClubID()))
                {
                    clubs.remove(i);
                }
            }
            adapter = new ClubAdapter(this, clubs);
            info_list.setAdapter(adapter);
        }

        detail_back.setOnClickListener(this);
        info_list.setOnItemClickListener(info_click);

        layout = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        layout.setOnRefreshListener(this);
        MainApplication.runDelay(layout);
    }

    private AdapterView.OnItemClickListener info_click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            Clubs clubs=(Clubs) adapterView.getAdapter().getItem(position);
            //  LogUtils.e(clubTest.getClubname());
            showInfoDetail(clubs);
        }
    };
    private void showInfoDetail(Clubs data) {
        Intent intent = new Intent();
        intent.setClass(this, GymPkClub.class);  //GymPkRelNumber 预选成员
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("club", data);
//        intent.putExtras(bundle);
        Bundle bundle=new Bundle();
        bundle.putSerializable("Clubs", data);
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
