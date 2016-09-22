package com.footballcitymobileandroid.Controller.Club;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/6/30.
 */
@SuppressLint("ValidFragment")

public class ChallengeStartedMatchplayerFragment extends Fragment{
    private View view;


    List<AranaMatchs> aranaMatchses;
    ClubList clubList;
    public ChallengeStartedMatchplayerFragment( ClubList clubList, List<AranaMatchs> aranaMatchses){
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

    }
    public ChallengeStartedMatchplayerFragment()
    {

    }
}
