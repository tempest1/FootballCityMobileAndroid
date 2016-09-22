package com.footballcitymobileandroid.Controller.Club;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
import com.footballcitymobileandroid.BLL.Util.CustomView.LoadingDialog;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.SportDetail;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/30.
 */
public class NormalMatchWriteEnd extends FragmentActivity implements View.OnClickListener ,ActionCallBackListener<BaseEntity<Void>>{
    private ViewPager mPageVp;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
    private ImageView mTabLineIv;
    private int currentIndex;
    private int screenWidth;


    private NormalMatchMeClub normalMatchMeClub;
    private NormalMatchYourClub normalMatchYourClub;

    ClubList clubList;
    SportDetail sportDetail;     //比赛内容

    private Button detail_title,detail_back;
    private TextView detail_title_center;

    private LoadingDialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_match_write_end);
        init();
    }
    private void init()
    {
        loadingDialog = new LoadingDialog(this,R.drawable.loading);
        sportDetail= (SportDetail) getIntent().getSerializableExtra("SportDetail");
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);
        detail_title=(Button)findViewById(R.id.detail_title);
        detail_title.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("录入比赛成绩");

        mTabLineIv = (ImageView) this.findViewById(R.id.id_tab_line_iv);

        mPageVp = (ViewPager) this.findViewById(R.id.id_page_vp);
        normalMatchMeClub=new NormalMatchMeClub(clubList);
        normalMatchYourClub=new NormalMatchYourClub(sportDetail);


        mFragmentList.add(normalMatchMeClub);
        mFragmentList.add(normalMatchYourClub);



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

//                Log.e("offset:", offset + "");
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
                doit();
                loadingDialog.show();
                //结束activity

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

    private void doit(){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_editScore(clubList.getClubID(),sportDetail.getS_record_id(),SportDetail.homescore,SportDetail.visitingscore, Params.fc_editScore,this);
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
        this.finish();
        loadingDialog.dismiss();
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
        loadingDialog.dismiss();
    }
}
