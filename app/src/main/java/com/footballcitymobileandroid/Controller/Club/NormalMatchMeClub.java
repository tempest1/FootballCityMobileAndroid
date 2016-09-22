package com.footballcitymobileandroid.Controller.Club;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.LoadingDialog;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.SportDetail;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/5/30.
 */
@SuppressLint("ValidFragment")

public class NormalMatchMeClub extends Fragment {
    private View view,dot;
    List<AranaMatchs> aranaMatchses;
    ClubList clubList;
    private LoadingDialog loadingDialog;

    TextView club_name;
    EditText point;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.normal_match_me_club, container,false);
        init();
        return view;

    }
    public NormalMatchMeClub(){

    }
    NormalMatchMeClub(ClubList clubList)
    {
        this.clubList=clubList;
    }

    private  void init()
    {
        SportDetail.homescore="";
        loadingDialog = new LoadingDialog(getActivity(),R.drawable.loading);
        dot=view.findViewById(R.id.v_dot0);
        dot.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));

        club_name=(TextView)view.findViewById(R.id.club_name);
        club_name.setText(""+clubList.getClubName());
        point= (EditText) view.findViewById(R.id.point);
        SportDetail.homescore= String.valueOf(point.getText());
        Log.i("score",SportDetail.homescore);
        LogUtils.e(clubList.toString());
        point.addTextChangedListener(watcher);
    }

    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub
            SportDetail.homescore= String.valueOf(point.getText());
            Log.i("score",SportDetail.homescore);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            SportDetail.homescore= String.valueOf(point.getText());
            Log.i("score",SportDetail.homescore);
        }
    };
//    /**
//     * 处理成功
//     *
//     * @param data 返回数据
//     */
//    @Override
//    public void onSuccess(BaseEntity<Void> data) {
//        loadingDialog.dismiss();
//        Toast toast = Toast.makeText(getApplicationContext(), "俱乐部球员信息"+e_Msg, Toast.LENGTH_LONG);
//        toast.show();
//    }
//
//    /**
//     * 请求失败
//     *
//     * @param e_Type 错误码
//     * @param e_Msg  错误详情
//     */
//    @Override
//    public void onFailure(String e_Type, String e_Msg) {
//        loadingDialog.dismiss();
//        Toast toast = Toast.makeText(getApplicationContext(), "俱乐部球员信息"+e_Msg, Toast.LENGTH_LONG);
//        toast.show();
//    }
}
