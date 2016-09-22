package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.CustomView.LoadingDialog;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubMemb;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.MatchMemb;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.SportDetail;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhoudi on 16/5/30.
 */
public class NormalMatchInfoDeal extends Activity implements View.OnClickListener{
    private   Intent intent;
    private RelativeLayout look_sign_people,sign,write_player,delete,looking;
    private Button detail_back;
    TextView detail_title_center;
    private View line1,line2,line3;
    String type;
    SportDetail sportDetail;     //比赛内容
    ClubList clubList;
    private LoadingDialog loadingDialog;
    List<MatchMemb> matchMemb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_match_match_info_deal);
        init();
        initData();
    }

    private void init()
    {
        loadingDialog = new LoadingDialog(this,R.drawable.loading);
        sportDetail= (SportDetail) getIntent().getSerializableExtra("SportDetail");
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        look_sign_people=(RelativeLayout)findViewById(R.id.look_sign_people);
        look_sign_people.setOnClickListener(this);

        sign=(RelativeLayout)findViewById(R.id.sign);
        sign.setOnClickListener(this);

        write_player=(RelativeLayout)findViewById(R.id.write_player);
        write_player.setOnClickListener(this);

        delete=(RelativeLayout)findViewById(R.id.delete);
        delete.setOnClickListener(this);



        looking=(RelativeLayout)findViewById(R.id.looking);
        looking.setOnClickListener(this);

        line1=(View)findViewById(R.id.line1);
        line2=(View)findViewById(R.id.line2);
        line3=(View)findViewById(R.id.line3);

        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("比赛功能");

    }
    private void initData()
    {
        intent=getIntent();
        type=intent.getStringExtra("type");
        LogUtils.e(""+type);
        if (type.equals("1"))
        {
            LogUtils.e(type);
            write_player.setVisibility(View.GONE);
            looking.setVisibility(View.GONE);
            line1.setVisibility(View.GONE);
        }else if (type.equals("2")){
            LogUtils.e(type);
            sign.setVisibility(View.GONE);
            look_sign_people.setVisibility(View.GONE);
            line3.setVisibility(View.GONE);

        }else if (type.equals("3")){
            LogUtils.e(type);
            sign.setVisibility(View.GONE);
            look_sign_people.setVisibility(View.GONE);
            write_player.setVisibility(View.GONE);
            line2.setVisibility(View.GONE);

        }else {
            LogUtils.e("wu");
        }
    }
    Bundle bundle;
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.look_sign_people:          //查看签到人员
                loadingDialog.show();
                intent = new Intent();
                intent.setClass(NormalMatchInfoDeal.this, NormalMatchSignNumberList.class);
                do_checkSportMemb();
//                intent = new Intent();
//                intent.setClass(this, NormalMatchSignNumberList.class);
//                Bundle bundle=new Bundle();
//
//
//                bundle.putSerializable("MatchMemb", (Serializable) matchMemb);
//                intent.putExtras(bundle);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("club", data);
//                intent.putExtras(bundle);




                break;
            case R.id.sign:                       //签到
                loadingDialog.show();
//                do_checkSportMemb();
//                do_checkClubMemb();
                do_sportSign();

                do_checkSportMemb();
                intent = new Intent();
                intent.setClass(this, NormalMatchSigns.class);
//                bundle = new Bundle();
//                bundle.putSerializable("clublist",clubList);
//                bundle.putSerializable("SportDetail", (Serializable) sportDetail);
//                bundle.putSerializable("MatchMemb", (Serializable) matchMemb);
////                bundle.putSerializable("clubMemb", (Serializable) clubMemb);
//                intent.putExtras(bundle);
//
////                intent.putExtras(bundle);
//                startActivity(intent);
//                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.write_player:           //录入比赛结果
                intent = new Intent();
                intent.setClass(this, NormalMatchWriteEnd.class);
                bundle = new Bundle();
                bundle.putSerializable("clublist",clubList);
                bundle.putSerializable("SportDetail", (Serializable) sportDetail);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.delete:        //删除比赛信息

                do_delClubSport();
                break;


            case R.id.looking:                //查看比赛内容
                intent = new Intent();

                if (type.equals("2")) {
                    intent.setClass(this, NormalStartedMatchInfo.class);
//                    bundle = new Bundle();
//                    bundle.putSerializable("clublist",clubList);
//                    bundle.putSerializable("SportDetail", (Serializable) sportDetail);
//                    bundle.putSerializable("MatchMemb", (Serializable) matchMemb);
                    do_checkSportMemb();
//                    intent.putExtras(bundle);
//                    intent.putExtras(bundle);
//                    startActivity(intent);
                }else {
                    intent.setClass(this, NormalEndMatchInfo.class);
//                    bundle = new Bundle();
//                    bundle.putSerializable("clublist",clubList);
//                    bundle.putSerializable("SportDetail", (Serializable) sportDetail);
//                    bundle.putSerializable("MatchMemb", (Serializable) matchMemb);
                    do_checkSportMemb();
//                    intent.putExtras(bundle);
//                    intent.putExtras(bundle);
//                    startActivity(intent);
                }
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
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

