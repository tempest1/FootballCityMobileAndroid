package com.footballcitymobileandroid.Controller.Base;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.FragmentAdapter;
import com.footballcitymobileandroid.Controller.Club.ClubFragement;
import com.footballcitymobileandroid.Controller.Club.ClubFragementFrag;
import com.footballcitymobileandroid.Controller.Club.ClubFragements;
import com.footballcitymobileandroid.Controller.Gymkhana.GymkhanaFragment;
import com.footballcitymobileandroid.Controller.Me.MeFragement;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private GymkhanaFragment gymkhanaFragment;
    private ClubFragement clubFragement;
    private MeFragement meFragement;
    private FragmentManager fragmentManager;


//    String [] a=new String[]{"俱乐部1","俱乐部2","俱乐部3","俱乐部4","俱乐部5"};
    ClubFragements clubFragements;
//    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
//    private ViewPager mPageVp;
//    private FragmentAdapter mFragmentAdapter;
//    private ImageView mTabLineIv;
//    private int currentIndex;
//    private int screenWidth;
//
//    FrameLayout main_frame;

    private RadioButton butA;
    private RadioButton butB;
    private RadioButton butC;
//    private ScrollableImageView mBlurredImageHeader;
  //  View dot0,dot1,dot2,s_dot0,s_dot1,s_dot2,s_dot3,s_dot4,s_dot5;

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.butA:
                    show();
                    setTabselection(0);
                    break;
                case R.id.butB:
                    setTabselection(1);
                    break;
                case R.id.butC:
                    show();
                    setTabselection(2);
                    break;
                default:
                    break;
            }
        }
    };

    private void show()
    {
//        main_frame.setVisibility(View.VISIBLE);
//        mPageVp.setVisibility(View.GONE);
//        dot0.setVisibility(View.INVISIBLE);
//        dot1.setVisibility(View.INVISIBLE);
//        dot2.setVisibility(View.INVISIBLE);
//        s_dot3.setVisibility(View.INVISIBLE);
//        s_dot4.setVisibility(View.INVISIBLE);
//        s_dot5.setVisibility(View.INVISIBLE);
//        s_dot0.setVisibility(View.INVISIBLE);
//        s_dot1.setVisibility(View.INVISIBLE);
//        s_dot2.setVisibility(View.INVISIBLE);


    }

    private void inData()
    {
        Intent intent = getIntent();

        String type = intent.getStringExtra("type");
        if (type.equals("login")) {
            setTabselection(0);
        }else if (type.equals("create")){
            setTabselection(2);

        }else if (type.equals("exit")){
            setTabselection(1);

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        initView();
        fragmentManager = getSupportFragmentManager();
        //设置默认为第一个
        inData();
    }

    private void setTabselection(int index) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                if (gymkhanaFragment == null) {
                    gymkhanaFragment = new GymkhanaFragment();
                    transaction.add(R.id.main_frame, gymkhanaFragment, "queryActivity");//homepage
                }
                transaction.show(gymkhanaFragment);
                butA.setChecked(true);
                break;
            case 1:
//                if(a.length==0) {

//                    if (clubFragement == null) {
//
//                        //在这里进行替换
//                        clubFragement = new ClubFragement();
//                        transaction.add(R.id.main_frame, clubFragement, "personpage");
//
//                    }
//                    transaction.show(clubFragement);

                if (clubFragements == null) {

                    //在这里进行替换
                    clubFragements = new ClubFragements();
                    transaction.add(R.id.main_frame, clubFragements, "personpage");

                }
                transaction.show(clubFragements);
//                }else{
//                        main_frame.setVisibility(View.GONE);
//                        mPageVp.setVisibility(View.VISIBLE);
//                    if(a.length==1)
//                    {
//                        dot0.setVisibility(View.VISIBLE);
//                    }else if (a.length==2){
//                        dot0.setVisibility(View.VISIBLE);
//                        dot1.setVisibility(View.VISIBLE);
//
//                    }else if(a.length==3){
//                        dot0.setVisibility(View.VISIBLE);
//                        dot1.setVisibility(View.VISIBLE);
//                        dot2.setVisibility(View.VISIBLE);
//                    }
//                    else if (a.length>3)
//                    {
//                        dot0.setVisibility(View.VISIBLE);
//                        dot1.setVisibility(View.VISIBLE);
//                        dot2.setVisibility(View.VISIBLE);
//                        s_dot3.setVisibility(View.VISIBLE);
//                        s_dot4.setVisibility(View.VISIBLE);
//                        s_dot5.setVisibility(View.VISIBLE);
//
//                    }
//
//
//                }
                butB.setChecked(true);
                break;
            case 2:


                if (meFragement == null) {
                    meFragement = new MeFragement();
                    transaction.add(R.id.main_frame, meFragement, "managerpage");
                }
                transaction.show(meFragement);
                butC.setChecked(true);
                break;

            default:
                break;

        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (gymkhanaFragment != null)
            transaction.hide(gymkhanaFragment);
//        if (clubFragement != null)
//            transaction.hide(clubFragement);
        if (clubFragements != null)
            transaction.hide(clubFragements);
        if (meFragement != null)
            transaction.hide(meFragement);
    }

    //实现对控件的绑定
    protected void initView() {

        //main_frame=(FrameLayout)findViewById(R.id.main_frame) ;
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_tab);
        radioGroup.setOnCheckedChangeListener(listener);
        butA = (RadioButton) findViewById(R.id.butA);
        butB = (RadioButton) findViewById(R.id.butB);
        butC = (RadioButton) findViewById(R.id.butC);
