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
import com.footballcitymobileandroid.BLL.Util.CustomView.LoadingDialog;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.join.Invitations;
import com.footballcitymobileandroid.Entity.ClubEntity.join.JoinApplys;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/7/22.
 */
public class ClubInvestment extends Activity implements View.OnClickListener ,ActionCallBackListener<BaseEntity<Void>>{

    private Button btn_accept,detail_back,btn_fail;
    private TextView detail_title_center;
    private Intent intent;

    CircleButton userimage;
    TextView number;
    private LoadingDialog loadingDialog;

    Invitations invitations;


    JoinApplys joinApplys;
    ClubList clubList;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_invest);
        init();
    }
    private void init()
    {
        loadingDialog = new LoadingDialog(this,R.drawable.loading);

        joinApplys= (JoinApplys) getIntent().getSerializableExtra("JoinApplys");
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");


        invitations= (Invitations) getIntent().getSerializableExtra("invitations");
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("邀请函");

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        btn_accept=(Button)findViewById(R.id.btn_accept);
        btn_accept.setOnClickListener(this);

        btn_fail=(Button)findViewById(R.id.btn_fail);
        btn_fail.setOnClickListener(this);

        userimage=(CircleButton)findViewById(R.id.user_image);
        number=(TextView)findViewById(R.id.number);



        try{
            if (joinApplys.getSenderInfo().getPhoto() != null) {
                bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(joinApplys.getSenderInfo().getPhoto()));
                userimage.setImageBitmap(bitmap);
            }
            else {
                userimage.setImageResource(R.mipmap.personhead);
            }
        }catch (Exception e){
            userimage.setImageResource(R.mipmap.personhead);
        }

        number.setText(joinApplys.getSenderInfo().getName());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
            case R.id.btn_accept:
               // doit("true");
                Accpet();
                break;
            case R.id.btn_fail:
                Fail();
//                doit("false");
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }


    private void Accpet()
    {
        //接受邀请进行数据传输
        doit("true");
    }

    private void Fail()
    {
        //删除这个列表

        doit("false");
    }

    private void doit(String bool){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_dealJoinApply(joinApplys.getSenderInfo().getPlayerID(),clubList.getClubID(),bool, Params.fc_dealJoinApply,this);
    }


    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Void> data) {
        Toast toast = Toast.makeText(getApplicationContext(), "处理成功", Toast.LENGTH_LONG);
        toast.show();
        loadingDialog.dismiss();
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
        Toast toast = Toast.makeText(getApplicationContext(), e_Msg, Toast.LENGTH_LONG);
        toast.show();
        loadingDialog.dismiss();
        this.finish();
    }
}
