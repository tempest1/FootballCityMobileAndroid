package com.footballcitymobileandroid.Controller.Club;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.footballcitymobileandroid.Controller.Me.MeLeaderSet;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubRecord;
import com.footballcitymobileandroid.Entity.ClubEntity.club.clubstatic;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhoudi on 16/5/23.
 */
@SuppressLint("ValidFragment")

public class MeClubLeader extends Fragment implements View.OnClickListener,ActionCallBackListener<BaseEntity<ClubRecord>> {
    private View view,dot;

    Intent intent;
    Context context;
    ClubList clubList;
    TextView club_name,number;
    CircleButton image;
    Bitmap bitmap;

    public MeClubLeader(Context context,ClubList clubList){
        this.context=context;
        this.clubList=clubList;

    }
    public MeClubLeader(){


    }

    Button btn_manage,btn_play_record,btn_number,btn_commuicate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.me_club_leader, container,false);
        init();
        return view;

    }
    private void init()
    {
        btn_manage=(Button)view.findViewById(R.id.btn_manage);
        btn_manage.setOnClickListener(this);
        if(clubList.getIsLeader().equals("false")){
            btn_manage.setVisibility(View.GONE);
        }

        btn_play_record=(Button)view.findViewById(R.id.btn_play_record);
        btn_play_record.setOnClickListener(this);

        btn_number=(Button)view.findViewById(R.id.btn_number);
        btn_number.setOnClickListener(this);
        btn_commuicate=(Button)view.findViewById(R.id.btn_commuicate);
        btn_commuicate.setOnClickListener(this);
        Log.i("clubnameaaa",clubList.getClubName());
        image=(CircleButton)view.findViewById(R.id.image);
        club_name=(TextView)view.findViewById(R.id.club_name);
        number=(TextView)view.findViewById(R.id.number);
        number.setText(clubList.getMemberNumb()+"人");

        club_name.setText(clubList.getClubName());

        if (clubList.getLogo() != null) {
            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(clubList.getLogo()));
            image.setImageBitmap(bitmap);
        }
        else {
            image.setImageResource(R.mipmap.term_sign);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_manage:       //管理

                intent = new Intent();
                intent.setClass(getContext(), MeLeaderSet.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("clublist",clubList);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;

            case R.id.btn_play_record:       //赛事记录
                doit();

                break;

            case R.id.btn_number:             //成员
                intent = new Intent();
                Bundle bundlea=new Bundle();
                bundlea.putSerializable("clublist",clubList);
                intent.putExtras(bundlea);
                intent.setClass(getContext(), ClubNumberMeun.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.btn_commuicate:         //信息
                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                intent.setClass(getActivity(), ClubMessage.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;

        }
    }



    public  void doit(){
        AppAction appAction= Factory.createAppActionImpl(getActivity());
        appAction.fc_queryClubRecord(clubList.getClubID(),Params.fc_queryClubCurRecord,this);
    }
    List<ClubRecord> clubRecord;
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<ClubRecord> data) {
        Toast toast = Toast.makeText(getActivity(), "查询成功", Toast.LENGTH_LONG);
        toast.show();
        intent = new Intent();
		Bundle bundle=new Bundle();
        clubRecord=data.getResponse().getClubRecord();
        LogUtils.e(""+clubRecord.get(0).getArena_SeasonName());
        bundle.putSerializable("clubRecord", (Serializable) clubRecord);
        intent.putExtras(bundle);
        intent.setClass(getActivity(), ClubRecords.class);
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
    }

    @Override
    public void onResume() {
        super.onResume();
        if(clubList.getClubID().equals(clubstatic.clubid)){
//            LogUtils.e("改图片2"+clubstatic.clubid+clubstatic.logo);
            if(clubstatic.station.equals("1")){
                if(!clubstatic.logo.equals("")&&clubstatic.logo!=null){
//                    LogUtils.e("改图片3"+clubstatic.clubid+clubstatic.logo);
                    bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(clubstatic.logo));
                    image.setImageBitmap(bitmap);
                    clubList.setLogo(clubstatic.logo);
                }
            }
            if(clubstatic.station.equals("2")){
                if(!clubstatic.name.equals("")&&clubstatic.name!=null){
//                    LogUtils.e("改名字"+clubstatic.clubid+clubstatic.logo);
//                bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(clubstatic.logo));
//                images.setImageBitmap(bitmap);
//                clubList.setLogo(clubstatic.logo);
                    club_name.setText(clubstatic.name);
                    clubList.setClubName(clubstatic.name);
                }
            }

        }
    }
}
