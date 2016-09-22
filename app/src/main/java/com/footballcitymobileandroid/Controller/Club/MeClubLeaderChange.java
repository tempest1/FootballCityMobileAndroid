package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.MeClubNumberAdapter;
import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.CustomView.LoadingDialog;
import com.footballcitymobileandroid.BLL.Util.Pull.PullToRefreshLayout;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Controller.TestData.MeClubNumberInfo;
import com.footballcitymobileandroid.Controller.TestData.MeClubNumbers;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubMemb;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/5/25.
 */
public class MeClubLeaderChange extends Activity implements View.OnClickListener,ActionCallBackListener<BaseEntity<Void>>,PullToRefreshLayout.OnRefreshListener {

    Button detail_title,detail_back;
    TextView detail_title_center;
     ListView listView;

    ClubList clubList;
    List<ClubMemb> clubMemb;
    MeClubNumberAdapter adapter;
    private LoadingDialog loadingDialog;


    private PullToRefreshLayout layout;
    private static int currentState = PullToRefreshLayout.PULL_REFRESH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_club_leader_change);
        init();
    }

    private void init()
    {
        loadingDialog = new LoadingDialog(this,R.drawable.loading);

        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        clubMemb= (List<ClubMemb>) getIntent().getSerializableExtra("clubmemb");
        if(clubMemb.size()!=0){
            LogUtils.e(clubMemb.get(0).getName());
        }else {
            LogUtils.e("null");
        }
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("变更领队");

        detail_title=(Button)findViewById(R.id.detail_title);
        detail_title.setOnClickListener(this);

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        listView=(ListView)findViewById(R.id.pull_list);


        LogUtils.e(clubList.getLeaderName());
        if (clubMemb!=null) {

            for (int i=0;i<clubMemb.size();i++)
            {
                LogUtils.e(clubMemb.get(i).getName());
                if (clubMemb.get(i).getName().equals(clubList.getLeaderName()))
                {
                    clubMemb.remove(i);
                }
            }
            adapter = new MeClubNumberAdapter(this, clubMemb);
            listView.setAdapter(adapter);

        }

        detail_back.setOnClickListener(this);
        layout = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        layout.setOnRefreshListener(this);
        MainApplication.runDelay(layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.detail_title:
                for (int i=0;i<MeClubNumberAdapter.states.size();i++)
                {
                    if (MeClubNumberAdapter.states.get(String.valueOf(i))==true)
                    {
                        LogUtils.e(String.valueOf(i));
                        do_changeLeader(i);
                    }

                }
                break;
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

    private void do_changeLeader(int i) {
        AppAction appAction = Factory.createAppActionImpl(this);
        appAction.fc_changeLeader(clubList.getClubID(),clubMemb.get(i).getClubMembID(), Params.fc_changeLeader, this);
    }

    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Void> data) {
        Toast toast = Toast.makeText(this, "领队更改成功", Toast.LENGTH_LONG);
        toast.show();
        do_queryclublist();
        this.finish();
    }

    /**
     * 请求失败
     *
     * @param e_Type 错误码
     * @param e_Msg  错误详情
     */
    @Override
    public void onFailure(String e_Type, String e_Msg) {
        Toast toast = Toast.makeText(this, e_Msg, Toast.LENGTH_LONG);
        toast.show();
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

    private void do_queryclublist() {
        AppAction appAction = Factory.createAppActionImpl(getApplicationContext());
        appAction.fc_getClubList(Params.fc_getClubList, clublistener);//"leader",
    }

    public ActionCallBackListener<BaseEntity<ClubList>> clublistener=new ActionCallBackListener<BaseEntity<ClubList>>(){

        /**
         * 处理成功
         *
         * @param data 返回数据
         */
        @Override
        public void onSuccess(BaseEntity<ClubList> data) {
            MainApplication.setClubList((List<com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList>) data.getResponse().getClubList());                      //////////////
            Toast toast = Toast.makeText(getApplicationContext(), "俱乐部获取成功", Toast.LENGTH_LONG);
            toast.show();
//            login();
            loadingDialog.dismiss();

        }

        /**
         * 请求失败
         *
         * @param e_Type 错误码
         * @param e_Msg  错误详情
         */
        @Override
        public void onFailure(String e_Type, String e_Msg) {
            Toast toast = Toast.makeText(getApplicationContext(), "俱乐部"+e_Msg, Toast.LENGTH_LONG);
            toast.show();
            loadingDialog.dismiss();
        }
    };
}
