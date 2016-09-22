package com.footballcitymobileandroid.Controller.Club;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.LogUtil.StringUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/6/30.
 */
@SuppressLint("ValidFragment")

public class ChallengeStartedMatchInfoFragment extends Fragment{
    private View view;

    private Button accept,refuse;
    private TextView text6;

    List<AranaMatchs> aranaMatchses;

    Bitmap bitmap;
    CircleButton ig;
    private TextView place,money,days,time,way,club_team;
    ClubList clubList;
    public ChallengeStartedMatchInfoFragment(ClubList clubList,List<AranaMatchs> aranaMatchses){
        this.clubList=clubList;
        this.aranaMatchses=aranaMatchses;          //选择第0个数据
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.gym_pk_deal_new_accepts, container,false);
        init();
        return view;
    }
    public ChallengeStartedMatchInfoFragment()
    {

    }
    private void init()
    {
        accept=(Button)view.findViewById(R.id.accept);
        refuse=(Button)view.findViewById(R.id.refuse);

        text6=(TextView)view.findViewById(R.id.text6);
        club_team=(TextView)view.findViewById(R.id.club_team);
        ig=(CircleButton)view.findViewById(R.id.ig);
        accept.setVisibility(View.INVISIBLE);
        refuse.setVisibility(View.INVISIBLE);
        text6.setVisibility(View.INVISIBLE);
        club_team.setVisibility(View.INVISIBLE);
        ig.setVisibility(View.INVISIBLE);

        place=(TextView)view.findViewById(R.id.plac1);
        money=(TextView)view.findViewById(R.id.money);
        days=(TextView)view.findViewById(R.id.days);
        time=(TextView)view.findViewById(R.id.time);

        way=(TextView)view.findViewById(R.id.way);

        if (aranaMatchses!=null)
        {
            LogUtils.e("aranaMatchses!=null");
            LogUtils.e(aranaMatchses.toString());
            try {
                place.setText(aranaMatchses.get(0).getAranaName());
            }catch (Exception e){
                place.setText("");
            }
            try {
                money.setText(StringUtils.costMode(aranaMatchses.get(0).getCostMode()));
            }catch (Exception e){
                money.setText("");
            }
            try {
                days.setText(aranaMatchses.get(0).getMatchDate());
            }catch (Exception e)
            {
                days.setText("");
            }
            try {
                time.setText(aranaMatchses.get(0).getMatchTime());
            }catch (Exception e)
            {
                time.setText("");
            }
            try {
                way.setText(aranaMatchses.get(0).getMatchRule()+"v"+aranaMatchses.get(0).getMatchRule());
            }catch (Exception e){
                way.setText("");
            }
        }else {
            LogUtils.e("aranaMatchses=null");
        }

    }
}
