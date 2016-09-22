package com.footballcitymobileandroid.Controller.Club;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
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
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
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

public class PlayerFindIfTime  extends Fragment implements View.OnClickListener,ActionCallBackListener<BaseEntity<Players>> {
    View view;
    private TextView week1,week2,week3,week4,week5,week6,week7;
    int[] week= new int[]{0,0,0,0,0,0,0};
    private LoadingDialog loadingDialog;
    String position="";//星期一
    Button button;
    ClubList clubList;
    public PlayerFindIfTime(ClubList clubList){
        this.clubList=clubList;
    }
    public PlayerFindIfTime(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.player_find_if_time, container,false);
        init();
        return view;
    }
    void init()
    {
        loadingDialog = new LoadingDialog(getActivity(),R.drawable.loading);
        week1=(TextView)view.findViewById(R.id.week1);
        week2=(TextView)view.findViewById(R.id.week2);
        week3=(TextView)view.findViewById(R.id.week3);
        week4=(TextView)view.findViewById(R.id.week4);
        week5=(TextView)view.findViewById(R.id.week5);
        week6=(TextView)view.findViewById(R.id.week6);
        week7=(TextView)view.findViewById(R.id.week7);
        week1.setOnClickListener(this);
        week2.setOnClickListener(this);
        week3.setOnClickListener(this);
        week4.setOnClickListener(this);
        week5.setOnClickListener(this);
        week6.setOnClickListener(this);
        week7.setOnClickListener(this);
        button= (Button) view.findViewById(R.id.find);
        button.setOnClickListener(this);
        button.setVisibility(View.VISIBLE);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.week1:
                setColor(week1,0);

                break;
            case R.id.week2:
                setColor(week2,1);
                break;
            case R.id.week3:
                setColor(week3,2);

                break;
            case R.id.week4:
                setColor(week4,3);

                break;
            case R.id.week5:
                setColor(week5,4);

                break;

            case R.id.week6:
                setColor(week6,5);

                break;
            case R.id.week7:
                setColor(week7,6);

                break;
            case R.id.find:
                if (position==null||position.equals(""))
                {
                    Toast.makeText(getActivity(),"活动时间不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    doit();
                }
                break;
        }
    }
    private void setColor(TextView weeks,int x)
    {
        week1.setTextColor(Color.BLACK);
        week2.setTextColor(Color.BLACK);
        week3.setTextColor(Color.BLACK);
        week4.setTextColor(Color.BLACK);
        week5.setTextColor(Color.BLACK);
        week6.setTextColor(Color.BLACK);
        week7.setTextColor(Color.BLACK);
        for (int i=0;i<7;i++)
        {
            week[i]=0;
        }

        if (week[x]==0) {
            week[x]=1;
            weeks.setTextColor(Color.RED);
            x=x+1;
            position=x+"";
        }else {
            week[x]=0;
            weeks.setTextColor(Color.BLACK);

        }
    }
String [] time=new String []{"星期一","星期二","星期三","星期四","星期五","星期六","星期七"};
    public  void doit(){
        LogUtils.e(position);
        loadingDialog.show();
        AppAction appAction= Factory.createAppActionImpl(getActivity());
        appAction.fc_queryPlayers("aty_Time",time[Integer.parseInt(position)-1], Params.fc_queryPlayers,this);
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
        Intent intent=new Intent();
        intent.setClass(getActivity(),MeClubNumberList.class);
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
