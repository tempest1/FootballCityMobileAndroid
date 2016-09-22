package com.footballcitymobileandroid.BLL.Util.CustomView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Adapter.BaseCommonAdapter.CityAdapter;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.MeEntity.userInfo;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;


public class Dialogs extends DialogFragment {

    CityAdapter cityAdapter;
    userInfo userInfo;
    Boolean aBoolean=false;
    private List<String> data=new ArrayList<>();



    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        LinearLayout linearLayout= (LinearLayout) inflater.inflate(R.layout.dialog_name_layout,null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.show();
        builder.setView(linearLayout);
        final EditText message= (EditText) linearLayout.findViewById(R.id.message);
        Button btn_sure= (Button) linearLayout.findViewById(R.id.btn_sure);
        Button btn_fail= (Button) linearLayout.findViewById(R.id.btn_fail);
        final RadioGroup choose_radio=(RadioGroup)linearLayout.findViewById(R.id.choose_radio);
        final ListView high=(ListView)linearLayout.findViewById(R.id.high);
        final TextView title=(TextView)linearLayout.findViewById(R.id.title);




        if (MainApplication.dialog.equals("name"))
        {
            choose_radio.setVisibility(View.GONE);
            message.setVisibility(View.VISIBLE);
        }else if(MainApplication.dialog.equals("age"))
        {
            message.setVisibility(View.GONE);

            choose_radio.setVisibility(View.VISIBLE);
            title.setText("选择性别");
        }else if(MainApplication.dialog.equals("high"))
        {
            choose_radio.setVisibility(View.GONE);
            message.setVisibility(View.VISIBLE);
          //  message.setVisibility(View.GONE);
          //  choose_radio.setVisibility(View.GONE);
           // high.setVisibility(View.VISIBLE);
        //    data= CityInfo.CityInfoData();
            //cityAdapter=new CityAdapter(getActivity(),data);

        }else if(MainApplication.dialog.equals("brithday"))
        {
            message.setVisibility(View.VISIBLE);
            choose_radio.setVisibility(View.GONE);
           //message.setText("出生日期");

        }

        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainApplication.dialog.equals("name"))
                {
                    LogUtils.e(""+message.getText().toString());
//                    MainApplication.dialog=message.getText().toString();
                    userInfo=MainApplication.getUserInfo();
                    userInfo.setName(message.getText().toString());

                }else if(MainApplication.dialog.equals("age"))
                {
                    switch (choose_radio.getCheckedRadioButtonId()){
                        case R.id.grade_one:
                            LogUtils.e("男");
                            MainApplication.getUserInfo().setSex("男");
                            break;
                        case R.id.grade_two:
                            LogUtils.e("女");
                            MainApplication.getUserInfo().setSex("女");
                            break;
                        default:break;
                    }
                }
                aBoolean=true;
               dismiss();
            }
        });
        btn_fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle(R.string.newUpdateAvailable);
//        builder.setMessage(getArguments().getString(Check.APK_UPDATE_CONTENT))
//                .setPositiveButton(R.string.dialogPositiveButton, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // FIRE ZE MISSILES!
//
//                        dismiss();
//                    }
//                })
//                .setNegativeButton(R.string.dialogNegativeButton, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User cancelled the dialog
//                        goToDownload();
//                        dismiss();
//                    }
//                });
        // Create the AlertDialog object and return it

        return builder.create();
    }


    public  void showDialog(Activity mContext) {
        Dialogs d = new Dialogs();
        d.show(mContext.getFragmentManager(), null);
    }



}
