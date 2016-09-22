package com.footballcitymobileandroid.Controller.Gymkhana;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.CustomView.LoadingDialog;
import com.footballcitymobileandroid.BLL.Util.Updata.CheckVersion;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Controller.Club.MeClubList;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.MeEntity.App;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhoudi on 16/6/12.
 */
public class GymkhanaFragment extends Fragment implements View.OnClickListener,ActionCallBackListener<BaseEntity<App>>  {
    private Button rank,challeage,rlue;
    private View view;
    private Intent intent;
    private LoadingDialog loadingDialog;

    TextView seas;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.gymkhana_fragment, container,false);
        init();
        return view;
    }

    void init()
    {

        doit();
        loadingDialog = new LoadingDialog(getActivity(),R.drawable.loading);
        rank=(Button)view.findViewById(R.id.rank);
        rank.setOnClickListener(this);

        challeage=(Button)view.findViewById(R.id.challeage);
        challeage.setOnClickListener(this);

        rlue=(Button)view.findViewById(R.id.rlue);
        rlue.setOnClickListener(this);
        
        rank.getBackground().setAlpha(100);

        LogUtils.e(MainApplication.SEASON_NAME);
        seas=(TextView)view.findViewById(R.id.seas);
        seas.setText(MainApplication.SEASON_NAME);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.rank:           //排行榜
                intent = new Intent();
                intent.setClass(getActivity(), RankActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.challeage:           //挑战
                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
                loadingDialog.show();
                do_queryclublist();
                intent.setClass(getActivity(), MeClubList.class);

                break;
            case R.id.rlue:               //规则
                intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);



                intent.setClass(getActivity(), RuleActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
        }
    }




    private void doit(){
        AppAction appAction= Factory.createAppActionImpl(getActivity());
        appAction.fc_latestVersion(Params.fc_latestVersion,this);
    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<App> data) {
        App app;
        app= (App) data.getResponse().getApp();
        CheckVersion check = new CheckVersion(getActivity(),app);
        check.parseJson(app);
        LogUtils.e("登录"+app.toString());
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
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    private void do_queryclublist() {
        AppAction appAction = Factory.createAppActionImpl(getActivity());
        appAction.fc_getClubList(Params.fc_getClubList, clublistener);//"leader",
    }

    public ActionCallBackListener<BaseEntity<ClubList>> clublistener=new ActionCallBackListener<BaseEntity<ClubList>>(){

        /**
         * 处理成功
         *
         * @param data 返回数据
         */
        @Override
        public void onSuccess(BaseEntity<ClubList> data) {
            Toast toast = Toast.makeText(getActivity(), "俱乐部获取成功", Toast.LENGTH_LONG);
            toast.show();
            Bundle bundle=new Bundle();
            MainApplication.setClubList((List<ClubList>) data.getResponse().getClubList());                      //////////////
            List<ClubList> clubList= data.getResponse().getClubList();
            LogUtils.e(""+clubList.size());
            bundle.putSerializable("clublist", (Serializable) clubList);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
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
            Toast toast = Toast.makeText(getActivity(), "俱乐部"+e_Msg, Toast.LENGTH_LONG);
            toast.show();
            loadingDialog.dismiss();
        }
    };
}
