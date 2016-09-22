package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.AppActionImpl;
import com.footballcitymobileandroid.BLL.Util.CustomView.LoadingDialog;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Clubs;
import com.footballcitymobileandroid.R;

import java.io.Serializable;

/**
 * Created by zhoudi on 16/5/24.
 */
public class ClubFindIf extends Activity implements View.OnClickListener ,ActionCallBackListener<BaseEntity<Clubs>> {

    private LoadingDialog loadingDialog;

    private Button find,btn_age;
    private Button detail_back;
    TextView detail_title_center;
    private  TextView age1,age2,age3,age4,age5,age6;
    private Intent intent;
    private TextView week1,week2,week3,week4,week5,week6,week7;
    int[] weekss= new int[]{0,0,0,0,0,0,0};
    int [] atytime=new int[]{0,0,0,0,0,0,0};
//    int [] age=new int[]{0,18};
    String age="0-18";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_club_if);
        init();
    }

    private void init()
    {
        loadingDialog = new LoadingDialog(this,R.drawable.loading);

        find=(Button)findViewById(R.id.find);
        find.setOnClickListener(this);

        detail_back=(Button) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);

        age1=(TextView)findViewById(R.id.age1);
        age2=(TextView)findViewById(R.id.age2);
        age3=(TextView)findViewById(R.id.age3);
        age4=(TextView)findViewById(R.id.age4);
        age5=(TextView)findViewById(R.id.age5);
        age6=(TextView)findViewById(R.id.age6);
        age1.setOnClickListener(this);
        age2.setOnClickListener(this);
        age3.setOnClickListener(this);
        age4.setOnClickListener(this);
        age5.setOnClickListener(this);
        age6.setOnClickListener(this);

        week1=(TextView)findViewById(R.id.week1);
        week2=(TextView)findViewById(R.id.week2);
        week3=(TextView)findViewById(R.id.week3);
        week4=(TextView)findViewById(R.id.week4);
        week5=(TextView)findViewById(R.id.week5);
        week6=(TextView)findViewById(R.id.week6);
        week7=(TextView)findViewById(R.id.week7);

        week1.setOnClickListener(this);
        week2.setOnClickListener(this);
        week3.setOnClickListener(this);
        week4.setOnClickListener(this);
        week5.setOnClickListener(this);
        week6.setOnClickListener(this);
        week7.setOnClickListener(this);

        btn_age=(Button) findViewById(R.id.btn_age);
        // btn_age.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.find:
                doit();
                break;
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;

            case R.id.age1:
                Age();
                age1.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_blue));
                btn_age.setText("十八岁以下");
//                age=new int[]{0,18};
                age="0-18";
                break;
            case R.id.age2:
                Age();
                age2.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_blue));
                btn_age.setText("十九岁到二十三岁");
//                age=new int[]{19,23};
                age="19-23";
                break;
            case R.id.age3:
                Age();
                age3.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_blue));
                btn_age.setText("二十四岁到二十八岁");
//                age=new int[]{24,28};
                age="24-28";
                break;
            case R.id.age4:
                Age();
                age4.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_blue));
                btn_age.setText("二十九到三十四岁");
//                age=new int[]{29,34};
                age="29-34";
                break;
            case R.id.age5:
                Age();
                age5.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_blue));
                btn_age.setText("三十四到四十五岁");
//                age=new int[]{34,45};
                age="34-45";
                break;
            case R.id.age6:
                Age();
                age6.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_blue));
                btn_age.setText("四十六以上");
//                age=new int[]{46,100};
                age="46-100";
                break;
            case R.id.btn_age:
                intent = new Intent();
                intent.setClass(this, ClubFindList.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("club", data);
//                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
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
        }
    }

    private void Age()
    {
        age1.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_while));
        age2.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_while));
        age3.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_while));
        age4.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_while));
        age5.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_while));
        age6.setBackgroundDrawable(getResources().getDrawable(R.drawable.oval_while));
    }
    private void setColor(TextView weeks,int x)
    {
        if (weekss[x]==0) {
            weekss[x]=1;
            weeks.setTextColor(Color.RED);
            atytime[x]=x+1;

        }else {
            weekss[x]=0;
            weeks.setTextColor(Color.BLACK);
            atytime[x]=0;

        }


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

    private void doit(){
        loadingDialog.show();

        AppAction appAction=new AppActionImpl(this);
        appAction.fc_queryClubs("1","",age,atytime,"", Params.fc_queryClubs,this);
//        Log.i("clubname",String.valueOf(age[0]));
    }
    @Override
    public void onSuccess(BaseEntity<Clubs> data) {
        intent = new Intent();
        intent.setClass(this, ClubFindList.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("Clubs", (Serializable) data.getResponse().getClubs());
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
        loadingDialog.dismiss();

    }

    @Override
    public void onFailure(String e_Type, String e_Msg) {
        Toast toast=Toast.makeText(this,e_Msg,Toast.LENGTH_LONG);
        toast.show();
        loadingDialog.dismiss();

    }
}
