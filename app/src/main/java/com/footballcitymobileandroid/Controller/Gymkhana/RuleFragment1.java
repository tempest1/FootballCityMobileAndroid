package com.footballcitymobileandroid.Controller.Gymkhana;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.footballcitymobileandroid.R;

/**
 * Created by smartlab on 16/7/30.
 */
public class RuleFragment1 extends Fragment{

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.gym_rule, container,false);
        init();
        return view;
    }
    void init() {

    }

}
