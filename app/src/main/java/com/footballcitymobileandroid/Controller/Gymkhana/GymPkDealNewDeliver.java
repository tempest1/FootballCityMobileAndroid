package com.footballcitymobileandroid.Controller.Gymkhana;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.ActivityCollector;
import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.DAL.Data.LogUtil.StringUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.MatchingInfo;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/23.
 */
@SuppressLint("ValidFragment")

public class GymPkDealNewDeliver extends Fragment implements View.OnClickListener {
    private View  view;
    private View dot0,dot1,dot2;
    private Button accept;
    ClubList clubList;

    MatchingInfo matchingInfoList;

    Bitmap bitmap;
    CircleButton view1,view2,view3;
    private TextView place,money,days,time,way,name1,name2,name3;

    public GymPkDealNewDeliver(ClubList clubList,MatchingInfo matchingInfoList){
        this.clubList=clubList;
        this.matchingInfoList=matchingInfoList;
    }
    public GymPkDealNewDeliver (){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.gym_pk_deal_new_deliver, container,false);
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

        place.setText(StringUtils.AtyField(matchingInfoList.getAranaName()));
        money.setText(matchingInfoList.getCostMode());
        days.setText(matchingInfoList.getMatchingDate());
        time.setText(matchingInfoList.getMatchingTime());
        way.setText(matchingInfoList.getMatchRule());

        accept=(Button)view.findViewById(R.id.accept);
        accept.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.accept:
                ActivityCollector.finishAll();
                break;
        }
    }
}
