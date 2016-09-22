package com.footballcitymobileandroid.Controller.Gymkhana;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Adapter.GymkhanaAdapter.GymRankAdapter;
import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.AppActionImpl;
import com.footballcitymobileandroid.BLL.Util.CustomView.ComparatorRanks;
import com.footballcitymobileandroid.BLL.Util.Pull.PullToRefreshLayout;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Controller.TestData.ClubInfo;
import com.footballcitymobileandroid.Controller.TestData.ClubTest;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.ClubRankings;
import com.footballcitymobileandroid.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by zhoudi on 16/5/16.
 */
public class RankClub extends Activity implements View.OnClickListener,PullToRefreshLayout.OnRefreshListener,ActionCallBackListener<BaseEntity<ClubRankings>> {


    private ListView info_list;
    private GymRankAdapter adapter;
    private Button detail_back;
    private TextView detail_title_center;

    Intent intent=new Intent();
    List<ClubRankings> data;

    private PullToRefreshLayout layout;
    private static int currentState = PullToRefreshLayout.PULL_REFRESH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gym_rank_club);
        init();
//        doit();
    }
    private void init()
    {
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("俱乐部排行");

//        Object a=data.getResponse().getClubRankings();
//        ClubRankings b =(ClubRankings)a;

//         data= (List<ClubRankings>) getIntent().getSerializableExtra("data");
//
//        Log.i("data", String.valueOf(data));
        detail_back=(Button)findViewById(R.id.detail_back);
        info_list = (ListView)findViewById(R.id.pull_list);
        List<ClubTest>info_data=ClubInfo.ClubInfoData();
        LogUtils.e(info_data.get(0).getClubname());

        detail_back.setOnClickListener(this);

//        info_list.setOnItemClickListener(info_click);
//        info_list.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), false, true));

        layout = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        layout.setOnRefreshListener(this);
        MainApplication.runDelay(layout);
//        adapter = new GymRankAdapter(this, (BaseEntity<ClubRankings>) data);
//        info_list.setAdapter(adapter);
        layout.autoRefresh();
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
//        appAction.loadCard(CMD_CARD_LIST, pagination, pa
//  currentState = PullToRefreshLayout.PULL_LOAD;
// geNum, frunm.getTimestamp(), Params.CARD_LIST_URL, this);
//        layout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
        doit();


    }

    /**
     * 加载操作
     *
     * @param pullToRefreshLayout
     */
    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
//        currentState = PullToRefreshLayout.PULL_LOAD;
//        pagination ++;
//        currentState = PullToRefreshLayout.PULL_REFRESH;
        currentState = PullToRefreshLayout.PULL_LOAD;
//        appAction.loadCard(CMD_CARD_LIST, pagination, pageNum, frunm.getTimestamp(), Params.CARD_LIST_URL, this);

        //layout.loadmoreFinish(PullToRefreshLayout.FAIL);
        layout.loadmoreFinish(PullToRefreshLayout.SUCCEED);

    }
    private void doit(){
        AppAction appAction=new AppActionImpl(this);
        appAction.fc_checkArenaClubRankings("1", Params.fc_checkArenaClubRankings,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<ClubRankings> data) {
       this.data=data.getResponse().getClubRankings();
        Log.i("ClubRankings",""+this.data.get(0).getClubLogo());
        LogUtils.e("ClubRankings"+this.data.toString());

        ComparatorRanks comparator=new ComparatorRanks();
        Collections.sort(this.data, comparator);
        adapter = new GymRankAdapter(this,this.data);
        info_list.setAdapter(adapter);
        layout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
        Toast toast=Toast.makeText(this,"查询成功",Toast.LENGTH_LONG);
        toast.show();

    }

    /**
     * 请求失败
     *
     * @param e_Type 错误码
     * @param e_Msg  错误详情
     */
    @Override
    public void onFailure(String e_Type, String e_Msg) {
        Toast toast=Toast.makeText(this,e_Msg,Toast.LENGTH_LONG);
        toast.show();
        layout.loadmoreFinish(PullToRefreshLayout.FAIL);
    }
}
