package com.footballcitymobileandroid.Controller.Me;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/6/22.
 */
public class MeCreateClub2 extends FragmentActivity implements View.OnClickListener {


    private MeSetClubDay meSetClubDay;
    private MeSetClubGreat meSetClubGreat;
    private MeSetClubImage meSetClubImage;
    private MeSetClubName meSetClubName;
    private MeSetClubPerson meSetClubPerson;
    private MeSetClubPlace meSetClubPlace;

    private Button detail_title,detail_back;
    TextView detail_title_center;
    private FragmentManager fragmentManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_create_club2);
        init();
    }
    private void init()
    {



        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);
        detail_title=(Button)findViewById(R.id.detail_title);
        detail_title.setOnClickListener(this);


        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("创建俱乐部");
        fragmentManager = getSupportFragmentManager();
        setTabselection(0);

    }

    private void setTabselection(int index) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                if (meSetClubImage == null) {
                    meSetClubImage = new MeSetClubImage();
                    transaction.add(R.id.main_frame, meSetClubImage, "meSetClubImage");//homepage
                }
                transaction.show(meSetClubImage);
                break;
            case 1:
                if (meSetClubName == null) {
                    meSetClubName = new MeSetClubName();
                    transaction.add(R.id.main_frame, meSetClubName, "meSetClubName");
                }
                transaction.show(meSetClubName);
                break;
            case 2:
                if (meSetClubPlace == null) {
                    meSetClubPlace = new MeSetClubPlace();
                    transaction.add(R.id.main_frame, meSetClubPlace, "meSetClubPlace");
                }
                transaction.show(meSetClubDay);
                break;
            case 3:
                if (meSetClubDay == null) {
                    meSetClubDay = new MeSetClubDay();
                    transaction.add(R.id.main_frame, meSetClubDay, "meSetClubDay");
                }
                transaction.show(meSetClubDay);
                break;
            case 4:
                if (meSetClubPerson == null) {
                    meSetClubPerson = new MeSetClubPerson();
                    transaction.add(R.id.main_frame, meSetClubPerson, "meSetClubPerson");
                }
                transaction.show(meSetClubPerson);
                break;
            case 5:
                if (meSetClubGreat == null) {
                    meSetClubGreat = new MeSetClubGreat();
                    transaction.add(R.id.main_frame, meSetClubGreat, "meSetClubGreat");
                }
                transaction.show(meSetClubGreat);
                break;

            default:
                break;

        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (meSetClubDay != null)
            transaction.hide(meSetClubDay);
        if (meSetClubName != null)
            transaction.hide(meSetClubName);
        if (meSetClubPlace != null)
            transaction.hide(meSetClubPlace);
        if (meSetClubDay != null)
            transaction.hide(meSetClubDay);
        if (meSetClubPerson != null)
            transaction.hide(meSetClubPerson);
        if (meSetClubGreat != null)
            transaction.hide(meSetClubGreat);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.detail_title:
                // 处理数据

                //结束activity
                this.finish();
                break;
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }
}
