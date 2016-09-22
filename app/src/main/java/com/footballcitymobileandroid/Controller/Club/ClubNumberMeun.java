package com.footballcitymobileandroid.Controller.Club;

import android.content.Intent;
import android.os.Bundle;
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
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Club;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubMemb;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhoudi on 16/6/27.
 */
public class ClubNumberMeun extends BaseActivity implements View.OnClickListener ,ActionCallBackListener<BaseEntity<Void>>{

    TextView club_number,club_number_place,detail_title_center,club_number_exit,club_message;
    Button detail_back;
    private Intent intent;
    ClubList clubList;
    Club club;
    List<ClubMemb> clubMemb;
    private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.club_number_meun);
        init();
    }

    private void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        loadingDialog = new LoadingDialog(this,R.drawable.loading);
        club_number=(TextView)findViewById(R.id.club_number);
        club_number_place=(TextView)findViewById(R.id.club_number_place);
        club_number_exit=(TextView)findViewById(R.id.club_number_exit);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_back=(Button) findViewById(R.id.detail_back);
        detail_title_center.setText("我的成员");

        club_number.setOnClickListener(this);
        club_number_place.setOnClickListener(this);

        detail_back.setOnClickListener(this);
        club_number_exit.setOnClickListener(this);
        club_message=(TextView)findViewById(R.id.club_message);

        club_message.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.club_number:         //俱乐部成员
                intent = new Intent();
                intent.setClass(this, MeClubNumbersList.class);
                loadingDialog.show();
                doit_checkClubMemb();
                break;
            case R.id.club_number_place:       //球员位置信息

                 intent = new Intent();
                intent.setClass(this, ClubNumberPlace.class);
                loadingDialog.show();
                doit_checkClubMemb();

                break;

            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
            case R.id.club_number_exit:        //退出俱乐部
//                ActivityCollector.finishAll();
                loadingDialog.show();
                do_exitClub();
                break;
            case R.id.club_message:          //俱乐部信息
                intent = new Intent();
                intent.setClass(this, MyClubMessage.class);
                loadingDialog.show();
                doit_queryClubDetail();

                break;
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

    private void do_exitClub(){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_exitClub(clubList.getClubID(), Params.fc_exitClub,this);
    }

    private void doit_checkClubMemb(){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_checkClubMemb(clubList.getClubID(), Params.fc_checkClubMemb,clublisten);
    }

    public ActionCallBackListener<BaseEntity<ClubMemb>> clublisten=new ActionCallBackListener<BaseEntity<ClubMemb>>(){

        /**
         * 处理成功
         *
         * @param data 返回数据
         */
        @Override
        public void onSuccess(BaseEntity<ClubMemb> data) {
            loadingDialog.dismiss();
            clubMemb=data.getResponse().getClubMemb();
            Toast toast = Toast.makeText(getApplicationContext(), "俱乐部球员信息获取成功", Toast.LENGTH_LONG);
            toast.show();
            Bundle bundles=new Bundle();
            bundles.putSerializable("club",club);
            bundles.putSerializable("clublist",clubList);
            bundles.putSerializable("clubmemb", (Serializable) clubMemb);
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
            loadingDialog.dismiss();
            Toast toast = Toast.makeText(getApplicationContext(), "俱乐部球员信息"+e_Msg, Toast.LENGTH_LONG);
            toast.show();
        }
    };

    private void doit_queryClubDetail(){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_queryClubDetail(clubList.getClubID(), Params.fc_queryClubDetail,queryClubDetail);
    }

    public ActionCallBackListener<BaseEntity<Club>> queryClubDetail=new ActionCallBackListener<BaseEntity<Club>>(){

        /**
         * 处理成功
         *
         * @param data 返回数据
         */
        @Override
        public void onSuccess(BaseEntity<Club> data) {
            loadingDialog.dismiss();
            club= (Club) data.getResponse().getClub();
            doit_checkClubMemb();
        }

        /**
         * 请求失败
         *
         * @param e_Type 错误码
         * @param e_Msg  错误详情
         */
        @Override
        public void onFailure(String e_Type, String e_Msg) {
            loadingDialog.dismiss();
            Toast toast = Toast.makeText(getApplicationContext(), "俱乐部球员信息"+e_Msg, Toast.LENGTH_LONG);
            toast.show();
        }
    };
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Void> data) {
        Toast toast = Toast.makeText(getApplicationContext(), "退出成功", Toast.LENGTH_LONG);
        toast.show();
        do_queryclublist();

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
        intent=new Intent();
        intent.setClass(this, MainActivity.class);
        intent.putExtra("type", "exit");
        startActivity(intent);
        ActivityCollector.finishAllNew();
        ActivityCollector.finishAll();
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
        loadingDialog.dismiss();
        Toast toast = Toast.makeText(getApplicationContext(), "俱乐部球员信息"+e_Msg, Toast.LENGTH_LONG);
        toast.show();
    }
}
