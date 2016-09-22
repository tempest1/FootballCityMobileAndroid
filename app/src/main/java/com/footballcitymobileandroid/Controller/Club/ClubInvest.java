package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.ClubInvestAdapter;
import com.footballcitymobileandroid.BLL.Util.Pull.PullToRefreshLayout;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.join.JoinApplys;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/7/22.
 */
public class ClubInvest extends Activity  implements View.OnClickListener,PullToRefreshLayout.OnRefreshListener{
    private Button detail_back;
    private TextView detail_title_center;
    private ListView list;
    private ClubInvestAdapter adapter;


    List<JoinApplys> joinApplys;
    ClubList clubList;

    private PullToRefreshLayout layout;
    private static int currentState = PullToRefreshLayout.PULL_REFRESH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_invest_list);
        init();
    }
    private void init()
    {
        joinApplys= (List<JoinApplys>) getIntent().getSerializableExtra("JoinApplys");
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("俱乐部邀请");

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);


        list=(ListView)findViewById(R.id.pull_list);

        if (joinApplys!=null) {
            adapter = new ClubInvestAdapter(this, joinApplys);
            list.setAdapter(adapter);
        }
        list.setOnItemClickListener(info_click);

    }
    private AdapterView.OnItemClickListener info_click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            Log.e("info_click", adapterView.getAdapter().getItem(position).toString() + "");
            JoinApplys invest=(JoinApplys) adapterView.getAdapter().getItem(position);
            //  LogUtils.e(clubTest.getClubname());
            showInfoDetail(invest);
        }
    };
    private void showInfoDetail(JoinApplys data) {
        Intent intent = new Intent();
        intent.setClass(this, ClubInvestment.class);
        //   Bundle bundle = new Bundle();
//        bundle.putSerializable("club", data);
        //   bundle.putSerializable("invitations", data);
        //     intent.putExtras(bundle);
        Bundle bundles=new Bundle();
        bundles.putSerializable("clublist",clubList);
        bundles.putSerializable("JoinApplys",data);
        intent.putExtras(bundles);
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
