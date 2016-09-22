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
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.SportDetail;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/30.
 */
@SuppressLint("ValidFragment")

public class NormalMatchSignReStartInfoFragment extends Fragment implements View.OnClickListener{

    private View view;
    private Button btn_sign;
    private SharedPreferences mpre_sign = MainApplication.getPreferences();

    private TextView detail_title_center;

    SportDetail sportDetail;     //比赛内容
    ClubList clubList;
//    List<MatchMemb> matchMemb;  //查看签到人数是否为空
//    List<ClubMemb> clubMemb;

    TextView place,money,days,time,way,match_point,text2;

    public NormalMatchSignReStartInfoFragment( ClubList clubList,SportDetail sportDetail)
    {
        this.clubList=clubList;
        this.sportDetail=sportDetail;
//        this.matchMemb=matchMemb;
//        this.clubMemb=clubMemb;
    }
    public NormalMatchSignReStartInfoFragment(){

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
        text2=(TextView)view.findViewById(R.id.text2);
        text2.setVisibility(View.GONE);

        text2=(TextView)view.findViewById(R.id.text2);
        text2.setVisibility(View.GONE);
        money.setVisibility(View.GONE);





        place.setText(sportDetail.getField_name());
        days.setText(sportDetail.getStart_time().substring(0,10));
        time.setText(sportDetail.getEnd_time().substring(11,19));
        way.setText(sportDetail.getJoin_num()+"v"+sportDetail.getJoin_num());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

        }
    }
}
