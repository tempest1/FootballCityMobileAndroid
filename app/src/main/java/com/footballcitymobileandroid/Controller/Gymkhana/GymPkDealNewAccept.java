package com.footballcitymobileandroid.Controller.Gymkhana;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
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
import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.DAL.Data.LogUtil.StringUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.MatchingInfo;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/23.
 */
@SuppressLint("ValidFragment")

public class GymPkDealNewAccept extends Fragment implements View.OnClickListener,ActionCallBackListener<BaseEntity<Void>> {
    private View  view;
    private View dot0,dot1,dot2;

    private Button accept,refuse;
    ClubList clubList;
    Bitmap bitmap;
    CircleButton ig;
    private TextView place,money,days,time,way,club_team;
    MatchingInfo matchingInfoList;
    public GymPkDealNewAccept(ClubList clubList,MatchingInfo matchingInfoList){
        this.clubList=clubList;
        this.matchingInfoList=matchingInfoList;
    }
    public GymPkDealNewAccept(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.gym_pk_deal_new_accepts, container,false);
        init();
        return view;
    }

    private void init(){

        place=(TextView)view.findViewById(R.id.plac1);
        money=(TextView)view.findViewById(R.id.money);
        days=(TextView)view.findViewById(R.id.days);
        time=(TextView)view.findViewById(R.id.time);
        way=(TextView)view.findViewById(R.id.way);
        club_team=(TextView)view.findViewById(R.id.club_team);

        ig=(CircleButton)view.findViewById(R.id.ig);

        place.setText(StringUtils.AtyField(matchingInfoList.getAranaName()));
        money.setText(matchingInfoList.getCostMode());
        days.setText(matchingInfoList.getMatchingDate());
        time.setText(matchingInfoList.getMatchingTime());
        way.setText(matchingInfoList.getMatchRule());
        club_team.setText(matchingInfoList.getSender().getClubName());
        if (matchingInfoList.getSender().getClubLogo() != null) {
            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(matchingInfoList.getSender().getClubLogo() ));
            ig.setImageBitmap(bitmap);
        }
        else {
            ig.setImageResource(R.mipmap.personhead);
        }


        accept=(Button)view.findViewById(R.id.accept);   //接受
        accept.setOnClickListener(this);

        refuse=(Button)view.findViewById(R.id.refuse);  //拒绝
        refuse.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.accept:
                doit("接受");
                break;
            case R.id.refuse:
                doit("拒绝");
                break;
        }
    }

    private void doit(String bool){
        AppAction appAction= Factory.createAppActionImpl(getActivity());
        appAction.fc_dealMatchingMsg(matchingInfoList.getArenaMatchID(),clubList.getClubID(),bool, Params.fc_dealMatchingMsg,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Void> data) {
        Toast toast = Toast.makeText(getActivity(), "处理成功", Toast.LENGTH_LONG);
        toast.show();
        getActivity().finish();
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
    }
}
