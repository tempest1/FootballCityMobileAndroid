package com.footballcitymobileandroid.Controller.Gymkhana;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.MatchMsg;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/21.
 */
public class GymPkInfoPay extends Fragment implements View.OnClickListener{
    private View view;
    private TextView pay_way,t1,t2,t3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.gym_pk_info_pay, container,false);
        init();
        return view;
    }
    void init()
    {
        MatchMsg.costModeKey="0";
        Log.i("cost",MatchMsg.costModeKey);
        pay_way=(TextView)view.findViewById(R.id.pay_way);
        t1=(TextView)view.findViewById(R.id.t1);
        t3=(TextView)view.findViewById(R.id.t3);
        t1.setOnClickListener(this);

        t3.setOnClickListener(this);


    }
    Resources resources;
    Drawable drawable;
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.t1:
                pay_way.setText("主队支付");
                MatchMsg.costModeKey="0";
                Log.i("cost",MatchMsg.costModeKey);
                resources=getContext().getResources();
                drawable=resources.getDrawable(R.color.transparent_red);
                t1.setBackgroundDrawable(drawable);

                drawable=resources.getDrawable(R.color.transparent_while);
                t3.setBackgroundDrawable(drawable);
                break;

            case R.id.t3:
                pay_way.setText("AA制");
                MatchMsg.costModeKey="1";
                Log.i("cost",MatchMsg.costModeKey);

                resources=getContext().getResources();
                drawable=resources.getDrawable(R.color.transparent_while);
                t1.setBackgroundDrawable(drawable);

                drawable=resources.getDrawable(R.color.transparent_red);
                t3.setBackgroundDrawable(drawable);
                break;

        }
    }
}
