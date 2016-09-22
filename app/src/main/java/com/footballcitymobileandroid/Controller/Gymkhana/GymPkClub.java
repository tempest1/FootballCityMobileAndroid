package com.footballcitymobileandroid.Controller.Gymkhana;

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
import com.footballcitymobileandroid.Controller.Base.InvestmentActivity;
import com.footballcitymobileandroid.Controller.TestData.ClubTest;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.LogUtil.StringUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Clubs;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.MatchMsg;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/21.
 */
public class  GymPkClub extends InvestmentActivity implements View.OnClickListener,ActionCallBackListener<BaseEntity<Void>>{

    Button change;
    TextView detail_title_center;
    Button detail_back;
    ClubTest club;
    Clubs clubs;
    ClubList clubList;
    Bitmap bitmap;
    private LoadingDialog loadingDialog;

    CircleButton image;
    private TextView place,time,play_way,pay_way,club_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gym_pk_club);
        inData();
        init();
    }
    private void init()
    {
        loadingDialog = new LoadingDialog(this,R.drawable.loading);
        clubs= (Clubs) getIntent().getSerializableExtra("Clubs");
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        change=(Button)findViewById(R.id.change);
        change.setOnClickListener(this);
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("匹配俱乐部");
        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        place=(TextView)findViewById(R.id.way);
        time=(TextView)findViewById(R.id.time);
        play_way=(TextView)findViewById(R.id.play_way);
        pay_way=(TextView)findViewById(R.id.pay_way);
        club_name=(TextView)findViewById(R.id.club_name);
        image=(CircleButton)findViewById(R.id.club_image);

        club_name.setText(clubs.getClubName());
        if (clubs.getLogo() != null&&!clubs.getLogo().equals("")) {
            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(clubs.getLogo()));
            image.setImageBitmap(bitmap);
        }
        else {
            image.setImageResource(R.mipmap.term_sign);
        }

        LogUtils.e(MatchMsg.fieldID);
        int x= Integer.parseInt(MatchMsg.fieldID)+1;
        place.setText(StringUtils.AtyField(x+""));

        LogUtils.e(MatchMsg.matchingDate);
        time.setText(MatchMsg.matchingDate+" "+MatchMsg.matchingTime.substring(0,5));
        play_way.setText(MatchMsg.matchRule+"v"+MatchMsg.matchRule);
        if(MatchMsg.costModeKey.equals("0"))
        {
            pay_way.setText("主队支付");
        }else if (MatchMsg.costModeKey.equals("1")){
            pay_way.setText("客队支付");

        }


    }
    private void inData()
    {
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        club = (ClubTest) bundle.getSerializable("club");
//        LogUtils.e(club.getClubname());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.change:
//                Intent intent = new Intent();
//                intent.setClass(this, GymPkDealNew.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("choose","challenge");
//                intent.putExtras(bundle);
//                startActivity(intent);
//                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                doit();

                break;
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
        }
    }

    List<String> reciver=new ArrayList<>();

    private void doit(){
        loadingDialog.show();

        reciver.add(clubs.getClubID());
        LogUtils.e(""+clubs.getClubID());
        AppAction appAction=Factory.createAppActionImpl(this);
        appAction.fc_sendMatchingMsg(clubList.getClubID(),"1", MatchMsg.matchingDate,MatchMsg.matchingTime,MatchMsg.matchRule,MatchMsg.costModeKey,reciver, Params.fc_sendMatchingMsg,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Void> data) {
        Toast toast = Toast.makeText(this, "发送成功", Toast.LENGTH_LONG);
        toast.show();
        this.finish();
        loadingDialog.dismiss();

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

}
