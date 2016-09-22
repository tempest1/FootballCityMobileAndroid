package com.footballcitymobileandroid.Controller.Me;

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
import com.footballcitymobileandroid.BLL.Interface.AppActionImpl;
import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.join.Invitations;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/5/27.
 */
public class MeInvestment extends Activity implements View.OnClickListener,ActionCallBackListener<BaseEntity<Void>>{


    private Button btn_accept,detail_back,btn_fail;
    private TextView detail_title_center;
    private Intent intent;

    CircleButton user_image;
    TextView club;

    Invitations invitations;

    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_invest);
        init();
    }
    private void init()
    {
        invitations= (Invitations) getIntent().getSerializableExtra("invitations");
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("邀请函");

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        btn_accept=(Button)findViewById(R.id.btn_accept);
        btn_accept.setOnClickListener(this);

        btn_fail=(Button)findViewById(R.id.btn_fail);
        btn_fail.setOnClickListener(this);

        user_image=(CircleButton)findViewById(R.id.user_image);
        club=(TextView)findViewById(R.id.club);

        club.setText(invitations.getSenderInfo().getClubName());

        if (invitations.getSenderInfo().getPhoto() != null) {
            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(invitations.getSenderInfo().getPhoto()));
            user_image.setImageBitmap(bitmap);
        }
        else {
            user_image.setImageResource(R.mipmap.personhead);
        }


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
                doit("true");
//                Accpet();
                break;
            case R.id.btn_fail:
//                Fail();
                doit("false");
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }
    private void doit(String a){
        AppAction appAction=new AppActionImpl(this);
        appAction.fc_dealInvitation(invitations.getSenderInfo().getClubID(),a, Params.fc_dealInvitation,this);
        //第一个参数为获取的clubid
    }

//    private void Accpet()
//    {
//
//        //接受邀请进行数据传输
//
//    }
//
//    private void Fail()
//    {
//        //删除这个列表
//        this.finish();
//    }

    @Override
    public void onSuccess(BaseEntity<Void> data) {
        Toast toast=Toast.makeText(this,"处理成功",Toast.LENGTH_LONG);
        toast.show();
        this.finish();  //退出
    }

    @Override
    public void onFailure(String e_Type, String e_Msg) {
        Toast toast=Toast.makeText(this,e_Msg,Toast.LENGTH_LONG);
        toast.show();
    }
}
