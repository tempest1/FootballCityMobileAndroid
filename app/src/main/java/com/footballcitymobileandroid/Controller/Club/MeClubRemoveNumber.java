package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.MeClubNumberAdapter;
import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.Controller.TestData.MeClubNumberInfo;
import com.footballcitymobileandroid.Controller.TestData.MeClubNumbers;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubMemb;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/5/26.
 */
public class MeClubRemoveNumber extends Activity implements View.OnClickListener,ActionCallBackListener<BaseEntity<Void>> {
    private Button detail_title,detail_back;
    private TextView detail_title_center;
    private ListView listView;
    MeClubNumberAdapter adapter;

    ClubList clubList;
    List<ClubMemb> clubMemb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_club_remove_number);

        init();
    }

    private void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        clubMemb= (List<ClubMemb>) getIntent().getSerializableExtra("clubmemb");
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("删除成员");

        detail_title=(Button)findViewById(R.id.detail_title);
        detail_title.setOnClickListener(this);

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        listView=(ListView)findViewById(R.id.my_club_list);
//        List<MeClubNumbers> info_data= MeClubNumberInfo.NumberInfoData();
//        LogUtils.e(info_data.get(0).getName());
        if (clubMemb!=null) {

            for (int i = 0; i < clubMemb.size(); i++) {
                LogUtils.e(clubMemb.get(i).getName());
                if (clubMemb.get(i).getName().equals(clubList.getLeaderName())) {
                    clubMemb.remove(i);
                }
            }
            adapter = new MeClubNumberAdapter(this, clubMemb);
            listView.setAdapter(adapter);
        }
        detail_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.detail_title:
                for (int i=0;i<MeClubNumberAdapter.states.size();i++)
                {
                    if (MeClubNumberAdapter.states.get(String.valueOf(i))==true)
                    {
                        LogUtils.e(String.valueOf(i));
                        doit_fireClubMemb(i);
                    }

                }                break;
            case R.id.detail_back:

                this.finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
        }
    }
    private void doit_fireClubMemb(int i) {


        AppAction appAction = Factory.createAppActionImpl(this);
        appAction.fc_fireClubMemb(clubList.getClubID(),clubMemb.get(0).getClubMembID(), Params.fc_fireClubMemb, this);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Void> data) {
        Toast toast = Toast.makeText(this, "删除成功", Toast.LENGTH_LONG);
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
