package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.ClubNumberPlaceChooseAdapter;
import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.CustomView.LoadingDialog;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubMemb;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Send;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/6/28.
 */
public class ClubNumberPlaceChoose extends Activity implements View.OnClickListener,ActionCallBackListener<BaseEntity<Void>> {

    private ListView list;
    Button detail_back,detail_title;
    TextView detail_title_center;

    ClubList clubList;
    List<ClubMemb> clubMemb;
    String position;
    String rString;
    private LoadingDialog loadingDialog;
   private ClubNumberPlaceChooseAdapter adapter;

    List<Send> sendList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_number_place_choose);
        loadingDialog = new LoadingDialog(this,R.drawable.loading);
        clubList= (ClubList) getIntent().getSerializableExtra("clubList");
        LogUtils.e(""+clubList.getClubID());
        clubMemb= (List<ClubMemb>) getIntent().getSerializableExtra("clubMemb");
        position= (String) getIntent().getSerializableExtra("position");
        if(position.equals("1")){
            rString="前锋";
        }else if(position.equals("2")){
            rString="中锋";
        }else if(position.equals("3")){
            rString="后卫";
        }else if(position.equals("4")){
            rString="门将";
        }
//        Log.i("positionID",positionID);
        list=(ListView)findViewById(R.id.pull_list);
        if (clubMemb!=null) {
            adapter = new ClubNumberPlaceChooseAdapter(this, clubMemb);
            list.setAdapter(adapter);
        }else {

        }

       // list.setOnItemClickListener(info_click);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText(rString+"人员选择");

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title=(Button)findViewById(R.id.detail_title);
        detail_title.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
            case R.id.detail_title:
                loadingDialog.show();
                doit_checkClubMemb();
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

    private void doit_checkClubMemb(){
        AppAction appAction= Factory.createAppActionImpl(this);
        LogUtils.e(""+clubMemb.get(0).getClubMembID());
        LogUtils.e(""+clubList.getClubID());
        LogUtils.e(""+position);

        int x=0;
        for (int i=0;i<MainApplication.CHOOSE_NUMBER.length;i++)
        {
            if (MainApplication.CHOOSE_NUMBER[i]==1){
                x=i;
                Send send=new Send();
                send.setClubMembID(clubMemb.get(x).getClubMembID());
                send.setPositionID(position);
                sendList.add(send);
            }
        }
        LogUtils.e(""+sendList.toString());
        appAction.fc_setPosition(clubList.getClubID(),sendList, Params.fc_setPosition,this);//clubMemb.get(x).getClubMembID(),position
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Void> data) {
        loadingDialog.dismiss();
        Toast toast = Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_LONG);
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
        loadingDialog.dismiss();
        Toast toast = Toast.makeText(getApplicationContext(), e_Msg, Toast.LENGTH_LONG);
        toast.show();
    }


}
