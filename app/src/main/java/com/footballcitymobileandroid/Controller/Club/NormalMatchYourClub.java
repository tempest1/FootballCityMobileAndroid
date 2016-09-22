package com.footballcitymobileandroid.Controller.Club;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.footballcitymobileandroid.Entity.ClubEntity.sport.SportDetail;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/30.
 */
@SuppressLint("ValidFragment")

public class NormalMatchYourClub extends Fragment {
    private View view,dot;

    TextView club_name;
    SportDetail sportDetail;     //比赛内容

    EditText point;
    NormalMatchYourClub(SportDetail sportDetail)
    {
        this.sportDetail=sportDetail;
    }
    public NormalMatchYourClub(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.normal_match_your_club, container,false);
        init();
        return view;

    }
    private  void init()
    {
        SportDetail.visitingscore="";
        dot=view.findViewById(R.id.v_dot1);
        dot.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
        club_name=(TextView)view.findViewById(R.id.club_name);
        club_name.setText(""+sportDetail.getGuest_club_name());
        point= (EditText) view.findViewById(R.id.point);
        SportDetail.visitingscore= String.valueOf(point.getText());
        Log.i("score",SportDetail.visitingscore);
        point.addTextChangedListener(watcher);

    }
    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub
            SportDetail.visitingscore= String.valueOf(point.getText());
            Log.i("score",SportDetail.visitingscore);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            SportDetail.visitingscore= String.valueOf(point.getText());
            Log.i("score",SportDetail.visitingscore);
        }
    };

}
