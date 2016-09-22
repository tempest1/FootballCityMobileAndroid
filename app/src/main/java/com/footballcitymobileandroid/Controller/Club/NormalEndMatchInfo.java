package com.footballcitymobileandroid.Controller.Club;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.RefuseAdapter;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.MatchMemb;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.SportDetail;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/7/2.
 */
public class NormalEndMatchInfo extends FragmentActivity implements View.OnClickListener {
    private FragmentManager fragmentManager;
    //1
    private NormalEndMatchInfoFragment normalEndMatchInfoFragment;


    private NormalStartedMatchplayerFragment normalStartedMatchplayerFragment;

    private RadioButton radio_info;
    private RadioButton radio_join;

    Button detail_back,detail_title;
    TextView detail_title_center,recode,best,look_place,compain;

    private RelativeLayout list;
    List<String> data=new ArrayList<>();
    RefuseAdapter adapter;
    Intent intent = new Intent();
    SportDetail sportDetail;     //比赛内容
    ClubList clubList;
    List<MatchMemb> matchMemb;

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
            Drawable rightDrawable = getResources().getDrawable(R.mipmap.head);
            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
            switch (checkId) {
                case R.id.radio_join:
                    setTabSelection(0);
                    selcet();
                    radio_join.setCompoundDrawables(null, null, null, rightDrawable);
                    break;
                case R.id.radio_info:
                    setTabSelection(1);
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
        radio_info.setCompoundDrawables(null, null, null, rightDrawable);

        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        radio_join.setCompoundDrawables(null, null, null, rightDrawable);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_end_match_info_new);
        fragmentManager = getSupportFragmentManager();
        init();
        inData();

    }
//
//    private AdapterView.OnItemClickListener info_click = new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//
//            //  LogUtils.e(clubTest.getClubname());
//            showInfoDetail(position);
//        }
//    };
//    private void showInfoDetail(int index) {
//        if (index==0)
//        {
//            list.setVisibility(View.GONE);
//            intent = new Intent();
//            intent.setClass(this, ChallengeEndMatchInfoMessage.class);
//            list.setVisibility(View.GONE);
//            startActivity(intent);
//            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);        }else if(index==1)
//        {
//            intent = new Intent();
////		Bundle bundle=new Bundle();
////		bundle.putString("type", "forget");
////		intent.putExtras(bundle);
//            intent.setClass(this, ChallengeCompain.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
//            list.setVisibility(View.GONE);
//
//        }else if(index==2){
//            intent = new Intent();
//            intent.setClass(this, NormalMatchReStartSet.class);
//            list.setVisibility(View.GONE);
//            startActivity(intent);
//            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
//        }else if(index==3){
//            list.setVisibility(View.GONE);
//            intent = new Intent();
//            intent.setClass(this, ChallengeEndMatchInfoGreatNumber.class);
//            list.setVisibility(View.GONE);
//            startActivity(intent);
//            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
//
//        }
//    }

    private void init()
    {
        sportDetail= (SportDetail) getIntent().getSerializableExtra("SportDetail");
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        matchMemb= (List<MatchMemb>) getIntent().getSerializableExtra("MatchMemb");
        RadioGroup radio_tab = (RadioGroup) findViewById(R.id.radio_tab);
        radio_info = (RadioButton) findViewById(R.id.radio_info);
        radio_join = (RadioButton) findViewById(R.id.radio_join);
        radio_tab.setOnCheckedChangeListener(listener);

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("匹配请求");

        detail_title=(Button)findViewById(R.id.detail_title);
        detail_title.setOnClickListener(this);

        list=(RelativeLayout)findViewById(R.id.list);

        recode=(TextView)findViewById(R.id.recode);
        recode.setOnClickListener(this);
        recode.setVisibility(View.GONE);

        best=(TextView)findViewById(R.id.best);
        best.setOnClickListener(this);
        best.setVisibility(View.GONE);

        look_place=(TextView)findViewById(R.id.look_place);
        look_place.setOnClickListener(this);

        compain=(TextView)findViewById(R.id.compain);
        compain.setOnClickListener(this);
        compain.setVisibility(View.GONE);



    }

    private void inData()
    {

        setTabSelection(1);


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
                if (normalStartedMatchplayerFragment == null) {
                    normalStartedMatchplayerFragment = new NormalStartedMatchplayerFragment(matchMemb);
                    transaction.add(R.id.frame, normalStartedMatchplayerFragment, FRAGMENT_TAG_HOME);

                }
                transaction.show(normalStartedMatchplayerFragment);
                radio_join.setChecked(true);

                break;
            case 1:
                if (normalEndMatchInfoFragment == null) {
                    normalEndMatchInfoFragment = new NormalEndMatchInfoFragment( clubList, sportDetail);
                    LogUtils.e("clubFragmens");
                    transaction.add(R.id.frame, normalEndMatchInfoFragment, FRAGMENT_TAG_INFO);

                }
                transaction.show(normalEndMatchInfoFragment);
                radio_info.setChecked(true);
                break;


            default:
                break;
        }
        transaction.commit();
    }
    private void hideFragments(FragmentTransaction transaction) {
        if (normalStartedMatchplayerFragment != null) {
            transaction.hide(normalStartedMatchplayerFragment);
        }
        if (normalEndMatchInfoFragment != null) {
            transaction.hide(normalEndMatchInfoFragment);
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
            case R.id.detail_title:
                if (list.getVisibility()==View.GONE) {
                    list.setVisibility(View.VISIBLE);
                }else {
                    list.setVisibility(View.GONE);
                }
                break;
            case R.id.recode:
                list.setVisibility(View.GONE);
                intent = new Intent();
                intent.setClass(this, ChallengeEndMatchInfoMessage.class);
                list.setVisibility(View.GONE);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.best:
                list.setVisibility(View.GONE);
                intent = new Intent();
                intent.setClass(this, ChallengeEndMatchInfoGreatNumber.class);
                list.setVisibility(View.GONE);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.look_place:
                intent = new Intent();
                intent.setClass(this, NormalMatchReStartSet.class);
                list.setVisibility(View.GONE);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);

                break;
            case R.id.compain:
                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                intent.setClass(this, ChallengeCompain.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                list.setVisibility(View.GONE);
                break;

        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

}
