package com.footballcitymobileandroid.Controller.Club;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.NormalMatchSignNumberAdapter;
import com.footballcitymobileandroid.BLL.Util.Pull.PullToRefreshLayout;
import com.footballcitymobileandroid.Controller.Base.BaseChangeActivity;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.DeployDetail;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.MatchMemb;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.SportDetail;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/30.
 */
public class NormalMatchSignNumberList extends BaseChangeActivity implements View.OnClickListener,PullToRefreshLayout.OnRefreshListener{
    ListView listView;
    private Button detail_back;

    private Button detail_title;

    private Intent intent;

    TextView detail_title_center;

    List<MatchMemb> matchMemb;
    List<DeployDetail> detail=new ArrayList<>();

    SportDetail sportDetail;     //比赛内容
    ClubList clubList;
    NormalMatchSignNumberAdapter adapter;
    private PullToRefreshLayout layout;
    private static int currentState = PullToRefreshLayout.PULL_REFRESH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_match_sign_player_list);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
    private void init()
    {
        matchMemb= (List<MatchMemb>) getIntent().getSerializableExtra("MatchMemb");
        sportDetail= (SportDetail) getIntent().getSerializableExtra("SportDetail");
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");

        MainApplication.PK_NUMBER_SET.removeAll(MainApplication.PK_NUMBER_SET);

        listView=(ListView)findViewById(R.id.pull_list);
        listView=(ListView)findViewById(R.id.pull_list);
        if(matchMemb!=null) {
            LogUtils.e("matchMemb!=null");
            adapter = new NormalMatchSignNumberAdapter(this, matchMemb);
            listView.setAdapter(adapter);

        }else {
            LogUtils.e("matchMemb=null");
        }

        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title=(Button) findViewById(R.id.detail_title);
        detail_title.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("球员签到列表");

        layout = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        layout.setOnRefreshListener(this);
        MainApplication.runDelay(layout);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

            case R.id.detail_back:
                this.finish();
                MainApplication.PK_NUMBER=null;

                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
            case R.id.detail_title:

                chooseNumber(MainApplication.RULE);//需要获取规则

                break;
        }
    }
    private void chooseNumber(int rule)
    {

        for (int i=0;i<MainApplication.PK_NUMBER.length;i++)
        {
            Log.e("sys",""+MainApplication.PK_NUMBER[i]);
        }
        int number=0;
        for (int i=0;i<MainApplication.PK_NUMBER.length;i++)
        {
            if (MainApplication.PK_NUMBER[i]==1)
            {
                number++;
            }
        }
//        if (rule==1) {
//            LogUtils.e("number="+number);
//            if (number<8)
//            {
//                toastShow("当前人数"+number+",应选择8-11人");
//            }else if(number>11){
//                toastShow("当前人数"+number+",应选择8-11人");
//            }else {
//
//                toastShow("当前人数"+number);
//
//                for (int i=0;i<MainApplication.PK_NUMBER.length;i++)
//                {
//                    if (MainApplication.PK_NUMBER[i]==1)
//                    {
//                        LogUtils.e(""+matchMemb.get(i).getName());
//                        MainApplication.PK_NUMBER_SET.add(matchMemb.get(i).getName());
//                    }
//                }
//                goPlaySetting();
//            }
//
//        }else if (rule==2) {
//
//            LogUtils.e("number="+number);
//            if (number<9)
//            {
//                toastShow("当前人数"+number+",应选择9-12人");
//            }else if(number>12){
//                toastShow("当前人数"+number+",应选择9-12人");
//            }else {
//
//                toastShow("当前人数"+number);
//                for (int i=0;i<MainApplication.PK_NUMBER.length;i++)
//                {
//                    if (MainApplication.PK_NUMBER[i]==1)
//                    {
//                        LogUtils.e(""+matchMemb.get(i).getName());
//                        MainApplication.PK_NUMBER_SET.add(matchMemb.get(i).getName());
//                    }
//                }
//                goPlaySetting();
//            }
//
//        }else if (rule==3){
//            LogUtils.e("number="+number);
//            if (number<10)
//            {
//                toastShow("当前人数"+number+",应选择10-13人");
//            }else if(number>13){
//                toastShow("当前人数"+number+",应选择10-13人");
//            }else {
//
//                toastShow("当前人数"+number);
//                for (int i=0;i<MainApplication.PK_NUMBER.length;i++)
//                {
//                    if (MainApplication.PK_NUMBER[i]==1)
//                    {
//                        LogUtils.e(""+matchMemb.get(i).getName());
//                        MainApplication.PK_NUMBER_SET.add(matchMemb.get(i).getName());
//                    }
//                }
//                goPlaySetting();
//            }
//        }else if (rule==4){
//            LogUtils.e("number="+number);
//            if (number<11)
//            {
//                toastShow("当前人数"+number+",应选择11-14人");
//            }else if(number>14){
//                toastShow("当前人数"+number+",应选择11-14人");
//            }else {
//
//                toastShow("当前人数"+number);
//                for (int i=0;i<MainApplication.PK_NUMBER.length;i++)
//                {
//                    if (MainApplication.PK_NUMBER[i]==1)
//                    {
//                        LogUtils.e(""+matchMemb.get(i).getName());
//                        MainApplication.PK_NUMBER_SET.add(matchMemb.get(i).getName());
//                    }
//                }
//                goPlaySetting();
//            }
//        }


        toastShow("当前人数"+number);
        for (int i=0;i<MainApplication.PK_NUMBER.length;i++)
        {
            if (MainApplication.PK_NUMBER[i]==1)
            {
                LogUtils.e(""+matchMemb.get(i).getName());
                MainApplication.PK_NUMBER_SET.add(matchMemb.get(i).getName());
                DeployDetail.ISMAIN.add(matchMemb.get(i).getIsMain());
                DeployDetail.USERID.add(matchMemb.get(i).getClubMembID());
            }
        }
        goPlaySetting();

    }

    private void goPlaySetting()
    {
        intent = new Intent();
        intent.setClass(this, NormalMatchAfterPlaySettingNew.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("matchMemb", (Serializable) matchMemb);
        bundle.putSerializable("SportDetail", (Serializable) sportDetail);
        bundle.putSerializable("clubList", clubList);
        Log.i("clublistid",clubList.getClubID());
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MainApplication.PK_NUMBER=null;
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }
    private void toastShow(String str)
    {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
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
/*

 */