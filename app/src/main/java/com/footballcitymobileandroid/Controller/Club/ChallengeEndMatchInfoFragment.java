package com.footballcitymobileandroid.Controller.Club;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.footballcitymobileandroid.DAL.Data.LogUtil.StringUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/7/2.
 */
@SuppressLint("ValidFragment")
public class ChallengeEndMatchInfoFragment extends Fragment {
    private View view;

    ClubList clubList;
    AranaMatchs aranaMatchses;

    private TextView place,money,days,time,way,match_point,game_point;
    public ChallengeEndMatchInfoFragment()
    {}

    public ChallengeEndMatchInfoFragment(ClubList clubList,AranaMatchs aranaMatchses){
        this.clubList=clubList;
        this.aranaMatchses=aranaMatchses;
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
        place=(TextView)view.findViewById(R.id.place);
        money=(TextView)view.findViewById(R.id.money);
        days=(TextView)view.findViewById(R.id.days);
        time=(TextView)view.findViewById(R.id.time);
        way=(TextView)view.findViewById(R.id.way);

        match_point=(TextView)view.findViewById(R.id.match_point);
        game_point=(TextView)view.findViewById(R.id.game_point);


        place.setText(StringUtils.AtyField(aranaMatchses.getAranaName()));
        money.setText(StringUtils.costMode(aranaMatchses.getCostMode()));
        days.setText(aranaMatchses.getMatchDate());
        time.setText(aranaMatchses.getMatchTime());
        way.setText(aranaMatchses.getMatchRule());
    }
}
