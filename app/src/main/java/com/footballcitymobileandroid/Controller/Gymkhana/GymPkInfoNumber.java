package com.footballcitymobileandroid.Controller.Gymkhana;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.FragmentAdapter;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.MatchMsg;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/21.
 */
public class GymPkInfoNumber extends Fragment {
    private View view;
    private FragmentAdapter mFragmentAdapter;
    private GymPkInfoNumber1 gymPkInfoNumber1;
    private GymPkInfoNumber2 gymPkInfoNumber2;
    private GymPkInfoNumber3 gymPkInfoNumber3;
    private GymPkInfoNumber4 gymPkInfoNumber4;

    private ViewPager mPageVp;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private ImageView mTabLineIv;
    View dot0,dot1,dot2;
    private int currentIndex;
    private int screenWidth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.gym_pk_info_number, container,false);
        init();
        return view;
    }
    void init()
    {
        MatchMsg.matchRule="8";
        Log.i("rule",MatchMsg.matchRule);
        mTabLineIv = (ImageView) view.findViewById(R.id.id_tab_line_iv);

        mPageVp = (ViewPager) view.findViewById(R.id.id_page_vp);

        gymPkInfoNumber1=new GymPkInfoNumber1();
        gymPkInfoNumber2=new GymPkInfoNumber2();

        gymPkInfoNumber3=new GymPkInfoNumber3();
        gymPkInfoNumber4=new GymPkInfoNumber4();
        mFragmentList.add(gymPkInfoNumber1);
        mFragmentList.add(gymPkInfoNumber2);
        mFragmentList.add(gymPkInfoNumber3);
        mFragmentList.add(gymPkInfoNumber4);

        dot0=(View)view.findViewById(R.id.v_dot0);
        dot1=(View)view.findViewById(R.id.v_dot1);
        dot2=(View)view.findViewById(R.id.v_dot2);
        mFragmentAdapter = new FragmentAdapter(
                getChildFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);

        mPageVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }


            int i=0;

            /**
             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
             * offsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float offset,
                                       int offsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                        .getLayoutParams();


                if (currentIndex == 0 && position == 0)// 0->1
                {
                    dot0.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                    dot1.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    dot0.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
                    dot1.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));

                } else if (currentIndex == 1 && position == 1) // 1->2
                {
                    dot1.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                    dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));

                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    dot1.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
                    dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                }else if (currentIndex == 2 && position == 2) // 2->1
                {
                    dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));


                }
                mTabLineIv.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                // resetTextView();
                switch (position) {
                    case 0:
                        // mTabChatTv.setTextColor(Color.BLUE);
                        MainApplication.RULE=1;
                        MatchMsg.matchRule="8";
                        Log.i("rule",MatchMsg.matchRule);
                        break;
                    case 1:
                        //  mTabFriendTv.setTextColor(Color.BLUE);
                        MainApplication.RULE=2;
                        MatchMsg.matchRule="9";
                        Log.i("rule",MatchMsg.matchRule);

                        break;
                    case 2:
                        MainApplication.RULE=3;
                        MatchMsg.matchRule="10";
                        Log.i("rule",MatchMsg.matchRule);
                        //   mTabContactsTv.setTextColor(Color.BLUE);
                        break;
                    case 3:
                        MainApplication.RULE=4;
                        MatchMsg.matchRule="11";
                        Log.i("rule",MatchMsg.matchRule);
                        //   mTabContactsTv.setTextColor(Color.BLUE);
                        break;
                }
                currentIndex = position;
            }
        });

    }


}
