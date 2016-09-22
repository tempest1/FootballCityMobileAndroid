package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.List;

/**
 * Created by zhoudi on 16/5/24.
 */
public class ClubFindNormal extends Activity implements View.OnClickListener,ActionCallBackListener<BaseEntity<Clubs>>{
    private Button detail_back;
    private Intent intent;
    private TextView find;
    EditText editText;
    TextView detail_title_center;
    int [] atytime=new int[]{0,0,0,0,0,0,0};

    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_club_normal);
        loadingDialog = new LoadingDialog(this,R.drawable.loading);

        find=(TextView)findViewById(R.id.find);
        find.setOnClickListener(this);
        editText= (EditText) findViewById(R.id.clubname);
        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("按名称查询");
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
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }
//    int[] age=new int[]{};
    String age="";
    private void doit(){
        loadingDialog.show();
        AppAction appAction=new AppActionImpl(this);

        appAction.fc_queryClubs("1",editText.getText().toString(),age,atytime,"", Params.fc_queryClubs,this);
        Log.i("clubname",editText.getText().toString());
    }
    @Override
    public void onSuccess(BaseEntity<Clubs> data) {
        List<Clubs> clubs=data.getResponse().getClubs();
        Log.i("size", String.valueOf(data.getResponse().getClubs().size()));
        intent=new Intent();
        intent.setClass(this,ClubFindList.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("Clubs", (Serializable) clubs);
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
