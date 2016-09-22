package com.footballcitymobileandroid.Controller.Me;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/23.
 */
public class MeSetClubDay extends Fragment implements View.OnClickListener{
    private View view,dot;
    private TextView week1,week2,week3,week4,week5,week6,week7;
    int[] weekss= new int[]{0,0,0,0,0,0,0};
    int [] atytime=new int[]{0,0,0,0,0,0,0};
    String week;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.me_set_club_day, container,false);
        init();
        return view;

    }
    private  void init()
    {
        dot=view.findViewById(R.id.v_dot3);
        dot.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
        week1=(TextView)view.findViewById(R.id.week1);
        week2=(TextView)view.findViewById(R.id.week2);
        week3=(TextView)view.findViewById(R.id.week3);
        week4=(TextView)view.findViewById(R.id.week4);
        week5=(TextView)view.findViewById(R.id.week5);
        week6=(TextView)view.findViewById(R.id.week6);
        week7=(TextView)view.findViewById(R.id.week7);
        week1.setOnClickListener(this);
        week2.setOnClickListener(this);
        week3.setOnClickListener(this);
        week4.setOnClickListener(this);
        week5.setOnClickListener(this);
        week6.setOnClickListener(this);
        week7.setOnClickListener(this);
//        if(MainApplication.getClub().getAtyTime()!=null){
//           if(MainApplication.getClub().getAtyTime()[0]!=0){
//                week1.setTextColor(Color.RED);
//            }
//            if(MainApplication.getClub().getAtyTime()[1]!=0){
//                week2.setTextColor(Color.RED);
//            }
//            if(MainApplication.getClub().getAtyTime()[2]!=0){
//                week3.setTextColor(Color.RED);
//            }
//            if(MainApplication.getClub().getAtyTime()[3]!=0){
//                week4.setTextColor(Color.RED);
//            }
//            if(MainApplication.getClub().getAtyTime()[4]!=0){
//                week5.setTextColor(Color.RED);
//            }
//            if(MainApplication.getClub().getAtyTime()[5]!=0){
//                week6.setTextColor(Color.RED);
//            }
//            if(MainApplication.getClub().getAtyTime()[6]!=0){
//                week7.setTextColor(Color.RED);
//            }
//        }
//        MainApplication.getClub().setAtyTime(atytime);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.week1:
                setColor(week1,0);
                break;
            case R.id.week2:
                setColor(week2,1);
                break;
            case R.id.week3:
                setColor(week3,2);

                break;
            case R.id.week4:
                setColor(week4,3);

                break;
            case R.id.week5:
                setColor(week5,4);

                break;

            case R.id.week6:
                setColor(week6,5);

                break;
            case R.id.week7:
                setColor(week7,6);

                break;
        }
    }

    private void setColor(TextView weeks,int x)
    {
            if (weekss[x]==0) {
                weekss[x]=1;
                weeks.setTextColor(Color.RED);
                atytime[x]=x+1;
                MainApplication.getClub().setAtyTimes(atytime);
                Log.i("time",String.valueOf(MainApplication.getClub().getAtyTimes()[x])+x);
            }else {
                weekss[x]=0;
                weeks.setTextColor(Color.BLACK);
                atytime[x]=0;
                MainApplication.getClub().setAtyTimes(atytime);
                Log.i("time", String.valueOf(MainApplication.getClub().getAtyTimes()[x])+x);
            }

        Log.i("times",String.valueOf(atytime[0])+String.valueOf(atytime[1])+String.valueOf(atytime[2])+String.valueOf(atytime[3])+String.valueOf(atytime[4])
        +String.valueOf(atytime[5])+String.valueOf(atytime[6]));

    }

}
