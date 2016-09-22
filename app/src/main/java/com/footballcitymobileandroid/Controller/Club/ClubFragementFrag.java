package com.footballcitymobileandroid.Controller.Club;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.CustomView.ActivityCollector;
import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.Controller.Common.CommonLogin;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubRecord;
import com.footballcitymobileandroid.Entity.ClubEntity.club.clubstatic;
import com.footballcitymobileandroid.R;
import com.footballcitymobileandroid.openimui.demo.FragmentTabs;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhoudi on 16/6/25.
 */
public class ClubFragementFrag  extends Fragment implements View.OnClickListener,ActionCallBackListener<BaseEntity<ClubRecord>> {
    private View view;

    private TextView club,club_number,pipie,recode,club_message,club_exchange,win,equit,fial,point;
    CircleButton images;

    ClubList clubList;

    private Intent intent;

    Bitmap bitmap;

    private TextView will_time,will_team,will_place,end_time,end_team,end_place;


     public void setClub(ClubList clubList)
    {

        this.clubList=clubList;
//        MainApplication.setClubLists(this.clubList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.club_fragement_frag, container,false);
//        LogUtils.e("改图片????"+clubstatic.clubid+clubstatic.logo);
        init();
        return view;
    }
    private  void init()
    {
//        clubstatic.logo="";
//        clubstatic.name="";
//        clubstatic.clubid="";
//        clubList=MainApplication.getClubLists();
//        LogUtils.e("修改测试1"+MainApplication.getClubLists().getClubName());
        club=(TextView)view.findViewById(R.id.club);
        try{
            club.setText(clubList.getClubName());



        images= (CircleButton) view.findViewById(R.id.image);
//        .setImageBitmap(bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(b.getClubLogo())));
        if (clubList.getLogo() != null) {
            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(clubList.getLogo()));
            images.setImageBitmap(bitmap);
        }
        else {
            images.setImageResource(R.mipmap.term_sign);
        }
        Log.i("logo",clubList.getLogo());
        }catch (Exception e){
            ActivityCollector.finishAll();
            Intent intent = new Intent();
            intent.setClass(getActivity(), CommonLogin.class);
            startActivity(intent);
        }
        club_number=(TextView)view.findViewById(R.id.club_number);
        pipie=(TextView)view.findViewById(R.id.pipie);
        recode=(TextView)view.findViewById(R.id.recode);
        club_message=(TextView)view.findViewById(R.id.club_message);
        club_exchange=(TextView)view.findViewById(R.id.club_exchange);

        win=(TextView)view.findViewById(R.id.win);
        equit=(TextView)view.findViewById(R.id.equit);
        fial=(TextView)view.findViewById(R.id.fial);
        point=(TextView)view.findViewById(R.id.point);


        club_number.setOnClickListener(this);
        pipie.setOnClickListener(this);
        recode.setOnClickListener(this);
        club_message.setOnClickListener(this);
        club_exchange.setOnClickListener(this);
        win.setOnClickListener(this);
        equit.setOnClickListener(this);
        fial.setOnClickListener(this);
        point.setOnClickListener(this);


        will_time=(TextView)view.findViewById(R.id.will_time);
        will_team=(TextView)view.findViewById(R.id.will_team);
        will_place=(TextView)view.findViewById(R.id.will_place);
        end_time=(TextView)view.findViewById(R.id.end_time);
        end_team=(TextView)view.findViewById(R.id.end_team);
        end_place=(TextView)view.findViewById(R.id.end_place);



        will_time.setText("");
        will_team.setText("暂无纪录");
        will_place.setText("");
        end_time.setText("");
        end_team.setText("暂无纪录");
        end_place.setText("");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.pipie:           //竞技挑战
                intent=new Intent();
                intent.setClass(getActivity(), ChallengeMatchMeun.class);
                Bundle bundles=new Bundle();
                bundles.putSerializable("clublist",clubList);
                intent.putExtras(bundles);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.club_number:      //我的俱乐部
                intent=new Intent();
                intent.setClass(getActivity(), MeClub.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("clublist",clubList);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.recode:        //普通比赛
                intent = new Intent();
                intent.setClass(getActivity(), NormalMathMeun.class);
                Bundle bundless=new Bundle();
                bundless.putSerializable("clublist",clubList);
                intent.putExtras(bundless);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.club_message:        //俱乐部消息
                intent = new Intent();
                intent.setClass(getActivity(), ClubMessage.class);
                Bundle bundlea=new Bundle();
                bundlea.putSerializable("clublist",clubList);
                intent.putExtras(bundlea);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;

            case R.id.club_exchange:        //交流室
                intent = new Intent();
                intent.setClass(getActivity(),FragmentTabs.class);
//                Bundle bundleb=new Bundle();
//                bundleb.putSerializable("clublist",clubList);
//                intent.putExtras(bundleb);
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
 //               showToast("此功能还没有完成");
                break;

            case R.id.win:
                doit();

                break;
            case R.id.equit:
                doit();

                break;
            case R.id.fial:
                doit();
                break;
            case R.id.point:
                doit();
                break;
        }
    }
    private void showToast(String str)
    {
        Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
    }
    public  void doit(){
        AppAction appAction= Factory.createAppActionImpl(getActivity());
        appAction.fc_queryClubRecord(clubList.getClubID(), Params.fc_queryClubCurRecord,this);
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
//        LogUtils.e("改图片///"+clubstatic.clubid+clubstatic.logo);
        if(clubList.getClubID().equals(clubstatic.clubid)){
//            LogUtils.e("改图片2"+clubstatic.clubid+clubstatic.logo);
            if(clubstatic.station.equals("1")){
             if(!clubstatic.logo.equals("")&&clubstatic.logo!=null){
//                LogUtils.e("改图片3"+clubstatic.clubid+clubstatic.logo);
                bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(clubstatic.logo));
                images.setImageBitmap(bitmap);
                clubList.setLogo(clubstatic.logo);
             }
            }
            if(clubstatic.station.equals("2")){
             if(!clubstatic.name.equals("")&&clubstatic.name!=null){
//                 LogUtils.e("改名字"+clubstatic.clubid+clubstatic.logo);
//                bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(clubstatic.logo));
//                images.setImageBitmap(bitmap);
//                clubList.setLogo(clubstatic.logo);
                 club.setText(clubstatic.name);
                 clubList.setClubName(clubstatic.name);
             }
            }

        }
//        clubstatic.logo="";
//        clubstatic.name="";
//        clubstatic.clubid="";
    }
}
