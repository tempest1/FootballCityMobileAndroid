package com.footballcitymobileandroid.Controller.Gymkhana;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.alibaba.mobileim.utility.LogUtil;
import com.footballcitymobileandroid.BLL.Util.CustomView.PickerView;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.MatchMsg;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/21.
 */
public class GymPkInfoTime extends Fragment implements View.OnClickListener{
    private View view,time;

    String h="12",m="30";


    PickerView minute_pv;
    PickerView second_pv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.gym_pk_info_time, container,false);
        init();
        return view;
    }
    void init()
    {
        MatchMsg.matchingTime=h+":"+m+":"+"00";

        minute_pv = (PickerView) view.findViewById(R.id.minute_pv);
        second_pv = (PickerView) view.findViewById(R.id.second_pv);
        List<String> data = new ArrayList<String>();
        List<String> seconds = new ArrayList<String>();

        for (int i = 0; i < 24; i++)
        {
            data.add(i < 10 ? "0" + i : "" + i );
        }
        for (int i = 0; i < 60; i++)
        {
            seconds.add(i < 10 ? "0" + i : "" + i);
        }
        minute_pv.setData(data);
        minute_pv.setOnSelectListener(new PickerView.onSelectListener()
        {

            @Override
            public void onSelect(String text)
            {
                MatchMsg.matchingTime=text+":"+m+":"+"00";
                h=text;
                LogUtils.e(MatchMsg.matchingTime);
            }
        });
        second_pv.setData(seconds);
        second_pv.setOnSelectListener(new PickerView.onSelectListener()
        {

            @Override
            public void onSelect(String text)
            {
                MatchMsg.matchingTime=h+":"+text+":"+"00";
                m=text;
                LogUtils.e(MatchMsg.matchingTime);

            }
        });

    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            //year.getSelectedItem().toString();

        }
    }


}
