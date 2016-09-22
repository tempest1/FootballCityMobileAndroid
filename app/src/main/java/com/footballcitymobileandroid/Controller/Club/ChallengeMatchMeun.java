package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.AppActionImpl;
import com.footballcitymobileandroid.BLL.Util.CustomView.LoadingDialog;
import com.footballcitymobileandroid.Controller.Gymkhana.GymPkInfo;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.MatchingInfo;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhoudi on 16/6/1.
 */
public class ChallengeMatchMeun  extends Activity implements View.OnClickListener,ActionCallBackListener<BaseEntity<AranaMatchs>> {

    TextView pipei_club,detail_title,start_club,userless_club,starting_club,over_club,detail_title_center,investment,exiting,user_exit;
    Button detail_back;
    Intent intent;
    private LoadingDialog loadingDialog;

    ClubList clubList;
    List<AranaMatchs> aranaMatchses;
    List<MatchingInfo> matchingInfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_match_meun);
        init();
    }


    private void  init()
    {
        loadingDialog = new LoadingDialog(this,R.drawable.loading);
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        detail_title=(TextView)findViewById(R.id.detail_title);
        detail_title.setText("");

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        pipei_club=(TextView)findViewById(R.id.pipei_club);
        pipei_club.setOnClickListener(this);

        start_club=(TextView)findViewById(R.id.start_club);
        start_club.setOnClickListener(this);

        userless_club=(TextView)findViewById(R.id.userless_club);
        userless_club.setOnClickListener(this);

        starting_club=(TextView)findViewById(R.id.starting_club);
        starting_club.setOnClickListener(this);

        over_club=(TextView)findViewById(R.id.over_club);
        over_club.setOnClickListener(this);
        investment=(TextView)findViewById(R.id.investment);
        investment.setOnClickListener(this);

        exiting=(TextView)findViewById(R.id.exiting);
        exiting.setOnClickListener(this);

        user_exit=(TextView)findViewById(R.id.user_exit);
        user_exit.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("选择竞技场比赛类型");

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.pipei_club:         //待匹配俱乐部

                intent = new Intent();
//                matchingInfoList=(List<MatchingInfo>) data.getResponse().getMatchingInfos();
                intent.setClass(ChallengeMatchMeun.this, GymPkInfo.class);
                Bundle bundle=new Bundle() ;
                bundle.putSerializable("matchingInfo", (Serializable) matchingInfoList);
                bundle.putSerializable("clublist",clubList);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);

                break;
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
            case R.id.start_club:         //待开始比赛

                intent = new Intent();
                intent.setClass(this, ChallengeAlreadyMatchList.class);
                doitAranaMatchs("1");
                break;
            case R.id.userless_club:        //已作废
                doitAranaMatchs("5");
                intent = new Intent();
                intent.setClass(this, ChallengeUselnessMatchList.class);
                break;
            case R.id.starting_club:  //正在进行

                doitAranaMatchs("3");
                intent = new Intent();
                intent.setClass(this, ChallengeMatchStratedMeun.class);
                break;
            case R.id.over_club:        //已结束
                doitAranaMatchs("6");
                intent = new Intent();
                intent.setClass(this, ChallengeEndMatchList.class);
                break;
            case R.id.investment:         //匹配请求
                intent = new Intent();
                intent.setClass(this, ClubChallengeList.class);
                doit();
                break;
            case R.id.user_exit:  //强制退赛的比赛
                doitAranaMatchs("4");
                intent = new Intent();
                intent.setClass(this, ChallengeExitMatch.class);
                break;

            case R.id.exiting:  //正在退赛中的比赛
                doitAranaMatchs("2");
                intent = new Intent();
                intent.setClass(this, ChallengeExitingMatch.class);
                break;
        }
    }


    private void doit(){
        loadingDialog.show();

        AppAction appAction=new AppActionImpl(this);
        appAction.fc_checkMatchingMsg("receiver","1",clubList.getClubID(),
                Params.fc_checkMatchingMsg,Matching);
    }


    //查看接受匹配信息
    public ActionCallBackListener<BaseEntity<MatchingInfo>> Matching = new ActionCallBackListener<BaseEntity<MatchingInfo>>() {
        @Override
        public void onSuccess(BaseEntity<MatchingInfo> data) {
            Toast toast = Toast.makeText(getApplicationContext(), "获取匹配信息成功", Toast.LENGTH_LONG);
            toast.show();
            LogUtils.e("getResult"+data.getResponse().getResult());
            LogUtils.e("getCurrentPage"+data.getResponse().getCurrentPage());
            LogUtils.e("getTotalPages"+data.getResponse().getTotalPages());


            matchingInfoList=(List<MatchingInfo>) data.getResponse().getMatchingInfos();

            Bundle bundle=new Bundle() ;
            bundle.putSerializable("matchingInfoList", (Serializable) matchingInfoList);
            bundle.putSerializable("clublist",clubList);
            intent.putExtras(bundle);
            startActivity(intent);
            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
            loadingDialog.dismiss();

        }

        @Override
        public void onFailure(String e_Type, String e_Msg) {
            Toast toast = Toast.makeText(getApplicationContext(), e_Msg, Toast.LENGTH_LONG);
            LogUtils.e("onFailure");

            toast.show();
            loadingDialog.dismiss();

        }
    };




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }
    //"竞技匹配状态1-待开始；2-退赛中；3-已开始；4-强制退赛结束；5-正常退赛结束；6-正常比赛结束",,
    private void doitAranaMatchs(String state){
        loadingDialog.show();
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        AppAction appAction=new AppActionImpl(this);
        appAction.fc_checkArenaMatch(state,"1",clubList.getClubID(), Params.fc_checkArenaMatch,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<AranaMatchs> data) {
        Toast toast = Toast.makeText(getApplicationContext(), "获取竞技信息成功", Toast.LENGTH_LONG);
        toast.show();
        aranaMatchses=(List<AranaMatchs>)data.getResponse().getAranaMatchs();
        if (aranaMatchses!=null) {

            if (aranaMatchses.toString().equals("[]"))
            {
                Toast.makeText(getApplicationContext(), "暂无比赛内容", Toast.LENGTH_LONG).show();
            }else {
                LogUtils.e(aranaMatchses.toString());
                Bundle bundle = new Bundle();
                bundle.putSerializable("AranaMatchs", (Serializable) aranaMatchses);
                bundle.putSerializable("clublist", clubList);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
            }
        }  else {
            LogUtils.e("aranaMatchses==null");

            Toast.makeText(getApplicationContext(), "暂无比赛内容", Toast.LENGTH_LONG).show();

        }
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
        if (e_Msg.equals("获取参数有误"))
        {

        }else {
            Toast toast = Toast.makeText(getApplicationContext(), e_Msg, Toast.LENGTH_LONG);
            toast.show();
        }
        loadingDialog.dismiss();

    }
}
