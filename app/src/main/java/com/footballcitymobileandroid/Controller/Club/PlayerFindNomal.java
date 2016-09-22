package com.footballcitymobileandroid.Controller.Club;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.CustomView.LoadingDialog;
import com.footballcitymobileandroid.Controller.Me.MeClubNumberList;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.MeEntity.Players;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhoudi on 16/5/28.
 */
public class PlayerFindNomal  extends Activity implements View.OnClickListener,ActionCallBackListener<BaseEntity<Players>>{

    private Button detail_back;
    private Intent intent;
    private TextView find,detail_title_center;
    EditText editText;
    String type;
    Object value;
    private LoadingDialog loadingDialog;
    ClubList clubList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_find_normal);

        clubList= (ClubList) getIntent().getSerializableExtra("clublist");
        loadingDialog = new LoadingDialog(this,R.drawable.loading);
        find=(TextView)findViewById(R.id.find);
        find.setOnClickListener(this);

        editText= (EditText) findViewById(R.id.findnomal);

        detail_back=(Button)findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        detail_title_center=(TextView)findViewById(R.id.detail_title_center);
        detail_title_center.setText("普通球员查找");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.find:

                intent=new Intent();
                intent.setClass(this,MeClubNumberList.class);
                dotext(editText.getText().toString());
                loadingDialog.show();
                doit();
                break;
            case R.id.detail_back:
                this.finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
        }
    }

    public  void doit(){
        AppAction appAction=Factory.createAppActionImpl(this);
        appAction.fc_queryPlayers(type,value, Params.fc_queryPlayers,this);
    }


    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Players> data) {
        Toast toast = Toast.makeText(getApplicationContext(), "查找成功", Toast.LENGTH_LONG);
        toast.show();
        loadingDialog.dismiss();
        Bundle bundle=new Bundle();
        bundle.putSerializable("player", (Serializable) data.getResponse().getPlayers());
        bundle.putSerializable("clublist",clubList);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
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
    }


    //区别中文与数字
    private void dotext(String txt){
//        String txt = edInput.getText().toString();

        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(txt);
        if(m.matches() ){
//            Toast.makeText(this,"输入的是数字", Toast.LENGTH_SHORT).show();
          p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
          m = p.matcher(txt);
            if (!m.matches()&&!TextUtils.isEmpty(txt)) {
                Toast.makeText(this,"手机号格式不正确", Toast.LENGTH_SHORT).show();

             }else {
                type="phone";
                value=txt;
            }
        }
        else {
            type="name";
            value=txt;
        }
//        p=Pattern.compile("[a-zA-Z]");
//        m=p.matcher(txt);
//        if(m.matches()){
//            Toast.makeText(this,"输入的是字母", Toast.LENGTH_SHORT).show();
//        }
//        p=Pattern.compile("[\u4e00-\u9fa5]");
//        m=p.matcher(txt);
//        if(m.matches()){
////            Toast.makeText(this,"输入的是汉字", Toast.LENGTH_SHORT).show();
//            type="name";
//            value=txt;
//        }
    }

}
