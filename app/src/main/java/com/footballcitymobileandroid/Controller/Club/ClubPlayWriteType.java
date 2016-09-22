package com.footballcitymobileandroid.Controller.Club;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.footballcitymobileandroid.Entity.ClubEntity.join.AddSport;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/24.
 */
public class ClubPlayWriteType extends Fragment implements View.OnClickListener{
    private View view,dot;
    private TextView type1,type2,type3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.club_play_write_type, container,false);
        init();
        return view;

    }
    private  void init()
    {
        AddSport.sportState="1";
        type1=(TextView)view.findViewById(R.id.type1);
        type2=(TextView)view.findViewById(R.id.type2);
        type3=(TextView)view.findViewById(R.id.type3);
        type1.setOnClickListener(this);
        type2.setOnClickListener(this);
        type3.setOnClickListener(this);

    }
    Resources resources;
    Drawable drawable;
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.type1:
                setColor();
                resources=getContext().getResources();
                drawable=resources.getDrawable(R.color.transparent_red);
                type1.setBackgroundDrawable(drawable);
                AddSport.sportState="1";
                break;
            case R.id.type2:
                setColor();
                resources=getContext().getResources();
                drawable=resources.getDrawable(R.color.transparent_red);
                type2.setBackgroundDrawable(drawable);
                AddSport.sportState="2";
                break;
            case R.id.type3:
                setColor();
                resources=getContext().getResources();
                drawable=resources.getDrawable(R.color.transparent_red);
                type3.setBackgroundDrawable(drawable);
                AddSport.sportState="3";
                break;
        }
    }

    private void setColor()
    {
        resources=getContext().getResources();
        drawable=resources.getDrawable(R.color.transparent_while);
        type1.setBackgroundDrawable(drawable);
        type2.setBackgroundDrawable(drawable);
        type3.setBackgroundDrawable(drawable);

    }

}
