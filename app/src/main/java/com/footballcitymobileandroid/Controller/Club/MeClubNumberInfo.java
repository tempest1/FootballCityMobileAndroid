package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.DAL.Data.LogUtil.StringUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubMemb;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/28.
 */
public class MeClubNumberInfo extends Activity implements View.OnClickListener {

    ClubMemb clubMemb;
    ClubList clubList;
    Bitmap bitmap;

    private TextView detail_title_center,name,place,sex,time,high,weight,activity_time,activity_place,play_place,price;
    Button detail_title,detail_back;
    CircleButton circleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_number);
        init();
    }

    private void init()
    {
        clubMemb= (ClubMemb) getIntent().getSerializableExtra("clubnumber");   //
      //  clubList= (ClubList) getIntent().getSerializableExtra("clublist");   //本俱乐部信息

        detail_title_center=(TextView)findViewById(R.id.detail_title_center) ;
        detail_title_center.setText("俱乐部成员");


        detail_title=(Button)findViewById(R.id.detail_title);
        detail_title.setText("");
        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        name=(TextView)findViewById(R.id.name);
//        place=(TextView)findViewById(R.id.way);
//        sex=(TextView)findViewById(R.id.sex);
   //     time=(TextView)findViewById(R.id.time);
//        high=(TextView)findViewById(R.id.high);
//        weight=(TextView)findViewById(R.id.weight);
//        activity_time=(TextView)findViewById(R.id.activity_time);
//        activity_place=(TextView)findViewById(R.id.activity_place);
        play_place=(TextView)findViewById(R.id.play_place);
        price=(TextView)findViewById(R.id.price);
        circleButton=(CircleButton)findViewById(R.id.roundBtn);



        name.setText(clubMemb.getName());

        if (clubMemb.getPhoto() != null&&!clubMemb.getPhoto().equals("")) {
            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(clubMemb.getPhoto()));
            circleButton.setImageBitmap(bitmap);
        }
        else {
            circleButton.setImageResource(R.mipmap.personhead);
        }
        try {
            price.setText("$"+clubMemb.getWorth().get(0).getArena_worth());
        }catch (Exception e){
            price.setText("$"+"0");

        }
        play_place.setText(StringUtils.playPlace(clubMemb.getClubPosition()));
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
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
        }
    }
}
