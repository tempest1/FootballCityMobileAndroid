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
import com.footballcitymobileandroid.BLL.Adapter.GymkhanaAdapter.MeClubListAdapter;
import com.footballcitymobileandroid.BLL.Util.Pull.PullToRefreshLayout;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Controller.TestData.ClubInfo;
import com.footballcitymobileandroid.Controller.TestData.ClubTest;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/6/1.
 */
public class MeClubList   extends Activity implements View.OnClickListener, PullToRefreshLayout.OnRefreshListener {
    private ListView list;
    private Button detail_back;

    private TextView detail_title_center;

    private MeClubListAdapter adapter;

    List<ClubList> clubList;
    private PullToRefreshLayout layout;
    private static int currentState = PullToRefreshLayout.PULL_REFRESH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_find_list);
        init();
    }

    private void init()
    {
        clubList= MainApplication.getClubList();
        list=(ListView)findViewById(R.id.pull_list);


        for (int i=0;i<clubList.size();i++)
        {

        }
        adapter=new MeClubListAdapter(this,clubList);

        list.setAdapter(adapter);

        list.setOnItemClickListener(info_click);

        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("选择我创建的俱乐部");

        layout = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        layout.setOnRefreshListener(this);
        MainApplication.runDelay(layout);
    }

    private AdapterView.OnItemClickListener info_click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            ClubList clubList=(ClubList) adapterView.getAdapter().getItem(position);
            //  LogUtils.e(clubTest.getClubname());
            showInfoDetail(clubList);
        }
    };
    private void showInfoDetail(ClubList data) {
        Intent intent = new Intent();
        intent.setClass(this, ChallengeMatchMeun.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("clublist", data);//改成i
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
