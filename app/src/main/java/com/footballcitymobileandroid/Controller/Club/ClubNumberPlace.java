package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.ClubNumberPlaceAdapter;
import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubMemb;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhoudi on 16/6/27.
 */
public class ClubNumberPlace extends Activity implements View.OnClickListener{

    int times;
    Button back;
    Intent intent;
    Bundle bunder;
    TextView add_door,add_later,add_center,add_leader,detail_title_center;

    ClubNumberPlaceAdapter leaderAdapter,doorAdapter,centerAdapter,laterAdapter;

    ClubList clubList;
    List<ClubMemb> clubMemb;

    private ListView door_list,later_list,center_list,head_list,null_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_number_place);
        init();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (times!=0)
        {
            doit_checkClubMemb();

        }
        times++;

    }

    private void  setAdapter()
    {
        leaderAdapter=new ClubNumberPlaceAdapter(this,clubMemb,"1");//前锋
        doorAdapter=new ClubNumberPlaceAdapter(this,clubMemb,"4");//门将
        centerAdapter=new ClubNumberPlaceAdapter(this,clubMemb,"2");//中锋
        laterAdapter=new ClubNumberPlaceAdapter(this,clubMemb,"3");//后卫

        door_list.setAdapter(doorAdapter);
        center_list.setAdapter(centerAdapter);
        later_list.setAdapter(laterAdapter);
        head_list.setAdapter(leaderAdapter);
    }


    private void init()
    {
        times=0;
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");   //本俱乐部信息
        clubMemb= (List<ClubMemb>) getIntent().getSerializableExtra("clubmemb");  //成员信息


        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(this);

        add_door=(TextView)findViewById(R.id.add_door);
        add_later=(TextView)findViewById(R.id.add_later);
        add_center=(TextView)findViewById(R.id.add_center);
        add_leader=(TextView)findViewById(R.id.add_leader);
        add_door.setOnClickListener(this);
        add_later.setOnClickListener(this);
        add_center.setOnClickListener(this);
        add_leader.setOnClickListener(this);
        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("球员位置信息");

        door_list=(ListView)findViewById(R.id.door_list);
        later_list=(ListView)findViewById(R.id.later_list);
        center_list=(ListView)findViewById(R.id.center_list);
        head_list=(ListView)findViewById(R.id.head_list);

        for (int i=0;i<clubMemb.size();i++) {
            LogUtils.e(clubMemb.get(i).toString());
        }

        setAdapter();


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }
    Bundle bundle=new Bundle();
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;

            case R.id.add_door:
                intent = new Intent();
                intent.setClass(this, ClubNumberPlaceChoose.class);

                bundle.putSerializable("clubList",clubList);
                bundle.putSerializable("clubMemb", (Serializable) clubMemb);
                bundle.putSerializable("position","4");
//                bunder = new Bundle();
//                bunder.putString("title", "门将");
                intent.putExtras( bundle );
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.add_later:
                intent = new Intent();
                intent.setClass(this, ClubNumberPlaceChoose.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("club", data);
//        intent.putExtras(bundle);
//                Bundle bundles=new Bundle();
                bundle.putSerializable("clubList",clubList);
                bundle.putSerializable("clubMemb", (Serializable) clubMemb);
                bundle.putSerializable("position","3");
//                bunder= new Bundle();
//                bunder.putString("title", "后卫");
                intent.putExtras( bundle );
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.add_center:
                intent = new Intent();
                intent.setClass(this, ClubNumberPlaceChoose.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("club", data);
//        intent.putExtras(bundle);
//                bunder= new Bundle();
//                bunder.putString("title", "中场");
//                intent.putExtras( bunder );
                bundle.putSerializable("clubList",clubList);
                bundle.putSerializable("clubMemb", (Serializable) clubMemb);
                bundle.putSerializable("position","2");
                intent.putExtras( bundle );
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.add_leader:
                intent = new Intent();
                intent.setClass(this, ClubNumberPlaceChoose.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("club", data);
//        intent.putExtras(bundle);
//                bunder= new Bundle();
//                bunder.putString("title", "前锋");
//                intent.putExtras( bunder );
                bundle.putSerializable("clubList",clubList);
                bundle.putSerializable("clubMemb", (Serializable) clubMemb);
                bundle.putSerializable("position","1");
                intent.putExtras( bundle );
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
        }
    }
    private void doit_checkClubMemb(){
        AppAction appAction= Factory.createAppActionImpl(this);
        appAction.fc_checkClubMemb(clubList.getClubID(), Params.fc_checkClubMemb,clublisten);
    }

    public ActionCallBackListener<BaseEntity<ClubMemb>> clublisten=new ActionCallBackListener<BaseEntity<ClubMemb>>(){

        /**
         * 处理成功
         *
         * @param data 返回数据
         */
        @Override
        public void onSuccess(BaseEntity<ClubMemb> data) {
            clubMemb=data.getResponse().getClubMemb();
            Toast toast = Toast.makeText(getApplicationContext(), "俱乐部球员信息获取成功", Toast.LENGTH_LONG);
            setAdapter();
            toast.show();


        }

        /**
         * 请求失败
         *
         * @param e_Type 错误码
         * @param e_Msg  错误详情
         */
        @Override
        public void onFailure(String e_Type, String e_Msg) {
            Toast toast = Toast.makeText(getApplicationContext(), "俱乐部球员信息"+e_Msg, Toast.LENGTH_LONG);
            toast.show();
        }
    };
}
