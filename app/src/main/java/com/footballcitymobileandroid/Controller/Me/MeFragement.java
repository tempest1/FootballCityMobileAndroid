package com.footballcitymobileandroid.Controller.Me;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.AppActionImpl;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Controller.Club.ClubFind;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.LogUtil.StringUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.join.Invitations;
import com.footballcitymobileandroid.Entity.MeEntity.PlayerRecord;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhoudi on 16/5/16.
 */
public class MeFragement extends Fragment implements View.OnClickListener,ActionCallBackListener<BaseEntity<Invitations>> {
    private Intent intent = new Intent();

    private View view;
    private TextView detail_title_center;
    private TextView investment,record,join_club,create_club,setting,name,price,age,place;
    private CircleButton user_image;
    private LinearLayout l1,l2,l3,l4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.me_fragment, container,false);
        init();
        LogUtils.e("个人信息"+MainApplication.getUserInfo().toString());
        //  LogUtils.e("俱乐部"+MainApplication.getClub().toString());
        //  LogUtils.e("俱乐部"+MainApplication.getClubList().toString());

        return view;


    }

    private void init()
    {
        detail_title_center=(TextView) view.findViewById(R.id.detail_title_center);
        detail_title_center.setText("我的");

        investment=(TextView) view.findViewById(R.id.investment);
        record=(TextView) view.findViewById(R.id.record);
        join_club=(TextView) view.findViewById(R.id.join_club);
        create_club=(TextView) view.findViewById(R.id.create_club);
        setting=(TextView) view.findViewById(R.id.setting);
        user_image=(CircleButton)view.findViewById(R.id.user_image);

        name=(TextView) view.findViewById(R.id.name);
//        price=(TextView) view.findViewById(R.id.price);
//        age=(TextView) view.findViewById(R.id.age);
//        place=(TextView) view.findViewById(R.id.place);


        investment.setOnClickListener(this);
        record.setOnClickListener(this);
        join_club.setOnClickListener(this);
        create_club.setOnClickListener(this);
        setting.setOnClickListener(this);
        user_image.setOnClickListener(this);
        initimage();

        l1=(LinearLayout)view.findViewById(R.id.l1);
        l3=(LinearLayout)view.findViewById(R.id.l3);
        l4=(LinearLayout)view.findViewById(R.id.l4);

        age=new TextView(getContext());
        age.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        age.setTextColor(Color.WHITE);
        age.setTextSize(16);
        l3.addView(age);


        place=new TextView(getContext());
        place.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        place.setTextColor(Color.WHITE);
        place.setTextSize(16);
        l4.addView(place);
//        tv=new TextView(getContext());
//        tv.setText(Html.fromHtml("<u>使用html实现下划线样式</u>"));
        l2=(LinearLayout)view.findViewById(R.id.l2);

        price=new TextView(getContext());
        price.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        price.setTextColor(Color.WHITE);
        price.setTextSize(16);
        price.setText(MainApplication.PLAYERPRICE);
        Drawable img = getResources().getDrawable(R.mipmap.dollar);
        img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
        price.setCompoundDrawables(img, null, null, null);
        l2.addView(price);

    }

    @Override
    public void onStart() {
        super.onStart();
        String strAge="";
        String strName="";
        try {
            //     age.setText(""+ StringUtils.getAge(MainApplication.getUserInfo().getBrithday()));
            strAge=StringUtils.getAge(MainApplication.getUserInfo().getBrithday())+"岁";
        }catch (Exception e)
        {
            //        age.setText("未设置");
            strAge="未设置";
        }
        name.setText(MainApplication.getUserInfo().getName());
        try {
            //     place.setText(MainApplication.getUserInfo().getPosition()[0]);

            strName=StringUtils.playPlace(MainApplication.getUserInfo().getPosition()[0]+"");
        }catch (Exception e)
        {
//            place.setText("前锋");
            strName="未设置";
        }
        age.setText(strAge);
        place.setText(strName);
    }

    private void initimage(){
        if(MainApplication.getUserInfo()!=null&&MainApplication.getUserInfo().getPhoto()!=null){
            Bitmap bitmap=bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(MainApplication.getUserInfo().getPhoto()));
            user_image.setImageBitmap(bitmap);
        }
        else {
            Bitmap bitmap=BitmapFactory.decodeResource(this.getResources(),R.mipmap.personhead);
            user_image.setImageBitmap(bitmap);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.investment:
                doit();

                break;
            case R.id.record:                //个人战绩
                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                intent.setClass(getActivity(), MePlayerRecord.class);
                do_queryPlayerCurRecord();


                break;
            case R.id.join_club:                //加盟俱乐部
                try {
                    MainApplication.getUserInfo().getName();
                    intent = new Intent();
                    Bundle bundle=new Bundle();
                    bundle.putString("type","me");
                    intent.putExtras(bundle);
                    intent.setClass(getActivity(), ClubFind.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                }catch (Exception e)
                {
                    Toast.makeText(getActivity(),"请先设置姓名",Toast.LENGTH_SHORT).show();
                }

                break;


            case R.id.create_club:                //创建俱乐部
                try {
                    MainApplication.getUserInfo().getName();
                    intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                    intent.setClass(getActivity(), MeCreateClub.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                }catch (Exception e)
                {
                    Toast.makeText(getActivity(),"请先设置姓名",Toast.LENGTH_SHORT).show();

                }
                break;

            case R.id.btn_pk:

                break;
            case R.id.setting:
                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                intent.setClass(getActivity(), MeSetting.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.user_image:
                if(MainApplication.getUserInfo().getPhoto()!=null&&MainApplication.getUserInfo()!=null){
                    Bitmap bitmap=bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(MainApplication.getUserInfo().getPhoto()));
                    user_image.setImageBitmap(bitmap);
                }
                else {
                    Bitmap bitmap=BitmapFactory.decodeResource(this.getResources(),R.mipmap.personhead);
                    user_image.setImageBitmap(bitmap);
                }
                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                intent.setClass(getActivity(), MeInfo.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
    private void doit(){               //邀请函
        AppAction appAction=new AppActionImpl(getActivity());
        appAction.fc_checkInvitation("1",Params.fc_checkInvitation,this);

    }
    @Override
    public void onSuccess(BaseEntity<Invitations> data) {
        Toast toast=Toast.makeText(getActivity(),"查询成功",Toast.LENGTH_LONG);
        toast.show();
        intent = new Intent();
        intent.setClass(getActivity(), MeInvest.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("Invitations", (Serializable) data.getResponse().getInvitations());
        intent.putExtras(bundle);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);

    }

    @Override
    public void onFailure(String e_Type, String e_Msg) {
        Toast toast=Toast.makeText(getActivity(),e_Msg,Toast.LENGTH_LONG);
        toast.show();
    }

    private void do_queryPlayerCurRecord() {
        AppAction appAction = Factory.createAppActionImpl(getActivity());
        appAction.fc_queryPlayerCurRecord("user",Params.fc_queryPlayerCurRecord, clublistener);
    }

    public ActionCallBackListener<BaseEntity<PlayerRecord>> clublistener=new ActionCallBackListener<BaseEntity<PlayerRecord>>(){

        /**
         * 处理成功
         *
         * @param data 返回数据
         */
        @Override
        public void onSuccess(BaseEntity<PlayerRecord> data) {
            Toast toast = Toast.makeText(getActivity(), "个人战绩获取成功", Toast.LENGTH_LONG);
            toast.show();
            List<PlayerRecord> playerRecord=data.getResponse().getPlayerRecord();
//            loadingDialog.dismiss();
            Bundle bundle=new Bundle();
            bundle.putSerializable("playerRecord", (Serializable) playerRecord);
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
            Toast toast = Toast.makeText(getActivity(), "俱乐部"+e_Msg, Toast.LENGTH_LONG);
            toast.show();
//            loadingDialog.dismiss();
        }
    };
}
