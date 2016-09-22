package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.ChallengeEndMatchInfoGreatNumberAdapter;
import com.footballcitymobileandroid.BLL.Adapter.GymkhanaAdapter.GymPlayerPirceAdapter;
import com.footballcitymobileandroid.BLL.Util.Pull.PullToRefreshLayout;
import com.footballcitymobileandroid.Controller.TestData.PlayerTest;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/7/2.
 */
public class ChallengeEndMatchInfoGreatNumber extends Activity implements View.OnClickListener,PullToRefreshLayout.OnRefreshListener {

    TextView detail_title_center;
    Button detail_back,detail_title;
    ListView list;
    private List<String> data=new ArrayList<>();
    ChallengeEndMatchInfoGreatNumberAdapter adapter;

    private PullToRefreshLayout layout;
    private static int currentState = PullToRefreshLayout.PULL_REFRESH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_end_match_info_great_number);
        init();
    }

    private void init()
    {
        detail_title=(Button) findViewById(R.id.detail_title);
        detail_title.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("最佳球员");

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        list=(ListView)findViewById(R.id.pull_list);
        data.add("队员1");
        data.add("队员2");
        data.add("队员3");
//        adapter=new ChallengeEndMatchInfoGreatNumberAdapter(this,data);
//
//        list.setAdapter(adapter);

        //     list.setAdapter(adapter);
//        list.setOnItemClickListener(info_click);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.detail_title:

                break;

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
