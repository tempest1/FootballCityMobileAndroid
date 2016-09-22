package com.footballcitymobileandroid.Controller.Club;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.RefuseAdapter;
import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.SportDetail;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/30.
 */
@SuppressLint("ValidFragment")

public class NormalMatchSignReStartFragment extends Fragment implements View.OnClickListener,ActionCallBackListener<BaseEntity<Void>>{

    private View view;
    private Button btn_sign;
    private SharedPreferences mpre_sign = MainApplication.getPreferences();
    private ListView list;
    RefuseAdapter adapter;
    private TextView my_club_name,you_club_name,place,time,way;
    private ImageView image_my_club,image_you_club;

    List<String> data=new ArrayList<>();
    Bitmap bitmap;
    SportDetail sportDetail;     //比赛内容
    ClubList clubList;
//    List<MatchMemb> matchMemb;  //查看签到人数是否为空
//    List<ClubMemb> clubMemb;

    public NormalMatchSignReStartFragment( ClubList clubList,SportDetail sportDetail)
    {
        this.clubList=clubList;
        this.sportDetail=sportDetail;
//        this.matchMemb=matchMemb;
//        this.clubMemb=clubMemb;
    }

    public NormalMatchSignReStartFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.normal_match_sign, container,false);
        init();
        return view;

    }


    private void init()
    {
        btn_sign=(Button)view.findViewById(R.id.btn_sign);
        btn_sign.setOnClickListener(this);
        boolean isSign = mpre_sign.getBoolean("sign",false);
        if(SportDetail.signresult.equals("true")){
            btn_sign.setText("签出");
        }
        else {
            btn_sign.setText("签到");
            }
//        try {
//        if (!isSign) {
//            btn_sign.setText("签到");
//
//        }else {
//
//            btn_sign.setText("签出");
//
//        }
//        }catch (Exception e){
//            btn_sign.setText("签到");
//
//        }

        image_my_club=(ImageView)view.findViewById(R.id.image_my_club);
        image_you_club=(ImageView)view.findViewById(R.id.image_you_club);

        my_club_name=(TextView)view.findViewById(R.id.my_club_name);
        you_club_name=(TextView)view.findViewById(R.id.you_club_name);
        place=(TextView)view.findViewById(R.id.place);
        time=(TextView)view.findViewById(R.id.time);
        way=(TextView)view.findViewById(R.id.way);

        if (clubList.getLogo() != null) {
            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(clubList.getLogo()));
            image_my_club.setImageBitmap(bitmap);
        }
        else {
            image_my_club.setImageResource(R.mipmap.term_sign);
        }
        my_club_name.setText(clubList.getClubName());
        you_club_name.setText(sportDetail.getGuest_club_name());

        place.setText(sportDetail.getField_name());
        time.setText(sportDetail.getStart_time().substring(0,10));
        way.setText(sportDetail.getJoin_num()+"v"+sportDetail.getJoin_num());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_sign:
                LogUtils.e("btn_sign");
                if(SportDetail.signresult.equals("true")){
                    doit("false");

                }
                else {
                    doit("true");
                }
//                try {
//                    LogUtils.e("1");
//
//                    boolean isSign = mpre_sign.getBoolean("sign",false);
//                    LogUtils.e("2"+isSign);
//                    if (!isSign) {
//                        LogUtils.e("3");
//                        SharedPreferences.Editor editor = mpre_sign.edit();
//                        editor.putBoolean("sign", true);
//                        editor.apply();
//                        MainApplication.BTN_SP_Sign="签出";
//                        getActivity().finish();
//                        Toast.makeText(getContext(),"签到成功",Toast.LENGTH_SHORT).show();
//
//
//                    }else {
//                        LogUtils.e("4");
//
//                        SharedPreferences.Editor editor = mpre_sign.edit();
//                        editor.putBoolean("sign", false);
//                        editor.apply();
//                        MainApplication.BTN_SP_Sign="签到";
//
//                        getActivity().finish();
//                        Toast.makeText(getContext(),"签出成功",Toast.LENGTH_SHORT).show();
//
//                    }
//                }catch (Exception e){
//                    LogUtils.e("5");
//
//                    SharedPreferences.Editor editor = mpre_sign.edit();
//                    editor.putBoolean("sign", true);
//                    editor.apply();
//                    MainApplication.BTN_SP_Sign="签出";
//                    getActivity().finish();
//                    Toast.makeText(getContext(),"签到成功",Toast.LENGTH_SHORT).show();
//
//                }


                break;


        }
    }

    private void doit(String sign){
        AppAction appAction= Factory.createAppActionImpl(getActivity());
        appAction.fc_sportSign(clubList.getClubID(),sportDetail.getS_record_id(),sign, Params.fc_sportSign,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Void> data) {
        if(SportDetail.signresult.equals("false")){
            btn_sign.setText("签出");
            SportDetail.signresult="true";
            Toast.makeText(getContext(),"签到成功",Toast.LENGTH_SHORT).show();
        }
        else {
            btn_sign.setText("签到");
            SportDetail.signresult="false";
            Toast.makeText(getContext(),"签出成功",Toast.LENGTH_SHORT).show();
        }


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
