package com.footballcitymobileandroid.Controller.Club;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.DAL.Data.LogUtil.StringUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.R;

/**
 * Created by smartlab on 16/7/26.
 */
@SuppressLint("ValidFragment")

public class ChallengeMatchSignReStartInfoFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Button btn_sign;
    private SharedPreferences mpre_sign = MainApplication.getPreferences();

    private TextView detail_title_center;

    ClubList clubList;
    AranaMatchs aranaMatchses;
//    List<MatchMemb> matchMemb;  //查看签到人数是否为空
//    List<ClubMemb> clubMemb;

    TextView place,money,days,time,way,match_point,text2;

    public ChallengeMatchSignReStartInfoFragment( ClubList clubList,AranaMatchs aranaMatchses)
    {
        this.clubList=clubList;
        this.aranaMatchses=aranaMatchses;
//        this.sportDetail=sportDetail;
//        this.matchMemb=matchMemb;
//        this.clubMemb=clubMemb;
    }
    public ChallengeMatchSignReStartInfoFragment()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.normal_match_info, container,false);
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







        place.setText(aranaMatchses.getAranaName());

        money.setText(StringUtils.costMode(aranaMatchses.getCostMode()));

        days.setText(aranaMatchses.getMatchDate());
        time.setText(aranaMatchses.getMatchTime());
        way.setText(aranaMatchses.getMatchRule()+"v"+aranaMatchses.getMatchRule());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

        }
    }
}
