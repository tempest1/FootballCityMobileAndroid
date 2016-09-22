package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.LogUtil.StringUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Club;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubMemb;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by zhoudi on 16/5/27.
 */
public class MyClubMessage extends Activity implements View.OnClickListener{

    private Button join,detail_back;
    TextView detail_title_center,number;
    private CircleButton image1,image2,image3;

    private TextView place,time,leader,age,bulid_time,great,name,club_number;
//    ClubList clubList;
    Club club;
    List<ClubMemb> clubMemb;
    CircleButton image;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_club_message);

        init();
    }
    private void init()
    {
        club= (Club) getIntent().getSerializableExtra("club");
        clubMemb= (List<ClubMemb>) getIntent().getSerializableExtra("clubmemb");
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("我的俱乐部");

        LogUtils.e(club.toString());

        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        number=(TextView)findViewById(R.id.number);
        number.setOnClickListener(this);
        club_number=(TextView)findViewById(R.id.club_number);
        club_number.setText(club.getMemberNumb());
        number.setText(club.getMemberNumb());
        LogUtils.e(""+MainApplication.FindClub);
        String flag=MainApplication.FindClub;
        join=(Button)findViewById(R.id.join);

        join.setVisibility(View.INVISIBLE);

        image1=(CircleButton)findViewById(R.id.image1);
        image2=(CircleButton)findViewById(R.id.image2);
        image3=(CircleButton)findViewById(R.id.image3);

        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);

        try {
            if (clubMemb.get(0).getPhoto() != null&&!clubMemb.get(0).getPhoto().equals("")) {
                bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(clubMemb.get(0).getPhoto()));
                image1.setImageBitmap(bitmap);
            }
            else {
                image1.setImageResource(R.mipmap.personhead);
            }
        }catch (Exception e){
            image1.setVisibility(View.GONE);
        }

        try {
            if (clubMemb.get(1).getPhoto() != null&&!clubMemb.get(1).getPhoto().equals("")) {
                bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(clubMemb.get(1).getPhoto()));
                image2.setImageBitmap(bitmap);
            }
            else {
                image2.setImageResource(R.mipmap.personhead);
            }
        }catch (Exception e){
            image2.setVisibility(View.GONE);
        }

        try {
            if (clubMemb.get(2).getPhoto() != null&&!clubMemb.get(2).getPhoto().equals("")) {
                bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(clubMemb.get(2).getPhoto()));
                image3.setImageBitmap(bitmap);
            }
            else {
                image3.setImageResource(R.mipmap.personhead);
            }
        }catch (Exception e){
            image3.setVisibility(View.GONE);
        }

        image=(CircleButton)findViewById(R.id.image);
        name=(TextView)findViewById(R.id.name);

        place=(TextView)findViewById(R.id.way);
        time=(TextView)findViewById(R.id.time);
        leader=(TextView)findViewById(R.id.leader);
        age=(TextView)findViewById(R.id.age);
        bulid_time=(TextView)findViewById(R.id.bulid_time);
        great=(TextView)findViewById(R.id.great);

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



        leader.setText(club.getLeaderName());
        age.setText(club.getAveAge());


        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String sd = sdf.format(new Date(Long.parseLong(club.getCreateTime())));
        bulid_time.setText(sd);
        great.setText(club.getClubWalfare());

        if (club.getLogo() != null) {
            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(club.getLogo()));
            image.setImageBitmap(bitmap);
        }
        else {
            image.setImageResource(R.mipmap.term_sign);
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
                this.finish();
                break;
            case R.id.detail_back:

                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;

            case R.id.number:
                NumberList();
                break;

            case R.id.image1:
                NumberList();
                break;
            case R.id.iamge2:
                NumberList();

                break;
            case R.id.iamge3:
                NumberList();

                break;

        }

    }
    private void NumberList()
    {
        Intent intent = new Intent();
        intent.setClass(this, ClubNumberList.class);//MeClubNumberList
        Bundle bundles=new Bundle();
        bundles.putSerializable("club",club);
        bundles.putSerializable("clubmemb", (Serializable) clubMemb);
        intent.putExtras(bundles);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
    }

}
