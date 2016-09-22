package com.footballcitymobileandroid.Controller.Gymkhana;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/6/8.
 */
public class InfoGym extends Fragment implements View.OnClickListener{

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.info_gym, container,false);
        init();
        return view;
    }
    void init()
    {

    }

    @Override
    public void onClick(View v) {

    }
}
