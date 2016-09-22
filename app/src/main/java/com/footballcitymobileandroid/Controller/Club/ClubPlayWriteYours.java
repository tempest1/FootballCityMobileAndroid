package com.footballcitymobileandroid.Controller.Club;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.CustomView.LoadingDialog;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Clubs;
import com.footballcitymobileandroid.Entity.ClubEntity.join.AddSport;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/5/24.
 */
public class ClubPlayWriteYours extends Fragment implements View.OnClickListener ,ActionCallBackListener<BaseEntity<Clubs>>{
    private View view,dot;
    String[] clubList;  //默认为1,2,3,4,5;

    List<Clubs> clubs;
    int[] a=new int []{};
    private LoadingDialog loadingDialog;

    EditText  club_name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.club_play_write_your, container,false);

        init();
        return view;

    }
    private  void init()
    {
        loadingDialog = new LoadingDialog(getActivity(),R.drawable.loading);
//        dot=view.findViewById(R.id.v_dot2);
//        dot.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));
        club_name=(EditText)view.findViewById(R.id.club_name);
        club_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                AddSport.visitingTeam=club_name.getText().toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                AddSport.visitingTeam=club_name.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                AddSport.visitingTeam=club_name.getText().toString();
            }
        });
//        club_name.setOnClickListener(this);
//        club_name.setText(AddSport.visitingTeam);
    }


    private void selectdialog(final String text, final TextView textView, final String[] a){
        android.app.Dialog alertDialog_position = new AlertDialog.Builder(getActivity()).
                setTitle(text)
                .setItems(a, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MeMyInfo.this, hignList[which], Toast.LENGTH_SHORT).show();
                        textView.setText(a[which]);
                        AddSport.visitingTeam=a[which];

                    }
                }).
                        setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).
                        create();
        alertDialog_position.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.club_name:
                loadingDialog.show();
//                doit();


                break;
        }
    }

    private void doit(){
        AppAction appAction= Factory.createAppActionImpl(getActivity());
        appAction.fc_queryClubs("1","","",a,"", Params.fc_queryClubs,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<Clubs> data) {
//        Toast toast = Toast.makeText(getActivity(), e_Msg, Toast.LENGTH_LONG);
//        toast.show();
        loadingDialog.dismiss();
        clubs=data.getResponse().getClubs();
//        Log.i("name",clubs.get(0).getClubName());
//        Log.i("size", String.valueOf(clubs.size()));
        if (clubs!=null) {
            clubList=new String[clubs.size()];
            for (int i = 0; i < clubs.size(); i++) {
                clubList[i]=clubs.get(i).getClubName();
            }
        }else {
            clubList=new String[0];
        }
        selectdialog("请选择俱乐部？",club_name,clubList);   //使用clubs 只用name
    }

    /**
     * 请求失败
     *
     * @param e_Type 错误码
     * @param e_Msg  错误详情
     */
    @Override
    public void onFailure(String e_Type, String e_Msg) {
        Toast toast = Toast.makeText(getActivity(), e_Msg, Toast.LENGTH_LONG);
        toast.show();
        loadingDialog.dismiss();
    }
}
