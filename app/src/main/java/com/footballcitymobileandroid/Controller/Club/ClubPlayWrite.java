package com.footballcitymobileandroid.Controller.Club;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.FragmentAdapter;
import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.join.AddSport;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/24.
 */
public class ClubPlayWrite extends FragmentActivity implements View.OnClickListener,ActionCallBackListener<BaseEntity<Void>> {

    ClubList clubList;
    private ViewPager mPageVp;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
    private ImageView mTabLineIv;

    private FragmentManager fragmentManager;


    private ClubPlayWritePlace clubPlayWritePlace;   //场地
    private ClubPlayWriteEndTime clubPlayWriteMe;
    private ClubPlayWriteStartTime clubPlayWriteTime;
    private ClubPlayWriteWays clubPlayWriteWays;        //人数
    private ClubPlayWriteYours clubPlayWriteYours;     //客队
    private ClubPlayWriteType clubPlayWriteType;        //状态 1. 未开始 2. 已开始 3. 已结束

    private Button sure,detail_title,detail_back;

    private TextView detail_title_center;

    private View dot0,dot1,dot2,dot3,dot4,dot5,dot6;

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
        setContentView(R.layout.club_play_write);
        fragmentManager = getSupportFragmentManager();


        init();
    }
    private void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        mTabLineIv = (ImageView) this.findViewById(R.id.id_tab_line_iv);

        mPageVp = (ViewPager) this.findViewById(R.id.id_page_vp);
        clubPlayWritePlace=new ClubPlayWritePlace();
        clubPlayWriteMe=new ClubPlayWriteEndTime();
        clubPlayWriteTime=new ClubPlayWriteStartTime();
        clubPlayWriteWays=new ClubPlayWriteWays();
        clubPlayWriteYours=new ClubPlayWriteYours();
        clubPlayWriteType=new  ClubPlayWriteType();

        dot0=(View)findViewById(R.id.v_dot0);
        dot1=(View)findViewById(R.id.v_dot1);
        dot2=(View)findViewById(R.id.v_dot2);
        dot3=(View)findViewById(R.id.v_dot3);
        dot4=(View)findViewById(R.id.v_dot4);
        dot5=(View)findViewById(R.id.v_dot5);


        mFragmentList.add(clubPlayWriteTime);
        mFragmentList.add(clubPlayWriteMe);
        mFragmentList.add(clubPlayWriteYours);
        mFragmentList.add(clubPlayWriteWays);
        mFragmentList.add(clubPlayWritePlace);
        mFragmentList.add(clubPlayWriteType);


        mFragmentAdapter = new FragmentAdapter(
                this.getSupportFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);

        sure=(Button)findViewById(R.id.sure);
        sure.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("添加比赛信息");

        detail_title=(Button)findViewById(R.id.detail_title) ;
        detail_title.setOnClickListener(this);

        detail_back=(Button)findViewById(R.id.detail_back) ;
        detail_back.setOnClickListener(this);


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

//                Log.e("offset:", offset + "");
                /**
                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
                 * 设置mTabLineIv的左边距 滑动场景：
                 * 记3个页面,
                 * 从左到右分别为0,1,2
                 * 0->1; 1->2; 2->1; 1->0
                 */
//                LogUtils.e("currentIndex"+currentIndex);

//                LogUtils.e("position"+position);

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
                }else if (currentIndex == 3 && position == 3) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                    dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                    dot4.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
                }else if (currentIndex == 4 && position == 3) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                    dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
                    dot4.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                }else if (currentIndex == 4 && position == 4) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                    dot4.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                    dot5.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
                }else if (currentIndex == 5 && position == 4) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                    dot4.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
                    dot5.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                }else if (currentIndex == 5 && position == 5) // 2->1
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
            case R.id.sure:

                finish();
                break;
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
            case R.id.detail_title:
                if(AddSport.visitingTeam.equals("选择对方俱乐部")){
                    Toast toast = Toast.makeText(this, "请选择对方俱乐部", Toast.LENGTH_LONG);
                    toast.show();
                }else if(AddSport.sportField.equals("选择体育场")){
                    Toast toast = Toast.makeText(this, "请选择体育场", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    doit();
                }
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

    private void doit(){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_addClubSport(clubList.getClubID(), AddSport.startTime,AddSport.endTime,AddSport.visitingTeam,AddSport.joinNum,AddSport.sportField,AddSport.sportState, Params.fc_addClubSport,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Void> data) {
        Toast toast = Toast.makeText(this, "添加成功", Toast.LENGTH_LONG);
        toast.show();
        finish();
    }

    /**
     * 请求失败
     *
     * @param e_Type 错误码
     * @param e_Msg  错误详情
     */
    @Override
    public void onFailure(String e_Type, String e_Msg) {
        Toast toast = Toast.makeText(this, e_Msg, Toast.LENGTH_LONG);
        toast.show();
    }
}
