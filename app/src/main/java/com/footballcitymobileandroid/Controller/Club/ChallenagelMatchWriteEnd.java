package com.footballcitymobileandroid.Controller.Club;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/5/30.
 */
public class ChallenagelMatchWriteEnd extends FragmentActivity implements View.OnClickListener ,ActionCallBackListener<BaseEntity<Void>>{

    List<AranaMatchs> aranaMatchses;
    ClubList clubList;




    private Button detail_title,detail_back;
    private TextView detail_title_center,club_name;
    private EditText point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenage_match_write_end);
        init();
    }
    private void init()
    {
        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        aranaMatchses= (List<AranaMatchs>) getIntent().getSerializableExtra("AranaMatchs");   //选择第0 个
        Log.i("shujuid","shuju"+aranaMatchses.get(0).getAreanaMatchID()+"    "+clubList.getClubID());
        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);
        detail_title=(Button)findViewById(R.id.detail_title);
        detail_title.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("录入比赛成绩");

        club_name=(TextView)findViewById(R.id.club_name);
        point=(EditText)findViewById(R.id.point);
        club_name.setText(clubList.getClubName());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.detail_title:
                // 处理数据
                doit();
                //结束activity
//                this.finish();
                break;
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }
    private void doit(){
        if(point.equals(""))
        {
            Toast toast = Toast.makeText(this, "请输入赢球数在确定", Toast.LENGTH_LONG);
            toast.show();
        }else {
            AppAction appAction = Factory.createAppActionImpl(this);
            appAction.fc_editGoals(aranaMatchses.get(0).getAreanaMatchID(), clubList.getClubID(), point.getText().toString(), Params.fc_editGoals, this);
        }
    }
    @Override
    public void onSuccess(BaseEntity<Void> data) {
        Toast toast = Toast.makeText(this, "编辑成功", Toast.LENGTH_LONG);
        toast.show();
        this.finish();
    }

    @Override
    public void onFailure(String e_Type, String e_Msg) {
        Toast toast = Toast.makeText(this, e_Msg, Toast.LENGTH_LONG);
        toast.show();
    }
}
