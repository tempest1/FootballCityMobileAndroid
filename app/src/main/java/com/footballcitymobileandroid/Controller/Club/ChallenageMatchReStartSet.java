package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.ActivityCollector;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena2.AranaMatchMembs;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/30.
 *
 * 查看
 */
public class ChallenageMatchReStartSet extends Activity implements View.OnClickListener{

    int screenWidth;
    int screenHeight;
    int lastX;
    int lastY;
    Button number1,number2,number3,number4,number5,number6,number7,number8,number9,number10,number11,number12,number13,number14,sure;

    ClubList clubList;
    AranaMatchs aranaMatchses;
    //    List<MatchMemb> matchMemb;  //查看签到人数是否为空
//    List<ClubMemb> clubMemb;
    List<AranaMatchMembs> aranaMatchMembs;

    List<Button> number=new ArrayList<>();
    TextView textView21;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_match_after_play_setting_new);
        init();
    }
    private  void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        aranaMatchses= (AranaMatchs) getIntent().getSerializableExtra("AranaMatchs");
        aranaMatchMembs= (List<AranaMatchMembs>) getIntent().getSerializableExtra("AranaMatchMembs");   //部署数据来源
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels - 50;

        textView21=(TextView)findViewById(R.id.textView21);
        textView21.setVisibility(View.INVISIBLE);


        number1=(Button)findViewById(R.id.number1);
        number2=(Button)findViewById(R.id.number2);
        number3=(Button)findViewById(R.id.number3);
        number4=(Button)findViewById(R.id.number4);
        number5=(Button)findViewById(R.id.number5);
        number6=(Button)findViewById(R.id.number6);
        number7=(Button)findViewById(R.id.number7);
        number8=(Button)findViewById(R.id.number8);
        number9=(Button)findViewById(R.id.number9);
        number10=(Button)findViewById(R.id.number10);
        number11=(Button)findViewById(R.id.number11);
        number12=(Button)findViewById(R.id.number12);
        number13=(Button)findViewById(R.id.number13);
        number14=(Button)findViewById(R.id.number14);
        sure=(Button)findViewById(R.id.sure);


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


        if (aranaMatchMembs!=null)
        {
            int x=0;
            for (int i=0;i<aranaMatchMembs.size();i++) {//获得姓名和图片
                number.get(i).setText(aranaMatchMembs.get(i).getPlayerName());
                x++;
                LogUtils.e("aranaMatchMembs="+aranaMatchMembs.get(i).getPlayerName());
            }

            LogUtils.e(""+x);
            for (int i=13;i>x-1;i--)
            {
                number.get(i).setVisibility(View.INVISIBLE);
            }
            set();
            setPlace();

            LogUtils.e(""+number1.getTop());
            showPlace();

        }else {
            for (int i=13;i>=0;i--)
            {
                number.get(i).setVisibility(View.INVISIBLE);
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
        for (int i=0;i<aranaMatchMembs.size();i++)
        {


            double xx= Double.parseDouble(aranaMatchMembs.get(i).getCoord_X());
            double yy= Double.parseDouble(aranaMatchMembs.get(i).getCoord_Y());


            x=xx*MainApplication.getWidth();
            y=yy*MainApplication.getHeight();
//            LogUtils.e("rx="+MainApplication.getNewLef[i]);
//            LogUtils.e("ry="+MainApplication.getNewTop[i]);


//            LogUtils.e("x="+x);
//            LogUtils.e("y="+y);

            z=(int)x;
            k=(int)y;

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

        }
    }
}
