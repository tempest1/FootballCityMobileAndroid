package com.footballcitymobileandroid.Controller.Club;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class PlayerFindIfPlace   extends Fragment implements View.OnClickListener,ActionCallBackListener<BaseEntity<Players>> {
    private View view;

    Button btn_door,btn_later,btn_center,btn_head;

    Intent intent;
    private LoadingDialog loadingDialog;
    String position;
    ClubList clubList;
    public PlayerFindIfPlace( ClubList clubList){
        this.clubList=clubList;
    }
    public PlayerFindIfPlace(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.player_find_if_place, container,false);
        init();
        return view;
    }
    void init()
    {
        loadingDialog = new LoadingDialog(getActivity(),R.drawable.loading);
        btn_door=(Button)view.findViewById(R.id.btn_door);
        btn_door.setOnClickListener(this);

        btn_later=(Button)view.findViewById(R.id.btn_later);
        btn_later.setOnClickListener(this);

        btn_center=(Button)view.findViewById(R.id.btn_center);
        btn_center.setOnClickListener(this);

        btn_head=(Button)view.findViewById(R.id.btn_head);
        btn_head.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_door:       //门将
                intent=new Intent();
                intent.setClass(getActivity(),MeClubNumberList.class);
               loadingDialog.show();
                position="4";
                doit();
                break;

            case R.id.btn_later:    //后卫
                intent=new Intent();
                intent.setClass(getActivity(),MeClubNumberList.class);
                loadingDialog.show();
                position="3";
                doit();
                break;

            case R.id.btn_center:   //中场
                intent=new Intent();
                intent.setClass(getActivity(),MeClubNumberList.class);
                loadingDialog.show();
                position="2";
                doit();
                break;

            case R.id.btn_head:      //前锋
                intent=new Intent();
                intent.setClass(getActivity(),MeClubNumberList.class);
                loadingDialog.show();
                position="1";
                doit();
                break;
        }
    }

    public  void doit(){
        AppAction appAction= Factory.createAppActionImpl(getActivity());
        appAction.fc_queryPlayers("position",position, Params.fc_queryPlayers,this);
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
