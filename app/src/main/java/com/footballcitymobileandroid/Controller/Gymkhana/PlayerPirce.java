package com.footballcitymobileandroid.Controller.Gymkhana;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Adapter.GymkhanaAdapter.GymPlayerPirceAdapter;
import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.AppActionImpl;
import com.footballcitymobileandroid.BLL.Util.CustomView.ComparatorPlace;
import com.footballcitymobileandroid.BLL.Util.CustomView.ComparatorRanks;
import com.footballcitymobileandroid.BLL.Util.Pull.PullToRefreshLayout;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.PlayerRankings;
import com.footballcitymobileandroid.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by zhoudi on 16/5/20.
 */
public class PlayerPirce extends Activity implements View.OnClickListener,PullToRefreshLayout.OnRefreshListener,ActionCallBackListener<BaseEntity<PlayerRankings>> {

    private ListView info_list;
    private GymPlayerPirceAdapter adapter;
    private Button detail_back;
    private TextView detail_title_center;
    List<PlayerRankings> playerRankings;
    /*
    * 上下拉刷新的操作
     */
    private PullToRefreshLayout layout;
    private static int currentState = PullToRefreshLayout.PULL_REFRESH;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gym_player_pirce);
        init();
    }
    private void init()
    {

       // playerRankings= (List<PlayerRankings>) getIntent().getSerializableExtra("PlayerRankings");
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("球员身价");

        detail_back=(Button)findViewById(R.id.detail_back);
        info_list = (ListView)findViewById(R.id.pull_list);
//        if (playerRankings!=null) {
//            ComparatorPlace comparator=new ComparatorPlace();
//            Collections.sort(playerRankings, comparator);
//
//            adapter = new GymPlayerPirceAdapter(this,playerRankings);
//            info_list.setAdapter(adapter);
//
//        }
        detail_back.setOnClickListener(this);

//        info_list.setOnItemClickListener(info_click);
//        info_list.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), false, true));

        layout = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        layout.setOnRefreshListener(this);
        MainApplication.runDelay(layout);      //  延时执行
//        doit();
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
//        appAction.loadCard(CMD_CARD_LIST, pagination, pageNum, frunm.getTimestamp(), Params.CARD_LIST_URL, this);
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


    }
    private void doit(){
        AppAction appAction=new AppActionImpl(this);
        appAction.fc_checkArenaPlayerRankings("1", Params.fc_checkArenaPlayerRankings,this);
    }

    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<PlayerRankings> data) {
        Toast toast=Toast.makeText(this,"查询成功",Toast.LENGTH_LONG);
        toast.show();
        if (data.getResponse().getClubRankings()!=null) {

            ComparatorPlace comparator=new ComparatorPlace();
            Collections.sort(data.getResponse().getClubRankings(), comparator);
            adapter = new GymPlayerPirceAdapter(this, data.getResponse().getClubRankings());
            info_list.setAdapter(adapter);
        }
        layout.loadmoreFinish(PullToRefreshLayout.SUCCEED);

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
        layout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
    }
}
