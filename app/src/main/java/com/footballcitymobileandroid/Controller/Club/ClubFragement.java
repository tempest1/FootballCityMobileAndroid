package com.footballcitymobileandroid.Controller.Club;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/16.
 */
public class ClubFragement extends Fragment implements View.OnClickListener {

    private View view;
    TextView detail_title_center;
    TextView pipie,challeage,pk,findclub,myclub,findplayer,message,play;

    private Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.club_fragment, container,false);
        init();

        return view;
    }

    private void init()
    {
        detail_title_center=(TextView) view.findViewById(R.id.detail_title_center);
        detail_title_center.setText("俱乐部");

        pk=(TextView) view.findViewById(R.id.pk);
        pk.setOnClickListener(this);


        myclub=(TextView) view.findViewById(R.id.myclub);
        myclub.setOnClickListener(this);

        findclub=(TextView) view.findViewById(R.id.findclub);
        findclub.setOnClickListener(this);

        play=(TextView) view.findViewById(R.id.play);
        play.setOnClickListener(this);

        findplayer=(TextView) view.findViewById(R.id.findplayer);
        findplayer.setOnClickListener(this);

        challeage=(TextView) view.findViewById(R.id.challeage);
        challeage.setOnClickListener(this);

        pipie=(TextView) view.findViewById(R.id.pipie);
        pipie.setOnClickListener(this);

        message=(TextView) view.findViewById(R.id.message);
        message.setOnClickListener(this);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pipie:
                intent = new Intent();
                intent.setClass(getActivity(), ClubChallengeList.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);


                break;
            case R.id.challeage:
                intent = new Intent();
                intent.setClass(getActivity(), MeClubList.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.pk:
                intent = new Intent();
                intent.setClass(getActivity(), MeClubList.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.findclub:
                intent = new Intent();
                intent.setClass(getActivity(), ClubFind.class);
                Bundle bundle = new Bundle();
                bundle.putString("type", "club");
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.myclub:
                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
//                intent.setClass(getContext(), MeClub.class);
                intent.setClass(getActivity(), ClubChange.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.findplayer:
                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                intent.setClass(getActivity(), PlayerFind.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.message:
                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                intent.setClass(getActivity(), ClubMessage.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.play:
                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
//                intent.setClass(getContext(), ClubPlayWrite.class);
                intent.setClass(getActivity(), NormalMathClubList.class);

                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
        }

    }


}
