package com.footballcitymobileandroid.Controller.Club;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.NormalMatchAdapter;
import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.Factory;
import com.footballcitymobileandroid.BLL.Util.Pull.PullToRefreshLayout;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.SportDetail;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhoudi on 16/7/13.
 */
@SuppressLint("ValidFragment")

public class NormalMatchNoStart extends Fragment implements PullToRefreshLayout.OnRefreshListener,ActionCallBackListener<BaseEntity<SportDetail>> {
    private View view;

    private ListView list;
    private NormalMatchAdapter adapter;


    List<SportDetail> sportDetail;
    private PullToRefreshLayout layout;
    private static int currentState = PullToRefreshLayout.PULL_REFRESH;

    ClubList clubList;
    public NormalMatchNoStart(ClubList clubList){
        this.clubList=clubList;
    }
    public NormalMatchNoStart(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.normal_match_start, container,false);
        init();
        return view;

    }
    private  void init()
    {
        list=(ListView)view.findViewById(R.id.pull_list);


        list.setOnItemClickListener(info_click);

        layout = (PullToRefreshLayout) view.findViewById(R.id.refresh_view);
        layout.setOnRefreshListener(this);
    }

    private AdapterView.OnItemClickListener info_click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            SportDetail sportDetail=(SportDetail) adapterView.getAdapter().getItem(position);
            //  LogUtils.e(clubTest.getClubname());
            showInfoDetail(sportDetail);
        }
    };
    private void showInfoDetail(SportDetail data) {


        Intent intent=new Intent();
        intent.setClass(getActivity(), NormalMatchInfoDeal.class);
        Bundle bundle = new Bundle();

        bundle.putString("type", "1");
        bundle.putSerializable("clublist",clubList);
        bundle.putSerializable("SportDetail", (Serializable) data);
//        Intent intent = new Intent();
//        intent.setClass(this, NormalMathMeun.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("club", data);
        intent.putExtras(bundle);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        layout.autoRefresh();
    }
    /**
     * 刷新操作
     *
     * @param pullToRefreshLayout
     */
    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        currentState = PullToRefreshLayout.PULL_REFRESH;
        doit();


    }

    /**
     * 加载操作
     *
     * @param pullToRefreshLayout
     */
    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        currentState = PullToRefreshLayout.PULL_LOAD;

        layout.loadmoreFinish(PullToRefreshLayout.SUCCEED);

       // layout.loadmoreFinish(PullToRefreshLayout.FAIL);

    }

    private void doit(){
        AppAction appAction= Factory.createAppActionImpl(getActivity());
        appAction.fc_checkSportDetail(clubList.getClubID(),"1","1", Params.fc_checkSportDetail,this);

    }
    /**
     * 处理成功
     *
     * @param data 返回数据
     */
    @Override
    public void onSuccess(BaseEntity<SportDetail> data) {
        sportDetail=data.getResponse().getSportDetail();

        if (sportDetail!=null) {
            adapter = new NormalMatchAdapter(getActivity(), sportDetail, clubList);
            list.setAdapter(adapter);
        }
        layout.refreshFinish(PullToRefreshLayout.SUCCEED);

    }

    /**
     * 请求失败
     *
     * @param e_Type 错误码
     * @param e_Msg  错误详情
     */
    @Override
    public void onFailure(String e_Type, String e_Msg) {
        try {
            Toast toast = Toast.makeText(getActivity(), e_Msg, Toast.LENGTH_LONG);
            toast.show();
            layout.refreshFinish(PullToRefreshLayout.FAIL);
        }catch (Exception e){

        }
    }
}
