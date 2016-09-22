package com.footballcitymobileandroid.Controller.Gymkhana;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.footballcitymobileandroid.BLL.Adapter.GymkhanaAdapter.GymRankAdapter;
import com.footballcitymobileandroid.Controller.Club.MeClubList;
import com.footballcitymobileandroid.R;


public class PkFragment extends Fragment implements View.OnClickListener{
	View view,dot0;
	Button btn_pk;

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		super.onCreateView(inflater, container, savedInstanceState);
		view = inflater.inflate(R.layout.gym_pk, container,false);
		init();
		return view;
	}
	void init()
	{
		dot0=view.findViewById(R.id.v_dot0);
		dot0.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focused));

		btn_pk=(Button) view.findViewById(R.id.btn_pk);
		btn_pk.setOnClickListener(this);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId())
		{
			case R.id.btn_pk:
				Intent intent = new Intent();
//		Bundle bundle=new Bundle();
//		bundle.putString("type", "forget");
//		intent.putExtras(bundle);
				intent.setClass(getContext(), MeClubList.class);
				startActivity(intent);
				getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
				break;
		}
	}
}
