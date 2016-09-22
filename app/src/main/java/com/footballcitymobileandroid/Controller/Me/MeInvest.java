package com.footballcitymobileandroid.Controller.Me;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Adapter.MeAdapter.MyInvestAdapter;
import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.AppActionImpl;
import com.footballcitymobileandroid.BLL.Util.Pull.PullToRefreshLayout;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Controller.TestData.Invest;
import com.footballcitymobileandroid.Controller.TestData.InvestInfo;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.join.Invitations;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/27.
 */
public class MeInvest extends Activity implements View.OnClickListener,PullToRefreshLayout.OnRefreshListener,ActionCallBackListener<BaseEntity<Invitations>> {


    int x=0;
    private Button detail_back;
    private TextView detail_title_center;
    private ListView list;
    private MyInvestAdapter adapter;

    List<Invitations> invitations;
    private PullToRefreshLayout layout;
    private static int currentState = PullToRefreshLayout.PULL_REFRESH;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_invest_list);
        init();
    }
    private void init()
    {
        invitations= (List<Invitations>) getIntent().getSerializableExtra("Invitations");
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("邀请函列表");

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);


        list=(ListView)findViewById(R.id.pull_list);

        if (invitations!=null) {
            adapter = new MyInvestAdapter(this, invitations);
            list.setAdapter(adapter);
//            LogUtils.e(invitations.toString());
        }else {

            LogUtils.e("null");
        }
        list.setOnItemClickListener(info_click);

        layout = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        layout.setOnRefreshListener(this);
        MainApplication.runDelay(layout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (x!=0) {
            layout.autoRefresh();
        }
        x++;
    }

    private AdapterView.OnItemClickListener info_click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            Log.e("info_click", adapterView.getAdapter().getItem(position).toString() + "");
            Invitations invest=(Invitations) adapterView.getAdapter().getItem(position);
            //  LogUtils.e(clubTest.getClubname());
            showInfoDetail(invest);
        }
    };
    private void showInfoDetail(Invitations data) {
        Intent intent = new Intent();
        intent.setClass(this, MeInvestment.class);
        Bundle bundle = new Bundle();
//        bundle.putSerializable("club", data);
        bundle.putSerializable("invitations", data);
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

    private void doit(){               //邀请函
        AppAction appAction=new AppActionImpl(this);
        appAction.fc_checkInvitation("1", Params.fc_checkInvitation,this);

    }
    @Override
    public void onSuccess(BaseEntity<Invitations> data) {
        Toast toast=Toast.makeText(this,"查询成功",Toast.LENGTH_LONG);
        toast.show();

        if (data.getResponse().getInvitations()!=null) {
            adapter = new MyInvestAdapter(this, data.getResponse().getInvitations());
            list.setAdapter(adapter);
//            LogUtils.e(invitations.toString());
        }else {

            LogUtils.e("null");
        }

        layout.loadmoreFinish(PullToRefreshLayout.SUCCEED);

    }
    @Override
    public void onFailure(String e_Type, String e_Msg) {
        Toast toast=Toast.makeText(this,e_Msg,Toast.LENGTH_LONG);
        toast.show();
        layout.loadmoreFinish(PullToRefreshLayout.FAIL);

    }
}
