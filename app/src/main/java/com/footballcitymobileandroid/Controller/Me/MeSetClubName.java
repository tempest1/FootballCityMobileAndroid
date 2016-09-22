package com.footballcitymobileandroid.Controller.Me;

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

import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/23.
 */
public class MeSetClubName extends Fragment {
    private View view,dot;
    EditText set_club_name;
    TextView leader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.me_set_club_name, container,false);
        init();
        return view;
    }
    private  void init()
    {
        dot=view.findViewById(R.id.v_dot1);
        dot.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
        set_club_name= (EditText) view.findViewById(R.id.set_club_name);
//        if(MainApplication.getClub().getClubName()!=null){
//            set_club_name.setText(MainApplication.getClub().getClubName());
//            MainApplication.getClub().setClubName(set_club_name.getText().toString());
//            Log.i("clubName",MainApplication.getClub().getClubName());
//        }
//        else {
//            MainApplication.getClub().setClubName(set_club_name.getText().toString());
//            set_club_name.setText(MainApplication.getClub().getClubName());
//            Log.i("clubName",MainApplication.getClub().getClubName());
//        }
        leader=(TextView)view.findViewById(R.id.leader);
        leader.setText( MainApplication.getUserInfo().getName());
         set_club_name.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                 MainApplication.getClub().setClubName(set_club_name.getText().toString());
//                  set_club_name.setText(MainApplication.getClub().getClubName());
                 Log.i("clubName",MainApplication.getClub().getClubName());
             }

             @Override
             public void afterTextChanged(Editable s) {
                 MainApplication.getClub().setClubName(set_club_name.getText().toString());
                 Log.i("clubName",MainApplication.getClub().getClubName());
             }
         });
    }


}
