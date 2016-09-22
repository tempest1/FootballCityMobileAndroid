package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.ChallengeEndMatchInfoGreatNumberAdapter;
import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.Pull.PullToRefreshLayout;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena2.AranaMatchMembs;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.GradeList;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/7/29.
 */
public class ChallengeEndMatchSetName extends Activity implements  View.OnClickListener,PullToRefreshLayout.OnRefreshListener,ActionCallBackListener<BaseEntity<Void>>{
    TextView detail_title_center;
    Button detail_back,detail_title;
    ListView list;
    private List<String> data=new ArrayList<>();
    ChallengeEndMatchInfoGreatNumberAdapter adapter;

    private PullToRefreshLayout layout;
    private static int currentState = PullToRefreshLayout.PULL_REFRESH;


    ClubList clubList;
    AranaMatchs aranaMatchses;
    List<AranaMatchMembs> aranaMatchMembs;


    List<GradeList> gradeList=new ArrayList<>();          //需要加数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_end_match_name);
        init();
    }
    private void init()
    {
        aranaMatchMembs= (List<AranaMatchMembs>) getIntent().getSerializableExtra("AranaMatchMembs");
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        aranaMatchses= (AranaMatchs) getIntent().getSerializableExtra("AranaMatchs");





        detail_title=(Button) findViewById(R.id.detail_title);
        detail_title.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("提名名单");

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        list=(ListView)findViewById(R.id.pull_list);

        if (aranaMatchMembs!=null)
        {
            for (int i=0;i<aranaMatchMembs.size();i++)
            {
                if (aranaMatchMembs.get(i).getCoord_X().equals("0"))
                {
                    aranaMatchMembs.remove(i);
                }
            }
            adapter=new ChallengeEndMatchInfoGreatNumberAdapter(this,aranaMatchMembs);
            list.setAdapter(adapter);

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.detail_title:
                doit();
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


    private void doit(){

        LogUtils.e(ChallengeEndMatchInfoGreatNumberAdapter.mMapContent.size()+"");
        for (int i =0;i<ChallengeEndMatchInfoGreatNumberAdapter.mMapContent.size();i++) {
            GradeList g = new GradeList();
            g.setClubMembID(aranaMatchMembs.get(i).getClubMembID());
            g.setScore(ChallengeEndMatchInfoGreatNumberAdapter.mMapContent.get(i));
            g.setNomination(ChallengeEndMatchInfoGreatNumberAdapter.getIsSelected().get(i)+"");
            LogUtils.e(""+ChallengeEndMatchInfoGreatNumberAdapter.mMapContent.get(i));
            LogUtils.e(""+ChallengeEndMatchInfoGreatNumberAdapter.getIsSelected().get(i));

            gradeList.add(g);

        }
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_gradeMatchMemb(aranaMatchses.getAreanaMatchID(),clubList.getClubID(),gradeList, Params.fc_gradeMatchMemb,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Void> data) {
        Toast toast = Toast.makeText(this, "提交成功", Toast.LENGTH_LONG);
        toast.show();
        gradeList.removeAll(gradeList);
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
        gradeList.removeAll(gradeList);
    }
}
