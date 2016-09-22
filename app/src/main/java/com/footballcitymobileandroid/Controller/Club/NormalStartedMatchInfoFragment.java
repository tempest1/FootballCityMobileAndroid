package com.footballcitymobileandroid.Controller.Club;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.SportDetail;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/6/30.
 */
@SuppressLint("ValidFragment")

public class NormalStartedMatchInfoFragment extends Fragment {
    private View view;

    private Button accept,refuse;
    private TextView text6,club_team,place,money,days,time,way,text2;
    CircleButton ig;

    SportDetail sportDetail;     //比赛内容
    ClubList clubList;
    NormalStartedMatchInfoFragment(ClubList clubList, SportDetail sportDetail)
    {
        this.sportDetail=sportDetail;
        this.clubList=clubList;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.gym_pk_deal_new_accepts, container,false);
        init();
        return view;
    }
    public NormalStartedMatchInfoFragment(){

    }
    private void init()
    {
        accept=(Button)view.findViewById(R.id.accept);
        refuse=(Button)view.findViewById(R.id.refuse);
        text2=(TextView)view.findViewById(R.id.text2);
        text2.setVisibility(View.GONE);

        text6=(TextView)view.findViewById(R.id.text6);
        club_team=(TextView)view.findViewById(R.id.club_team);
        ig=(CircleButton)view.findViewById(R.id.ig);
        accept.setVisibility(View.INVISIBLE);
        refuse.setVisibility(View.INVISIBLE);
        text6.setVisibility(View.INVISIBLE);
        club_team.setVisibility(View.INVISIBLE);
        ig.setVisibility(View.INVISIBLE);

        place=(TextView)view.findViewById(R.id.place);
        money=(TextView)view.findViewById(R.id.money);
        days=(TextView)view.findViewById(R.id.days);
        time=(TextView)view.findViewById(R.id.time);
        way=(TextView)view.findViewById(R.id.way);
        money.setVisibility(View.GONE);
        LogUtils.e("getField_name"+sportDetail.getField_name());
        LogUtils.e("getStart_time"+sportDetail.getStart_time());
        LogUtils.e("getEnd_time"+sportDetail.getEnd_time());
        LogUtils.e("getJoin_num"+sportDetail.getJoin_num());

        place.setText(sportDetail.getField_name());
        days.setText(sportDetail.getStart_time().substring(0,10));
        time.setText(sportDetail.getStart_time().substring(11,19));
        way.setText(sportDetail.getJoin_num()+"v"+sportDetail.getJoin_num());


    }
}
