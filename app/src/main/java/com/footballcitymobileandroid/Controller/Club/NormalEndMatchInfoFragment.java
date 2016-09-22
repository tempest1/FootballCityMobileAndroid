package com.footballcitymobileandroid.Controller.Club;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.SportDetail;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/7/2.
 */
@SuppressLint("ValidFragment")

public class NormalEndMatchInfoFragment extends Fragment {
    private View view;
    ClubList clubList;
    SportDetail sportDetail;

    TextView place,money,days,time,way,match_point,game_point,text2,text7;
    NormalEndMatchInfoFragment(ClubList clubList,SportDetail sportDetail)
    {
        this.clubList=clubList;
        this.sportDetail=sportDetail;
    }

    public NormalEndMatchInfoFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.challenge_match_end_info_fragment, container,false);
        init();
        return view;
    }

    private void init()
    {
        place=(TextView)view.findViewById(R.id.way);
        money=(TextView)view.findViewById(R.id.money);
        days=(TextView)view.findViewById(R.id.days);
        time=(TextView)view.findViewById(R.id.time);
        way=(TextView)view.findViewById(R.id.way);
        match_point=(TextView)view.findViewById(R.id.match_point);
        game_point=(TextView)view.findViewById(R.id.game_point);
        text7=(TextView)view.findViewById(R.id.text7);
        text7.setVisibility(View.GONE);
        game_point.setVisibility(View.GONE);
        text2=(TextView)view.findViewById(R.id.text2);
        text2.setVisibility(View.GONE);
        money.setVisibility(View.GONE);
        LogUtils.e("getField_name"+sportDetail.getField_name());
        LogUtils.e("getSport_state"+sportDetail.getSport_state());
        LogUtils.e("getStart_time"+sportDetail.getStart_time());
        LogUtils.e("getEnd_time"+sportDetail.getEnd_time());
        LogUtils.e("getJoin_num"+sportDetail.getJoin_num());



        place.setText(sportDetail.getField_name());
        days.setText(sportDetail.getStart_time().substring(0,10));
        time.setText(sportDetail.getEnd_time().substring(11,19));
        way.setText(sportDetail.getJoin_num()+"v"+sportDetail.getJoin_num());
        match_point.setText(sportDetail.getH_club_goal()+":"+sportDetail.getG_club_goal());
    }
}
