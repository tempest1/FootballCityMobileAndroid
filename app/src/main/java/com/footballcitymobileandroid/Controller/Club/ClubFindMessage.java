package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.LogUtil.StringUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Club;
import com.footballcitymobileandroid.R;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by zhoudi on 16/5/27.
 */
public class ClubFindMessage extends Activity implements View.OnClickListener,ActionCallBackListener<BaseEntity<Void>>{

    Club club;

    private Button join,detail_back;
    TextView detail_title_center,number;
    private CircleButton image,image1,image2,image3;
    Bitmap bitmap;

    TextView place,time,leader,age,build_time,great,name,club_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_find_message);

        init();
    }
    private void init()
    {
        club= (Club) getIntent().getSerializableExtra("club");
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("查询俱乐部详情");

        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);



        LogUtils.e(""+MainApplication.FindClub);
        String flag=MainApplication.FindClub;
        join=(Button)findViewById(R.id.join);

        image=(CircleButton)findViewById(R.id.image);
        name=(TextView)findViewById(R.id.name);

        place=(TextView)findViewById(R.id.way);
        time=(TextView)findViewById(R.id.time);
        leader=(TextView)findViewById(R.id.leader);
        age=(TextView)findViewById(R.id.age);
        build_time=(TextView)findViewById(R.id.bulid_time);
        great=(TextView)findViewById(R.id.great);

        club_number=(TextView)findViewById(R.id.club_number);


        if (flag.equals("me"))
        {
            join.setVisibility(View.VISIBLE);
        }else
        {

            join.setVisibility(View.INVISIBLE);

        }
        join.setOnClickListener(this);


        name.setText(club.getClubName());

        place.setText(StringUtils.AtyField(club.getAtyField()));


        String[] sourceStrArray = club.getAtyTime().split(",");
        for (int i = 0; i < sourceStrArray.length; i++) {
            LogUtils.e(sourceStrArray[i]);
        }
        String week="";
        for (int i=0;i<sourceStrArray.length;i++)
        {
            if (sourceStrArray[i].equals("0"))
            {


            }else {
                if(i==0){
                    week=week+"周一 ";
                }if(i==1){
                    week=week+"周二 ";

                }if (i==2) {
                    week=week+"周三 ";

                }if (i==3) {
                    week=week+"周四 ";

                }if (i==4) {
                    week=week+"周五 ";

                }if (i==5) {
                    week=week+"周六 ";

                }if (i==6) {
                    week=week+"周日 ";

                }
            }
        }

        time.setText(week);
        great.setText(club.getClubWalfare());
        age.setText(club.getAveAge());

       // build_time.setText(club.getCreateTime());

        club_number.setText(club.getMemberNumb());
//        for (int i=0;i<clubs.getStyTime().size();i++) {
//            LogUtils.e("" + clubs.getStyTime().get(i));
//        }
//        leader.setText(clubs.get);
//        age.setText(clubs.getAveAge());
        build_time.setText(club.getLeaderName());

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sd = sdf.format(new Date(Long.parseLong(club.getCreateTime())));
        build_time.setText(sd);

        if (club.getLogo() != null) {
            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(club.getLogo()));
            image.setImageBitmap(bitmap);
        }
        else {
            image.setImageResource(R.mipmap.ic_launcher);
        }


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.join:
                doit();
                break;
            case R.id.detail_back:

                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;

//            case R.id.image1:
//                NumberList();
//                break;
//            case R.id.iamge2:
//                NumberList();
//
//                break;
//            case R.id.iamge3:
//                NumberList();

//                break;

        }

    }
    private void NumberList()
    {
        Intent intent = new Intent();
        intent.setClass(this, ClubNumberList.class);//MeClubNumberList
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("club", data);
//        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
    }


    private void doit() {
        AppAction appAction = Factory.createAppActionImpl(this);
        appAction.fc_createJoinApply(club.getClubID(), Params.fc_createJoinApply, this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Void> data) {
        Toast toast = Toast.makeText(this, "加盟成功", Toast.LENGTH_LONG);
        toast.show();
        this.finish();
    }

    /**
     * 请求失败
     *
     * @param e_Type 错误码
     * @param e_Msg  错误详情
     */
    @Override
    public void onFailure(String e_Type, String e_Msg) {
        Toast toast = Toast.makeText(this, e_Msg, Toast.LENGTH_LONG);
        toast.show();
    }
}