//删除比赛信息
private void do_delClubSport() {
    AppAction appAction = Factory.createAppActionImpl(getApplicationContext());
    appAction.fc_delClubSport(clubList.getClubID(),sportDetail.getS_record_id(),Params.fc_delClubSport, fc_delClubSport);//"leader",
}

    public ActionCallBackListener<BaseEntity<Void>> fc_delClubSport=new ActionCallBackListener<BaseEntity<Void>>(){

        /**
         * 处理成功
         *
         * @param data 返回数据
         */
        @Override
        public void onSuccess(BaseEntity<Void> data) {
//            MainApplication.setClubList((List<com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList>) data.getResponse().getClubList());                      //////////////
            Toast toast = Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_LONG);
            toast.show();
            finish();
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
            Toast toast = Toast.makeText(getApplicationContext(), e_Msg, Toast.LENGTH_LONG);
            toast.show();
            loadingDialog.dismiss();
        }
    };

    //查看签到人员
    private void do_checkSportMemb() {
        AppAction appAction = Factory.createAppActionImpl(getApplicationContext());
        appAction.fc_checkSportMemb(clubList.getClubID(),sportDetail.getS_record_id(),Params.fc_checkSportMemb, fc_checkSportMemb);//"leader",
    }



    public ActionCallBackListener<BaseEntity<MatchMemb>> fc_checkSportMemb=new ActionCallBackListener<BaseEntity<MatchMemb>>(){

        /**
         * 处理成功
         *
         * @param data 返回数据
         */
        @Override
        public void onSuccess(BaseEntity<MatchMemb> data) {
            matchMemb=data.getResponse().getMatchMemb();
            Toast toast = Toast.makeText(getApplicationContext(), "查看成功", Toast.LENGTH_LONG);
            toast.show();
            Bundle bundle=new Bundle();
            bundle.putSerializable("clublist",clubList);
            bundle.putSerializable("SportDetail", (Serializable) sportDetail);
            bundle.putSerializable("MatchMemb", (Serializable) matchMemb);
            intent.putExtras(bundle);
            startActivity(intent);
            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
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
            Toast toast = Toast.makeText(getApplicationContext(), e_Msg, Toast.LENGTH_LONG);
            toast.show();
            loadingDialog.dismiss();
        }
    };

//    private void do_checkClubMemb() {
//        AppAction appAction = Factory.createAppActionImpl(this);
//        appAction.fc_checkClubMemb(clubList.getClubID(),Params.fc_checkClubMemb, checkClubMemb);//"leader",
//    }
//List<ClubMemb> clubMemb;
//    public ActionCallBackListener<BaseEntity<ClubMemb>> checkClubMemb=new ActionCallBackListener<BaseEntity<ClubMemb>>(){
//
//        /**
//         * 处理成功
//         *
//         * @param data 返回数据
//         */
//        @Override
//        public void onSuccess(BaseEntity<ClubMemb> data) {
//            clubMemb=data.getResponse().getClubMemb();
//            loadingDialog.dismiss();
//
//        }
//
//        /**
//         * 请求失败
//         *
//         * @param e_Type 错误码
//         * @param e_Msg  错误详情
//         */
//        @Override
//        public void onFailure(String e_Type, String e_Msg) {
//            Toast toast = Toast.makeText(getApplicationContext(), "俱乐部"+e_Msg, Toast.LENGTH_LONG);
//            toast.show();
//            loadingDialog.dismiss();
//        }
//    };
private void do_sportSign() {
        AppAction appAction = Factory.createAppActionImpl(this);
        appAction.fc_sportSign(clubList.getClubID(),sportDetail.getS_record_id(),"other",Params.fc_sportSign, fc_sportSign);//"leader",
    }
List<ClubMemb> clubMemb;
    public ActionCallBackListener<BaseEntity<Void>> fc_sportSign=new ActionCallBackListener<BaseEntity<Void>>(){

        /**
         * 处理成功
         *
         * @param data 返回数据
         */
        @Override
        public void onSuccess(BaseEntity<Void> data) {
//            clubMemb=data.getResponse().getClubMemb();
            SportDetail.signresult=data.getResponse().getSign();
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
            Toast toast = Toast.makeText(getApplicationContext(), e_Msg, Toast.LENGTH_LONG);
            toast.show();
            loadingDialog.dismiss();
        }
    };
}
