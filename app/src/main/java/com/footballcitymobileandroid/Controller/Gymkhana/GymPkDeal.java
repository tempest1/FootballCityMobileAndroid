package com.footballcitymobileandroid.Controller.Gymkhana;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.footballcitymobileandroid.Controller.Club.ClubFragement;
import com.footballcitymobileandroid.Controller.Me.MeFragement;
import com.footballcitymobileandroid.Controller.TestData.ClubChallenge;
import com.footballcitymobileandroid.Controller.TestData.ClubTest;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/23.
 */
public class GymPkDeal extends FragmentActivity implements View.OnClickListener {

    private FragmentManager fragmentManager;

    private GymPkDealAccept gymPkDealAccept;
    private GymPkDealBecome gymPkDealBecome;
    private GymPkDealDeliver gymPkDealDeliver;

    private RadioButton radio_accept;
    private RadioButton radio_deliver;
    private RadioButton radio_become;

    Button detail_back;
    TextView detail_title_center;


    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
            Drawable rightDrawable = getResources().getDrawable(R.mipmap.head);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
            switch (checkId) {
                case R.id.radio_deliver:
                    setTabSelection(0);
                    selcet();
                    radio_deliver.setCompoundDrawables(null, null, null, rightDrawable);
                    break;
                case R.id.radio_accept:
                    setTabSelection(1);
                    selcet();
                    radio_accept.setCompoundDrawables(null, null, null, rightDrawable);

                    break;
                case R.id.radio_become:
                    setTabSelection(2);
                    selcet();
                    radio_become.setCompoundDrawables(null, null, null, rightDrawable);

                    break;

            }
        }
    };


    private void selcet()
    {
        Drawable rightDrawable = getResources().getDrawable(R.mipmap.agenull);
        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        radio_accept.setCompoundDrawables(null, null, null, rightDrawable);

        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        radio_deliver.setCompoundDrawables(null, null, null, rightDrawable);

        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        radio_become.setCompoundDrawables(null, null, null, rightDrawable);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gym_pk_deal);
        fragmentManager = getSupportFragmentManager();
        init();
        inData();

    }

    private void init()
    {
        RadioGroup radio_tab = (RadioGroup) findViewById(R.id.radio_tab);

        radio_accept = (RadioButton) findViewById(R.id.radio_accept);
        radio_deliver = (RadioButton) findViewById(R.id.radio_deliver);
        radio_become = (RadioButton) findViewById(R.id.radio_become);
        radio_tab.setOnCheckedChangeListener(listener);

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("匹配请求");

    }

    private void inData()
    {
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        ClubTest club = (ClubTest) bundle.getSerializable("club");
        if(club!=null) {
            LogUtils.e(club.getClubname());
            setTabSelection(2);
        }
        setTabSelection(2);

        ClubChallenge challenge = (ClubChallenge) bundle.getSerializable("challenge");
        if (challenge!=null) {
            LogUtils.e(challenge.getMeclub());
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
            case 0:
                if (gymPkDealDeliver == null) {
                    gymPkDealDeliver = new GymPkDealDeliver();
                    transaction.add(R.id.frame, gymPkDealDeliver, FRAGMENT_TAG_HOME);

                }
                transaction.show(gymPkDealDeliver);
                radio_deliver.setChecked(true);

                break;
            case 1:
                if (gymPkDealAccept == null) {
                    gymPkDealAccept = new GymPkDealAccept();
                    LogUtils.e("clubFragmens");
                    transaction.add(R.id.frame, gymPkDealAccept, FRAGMENT_TAG_INFO);

                }
                transaction.show(gymPkDealAccept);
                radio_accept.setChecked(true);
                break;
            case 2:
                if (gymPkDealBecome == null) {
                    gymPkDealBecome = new GymPkDealBecome();
                    transaction.add(R.id.frame, gymPkDealBecome, FRAGMENT_TAG_FORUM);

                }
                transaction.show(gymPkDealBecome);
                radio_become.setChecked(true);
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
        if (gymPkDealBecome != null) {
            transaction.hide(gymPkDealBecome);
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
