package com.footballcitymobileandroid.Controller.Gymkhana;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.footballcitymobileandroid.Entity.ClubEntity.join.AddSport;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/6/28.
 */
public class GymPkInfoNumber4 extends Fragment {

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.gym_pk_info_number4, container,false);
        AddSport.joinNum="11";
        return view;

    }
}
