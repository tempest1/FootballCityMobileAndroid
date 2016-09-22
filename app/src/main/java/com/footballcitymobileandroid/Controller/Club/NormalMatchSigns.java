package com.footballcitymobileandroid.Controller.Club;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.FragmentAdapter;
import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.RefuseAdapter;
import com.footballcitymobileandroid.BLL.Util.CustomView.BaseDialog;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.MatchMemb;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.SportDetail;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/6/23.
 */
public class NormalMatchSigns extends FragmentActivity implements View.OnClickListener {

    SportDetail sportDetail;     //比赛内容
    ClubList clubList;

    List<MatchMemb> matchMemb;  //查看签到人数是否为空
//    List<ClubMemb> clubMemb;

    private ViewPager mPageVp;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
    private ImageView mTabLineIv;

    private FragmentManager fragmentManager;
    List<String> data=new ArrayList<>();

    private BaseDialog mBackDialog;

    private NormalMatchSignReStartFragment normalMatchSignFragment;     //签到

    private NormalMatchSignReStartInfoFragment normalMatchSignReStartInfoFragment;   //详情


    private Button sure,detail_title,detail_back;

    private TextView detail_title_center,look_place,set_place,backmatch;

    private View dot0,dot1,dot2,dot3,dot4,dot5;

    private RelativeLayout list;
    RefuseAdapter adapter;

    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;

    /**
     * 屏幕的宽度
     */
    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_match_signs);
        fragmentManager = getSupportFragmentManager();


        init();
    }

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
//        }else if(index==1)
//        {
//            Intent intent = new Intent();
//            intent.setClass(this, NormalMatchReStartSet.class);
//            list.setVisibility(View.GONE);
//            startActivity(intent);
//            overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
//        }else if(index==2)
//        {
//            if (MainApplication.PK_NUMBER_SET.size()!=0) {
//                mBackDialog.show();
//            }else{
//                goNormalMatchSignNumberList();
//            }
//        }
//    }

    private void goNormalMatchSignNumberList()
    {
        Intent intent = new Intent();
        intent.setClass(this, NormalMatchSignNumberList.class);
        list.setVisibility(View.GONE);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
    }

    private void initBackDialog() {
        mBackDialog = BaseDialog.getDialog(NormalMatchSigns.this, "提示",
                "确认要重新部署了么?", "确认", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goNormalMatchSignNumberList();
                        MainApplication.PK_NUMBER=null;

                        for (int i=0;i<MainApplication.getTop.length;i++) {
                            MainApplication.getTop[i] = 0;
                            MainApplication.getLeft[i] =0;
                        }
                        dialog.dismiss();
                        finish();
                    }
                }, "取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.setVisibility(View.GONE);
                        dialog.cancel();
                    }
                });
        mBackDialog.setButton1Background(R.drawable.btn_default_popsubmit);

    }

    private void init()
    {
        sportDetail= (SportDetail) getIntent().getSerializableExtra("SportDetail");
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        matchMemb= (List<MatchMemb>) getIntent().getSerializableExtra("MatchMemb");
//        clubMemb= (List<ClubMemb>) getIntent().getSerializableExtra("clubMemb");
        mTabLineIv = (ImageView) this.findViewById(R.id.id_tab_line_iv);

        mPageVp = (ViewPager) this.findViewById(R.id.id_page_vp);
        normalMatchSignFragment=new NormalMatchSignReStartFragment(clubList,sportDetail);
        normalMatchSignReStartInfoFragment=new NormalMatchSignReStartInfoFragment(clubList,sportDetail);

        dot0=(View)findViewById(R.id.v_dot0);
        dot1=(View)findViewById(R.id.v_dot1);
        dot2=(View)findViewById(R.id.v_dot2);
        dot3=(View)findViewById(R.id.v_dot3);
        dot4=(View)findViewById(R.id.v_dot4);
        dot5=(View)findViewById(R.id.v_dot5);

        dot2.setVisibility(View.GONE);
        dot3.setVisibility(View.GONE);
        dot4.setVisibility(View.GONE);
        dot5.setVisibility(View.GONE);
        mFragmentList.add(normalMatchSignFragment);
        mFragmentList.add(normalMatchSignReStartInfoFragment);


        mFragmentAdapter = new FragmentAdapter(
                this.getSupportFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);

        sure=(Button)findViewById(R.id.sure);
        sure.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("待开始比赛");

        detail_title=(Button)findViewById(R.id.detail_title) ;
        detail_title.setText("");
        detail_title.setOnClickListener(this);

        detail_back=(Button)findViewById(R.id.detail_back) ;
        detail_back.setOnClickListener(this);

        look_place=(TextView) findViewById(R.id.look_place) ;
        look_place.setOnClickListener(this);

        set_place=(TextView)findViewById(R.id.set_place) ;
        set_place.setOnClickListener(this);
        set_place.setVisibility(View.GONE);

        backmatch=(TextView)findViewById(R.id.backmatch) ;
        backmatch.setOnClickListener(this);
        backmatch.setVisibility(View.GONE);

        list=(RelativeLayout)findViewById(R.id.list);

        initBackDialog();

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
                LogUtils.e("currentIndex"+currentIndex);

                LogUtils.e("position"+position);

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
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                    dot1.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
                    dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                }else if (currentIndex == 2 && position == 2) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                    dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                    dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));

                }else if (currentIndex == 3 && position == 2) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                    dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
                    dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                }else if (currentIndex == 3 && position == 2) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                    dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
                    dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                }else if (currentIndex == 3 && position == 3) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                    dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
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
            case R.id.sure:

                finish();
                break;
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
            case R.id.detail_title:
                if (list.getVisibility()==View.GONE) {
                    list.setVisibility(View.VISIBLE);
                }else {
                    list.setVisibility(View.GONE);
                }
                break;
            case R.id.look_place:
                Intent intent = new Intent();
                intent.setClass(this, NormalMatchReStartSet.class);
                list.setVisibility(View.GONE);
                Bundle bundle=new Bundle();
                bundle.putSerializable("clublist",clubList);
                bundle.putSerializable("SportDetail", (Serializable) sportDetail);
                bundle.putSerializable("MatchMemb", (Serializable) matchMemb);
                LogUtils.e(""+matchMemb.get(0).getName());
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.set_place:

                if (MainApplication.PK_NUMBER_SET.size()!=0) {
                    mBackDialog.show();
                }else{
                    goNormalMatchSignNumberList();
                }

                break;
            case R.id.backmatch:
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
