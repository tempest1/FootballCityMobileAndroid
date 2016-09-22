package com.footballcitymobileandroid.Controller.Club;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Controller.Me.MeCreateClub;
import com.footballcitymobileandroid.R;

/**
 * Created by Administrator on 2016/7/6.
 */
public class ClubFragementNull  extends Fragment implements View.OnClickListener{

    private View view;
    private TextView create_club,join_club;

    private Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.club_fragment_null, container,false);
        init();
        return view;
    }

    private void init()
    {
        create_club=(TextView)view.findViewById(R.id.create_club);
        create_club.setOnClickListener(this);

        join_club=(TextView)view.findViewById(R.id.join_club);
        join_club.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.create_club:
                try {
                    MainApplication.getUserInfo().getName();
                    intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);

                    intent.setClass(getActivity(), MeCreateClub.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                }catch (Exception e){
                    Toast.makeText(getActivity(),"请先设置姓名",Toast.LENGTH_SHORT).show();

                }
                break;

            case R.id.join_club:
                try {
                    MainApplication.getUserInfo().getName();
                    intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "me");
                    intent.putExtras(bundle);
                    intent.setClass(getActivity(), ClubFind.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                }catch (Exception e){
                    Toast.makeText(getActivity(),"请先设置姓名",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
