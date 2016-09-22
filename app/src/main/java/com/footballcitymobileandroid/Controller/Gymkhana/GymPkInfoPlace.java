package com.footballcitymobileandroid.Controller.Gymkhana;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.MatchMsg;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/21.
 */
public class GymPkInfoPlace extends Fragment implements View.OnClickListener{
    private View view;
    private TextView place;
    final String[] placeList=new String[]{"金州体育场","大连市体育馆","大连市体育中心足球场","大连凌云室内足球场","奥克体育场"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        LogUtils.e("GymPkInfoPlace");
        view = inflater.inflate(R.layout.gym_pk_info_place, container,false);
        init();
        return view;
    }
    void init()
    {
        place=(TextView)view.findViewById(R.id.way);
        place.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.way:
                android.app.Dialog alertDialog_position = new AlertDialog.Builder(getActivity()).
                        setTitle("请选择您的位置？")
                        .setItems(placeList, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(MeMyInfo.this, hignList[which], Toast.LENGTH_SHORT).show();
                                place.setText(placeList[which]);
                                MatchMsg.fieldID= String.valueOf(which);
                            }
                        }).
                                setNegativeButton("取消", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).
                                create();
                alertDialog_position.show();
                break;
        }
    }
}
