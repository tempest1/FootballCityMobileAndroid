package com.footballcitymobileandroid.Controller.Me;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.CustomView.ActivityCollector;
import com.footballcitymobileandroid.BLL.Util.CustomView.LoadingDialog;
import com.footballcitymobileandroid.Controller.Base.BaseActivity;
import com.footballcitymobileandroid.Controller.Base.MainActivity;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Controller.Club.ClubInvest;
import com.footballcitymobileandroid.Controller.Club.MeClubLeaderChange;
import com.footballcitymobileandroid.Controller.Club.MeClubRemoveNumber;
import com.footballcitymobileandroid.Controller.Club.PlayerFind;
import com.footballcitymobileandroid.Controller.Club.UpdateClub;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubMemb;
import com.footballcitymobileandroid.Entity.ClubEntity.join.JoinApplys;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhoudi on 16/5/20.
 */
public class MeLeaderSet extends BaseActivity implements View.OnClickListener,ActionCallBackListener<BaseEntity<Void>> {

    TextView setclub,detail_title,change_leader,number,add_number,del_club,club_invest;
    Button  detail_back;
    Intent intent;
    private LoadingDialog loadingDialog;

    ClubList clubList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_leadeer_set);
        init();
    }

    private void  init()
    {
        loadingDialog = new LoadingDialog(this,R.drawable.loading);
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        Log.i("clubnamebbbbb",clubList.getClubName());
        setclub=(TextView)findViewById(R.id.set_club);
        setclub.setOnClickListener(this);
        detail_title=(TextView)findViewById(R.id.detail_title);
        detail_title.setText("");

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        change_leader=(TextView)findViewById(R.id.change_leader);
        change_leader.setOnClickListener(this);

        number=(TextView)findViewById(R.id.number);
        number.setOnClickListener(this);

        add_number=(TextView)findViewById(R.id.add_number);
        add_number.setOnClickListener(this);

        del_club=(TextView)findViewById(R.id.del_club);
        del_club.setOnClickListener(this);

        club_invest=(TextView)findViewById(R.id.club_invest);
        club_invest.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.set_club:         //编辑俱乐部资料
                 intent = new Intent();
                intent.setClass(this, UpdateClub.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("clublist",clubList);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);

                break;
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
            case R.id.change_leader:               //更改领队
                intent = new Intent();
                intent.setClass(this, MeClubLeaderChange.class);
                loadingDialog.show();
                doit_checkClubMemb();

                break;
            case R.id.number:               //删除成员
                intent = new Intent();
                intent.setClass(this, MeClubRemoveNumber.class);
                loadingDialog.show();
                doit_checkClubMemb();
                break;
            case R.id.add_number:           //添加成员
                intent = new Intent();
                intent.setClass(this, PlayerFind.class);
                Bundle bundleb=new Bundle();
                bundleb.putSerializable("clublist",clubList);
                intent.putExtras(bundleb);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.del_club:        //解散俱乐部
                doit();
                break;

            case R.id.club_invest:     //成员邀请俱乐部

                intent = new Intent();
                intent.setClass(this, ClubInvest.class);
                do_checkJoinApply();

                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }
    private void doit(){
        loadingDialog.show();
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_fireClub(clubList.getClubID(), Params.fc_fireClub,this);
    }
    @Override
    public void onSuccess(BaseEntity<Void> data) {
//        ActivityCollector.finishAll();
        do_queryclublist();
    }

    @Override
    public void onFailure(String e_Type, String e_Msg) {
        Toast toast = Toast.makeText(this, e_Msg, Toast.LENGTH_LONG);
        toast.show();
        loadingDialog.dismiss();

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
            MainApplication.setClubList((List<ClubList>) data.getResponse().getClubList());                      //////////////
            Toast toast = Toast.makeText(getApplicationContext(), "俱乐部获取成功", Toast.LENGTH_LONG);
            toast.show();
            goMain();
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

    private void goMain()
    {
        Intent intent=new Intent();
        intent.setClass(this, MainActivity.class);
        intent.putExtra("type", "exit");
        startActivity(intent);
        ActivityCollector.finishAllNew();
        ActivityCollector.finishAll();
        this.finish();
    }


    private void doit_checkClubMemb(){
        loadingDialog.show();
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_checkClubMemb(clubList.getClubID(), Params.fc_checkClubMemb,clublistene);
    }

    public ActionCallBackListener<BaseEntity<ClubMemb>> clublistene=new ActionCallBackListener<BaseEntity<ClubMemb>>() {
        /**
         * 处理成功
         *
         * @param data 返回数据
         */
        @Override
        public void onSuccess(BaseEntity<ClubMemb> data) {
            loadingDialog.dismiss();
            Bundle bundle = new Bundle();
            bundle.putSerializable("clublist", clubList);
            bundle.putSerializable("clubmemb", (Serializable) data.getResponse().getClubMemb());
            intent.putExtras(bundle);
            startActivity(intent);
            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);

        }

        /**
         * 请求失败
         *
         * @param e_Type 错误码
         * @param e_Msg  错误详情
         */
        @Override
        public void onFailure(String e_Type, String e_Msg) {
            Toast toast = Toast.makeText(getApplicationContext(), e_Msg, Toast.LENGTH_LONG);
            toast.show();
            loadingDialog.dismiss();
        }
    };


    //俱乐部查询申请加盟记录（fc_checkJoinApply）（需要登录，系统权限：领队，业务权限：现阶段无）
    private void do_checkJoinApply() {
        AppAction appAction = Factory.createAppActionImpl(getApplicationContext());
        appAction.fc_checkJoinApply(clubList.getClubID(),"1",Params.fc_checkJoinApply, checkJoinApplys);
    }
    public ActionCallBackListener<BaseEntity<JoinApplys>> checkJoinApplys=new ActionCallBackListener<BaseEntity<JoinApplys>>(){

        /**
         * 处理成功
         *
         * @param data 返回数据
         */
        @Override
        public void onSuccess(BaseEntity<JoinApplys> data) {
            Toast toast = Toast.makeText(getApplicationContext(), "加盟信息获取成功", Toast.LENGTH_LONG);
            toast.show();
            loadingDialog.dismiss();
            Bundle bundles=new Bundle();
            bundles.putSerializable("clublist",clubList);
            bundles.putSerializable("JoinApplys", (Serializable) data.getResponse().getJoinApplys());
            intent.putExtras(bundles);
            startActivity(intent);
            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);

        }

        /**
         * 请求失败
         *
         * @param e_Type 错误码
         * @param e_Msg  错误详情
         */
        @Override
        public void onFailure(String e_Type, String e_Msg) {
            Toast toast = Toast.makeText(getApplicationContext(), e_Msg, Toast.LENGTH_LONG);
            toast.show();
            loadingDialog.dismiss();
        }
    };

}
