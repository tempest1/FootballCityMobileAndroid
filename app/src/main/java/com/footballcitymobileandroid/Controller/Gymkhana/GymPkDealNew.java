package com.footballcitymobileandroid.Controller.Gymkhana;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.footballcitymobileandroid.Controller.Base.InvestmentActivity;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.MatchingInfo;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/23.
 */
public class GymPkDealNew extends InvestmentActivity implements View.OnClickListener {

    private FragmentManager fragmentManager;

    private GymPkDealNewAccept gymPkDealAccept;
    private GymPkDealNewDeliver gymPkDealDeliver;

    private RadioButton radio_accept;
    private RadioButton radio_deliver;

    Button detail_back;
    TextView detail_title_center;

    ClubList clubList;

    MatchingInfo matchingInfoList;

//    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
//            Drawable rightDrawable = getResources().getDrawable(R.mipmap.head);
//            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
//            switch (checkId) {
//                case R.id.radio_deliver:
//                    setTabSelection(0);
//                    selcet();
//                    radio_deliver.setCompoundDrawables(null, null, null, rightDrawable);
//                    break;
//                case R.id.radio_accept:
//                    setTabSelection(1);
//                    selcet();
//                    radio_accept.setCompoundDrawables(null, null, null, rightDrawable);
//
//                    break;
//
//
//            }
//        }
//    };


    private void selcet()
    {
        Drawable rightDrawable = getResources().getDrawable(R.mipmap.agenull);
        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        radio_accept.setCompoundDrawables(null, null, null, rightDrawable);

        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        radio_deliver.setCompoundDrawables(null, null, null, rightDrawable);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gym_pk_deal_new);
        fragmentManager = getSupportFragmentManager();
        init();
        inData();

    }

    private void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        matchingInfoList= (MatchingInfo) getIntent().getSerializableExtra("matchingInfoList");
        RadioGroup radio_tab = (RadioGroup) findViewById(R.id.radio_tab);

        radio_accept = (RadioButton) findViewById(R.id.radio_accept);
        radio_deliver = (RadioButton) findViewById(R.id.radio_deliver);
        //radio_tab.setOnCheckedChangeListener(listener);

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("匹配请求");

    }

    private void inData()
    {
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        String choose=bundle.getString("choose");

//        ClubTest club = (ClubTest) bundle.getSerializable("club");
//        if(club!=null) {
//            LogUtils.e(club.getClubname());
//            setTabSelection(0);
//        }
//
//
//        ClubChallenge challenge = (ClubChallenge) bundle.getSerializable("challenge");
//        if (challenge!=null) {
//            LogUtils.e(challenge.getMeclub());
//            setTabSelection(1);
//        }
        if (choose.equals("challenge")) {
            setTabSelection(0);
        }else
        {
            setTabSelection(1);

        }
    }

    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        String FRAGMENT_TAG_HOME = "home";
        String FRAGMENT_TAG_INFO = "infoPage";
        String FRAGMENT_TAG_FORUM = "forum";
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:                               //发送
                if (gymPkDealDeliver == null) {
                    gymPkDealDeliver = new GymPkDealNewDeliver(clubList,matchingInfoList);
                    transaction.add(R.id.frame, gymPkDealDeliver, FRAGMENT_TAG_HOME);

                }
                transaction.show(gymPkDealDeliver);
                radio_deliver.setChecked(true);

                break;
            case 1:                              //接受
                if (gymPkDealAccept == null) {
                    gymPkDealAccept = new GymPkDealNewAccept(clubList,matchingInfoList);
                    LogUtils.e("clubFragmens");
                    transaction.add(R.id.frame, gymPkDealAccept, FRAGMENT_TAG_INFO);

                }
                transaction.show(gymPkDealAccept);
                radio_accept.setChecked(true);
                break;


            default:
                break;
        }
        transaction.commit();
    }
    private void hideFragments(FragmentTransaction transaction) {
        if (gymPkDealDeliver != null) {
            transaction.hide(gymPkDealDeliver);
        }
        if (gymPkDealAccept != null) {
            transaction.hide(gymPkDealAccept);
        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
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
}