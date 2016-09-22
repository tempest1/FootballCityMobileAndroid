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

import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/23.
 */
public class MeSetClubGreat extends Fragment {
    private View view,dot;
    EditText textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.me_set_club_great, container,false);
       init();

        return view;

    }
    private  void init()
    {
        dot=view.findViewById(R.id.v_dot5);
        dot.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
        Log.i("clublogo", MainApplication.getClub().getLogo());
        textView= (EditText) view.findViewById(R.id.set_club_fuli);
        MainApplication.getClub().setClubWalfare(textView.getText().toString());
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainApplication.getClub().setClubWalfare(textView.getText().toString());
                Log.i("walfare",MainApplication.getClub().getClubWalfare());
            }

            @Override
            public void afterTextChanged(Editable s) {
                MainApplication.getClub().setClubWalfare(textView.getText().toString());
                Log.i("walfare",MainApplication.getClub().getClubWalfare());
            }
        });
    }
}
