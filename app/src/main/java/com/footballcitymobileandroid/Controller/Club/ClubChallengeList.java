package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.ClubChenllageAdapter;
import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.AppActionImpl;
import com.footballcitymobileandroid.BLL.Util.Pull.PullToRefreshLayout;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Controller.Gymkhana.GymPkDealNew;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.MatchingInfo;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhoudi on 16/5/31.
 */
public class ClubChallengeList extends Activity implements View.OnClickListener,PullToRefreshLayout.OnRefreshListener {

    private ListView list;
    private Button detail_back;

    private TextView detail_title_center;
    private PullToRefreshLayout layout;
    private static int currentState = PullToRefreshLayout.PULL_REFRESH;
    private ClubChenllageAdapter adapter;

    ClubList clubList;

    int station=0;
    List<MatchingInfo> matchingInfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_challenge_list);
        init();
    }
    private void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        matchingInfoList= (List<MatchingInfo>) getIntent().getSerializableExtra("matchingInfoList");

        list=(ListView)findViewById(R.id.pull_list);

        if (matchingInfoList!=null) {
            adapter = new ClubChenllageAdapter(this, matchingInfoList);
            list.setAdapter(adapter);
        }
        list.setOnItemClickListener(info_click);

        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("受邀列表");

        layout = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        layout.setOnRefreshListener(this);
        MainApplication.runDelay(layout);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (station!=0) {
            layout.autoRefresh();
        }
        station++;
    }

    private AdapterView.OnItemClickListener info_click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            MatchingInfo matchingInfo=(MatchingInfo) adapterView.getAdapter().getItem(position); //根据matchingInfoList重新适配
            //  LogUtils.e(clubTest.getClubname());
            showInfoDetail(matchingInfo);
        }
    };
    private void showInfoDetail(MatchingInfo data) {
        Intent intent = new Intent();
        intent.setClass(this, GymPkDealNew.class);
//        Bundle bundle = new Bundle();

        Bundle bundle=new Bundle() ;
        bundle.putString("choose", "invest");
        intent.putExtras(bundle);
        bundle.putSerializable("matchingInfoList", data); //根据选择i确定俱乐部邀请
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
        doit();

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
    private void doit(){

        AppAction appAction=new AppActionImpl(this);
        appAction.fc_checkMatchingMsg("receiver","1",clubList.getClubID(),
                Params.fc_checkMatchingMsg,Matching);
    }

    private void setAdapter( List<MatchingInfo> info)
    {
        adapter = new ClubChenllageAdapter(this, info);
        list.setAdapter(adapter);
    }

    //查看接受匹配信息
    public ActionCallBackListener<BaseEntity<MatchingInfo>> Matching = new ActionCallBackListener<BaseEntity<MatchingInfo>>() {
        @Override
        public void onSuccess(BaseEntity<MatchingInfo> data) {
            matchingInfoList=(List<MatchingInfo>) data.getResponse().getMatchingInfos();
            if (matchingInfoList!=null)
            {
                setAdapter(matchingInfoList);
            }
            layout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
        }

        @Override
        public void onFailure(String e_Type, String e_Msg) {
            Toast toast = Toast.makeText(getApplicationContext(), e_Msg, Toast.LENGTH_LONG);
            LogUtils.e("onFailure");
            toast.show();
            layout.loadmoreFinish(PullToRefreshLayout.FAIL);

        }
    };
}
