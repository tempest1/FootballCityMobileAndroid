package com.footballcitymobileandroid.Controller.Gymkhana;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.FragmentAdapter;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/6/12.
 */
public class RuleActivity   extends FragmentActivity implements View.OnClickListener {
    private ViewPager mPageVp;

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;



    private Button detail_back;

    private FrameLayout main_frame;
    /**
     * Tab的那个引导线
     */
    private ImageView mTabLineIv;
    /**
     * Fragment
     */
    private RuleFragment1 rf1;
    private RuleFragment2 rf2;
    private RuleFragment3 rf3;


    RelativeLayout club_title;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;
    /**
     * 屏幕的宽度
     */
    private int screenWidth;

    private TextView detail_title_center;

    private FragmentManager fragmentManager;

    private LinearLayout main;


    private View dot1,dot0,dot2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank_activity);

        fragmentManager = getSupportFragmentManager();
        init();

    }




    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.detail_title:
//                LogUtils.e("detail_title");
//                this.findViewById(R.id.main_frame).setVisibility(View.GONE);
//                mPageVp.setVisibility(View.VISIBLE);
                break;
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
        }
    }

    private void init() {

        mTabLineIv = (ImageView) this.findViewById(R.id.id_tab_line_iv);

        mPageVp = (ViewPager) this.findViewById(R.id.id_page_vp);

        main_frame =  (FrameLayout) this.findViewById(R.id.main_frame);

        club_title=(RelativeLayout)this.findViewById(R.id.club_title);
        rf1 = new RuleFragment1();
        rf2 = new RuleFragment2();
        rf3 = new RuleFragment3();
//        pkFragment = new PkFragment();
//        mFragmentList.add(pkFragment);
        mFragmentList.add(rf1);
        mFragmentList.add(rf2);
        mFragmentList.add(rf3);



        detail_back=(Button)this.findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);


        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("规则");

        mFragmentAdapter = new FragmentAdapter(
                this.getSupportFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);

        dot0=(View)findViewById(R.id.v_dot0) ;
        dot1=(View)findViewById(R.id.v_dot1) ;
        dot2=(View)findViewById(R.id.v_dot2);

        dot2.setVisibility(View.VISIBLE);


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
                    dot0.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                    dot1.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                    dot0.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
                    dot1.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));

                } else if (currentIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                    dot1.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                    dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));

                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    dot1.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
                    dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                }else if (currentIndex == 2 && position == 2) // 1->2
                {
                    dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                    //dot1.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                } else if (currentIndex == 3 && position == 2) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                }else if (currentIndex == 3 && position == 3) // 2->1
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
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

}
