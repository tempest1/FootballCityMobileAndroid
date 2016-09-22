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
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/7/28.
 */
public class ChallengeExitingMatch extends Activity implements View.OnClickListener,PullToRefreshLayout.OnRefreshListener {
    private Button detail_back;
    private ListView list;
    private AlreadyMatchAdapter adapter;
    TextView detail_title_center;
    List<AranaMatchs> aranaMatchses;
    ClubList clubList;
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
        aranaMatchses=(List<AranaMatchs>) getIntent().getSerializableExtra("AranaMatchs");
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        list=(ListView)findViewById(R.id.pull_list);

        if (clubList==null)
        {
            LogUtils.e("clubList=null");
        }
        if (aranaMatchses!=null)
        {
            adapter = new AlreadyMatchAdapter(this, aranaMatchses,clubList);
            list.setAdapter(adapter);
            LogUtils.e("");
        }else {
            LogUtils.e("null");

        }

        list.setOnItemClickListener(info_click);

        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("正在退赛中的比赛");

        layout = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        layout.setOnRefreshListener(this);
        MainApplication.runDelay(layout);
    }

    private AdapterView.OnItemClickListener info_click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            AranaMatchs matchNormal=(AranaMatchs) adapterView.getAdapter().getItem(position);
            //  LogUtils.e(clubTest.getClubname());
            showInfoDetail(matchNormal);
        }
    };
    private void showInfoDetail(AranaMatchs data) {


        Intent intent=new Intent();
        intent.setClass(this, ChallengeExitMatchInfo.class);
        Bundle bundle = new Bundle();
//        intent.setClass(this, NormalMathMeun.class);
//        Bundle bundle = new Bundle();
        bundle.putSerializable("AranaMatchs", data);    //需要适配
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
