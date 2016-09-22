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
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.PlayerRankings;
import com.footballcitymobileandroid.R;

import java.io.Serializable;
import java.util.List;


public class PlayerFragment extends Fragment implements View.OnClickListener,ActionCallBackListener<BaseEntity<PlayerRankings>> {

	Button btn_playerprice;
	View view;
	View dot2;
	List<PlayerRankings> playerRankings;
	private LoadingDialog loadingDialog;

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		view = inflater.inflate(R.layout.gym_player, container,false);
		init();
		return view;
	}

	void init()
	{
		loadingDialog = new LoadingDialog(getActivity(),R.drawable.loading);

		btn_playerprice= (Button) view.findViewById(R.id.btn_playerprice);
		btn_playerprice.setOnClickListener(this);


	}

	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId())
		{
			case R.id.btn_playerprice:
				//doit();
				Intent intent = new Intent();
				intent.setClass(getContext(), PlayerPirce.class);
//				Bundle bundle=new Bundle();
//				bundle.putSerializable("PlayerRankings", (Serializable) playerRankings);
//				intent.putExtras(bundle);
				startActivity(intent);
				getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);

				break;
		}
	}

	private void doit(){
		loadingDialog.show();

		AppAction appAction=new AppActionImpl(getActivity());
		appAction.fc_checkArenaPlayerRankings("1", Params.fc_checkArenaPlayerRankings,this);
	}
	/**
	 * 处理成功
	 *
	 * @param data 返回数据
	 */
	@Override
	public void onSuccess(BaseEntity<PlayerRankings> data) {
		playerRankings=data.getResponse().getPlayerRankings();
		Toast toast=Toast.makeText(getActivity(),"查询成功",Toast.LENGTH_LONG);
		toast.show();
		Intent intent = new Intent();
		intent.setClass(getContext(), PlayerPirce.class);
		Bundle bundle=new Bundle();
		bundle.putSerializable("PlayerRankings", (Serializable) playerRankings);
		intent.putExtras(bundle);
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
		Toast toast=Toast.makeText(getActivity(),e_Msg,Toast.LENGTH_LONG);
		toast.show();
		loadingDialog.dismiss();
	}
}
