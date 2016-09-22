package com.footballcitymobileandroid.Controller.Club;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.FragmentAdapter;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/6/25.
 */
public class  ClubFragements extends Fragment implements View.OnClickListener{

    List<ClubList> clubLists ;
    private View view;

    private TextView club;

    private String clubText;

    private ClubFragementNull clubFragementNull;
    String[] a;
//    String[] b;
    ClubList[] clubList;
    public void setname() {

        if (clubLists==null)
        {
            a=new String[0];
            clubList = new ClubList[0];
        }else {
            a = new String[clubLists.size()];

            Log.i("size", String.valueOf(clubLists.size()));
//        b=new String [clubLists.size()];
            clubList = new ClubList[clubLists.size()];
            for (int i = 0; i < clubLists.size(); i++) {
//            a[i]= clubLists.get(i).getClubName();
//            b[i]=clubLists.get(i).getLogo();
                clubList[i] = clubLists.get(i);
            }
        }
    }

//    String [] a=new String[]{"俱乐部1","俱乐部2","俱乐部3","俱乐部4","俱乐部5"};
    ClubFragementFrag clubFragementFrag;

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private ViewPager mPageVp;
    private FragmentAdapter mFragmentAdapter;
    private ImageView mTabLineIv;
    private int currentIndex;
    private int screenWidth;
    View dot0,dot1,dot2,dot3,dot4,s_dot0,s_dot1,s_dot2,s_dot3,s_dot4,s_dot5;


    public void setClub(String clubText)
    {
        this.clubText=clubText;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        LogUtils.e("onCreateView");
        view = inflater.inflate(R.layout.club_fragments, container,false);
//        setname();
//        init();
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        clubLists = MainApplication.getClubList();
        mFragmentList.removeAll(mFragmentList);
        setname();
        init();
    }

    private  void init()
    {
        club=(TextView)view.findViewById(R.id.club);
//        club.setText(clubText);
        mTabLineIv = (ImageView) view.findViewById(R.id.id_tab_line_iv);

        mPageVp = (ViewPager) view.findViewById(R.id.id_page_vp);

        if(a.length==0)
        {
            clubFragementNull=new ClubFragementNull();

            mFragmentList.add(clubFragementNull);

        }
        else
        {
            LogUtils.e(""+a.length);
            for (int i = 0; i < a.length; i++) {
                clubFragementFrag = new ClubFragementFrag();
                clubFragementFrag.setClub(clubList[i]);
                mFragmentList.add(clubFragementFrag);

            }
        }

        dot0=(View)view.findViewById(R.id.v_dot0);
        dot1=(View)view.findViewById(R.id.v_dot1);
        dot2=(View)view.findViewById(R.id.v_dot2);
//        dot3=(View)view.findViewById(R.id.v_dot3);
//        dot4=(View)view.findViewById(R.id.v_dot4);

        s_dot0=(View)view.findViewById(R.id.s_dot0);
//        s_dot1=(View)view.findViewById(R.id.s_dot1);
//        s_dot2=(View)view.findViewById(R.id.s_dot2);
        s_dot3=(View)view.findViewById(R.id.s_dot3);
//        s_dot4=(View)view.findViewById(R.id.s_dot4);
//        s_dot5=(View)view.findViewById(R.id.s_dot5);

        if (a.length==0)
        {

        }
        if(a.length==1)
        {

        }else if (a.length==2){
            dot0.setVisibility(View.VISIBLE);
            dot1.setVisibility(View.VISIBLE);

        }else if(a.length==3){
            dot0.setVisibility(View.VISIBLE);
            dot1.setVisibility(View.VISIBLE);
            dot2.setVisibility(View.VISIBLE);
        }
        else if(a.length>3)
        {
            dot0.setVisibility(View.VISIBLE);
            dot1.setVisibility(View.VISIBLE);
            dot2.setVisibility(View.VISIBLE);
//            dot3.setVisibility(View.VISIBLE);
            s_dot3.setVisibility(View.VISIBLE);
//            s_dot4.setVisibility(View.VISIBLE);
//            s_dot5.setVisibility(View.VISIBLE);

        }
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
//                    dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));

                    s_dot0.setVisibility(View.INVISIBLE);
//                    s_dot1.setVisibility(View.INVISIBLE);
//                    s_dot2.setVisibility(View.INVISIBLE);
                    s_dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));


                }else if (currentIndex == 3 && position == 2) // 2->1
                {
                    dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
 //                   dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
//                    s_dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                    s_dot0.setVisibility(View.VISIBLE);
//                    s_dot1.setVisibility(View.VISIBLE);
//                    s_dot2.setVisibility(View.VISIBLE);

                }else if (currentIndex == 3 && position == 3) // 2->1
                {
                    dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));

                    //                   dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
//                    dot4.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
                    s_dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
//                    s_dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));

                }else if (currentIndex == 4 && position == 3) // 2->1
                {
                    dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
//                    dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
 //                   dot4.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                    s_dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
//                    s_dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));


                }else if (currentIndex == 4 && position == 4) // 2->1
                {
                    dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                    s_dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
//                    s_dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));


                }else if (currentIndex == 5 && position == 4) // 2->1
                {
                    dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
                    s_dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
//                    s_dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                }else if (currentIndex == 5 && position == 5) // 2->1
                {
                    dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
                    s_dot3.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
//                    s_dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_normal));
                }
                if (a.length>3) {
                    if (position == a.length - 1) {
                        s_dot3.setVisibility(View.INVISIBLE);
//                        s_dot4.setVisibility(View.INVISIBLE);
//                        s_dot5.setVisibility(View.INVISIBLE);
                    } else if (currentIndex == a.length - 1 && position == a.length - 2) {
                        s_dot3.setVisibility(View.VISIBLE);
//                        s_dot4.setVisibility(View.VISIBLE);
//                        s_dot5.setVisibility(View.VISIBLE);
                    }
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

    }
}
