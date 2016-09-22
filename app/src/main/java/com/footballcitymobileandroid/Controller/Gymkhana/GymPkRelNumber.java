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

import com.footballcitymobileandroid.BLL.Adapter.GymkhanaAdapter.GymPkRelNumberAdapter;
import com.footballcitymobileandroid.BLL.Util.Pull.PullToRefreshLayout;
import com.footballcitymobileandroid.Controller.Base.InvestmentActivity;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Controller.TestData.MeClubNumbers;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/6/29.
 */
public class GymPkRelNumber extends InvestmentActivity implements View.OnClickListener,PullToRefreshLayout.OnRefreshListener{
    private TextView detail_title_center,detail_title;
    private Button detail_back;
    ListView listView;
    List<MeClubNumbers> infos= new ArrayList<>();




    GymPkRelNumberAdapter adapter;

    private PullToRefreshLayout layout;
    private static int currentState = PullToRefreshLayout.PULL_REFRESH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gym_pk_rel_number);
        init();
    }
    private void init()
    {
        MainApplication.PK_REL_NUMBER_SET.removeAll(MainApplication.PK_REL_NUMBER_SET);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_back=(Button)findViewById(R.id.detail_back);
        detail_title_center.setText("预选成员");
        detail_back.setOnClickListener(this);
        listView=(ListView)findViewById(R.id.pull_list);
        infos= com.footballcitymobileandroid.Controller.TestData.MeClubNumberInfo.NumberInfoData();
        listView=(ListView)findViewById(R.id.pull_list);
        adapter=new GymPkRelNumberAdapter(this,infos);
        listView.setAdapter(adapter);
        detail_title=(Button)findViewById(R.id.detail_title);
        detail_title.setOnClickListener(this);

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
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                MainApplication.PK_REL_NUMBER=null;
                break;
            case R.id.detail_title:

                chooseNumber(MainApplication.RULE);
                // goGymPkClub();
                break;
        }
    }


    private void goGymPkClub()
    {
        Intent intent = new Intent();
        intent.setClass(this, GymPkClub.class);
        Bundle bundle = new Bundle();
        bundle.putString("club", "challenge");
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
    }

    private void chooseNumber(int rule)
    {

        for (int i=0;i<MainApplication.PK_REL_NUMBER.length;i++)
        {
            Log.e("sys",""+MainApplication.PK_REL_NUMBER[i]);
        }
        int number=0;
        for (int i=0;i<MainApplication.PK_REL_NUMBER.length;i++)
        {
            if (MainApplication.PK_REL_NUMBER[i]==1)
            {
                number++;
            }
        }
        if (rule==1) {
            LogUtils.e("number="+number);
            if (number<8)
            {
                toastShow("当前人数"+number+",应选择8-11人");
            }else if(number>11){
                toastShow("当前人数"+number+",应选择8-11人");
            }else {

                toastShow("当前人数"+number);

                for (int i=0;i<MainApplication.PK_REL_NUMBER.length;i++)
                {
                    if (MainApplication.PK_REL_NUMBER[i]==1)
                    {
                        LogUtils.e(""+infos.get(i).getName());
                        MainApplication.PK_REL_NUMBER_SET.add(infos.get(i).getName());
                    }
                }
                goGymPkClub();
            }

        }else if (rule==2) {

            LogUtils.e("number="+number);
            if (number<9)
            {
                toastShow("当前人数"+number+",应选择9-12人");
            }else if(number>12){
                toastShow("当前人数"+number+",应选择9-12人");
            }else {

                toastShow("当前人数"+number);
                for (int i=0;i<MainApplication.PK_REL_NUMBER.length;i++)
                {
                    if (MainApplication.PK_REL_NUMBER[i]==1)
                    {
                        LogUtils.e(""+infos.get(i).getName());
                        MainApplication.PK_REL_NUMBER_SET.add(infos.get(i).getName());
                    }
                }
                goGymPkClub();
            }

        }else if (rule==3) {
            LogUtils.e("number=" + number);
            if (number < 10) {
                toastShow("当前人数" + number + ",应选择10-13人");
            } else if (number > 13) {
                toastShow("当前人数" + number + ",应选择10-13人");
            } else if (rule == 3) {

                toastShow("当前人数" + number);
                for (int i = 0; i < MainApplication.PK_REL_NUMBER.length; i++) {
                    if (MainApplication.PK_REL_NUMBER[i] == 1) {
                        LogUtils.e("" + infos.get(i).getName());
                        MainApplication.PK_REL_NUMBER_SET.add(infos.get(i).getName());
                    }
                }
                goGymPkClub();
            }
        }
        else if (rule==4){
            LogUtils.e("number="+number);
            if (number<11)
            {
                toastShow("当前人数"+number+",应选择11-14人");
            }else if(number>14){
                toastShow("当前人数"+number+",应选择11-14人");
            }else {

                toastShow("当前人数"+number);
                for (int i=0;i<MainApplication.PK_REL_NUMBER.length;i++)
                {
                    if (MainApplication.PK_REL_NUMBER[i]==1)
                    {
                        LogUtils.e(""+infos.get(i).getName());
                        MainApplication.PK_REL_NUMBER_SET.add(infos.get(i).getName());
                    }
                }
                goGymPkClub();
            }
        }
    }





    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
        MainApplication.PK_REL_NUMBER=null;
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
//        appAction.loadCard(CMD_CARD_LIST, pagination, pageNum, frunm.getTimestamp(), Params.CARD_LIST_URL, this);

        //layout.loadmoreFinish(PullToRefreshLayout.FAIL);
        layout.loadmoreFinish(PullToRefreshLayout.SUCCEED);

    }
}
