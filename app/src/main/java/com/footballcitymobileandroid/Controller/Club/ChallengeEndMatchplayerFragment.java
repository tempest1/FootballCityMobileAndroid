package com.footballcitymobileandroid.Controller.Club;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.GvAdpater;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/7/21.
 */

@SuppressLint("ValidFragment")

public class ChallengeEndMatchplayerFragment extends Fragment{
    private View view;

    private GridView gview;

    AranaMatchs aranaMatchses;
    ClubList clubList;
    GvAdpater adpater;

    public ChallengeEndMatchplayerFragment( ){

    }

    public ChallengeEndMatchplayerFragment( ClubList clubList, AranaMatchs aranaMatchses){
        this.clubList=clubList;
        this.aranaMatchses=aranaMatchses;           //选择第0个数据
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.challenge_started_matchplayer_fragment, container,false);
        init();
        return view;
    }

    private void init()
    {

        gview=(GridView)view.findViewById(R.id.gview);
        if (aranaMatchses!=null)
        {
//            adpater=new GvAdpater(aranaMatchses,getContext())
//            gview.setAdapter();
        }
    }
}
