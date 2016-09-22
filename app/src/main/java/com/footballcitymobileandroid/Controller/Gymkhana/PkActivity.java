package com.footballcitymobileandroid.Controller.Gymkhana;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.footballcitymobileandroid.Controller.Club.MeClubList;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/6/12.
 */
public class PkActivity  extends Activity implements View.OnClickListener{
    View view,dot0;
    Button btn_pk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gym_pk);
    }

    void init()
    {
        dot0=view.findViewById(R.id.v_dot0);
        dot0.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));

        btn_pk=(Button) view.findViewById(R.id.btn_pk);
        btn_pk.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_pk:
                Intent intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                intent.setClass(this, MeClubList.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
        }
    }
}
