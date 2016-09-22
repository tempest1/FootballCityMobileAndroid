package com.footballcitymobileandroid.Controller.Club;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.FragmentAdapter;
import com.footballcitymobileandroid.Controller.Me.MeSetClubDay;
import com.footballcitymobileandroid.Controller.Me.MeSetClubGreat;
import com.footballcitymobileandroid.Controller.Me.MeSetClubImage;
import com.footballcitymobileandroid.Controller.Me.MeSetClubName;
import com.footballcitymobileandroid.Controller.Me.MeSetClubPerson;
import com.footballcitymobileandroid.Controller.Me.MeSetClubPlace;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/23.
 */
public class SettingClub extends FragmentActivity implements View.OnClickListener {

    private ViewPager mPageVp;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
    private ImageView mTabLineIv;
    private int currentIndex;
    private int screenWidth;


    private MeSetClubDay meSetClubDay;
    private MeSetClubGreat meSetClubGreat;
    private MeSetClubImage meSetClubImage;
    private MeSetClubName meSetClubName;
    private MeSetClubPerson meSetClubPerson;
    private MeSetClubPlace meSetClubPlace;

    private Button detail_title,detail_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_set_club);
        init();
    }
    private void init()
    {

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);
        detail_title=(Button)findViewById(R.id.detail_title);
        detail_title.setOnClickListener(this);

        mTabLineIv = (ImageView) this.findViewById(R.id.id_tab_line_iv);

        mPageVp = (ViewPager) this.findViewById(R.id.id_page_vp);
        meSetClubDay=new MeSetClubDay();
        meSetClubGreat=new MeSetClubGreat();
        meSetClubImage=new MeSetClubImage();
        meSetClubName=new MeSetClubName();
        meSetClubPerson=new MeSetClubPerson();
        meSetClubPlace=new MeSetClubPlace();

        mFragmentList.add(meSetClubImage);
        mFragmentList.add(meSetClubName);
        mFragmentList.add(meSetClubPlace);
        mFragmentList.add(meSetClubDay);
        mFragmentList.add(meSetClubPerson);
        mFragmentList.add(meSetClubGreat);


        mFragmentAdapter = new FragmentAdapter(
                this.getSupportFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);

        mPageVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }

            /**
             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
             * offsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float offset,
                                       int offsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                        .getLayoutParams();

                Log.e("offset:", offset + "");
                /**
                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
                 * 设置mTabLineIv的左边距 滑动场景：
                 * 记3个页面,
                 * 从左到右分别为0,1,2
                 * 0->1; 1->2; 2->1; 1->0
                 */

                if (currentIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));

                } else if (currentIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                }
                mTabLineIv.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                // resetTextView();
                switch (position) {
                    case 0:
                        // mTabChatTv.setTextColor(Color.BLUE);

                        break;
                    case 1:
                        //  mTabFriendTv.setTextColor(Color.BLUE);
                        break;
                    case 2:
                        //   mTabContactsTv.setTextColor(Color.BLUE);
                        break;
                }
                currentIndex = position;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.detail_title:
                // 处理数据

                //结束activity
                 this.finish();
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
}
