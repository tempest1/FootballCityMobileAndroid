package com.footballcitymobileandroid.Controller.Club;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.CustomView.LoadingDialog;
import com.footballcitymobileandroid.Controller.Me.MeClubNumberList;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.MeEntity.Players;
import com.footballcitymobileandroid.R;

import java.io.Serializable;

/**
 * Created by zhoudi on 16/5/30.
 */
@SuppressLint("ValidFragment")

public class PlayerFindIfAge extends Fragment implements View.OnClickListener,ActionCallBackListener<BaseEntity<Players>> {
    private View view;
    private Button age_find;
    private Intent intent;
    private TextView age1,age2,age3,age4,age5,age6;
    private LoadingDialog loadingDialog;
//    int[] age=new int[]{0,18};
    String age="0-18";
    ClubList clubList;
    public PlayerFindIfAge(ClubList clubList){
        this.clubList=clubList;
    }
    public PlayerFindIfAge(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.player_find_if_age, container,false);
        init();
        return view;
    }
    void init()
    {
        loadingDialog = new LoadingDialog(getActivity(),R.drawable.loading);
        age_find=(Button)view.findViewById(R.id.age_find);
        age_find.setOnClickListener(this);

        age1=(TextView)view.findViewById(R.id.age1);
        age2=(TextView)view.findViewById(R.id.age2);
        age3=(TextView)view.findViewById(R.id.age3);
        age4=(TextView)view.findViewById(R.id.age4);
        age5=(TextView)view.findViewById(R.id.age5);
        age6=(TextView)view.findViewById(R.id.age6);
        age1.setOnClickListener(this);
        age2.setOnClickListener(this);
        age3.setOnClickListener(this);
        age4.setOnClickListener(this);
        age5.setOnClickListener(this);
        age6.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.age_find:
                intent=new Intent();
                intent.setClass(getActivity(),MeClubNumberList.class);
                doit();

                break;
            case R.id.age1:
                Age();
                age1.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_blue));
                age_find.setText("十八岁以下");
                age="0-18";
                break;
            case R.id.age2:
                Age();
                age2.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_blue));
                age_find.setText("十九岁到二十三岁");
                age="19-23";
                break;
            case R.id.age3:
                Age();
                age3.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_blue));
                age_find.setText("二十四岁到二十八岁");
                age="24-28";
                break;
            case R.id.age4:
                Age();
                age4.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_blue));
                age_find.setText("二十九到三十四岁");
                age="29-34";
                break;
            case R.id.age5:
                Age();
                age5.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_blue));
                age_find.setText("三十四到四十五岁");
                age="34-45";
                break;
            case R.id.age6:
                Age();
                age6.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_blue));
                age_find.setText("四十六以上");
                age="46-100";
                break;
        }


    }
    private void Age()
    {
        age1.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_while));
        age2.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_while));
        age3.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_while));
        age4.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_while));
        age5.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_while));
        age6.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_while));
    }

    public  void doit(){
        loadingDialog.show();
        AppAction appAction= Factory.createAppActionImpl(getActivity());
        appAction.fc_queryPlayers("age",age, Params.fc_queryPlayers,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Players> data) {
        Toast toast = Toast.makeText(getActivity(), "查找成功", Toast.LENGTH_LONG);
        toast.show();
        loadingDialog.dismiss();
        Bundle bundle=new Bundle();
        bundle.putSerializable("player", (Serializable) data.getResponse().getPlayers());
        bundle.putSerializable("clublist",clubList);
        intent.putExtras(bundle);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
    }

    /**
     * 请求失败
     *
     * @param e_Type 错误码
     * @param e_Msg  错误详情
     */
    @Override
    public void onFailure(String e_Type, String e_Msg) {
        Toast toast = Toast.makeText(getActivity(), e_Msg, Toast.LENGTH_LONG);
        toast.show();
        loadingDialog.dismiss();
    }
}
