package com.footballcitymobileandroid.Controller.Base;

//package com.footballcitymobileandroid.Controller.Base;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v4.view.ViewPager;
//import android.util.DisplayMetrics;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.FragmentAdapter;
//import com.footballcitymobileandroid.Controller.Club.ClubFragement;
//import com.footballcitymobileandroid.Controller.Gymkhana.GymClubFragment;
//import com.footballcitymobileandroid.Controller.Gymkhana.GymkhanaFragment;
//import com.footballcitymobileandroid.Controller.Gymkhana.PkFragment;
//import com.footballcitymobileandroid.Controller.Gymkhana.PlayerFragment;
//import com.footballcitymobileandroid.Controller.Me.MeFragement;
//import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
//import com.footballcitymobileandroid.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by zhoudi on 16/5/16.
// * 主界面
// */
public class MainContent extends BaseActivity {
//    private ViewPager mPageVp;
//
//    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
//    private FragmentAdapter mFragmentAdapter;
//
//
//    private RadioButton radioHome;
//    private RadioButton radioInfo;
//    private RadioButton radioCommuication;
//
//    private Button detail_title;
//
//    private  FrameLayout main_frame;
//    /**
//     * Tab的那个引导线
//     */
//    private ImageView mTabLineIv;
//    /**
//     * Fragment
//     */
//    private PkFragment pkFragment;
//    private GymClubFragment mFriendFg;
//    private PlayerFragment mContactsFg;
//
//    private GymkhanaFragment gymkhanaFragment;
//    private MeFragement meFragement;
//    private ClubFragement clubFragmens;
//
//
//    RelativeLayout club_title;
//    /**
//     * ViewPager的当前选中页
//     */
//    private int currentIndex;
//    /**
//     * 屏幕的宽度
//     */
//    private int screenWidth;
//
//    private TextView detail_title_center;
//
//    private FragmentManager fragmentManager;
//
//    private LinearLayout main;
//
//    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
//            mPageVp.setVisibility(View.GONE);
//            main_frame.setVisibility(View.VISIBLE);
//            club_title.setVisibility(View.GONE);
//            switch (checkId) {
//                case R.id.radio_home:
//                    club_title.setVisibility(View.VISIBLE);
//                    setTabSelection(0);
//                    main.setBackgroundDrawable(getResources().getDrawable(R.mipmap.main_gyms));
//                    break;
//                case R.id.radio_info:
//                    setTabSelection(1);
//                    main.setBackgroundDrawable(getResources().getDrawable(R.mipmap.main_club));
//                    break;
//                case R.id.radio_me:
//                    setTabSelection(2);
//                    main.setBackgroundDrawable(getResources().getDrawable(R.mipmap.main_me));
//                    break;
//
//            }
//        }
//    };
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main_content);
//
//        fragmentManager = getSupportFragmentManager();
//        init();
//        main.setBackgroundDrawable(getResources().getDrawable(R.mipmap.main_gyms));
//        setTabSelection(0);
//
//    }
//
//    private void setTabSelection(int index) {
//        // 每次选中之前先清楚掉上次的选中状态
//        String FRAGMENT_TAG_HOME = "home";
//        String FRAGMENT_TAG_INFO = "infoPage";
//        String FRAGMENT_TAG_FORUM = "forum";
//        // 开启一个Fragment事务
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
//        hideFragments(transaction);
//        switch (index) {
//            case 0:
//                if (gymkhanaFragment == null) {
//                    gymkhanaFragment = new GymkhanaFragment();
//                    transaction.add(R.id.main_frame, gymkhanaFragment, FRAGMENT_TAG_HOME);
//
//                }
//                transaction.show(gymkhanaFragment);
//                radioHome.setChecked(true);
//
//                break;
//            case 1:
//                if (clubFragmens == null) {
//                    clubFragmens = new ClubFragement();
//                    LogUtils.e("clubFragmens");
//                    transaction.add(R.id.main_frame, clubFragmens, FRAGMENT_TAG_INFO);
//
//                }
//                transaction.show(clubFragmens);
//                radioInfo.setChecked(true);
//                break;
//            case 2:
//                if (meFragement == null) {
//                    meFragement = new MeFragement();
//                    transaction.add(R.id.main_frame, meFragement, FRAGMENT_TAG_FORUM);
//
//                }
//                transaction.show(meFragement);
//                radioCommuication.setChecked(true);
//                break;
//
//            default:
//                break;
//        }
//        transaction.commit();
//    }
//    private void hideFragments(FragmentTransaction transaction) {
//        if (gymkhanaFragment != null) {
//            transaction.hide(gymkhanaFragment);
//        }
//        if (meFragement != null) {
//            transaction.hide(meFragement);
//        }
//        if (clubFragmens != null) {
//            transaction.hide(clubFragmens);
//        }
//
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId())
//        {
//            case R.id.detail_title:
//                LogUtils.e("detail_title");
//                this.findViewById(R.id.main_frame).setVisibility(View.GONE);
//                mPageVp.setVisibility(View.VISIBLE);
//                break;
//        }
//    }
//
//    private void init() {
//        main=(LinearLayout)findViewById(R.id.main);
//
//        mTabLineIv = (ImageView) this.findViewById(R.id.id_tab_line_iv);
//
//        mPageVp = (ViewPager) this.findViewById(R.id.id_page_vp);
//
//        main_frame =  (FrameLayout) this.findViewById(R.id.main_frame);
//
//        club_title=(RelativeLayout)this.findViewById(R.id.club_title);
//        mFriendFg = new GymClubFragment();
//        mContactsFg = new PlayerFragment();
//        pkFragment = new PkFragment();
//        mFragmentList.add(pkFragment);
//        mFragmentList.add(mFriendFg);
//        mFragmentList.add(mContactsFg);
//
//        RadioGroup radio_tab = (RadioGroup) findViewById(R.id.radio_tab);
//        radio_tab.setOnCheckedChangeListener(listener);
//        radioHome = (RadioButton) findViewById(R.id.radio_home);
//        radioInfo = (RadioButton) findViewById(R.id.radio_info);
//        radioCommuication = (RadioButton) findViewById(R.id.radio_me);
//
//        detail_title=(Button)this.findViewById(R.id.detail_title);
//        detail_title.setOnClickListener(this);
//        detail_title.setText("");
//
//        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
//        detail_title_center.setText("赛季");
//
//        mFragmentAdapter = new FragmentAdapter(
//                this.getSupportFragmentManager(), mFragmentList);
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
//                Log.e("offset:", offset + "");
//                /**
//                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
//                 * 设置mTabLineIv的左边距 滑动场景：
//                 * 记3个页面,
//                 * 从左到右分别为0,1,2
//                 * 0->1; 1->2; 2->1; 1->0
//                 */
//
//                if (currentIndex == 0 && position == 0)// 0->1
//                {
//                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
//                            * (screenWidth / 3));
//
//                } else if (currentIndex == 1 && position == 0) // 1->0
//                {
//                    lp.leftMargin = (int) (-(1 - offset)
//                            * (screenWidth * 1.0 / 3) + currentIndex
//                            * (screenWidth / 3));
//
//                } else if (currentIndex == 1 && position == 1) // 1->2
//                {
//                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
//                            * (screenWidth / 3));
//                } else if (currentIndex == 2 && position == 1) // 2->1
//                {
//                    lp.leftMargin = (int) (-(1 - offset)
//                            * (screenWidth * 1.0 / 3) + currentIndex
//                            * (screenWidth / 3));
//                }
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
//
//    }
//
//
}
