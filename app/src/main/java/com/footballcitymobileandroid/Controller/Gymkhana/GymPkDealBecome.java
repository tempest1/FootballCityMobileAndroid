package com.footballcitymobileandroid.Controller.Gymkhana;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/23.
 */
public class GymPkDealBecome extends Fragment {
    private View  view;
    private View dot0,dot1,dot2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.gym_pk_deal_become, container,false);
        init();
        return view;
    }

    private void init()
    {
        dot0=view.findViewById(R.id.v_dot0);
        dot1=view.findViewById(R.id.v_dot1);
        dot2=view.findViewById(R.id.v_dot2);
        dot2.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
    }


}
