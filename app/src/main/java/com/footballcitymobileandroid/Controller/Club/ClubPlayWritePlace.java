package com.footballcitymobileandroid.Controller.Club;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.join.AddSport;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/24.
 */
public class ClubPlayWritePlace extends Fragment implements View.OnClickListener{
    private View view,dot;
    private TextView palce_name;
    final String[] placeList=new String[]{"金州体育场","大连市体育馆","大连市体育中心足球场","大连凌云室内足球场","奥克体育场"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.club_play_write_place, container,false);
        init();
        return view;

    }
    private  void init()
    {
//        dot=view.findViewById(R.id.v_dot4);
//        dot.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
        palce_name=(TextView)view.findViewById(R.id.palce_name);
        palce_name.setOnClickListener(this);
        palce_name.setText(AddSport.sportField);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.palce_name:
                LogUtils.e("palce_name");
                android.app.Dialog alertDialog_position = new AlertDialog.Builder(getActivity()).
                        setTitle("请选择您的位置？")
                        .setItems(placeList, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(MeMyInfo.this, hignList[which], Toast.LENGTH_SHORT).show();
                                palce_name.setText(placeList[which]);
                                AddSport.sportField=placeList[which];
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
