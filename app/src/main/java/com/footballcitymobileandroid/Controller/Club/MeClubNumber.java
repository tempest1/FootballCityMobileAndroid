package com.footballcitymobileandroid.Controller.Club;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.footballcitymobileandroid.Controller.Me.MePlayerRecord;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/23.
 */
@SuppressLint("ValidFragment")

public class MeClubNumber extends Fragment implements  View.OnClickListener {
    private View view,dot;

    private Intent intent;

    private Button btn_play_record,btn_number,btn_commuicate;
    public MeClubNumber(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.me_club_number, container,false);
        init();
        return view;

    }
    private void init()
    {
        btn_play_record=(Button)view.findViewById(R.id.btn_play_record);
        btn_play_record.setOnClickListener(this);

        btn_number=(Button)view.findViewById(R.id.btn_number);
        btn_number.setOnClickListener(this);

        btn_commuicate=(Button)view.findViewById(R.id.btn_commuicate);
        btn_commuicate.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

            case R.id.btn_play_record:

//                intent = new Intent();
////		Bundle bundle=new Bundle();
////		bundle.putString("type", "forget");
////		intent.putExtras(bundle);
//                intent.setClass(getContext(), MeClubPlayRecord.class);
//                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                intent.setClass(getActivity(), MePlayerRecord.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;

            case R.id.btn_number:
                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                intent.setClass(getContext(), ClubNumberMeun.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.btn_commuicate:
                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                intent.setClass(getActivity(), ClubMessage.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
        }
    }
}
