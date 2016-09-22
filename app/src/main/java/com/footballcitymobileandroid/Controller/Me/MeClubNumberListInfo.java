package com.footballcitymobileandroid.Controller.Me;

import android.app.Activity;
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
import com.footballcitymobileandroid.Entity.MeEntity.Players;
import com.footballcitymobileandroid.R;

/**
 * Created by zhoudi on 16/6/7.
 */
public class MeClubNumberListInfo extends Activity implements View.OnClickListener,ActionCallBackListener<BaseEntity<Void>>{

    Button detail_back,investment;
    private LoadingDialog loadingDialog;
    ClubList clubList;
    TextView place,age,name;
    CircleButton image;
    Players players;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_club_number_info);
        init();
    }

    private void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        loadingDialog = new LoadingDialog(this,R.drawable.loading);
        players= (Players) getIntent().getSerializableExtra("player");
        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        investment=(Button)findViewById(R.id.investment);
        investment.setOnClickListener(this);

        place=(TextView)findViewById(R.id.way);
        age=(TextView)findViewById(R.id.age);
        name=(TextView)findViewById(R.id.name);
        image=(CircleButton)findViewById(R.id.image);

        try {
            place.setText(players.getPosition().get(0));
        }catch (Exception e) {
            place.setText("未设置");
        }
        age.setText(players.getAge()+"岁");
        name.setText(players.getName());
        if (players.getPhoto() != null) {
            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(players.getPhoto()));
            image.setImageBitmap(bitmap);
        }
        else {
            image.setImageResource(R.mipmap.personhead);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
            case R.id.investment:
                doit();
//                this.finish();
//                Toast.makeText(this,"邀请成功",Toast.LENGTH_SHORT).show();

                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

    private void doit(){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_createInvitation(clubList.getClubID(),players.getPlayerID(),Params.fc_createInvitation,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Void> data) {
        Toast toast = Toast.makeText(this, "发送邀请成功", Toast.LENGTH_LONG);
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
        Toast toast = Toast.makeText(this, e_Msg, Toast.LENGTH_LONG);
        toast.show();
        loadingDialog.dismiss();
    }
}
