package com.footballcitymobileandroid.Controller.Me;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.FragmentAdapter;
import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.AppActionImpl;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.CustomView.ActivityCollector;
import com.footballcitymobileandroid.BLL.Util.CustomView.LoadingDialog;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.Controller.Base.BaseActivity;
import com.footballcitymobileandroid.Controller.Base.MainActivity;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Club;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/20.
 */
public class MeCreateClub extends BaseActivity implements View.OnClickListener,ActionCallBackListener<BaseEntity<Club>> {
     ViewPager mPageVp;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
     FragmentAdapter mFragmentAdapter;
    private ImageView mTabLineIv;
    private int currentIndex;
    private int screenWidth;
    private LoadingDialog loadingDialog;


    MeSetClubDay meSetClubDay;
    MeSetClubGreat meSetClubGreat;
    MeSetClubImage meSetClubImage;
    MeSetClubName meSetClubName;
    MeSetClubPerson meSetClubPerson;
    MeSetClubPlace meSetClubPlace;

    Button detail_title,detail_back;
    TextView detail_title_center;

    private View dot0,dot1,dot2,dot3,dot4,dot5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_create_club);
        init();
    }
    private void init()
    {
        loadingDialog = new LoadingDialog(this,R.drawable.loading);

        dot0=(View)findViewById(R.id.v_dot0);
        dot1=(View)findViewById(R.id.v_dot1);
        dot2=(View)findViewById(R.id.v_dot2);
        dot3=(View)findViewById(R.id.v_dot3);
        dot4=(View)findViewById(R.id.v_dot4);
        dot5=(View)findViewById(R.id.v_dot5);


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

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("创建俱乐部");

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
        Club club=new Club();
        MainApplication.setClub(club);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
                R.mipmap.ic_launcher);
        String a=bytewithString.bytesToHexString( bitmapwithbyte.Bitmap2Bytes(bitmap));
        MainApplication.getClub().setLogo(a);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.detail_title:
                // 处理数据

                try {
                    doit();
                }catch (Exception e){
                    Toast.makeText(this,"请完善俱乐部信息",Toast.LENGTH_LONG).show();
                }
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
        loadingDialog.show();
        AppAction appAction=new AppActionImpl(this);
        appAction.fc_createClub(MainApplication.getClub().getLogo(),MainApplication.getClub().getClubName()
                ,MainApplication.getClub().getCityID(),MainApplication.getClub().getCreateTime()
                ,MainApplication.getClub().getAtyField(),MainApplication.getClub().getAtyTimes()
                ,MainApplication.getClub().getClubWalfare(), Params.fc_createClub,this);




        LogUtils.e("");
//        Log.e("clubdatas",""+MainApplication.getClub().getCreateTime()
//                +MainApplication.getClub().getAtyField()+MainApplication.getClub().getAtyTimes()[0]
//                +MainApplication.getClub().getClubWalfare());
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Club> data) {
        Toast toast=Toast.makeText(this,"创建成功",Toast.LENGTH_LONG);
        toast.show();

        do_queryclublist();
      //  this.finish();



    }

    @Override
    public void onFailure(String e_Type, String e_Msg) {

        Toast toast=Toast.makeText(this,e_Msg,Toast.LENGTH_LONG);
        toast.show();
        loadingDialog.dismiss();

    }

    private void do_queryclublist() {
        AppAction appAction = Factory.createAppActionImpl(getApplicationContext());
        appAction.fc_getClubList(Params.fc_getClubList, clublistener);//"leader",
    }

    public ActionCallBackListener<BaseEntity<ClubList>> clublistener=new ActionCallBackListener<BaseEntity<ClubList>>(){

        /**
         * 处理成功
         *
         * @param data 返回数据
         */
        @Override
        public void onSuccess(BaseEntity<ClubList> data) {
            MainApplication.setClubList((List<com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList>) data.getResponse().getClubList());                      //////////////
            Toast toast = Toast.makeText(getApplicationContext(), "俱乐部获取成功", Toast.LENGTH_LONG);
            toast.show();

            for (int i=0;i<MainApplication.getClubList().size();i++) {
                LogUtils.e(MainApplication.getClubList().get(i).toString());
            }
            goMain();
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
            Toast toast = Toast.makeText(getApplicationContext(), "俱乐部"+e_Msg, Toast.LENGTH_LONG);
            toast.show();
            loadingDialog.dismiss();
        }
    };

    private void goMain()
    {
        Intent intent=new Intent();
        intent.setClass(this, MainActivity.class);
        intent.putExtra("type", "create");
        startActivity(intent);
        ActivityCollector.finishAllNew();

    }

}
