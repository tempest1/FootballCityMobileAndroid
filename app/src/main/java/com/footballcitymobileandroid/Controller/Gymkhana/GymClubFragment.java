package com.footballcitymobileandroid.Controller.Gymkhana;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Interface.ActionCallBackListener;
import com.footballcitymobileandroid.BLL.Interface.AppAction;
import com.footballcitymobileandroid.BLL.Interface.AppActionImpl;
import com.footballcitymobileandroid.BLL.Util.CustomView.LoadingDialog;
import com.footballcitymobileandroid.DAL.Data.Uri.Params;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.ClubRankings;
import com.footballcitymobileandroid.R;

import java.util.List;


public class GymClubFragment extends Fragment implements View.OnClickListener,ActionCallBackListener<BaseEntity<ClubRankings>>{

	 Button btn_playerinfo;
	private View view;
	List<ClubRankings> data;
	private LoadingDialog loadingDialog;

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		view = inflater.inflate(R.layout.gym_club, container,false);
		init();
		return view;
	}

	void init()
	{
		loadingDialog = new LoadingDialog(getActivity(),R.drawable.loading);

		btn_playerinfo= (Button) view.findViewById(R.id.btn_playerinfo);
		btn_playerinfo.setOnClickListener(this);



	}


	@Override
	public void onClick(View v) {
		switch (v.getId())
		{
			case R.id.btn_playerinfo:
//				doit();

				Click();
				break;
		}
	}
	public static String shuju="data";
	private void Click() {
		Intent intent = new Intent();
		intent.setClass(getContext(), RankClub.class);
		Bundle mBundle=new Bundle();
//		mBundle.putSerializable(shuju,data);
//		intent.putExtras(mBundle);
		startActivity(intent);
		getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
	}

	private void doit(){
		AppAction appAction=new AppActionImpl(getActivity());
		appAction.fc_checkArenaClubRankings("1", Params.fc_checkArenaClubRankings,this);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
	}

	/**
	 * 处理成功
	 *
	 * @param data 返回数据
	 */
	@Override
	public void onSuccess(BaseEntity<ClubRankings> data) {
		Toast toast=Toast.makeText(getActivity(),"查询成功",Toast.LENGTH_LONG);
		toast.show();
		this.data=data.getResponse().getClubRankings();
//		Click();
		Intent intent = new Intent();
		intent.setClass(getContext(), RankClub.class);
		Bundle mBundle=new Bundle();
		mBundle.putSerializable(shuju,data);
		intent.putExtras(mBundle);
		startActivity(intent);
		getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
	}

	/**
	 * 请求失败
	 *
	 * @param e_Type 错误码
	 * @param e_Msg  错误详情
	 */
	@Override
	public void onFailure(String e_Type, String e_Msg) {
		Toast toast=Toast.makeText(getActivity(),e_Msg,Toast.LENGTH_LONG);
		toast.show();
	}
}
