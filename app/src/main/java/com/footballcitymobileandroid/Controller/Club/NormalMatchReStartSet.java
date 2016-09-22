package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.footballcitymobileandroid.BLL.Util.CustomView.ActivityCollector;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.MatchMemb;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.SportDetail;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/30.
 */
public class NormalMatchReStartSet extends Activity implements View.OnClickListener{

    int screenWidth;
    int screenHeight;
    int lastX;
    int lastY;
    Button number1,number2,number3,number4,number5,number6,number7,number8,number9,number10,number11,number12,number13,number14,sure;

    SportDetail sportDetail;     //比赛内容
    ClubList clubList;
    List<MatchMemb> matchMemb;

    List<Button> number=new ArrayList<>();

    List<MatchMemb> mumber=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_match_after_play_setting_new);
        init();
    }
    private  void init()
    {
        matchMemb= (List<MatchMemb>) getIntent().getSerializableExtra("MatchMemb");
        sportDetail= (SportDetail) getIntent().getSerializableExtra("SportDetail");
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels - 50;




            number1 = (Button) findViewById(R.id.number1);
            number2 = (Button) findViewById(R.id.number2);
            number3 = (Button) findViewById(R.id.number3);
            number4 = (Button) findViewById(R.id.number4);
            number5 = (Button) findViewById(R.id.number5);
            number6 = (Button) findViewById(R.id.number6);
            number7 = (Button) findViewById(R.id.number7);
            number8 = (Button) findViewById(R.id.number8);
            number9 = (Button) findViewById(R.id.number9);
            number10 = (Button) findViewById(R.id.number10);
            number11 = (Button) findViewById(R.id.number11);
            number12 = (Button) findViewById(R.id.number12);
            number13 = (Button) findViewById(R.id.number13);
            number14 = (Button) findViewById(R.id.number14);
            sure = (Button) findViewById(R.id.sure);


            sure.setVisibility(View.INVISIBLE);

            number.add(number1);
            number.add(number2);
            number.add(number3);
            number.add(number4);
            number.add(number5);
            number.add(number6);
            number.add(number7);
            number.add(number8);
            number.add(number9);
            number.add(number10);
            number.add(number11);
            number.add(number12);
            number.add(number13);
            number.add(number14);
            if (matchMemb==null) {
                LogUtils.e("m=null");
                for (int i = 0; i < number.size(); i++) {
                    number.get(i).setVisibility(View.INVISIBLE);
                }
            }else {
                if (matchMemb.size() == 0) {
                    LogUtils.e("matchMemb=null");
                    for (int i = 0; i < number.size(); i++) {
                        number.get(i).setVisibility(View.INVISIBLE);
                    }
                } else {
                    for (int i = 0; i < matchMemb.size(); i++) {

                        LogUtils.e("matchMemb=null");
                        if (matchMemb.get(i).getCoordX() != null) {
                            if (!matchMemb.get(i).getCoordX().equals("0")) {
                                if (!matchMemb.get(i).getCoordX().equals("")) {
                                    mumber.add(matchMemb.get(i));
                                }
                            }
                        }
                    }


                    int x = 0;
                    for (int i = 0; i < mumber.size(); i++) {//获得姓名和图片
                        LogUtils.e(mumber.get(i).getName());
                        number.get(i).setText(mumber.get(i).getName());
                        x++;
                        LogUtils.e(mumber.get(i).getName());
                    }

                    LogUtils.e("" + x);
                    for (int i = 13; i > x - 1; i--) {
                        number.get(i).setVisibility(View.INVISIBLE);
                    }


                    set();
                    setPlace();

                    LogUtils.e("" + number1.getTop());
                    showPlace();
                }
            }

    }

    private void set()
    {
        setLayout(number1,0,45);
        setLayout(number2,90,45);
        setLayout(number3,180,45);
        setLayout(number4,270,45);
        setLayout(number5,360,45);
        setLayout(number6,450,45);
        setLayout(number7,540,45);
        setLayout(number8,630,45);

        setLayout(number9,0,160);
        setLayout(number10,90,160);
        setLayout(number11,180,160);
        setLayout(number12,270,160);
        setLayout(number13,360,160);
        setLayout(number14,450,160);
        int n=0;
        for (int j=0;j<MainApplication.getTop.length;j++) {
            if (MainApplication.getTop[j] == 0)
            {
                for(int i=0;i<8;i++)
                {
                    MainApplication.getTop[i]=45;
                    MainApplication.getLeft[i]=n;

                    MainApplication.getNewTop[i]=1.00*45/MainApplication.getHeight();
                    MainApplication.getNewLef[i]=1.00*n/MainApplication.getWidth();

                    n=n+90;
                }
                n=0;
                for (int i=0;i<6;i++)
                {
                    MainApplication.getTop[8+i]=160;
                    MainApplication.getLeft[8+i]=n;

                    MainApplication.getNewTop[i+8]=1.00*160/MainApplication.getHeight();
                    MainApplication.getNewLef[i+8]=1.00*n/MainApplication.getWidth();
                    n=n+90;
                }
            }

        }


    }

    private void setPlace()
    {
        double x,y;
        int z,k;
        for (int i=0;i<matchMemb.size();i++)
        {

//            x=MainApplication.getNewLef[i]*MainApplication.getWidth();
//            y=MainApplication.getNewTop[i]*MainApplication.getHeight();
//            LogUtils.e("rx="+MainApplication.getNewLef[i]);
//            LogUtils.e("ry="+MainApplication.getNewTop[i]);


            double aa = Double.valueOf(matchMemb.get(i).getCoordX());
            double bb = Double.valueOf(matchMemb.get(i).getCoordY());


//            LogUtils.e("x="+x);
//            LogUtils.e("y="+y);

            aa=aa*MainApplication.getWidth();
            bb=bb*MainApplication.getHeight();

            z=(int) aa;
            k=(int)bb;


//            z=(int)x;
//            k=(int)y;




            LogUtils.e("z="+z);
            LogUtils.e("k="+k);
            setLayout(number.get(i),z,k);
            LogUtils.e("a"+MainApplication.getLeft[i]);
        }
    }

    public static void setLayout(View view,int x,int y)
    {
        ViewGroup.MarginLayoutParams margin=new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(x,y, x+margin.width, y+margin.height);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();

         overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }


    /*
    * 查看位置
     */

    private void showPlace()
    {
        for (int i=0;i<MainApplication.getNewTop.length;i++)
        {
            LogUtils.e(""+MainApplication.getNewTop[i]);
            LogUtils.e(""+MainApplication.getNewLef[i]);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.sure:
                /*
                   *对话框
                 */
                ActivityCollector.finishAll();

                break;
        }
    }
}