//        mBlurredImageHeader = (ScrollableImageView) findViewById(R.id.blurred_image_header);

//        dot0=(View)findViewById(R.id.v_dot0);
//        dot1=(View)findViewById(R.id.v_dot1);
//        dot2=(View)findViewById(R.id.v_dot2);
//
//        s_dot0=(View)findViewById(R.id.s_dot0);
//        s_dot1=(View)findViewById(R.id.s_dot1);
//        s_dot2=(View)findViewById(R.id.s_dot2);
//        s_dot3=(View)findViewById(R.id.s_dot3);
//        s_dot4=(View)findViewById(R.id.s_dot4);
//        s_dot5=(View)findViewById(R.id.s_dot5);


//        mTabLineIv = (ImageView) this.findViewById(R.id.id_tab_line_iv);
//
//        mPageVp = (ViewPager) this.findViewById(R.id.id_page_vp);
//        for(int i=0;i<a.length;i++) {
//            clubFragementFrag = new ClubFragementFrag();
//            clubFragementFrag.setClub(a[i]);
//            mFragmentList.add(clubFragementFrag);
//
//        }
//
//
//
//        mFragmentAdapter = new FragmentAdapter(
//                this.getSupportFragmentManager(), mFragmentList);
//        mPageVp.setAdapter(mFragmentAdapter);
//        mPageVp.setCurrentItem(0);
//        mPageVp.setAdapter(mFragmentAdapter);
//        mPageVp.setCurrentItem(0);
//
//        mPageVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            /**
//             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
//             */
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//
//            void Showdot(int currentIndex,int position)
//            {
//                if (currentIndex == 0 && position == 0)// 0->1
//                {
//
//                }
//            }
//
//            int i=0;
//
//            /**
//             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
//             * offsetPixels:当前页面偏移的像素位置
//             */
//            @Override
//            public void onPageScrolled(int position, float offset,
//                                       int offsetPixels) {
//                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
//                        .getLayoutParams();
//
//
//                if (currentIndex == 0 && position == 0)// 0->1
//                {
//                    dot0.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
//                    dot1.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
//                    i=1;
//
//                } else if (currentIndex == 1 && position == 0) // 1->0
//                {
//                    i=2;
//                    dot0.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
//                    dot1.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
//
//                } else if (currentIndex == 1 && position == 1) // 1->2
//                {
//                    i=3;
//                    dot1.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
//                    dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
//
//                } else if (currentIndex == 2 && position == 1) // 2->1
//                {
//                    dot1.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
//                    dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
//                }else if (currentIndex == 2 && position == 2) // 2->1
//                {
//                    dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
//                    s_dot0.setVisibility(View.INVISIBLE);
//                    s_dot1.setVisibility(View.INVISIBLE);
//                    s_dot2.setVisibility(View.INVISIBLE);
//                    s_dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
//
//
//                }else if (currentIndex == 3 && position == 2) // 2->1
//                {
//                    s_dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
//                    s_dot0.setVisibility(View.VISIBLE);
//                    s_dot1.setVisibility(View.VISIBLE);
//                    s_dot2.setVisibility(View.VISIBLE);
//
//                }else if (currentIndex == 3 && position == 3) // 2->1
//                {
//                    s_dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
//
//                    s_dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
//
//                }else if (currentIndex == 4 && position == 3) // 2->1
//                {
//                    s_dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
//                    s_dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
//
//
//                }else if (currentIndex == 4 && position == 4) // 2->1
//                {
//                    s_dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
//                    s_dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
//
//
//                }else if (currentIndex == 5 && position == 4) // 2->1
//                {
//                    s_dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
//                    s_dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
//                }else if (currentIndex == 5 && position == 5) // 2->1
//                {
//                    s_dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
//                    s_dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
//                }
//                    if(position==a.length-1){
//                        s_dot3.setVisibility(View.INVISIBLE);
//                        s_dot4.setVisibility(View.INVISIBLE);
//                        s_dot5.setVisibility(View.INVISIBLE);
//                    }else if(currentIndex == a.length-1 && position == a.length-2){
//                        s_dot3.setVisibility(View.VISIBLE);
//                        s_dot4.setVisibility(View.VISIBLE);
//                        s_dot5.setVisibility(View.VISIBLE);
//                    }
//                mTabLineIv.setLayoutParams(lp);
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                // resetTextView();
//                switch (position) {
//                    case 0:
//                        // mTabChatTv.setTextColor(Color.BLUE);
//
//                        break;
//                    case 1:
//                        //  mTabFriendTv.setTextColor(Color.BLUE);
//                        break;
//                    case 2:
//                        //   mTabContactsTv.setTextColor(Color.BLUE);
//                        break;
//                }
//                currentIndex = position;
//            }
//        });
    }

}
