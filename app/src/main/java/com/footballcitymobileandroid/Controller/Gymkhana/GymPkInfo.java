package com.footballcitymobileandroid.Controller.Gymkhana;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.Controller.Base.InvestmentActivity;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Clubs;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.MatchMsg;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.MatchingInfo;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.net.Inet4Address;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zhoudi on 16/5/20.
 */
public class GymPkInfo extends InvestmentActivity implements View.OnClickListener,ActionCallBackListener<BaseEntity<Clubs>>{

    private FragmentManager fragmentManager;


    private GymPkInfoNumber gymPkInfoNumber;
    private GymPkInfoPlace gymPkInfoPlace;
    private GymPkInfoPay gymPkInfoPay;
    private GymPkInfoDate gymPkInfoDate;
    private GymPkInfoTime gymPkInfoTime;


    private Button next,detail_back,pre;

    private TextView detail_title_center;
    List<MatchingInfo> matchingInfoList;

    ClubList clubList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gym_pk_info);
        fragmentManager = getSupportFragmentManager();
        init();
        inData();
        setTabSelection(0);
    }

    private void init()
    {
        matchingInfoList= (List<MatchingInfo>) getIntent().getSerializableExtra("matchingInfo");
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(this);

        pre=(Button)findViewById(R.id.pre);
        pre.setOnClickListener(this);

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("设置详细信息");
    }

    private void inData()
    {
//        Intent intent = getIntent();
//
//        Bundle bundle = intent.getExtras();
//
//        ClubTest club = (ClubTest) bundle.getSerializable("club");
//        LogUtils.e(club.getClubname());
    }

    private int Index=0;

    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        String FRAGMENT_TAG_HOME = "home";
        String FRAGMENT_TAG_INFO = "infoPage";
        String FRAGMENT_TAG_FORUM = "forum";
        String Time="time";
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                if (gymPkInfoPlace == null) {              //地点
                    gymPkInfoPlace = new GymPkInfoPlace();
                    transaction.add(R.id.frame, gymPkInfoPlace, FRAGMENT_TAG_HOME);

                }Index=0;
                transaction.show(gymPkInfoPlace);

                break;
            case 1:
                if (gymPkInfoDate == null) {//日期


                    gymPkInfoDate = new GymPkInfoDate();
                    transaction.add(R.id.frame, gymPkInfoDate, Time);


                }Index=1;
                transaction.show(gymPkInfoDate);
                break;
            case 2:
                if (gymPkInfoTime == null) {            //时间
                    gymPkInfoTime = new GymPkInfoTime();
                    transaction.add(R.id.frame, gymPkInfoTime, Time);


                }Index=2;
                transaction.show(gymPkInfoTime);
                break;
            case 3:
                if (gymPkInfoNumber == null) {         //赛制
                    gymPkInfoNumber = new GymPkInfoNumber();
                    LogUtils.e("clubFragmens");
                    transaction.add(R.id.frame, gymPkInfoNumber, FRAGMENT_TAG_INFO);

                }Index=3;
                transaction.show(gymPkInfoNumber);
                break;
            case 4:
                if (gymPkInfoPay == null) {         //支付方式 0主场支付，1 aa制
                    gymPkInfoPay = new GymPkInfoPay();
                    transaction.add(R.id.frame, gymPkInfoPay, FRAGMENT_TAG_FORUM);

                }Index=4;

                transaction.show(gymPkInfoPay);
                break;

            default:
                break;
        }
        transaction.commit();
    }
    private void hideFragments(FragmentTransaction transaction) {
        if (gymPkInfoPlace != null) {
            transaction.hide(gymPkInfoPlace);
        }
        if(gymPkInfoDate!=null)
        {
            transaction.hide(gymPkInfoDate);
        }
        if(gymPkInfoTime!=null)
        {
            transaction.hide(gymPkInfoTime);
        }
        if (gymPkInfoNumber != null) {
            transaction.hide(gymPkInfoNumber);
        }
        if (gymPkInfoPay != null) {
            transaction.hide(gymPkInfoPay);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.next:
                if (Index==0) {
                    setTabSelection(1);
                    next.setText("下一步");
                }
                else if(Index==1)
                {
                    try {
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
                        String dstr=MatchMsg.matchingDate;
                        Date date=sdf.parse(dstr);
                        SimpleDateFormat formatter  =   new   SimpleDateFormat("yyyy-MM-dd");
                        Date curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
                        String    str    =    formatter.format(curDate);

                        LogUtils.e(dstr);
                        LogUtils.e(curDate.toString());
                        if (curDate.before(date))
                        {
                            setTabSelection(2);
                            next.setText("下一步");
                        }else if (dstr.equals(str)){
                            setTabSelection(2);
                            next.setText("下一步");
                        }else {
                            strToast("所选日期已过");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                else  if(Index==2)
                {
                    setTabSelection(3);
                    next.setText("下一步");
                }
                else if(Index==3)
                {
                    setTabSelection(4);
                    next.setText("下一步");
                } else if(Index==4)
                {
                    intent.setClass(this, GymPkRankClub.class);  //GymPkRankClub 预选成员
//                    intent.setClass(this, GymPkDealNew.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("club", "challenge");
//                    intent.putExtras(bundle);
                    doit();
                }
                break;

            case R.id.pre:
                if (Index==4) {
                    setTabSelection(3);

                }
                else if(Index==3)
                {
                    setTabSelection(2);
                }
                else  if(Index==2)
                {
                    setTabSelection(1);

                }
                else if(Index==1)
                {
                    setTabSelection(0);


                } else if(Index==0)
                {

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
    Intent intent = new Intent();
    private void doit(){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_getMatchingClub("1",MatchMsg.fieldID,MatchMsg.matchingDate, Params.fc_getMatchingClub,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Clubs> data) {
        Toast toast = Toast.makeText(this, "获取成功", Toast.LENGTH_LONG);
        toast.show();
        Bundle bundle=new Bundle();
        bundle.putSerializable("Clubs", (Serializable) data.getResponse().getClubs());
        bundle.putSerializable("clublist",clubList);
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
        Toast toast = Toast.makeText(this, e_Msg, Toast.LENGTH_LONG);
        toast.show();
    }

    private void strToast(String str)
    {
        Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
        toast.show();
    }
}
