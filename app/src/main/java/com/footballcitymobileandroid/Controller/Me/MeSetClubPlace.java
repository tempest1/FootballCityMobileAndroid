package com.footballcitymobileandroid.Controller.Me;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/23.
 */
public class MeSetClubPlace extends Fragment implements View.OnClickListener{
    private View view,dot;
    private TextView place;
    final String[] positionList=new String[]{"金州体育场","大连市体育馆","大连市体育中心足球场","大连凌云室内足球场","奥克体育场"};//ID为1,2,3,4,5

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.me_set_club_place, container,false);
        init();
        return view;

    }
    private  void init()
    {
        dot=view.findViewById(R.id.v_dot2);
        dot.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
        place=(TextView)view.findViewById(R.id.way);
        place.setOnClickListener(this);

        if(MainApplication.getClub().getAtyField()!=null){
            place.setText(MainApplication.getClub().getAtyField());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.way:
                android.app.Dialog alertDialog_position = new AlertDialog.Builder(getContext()).
                        setTitle("请选择体育场？")
                        .setItems(positionList, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(MeMyInfo.this, hignList[which], Toast.LENGTH_SHORT).show();
                                MainApplication.getClub().setAtyField(String.valueOf(which+1));
                                place.setText(positionList[which]);
//                                MainApplication.getClub().setAtyField(place.getText().toString());
                                Log.i("atyfield",MainApplication.getClub().getAtyField()+","+MainApplication.getClub().getAtyField());
                            }
                        }).
                                setNegativeButton("取消", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // TODO Auto-generated method stub
                                    }
                                }).
                                create();
                alertDialog_position.show();
                break;
        }
    }
}
