package com.footballcitymobileandroid.Controller.Club;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.footballcitymobileandroid.Controller.Me.MeClubNumberList;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/28.
 */
public class PlayerFindIf extends FragmentActivity implements View.OnClickListener{
    ClubList clubList;


    private FrameLayout main_frame;

    private PlayerFindIfAge playerFindIfAge;
    private PlayerFindIfTime playerFindIfTime;
    private PlayerFindIfPlace playerFindIfPlace;


    private FragmentManager fragmentManager;

    private RadioButton radio_age;
    private RadioButton radio_place;
    private RadioButton radio_time;

    private Button find,detail_back;
    private Intent intent;
    TextView detail_title_center;

    Drawable drawable,drawablenull;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_find_if);

        fragmentManager = getSupportFragmentManager();

        init();

        setTabSelection(0);
    }



    private void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("条件球员查找");

        find=(Button)findViewById(R.id.find);
        find.setOnClickListener(this);

        RadioGroup radio_tab = (RadioGroup) findViewById(R.id.radio_tab);
        radio_tab.setOnCheckedChangeListener(listener);
        radio_age = (RadioButton) findViewById(R.id.radio_age);
        radio_place = (RadioButton) findViewById(R.id.radio_place);
        radio_time = (RadioButton) findViewById(R.id.radio_time);


    }

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkId) {

            switch (checkId) {
                case R.id.radio_age:
                    setTabSelection(0);
                    Age();
                    drawable= getResources().getDrawable(R.mipmap.head);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    radio_age.setCompoundDrawables(null,null,null,drawable);

                    find.setVisibility(View.INVISIBLE);
                    break;
                case R.id.radio_place:
                    setTabSelection(1);
                    Age();
                    drawable= getResources().getDrawable(R.mipmap.head);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    radio_place.setCompoundDrawables(null,null,null,drawable);
                    find.setVisibility(View.INVISIBLE);
                    break;
                case R.id.radio_time:
                    find.setVisibility(View.INVISIBLE);
                    Age();
                    drawable= getResources().getDrawable(R.mipmap.head);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    radio_time.setCompoundDrawables(null,null,null,drawable);
                    setTabSelection(2);
                    break;

            }
        }
    };

    private void Age()
    {
        drawable= getResources().getDrawable(R.mipmap.agenull);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        radio_age.setCompoundDrawables(null,null,null,drawable);
        radio_place.setCompoundDrawables(null,null,null,drawable);
        radio_time.setCompoundDrawables(null,null,null,drawable);
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
                if (playerFindIfAge == null) {
                    playerFindIfAge = new PlayerFindIfAge(clubList);
                    transaction.add(R.id.main_frame, playerFindIfAge, FRAGMENT_TAG_HOME);

                }
                transaction.show(playerFindIfAge);
                radio_age.setChecked(true);

                break;
            case 1:
                if (playerFindIfPlace == null) {
                    playerFindIfPlace = new PlayerFindIfPlace(clubList);
                    LogUtils.e("clubFragmens");
                    transaction.add(R.id.main_frame, playerFindIfPlace, FRAGMENT_TAG_INFO);

                }
                transaction.show(playerFindIfPlace);
                radio_place.setChecked(true);
                break;
            case 2:
                if (playerFindIfTime == null) {
                    playerFindIfTime = new PlayerFindIfTime(clubList);
                    transaction.add(R.id.main_frame, playerFindIfTime, FRAGMENT_TAG_FORUM);

                }
                transaction.show(playerFindIfTime);
                radio_time.setChecked(true);
                break;

            default:
                break;
        }
        transaction.commit();
    }
    private void hideFragments(FragmentTransaction transaction) {
        if (playerFindIfAge != null) {
            transaction.hide(playerFindIfAge);
        }
        if (playerFindIfPlace != null) {
            transaction.hide(playerFindIfPlace);
        }
        if (playerFindIfTime != null) {
            transaction.hide(playerFindIfTime);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;

            case R.id.find:
                intent=new Intent();
                intent.setClass(this,MeClubNumberList.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }
}
