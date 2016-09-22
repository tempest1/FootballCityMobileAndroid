package com.footballcitymobileandroid.Controller.Club;

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

import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/7/13.
 */
public class NormalMatchInfo extends FragmentActivity implements View.OnClickListener{
    private FragmentManager fragmentManager;

    private NormalMatchNoStart normalMatchNoStart;
    private NormalMatchStarting normalMatchStarting;
    private NormalMatchEnd normalMatchEnd;

    private RadioButton radio_accept;
    private RadioButton radio_deliver;
    private RadioButton radio_info;

    Button detail_back;
    TextView detail_title_center;
    ClubList clubList;

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
            Drawable rightDrawable = getResources().getDrawable(R.mipmap.head);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
            switch (checkId) {
                case R.id.radio_accept:
                    setTabSelection(0);
                    LogUtils.e("radio_accept");
                    selcet();
                    radio_accept.setCompoundDrawables(null, null, null, rightDrawable);
                    break;
                case R.id.radio_deliver:
                    setTabSelection(1);
                    LogUtils.e("radio_deliver");

                    selcet();
                    radio_deliver.setCompoundDrawables(null, null, null, rightDrawable);
                    break;

                case R.id.radio_info:
                    setTabSelection(2);
                    LogUtils.e("radio_info");
                    selcet();
                    radio_info.setCompoundDrawables(null, null, null, rightDrawable);

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
        radio_info.setCompoundDrawables(null, null, null, rightDrawable);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_match);
        fragmentManager = getSupportFragmentManager();
        init();
        inData();

    }

    private void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        RadioGroup radio_tab = (RadioGroup) findViewById(R.id.radio_tab);

        radio_accept = (RadioButton) findViewById(R.id.radio_accept);
        radio_deliver = (RadioButton) findViewById(R.id.radio_deliver);
        radio_info = (RadioButton) findViewById(R.id.radio_info);

        radio_tab.setOnCheckedChangeListener(listener);

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("普通比赛");

    }

    private void inData()
    {


        setTabSelection(0);

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
            if (normalMatchNoStart == null) {
                normalMatchNoStart = new NormalMatchNoStart(clubList);
                transaction.add(R.id.frame, normalMatchNoStart, FRAGMENT_TAG_INFO);

            }
            transaction.show(normalMatchNoStart);
            radio_accept.setChecked(true);
            break;
            case 1:
                if (normalMatchStarting == null) {
                    normalMatchStarting = new NormalMatchStarting(clubList);
                    transaction.add(R.id.frame, normalMatchStarting, FRAGMENT_TAG_HOME);

                }
                transaction.show(normalMatchStarting);
                radio_deliver.setChecked(true);

                break;

            case 2:
                if (normalMatchEnd == null) {
                    normalMatchEnd = new NormalMatchEnd(clubList);
                    LogUtils.e("clubFragmens");
                    transaction.add(R.id.frame, normalMatchEnd, FRAGMENT_TAG_FORUM);

                }
                transaction.show(normalMatchEnd);
                radio_info.setChecked(true);
                break;

            default:
                break;
        }
        transaction.commit();
    }
    private void hideFragments(FragmentTransaction transaction) {
        if (normalMatchStarting != null) {
            transaction.hide(normalMatchStarting);
        }
        if (normalMatchNoStart != null) {
            transaction.hide(normalMatchNoStart);
        } if (normalMatchEnd != null) {
            transaction.hide(normalMatchEnd);
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
