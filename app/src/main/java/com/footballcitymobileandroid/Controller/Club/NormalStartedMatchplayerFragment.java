package com.footballcitymobileandroid.Controller.Club;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.GvAdpater;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.MatchMemb;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/6/30.
 */@SuppressLint("ValidFragment")

public class NormalStartedMatchplayerFragment extends Fragment {
    private View view;

    List<MatchMemb> matchMemb;

    GvAdpater adapter;

    GridView gv;

    public NormalStartedMatchplayerFragment(List<MatchMemb> matchMemb){
        this.matchMemb=matchMemb;
    }
    public NormalStartedMatchplayerFragment(){

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
        gv=(GridView)view.findViewById(R.id.gview);

        if (matchMemb!=null) {
            adapter = new GvAdpater(matchMemb, getContext());
            gv.setAdapter(adapter);
        }else {
            LogUtils.e("null");
        }

    }
}
