package com.footballcitymobileandroid.Controller.Club;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.footballcitymobileandroid.BLL.Adapter.ClubAdapter.ClubAdapter;
import com.footballcitymobileandroid.Controller.TestData.ClubInfo;
import com.footballcitymobileandroid.Controller.TestData.ClubInfoJoin;
import com.footballcitymobileandroid.Controller.TestData.ClubTest;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/5/28.
 */
public class ClubListYour extends Fragment {


    private View view;
    private ListView list;

    private ClubAdapter adapter;
    private List<ClubTest> data=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null){
                parent.removeView(view);
            }
            return view;
        } else {
            view = inflater.inflate(R.layout.club_list_your, container, false);
        }
        init();
        return view;
    }

    private void init()
    {
        data= ClubInfoJoin.ClubInfoData();
        list=(ListView)view.findViewById(R.id.list);
       // adapter=new ClubAdapter(getContext(),data);

       // list.setAdapter(adapter);

        list.setOnItemClickListener(info_click);


    }

    private AdapterView.OnItemClickListener info_click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            ClubTest clubTest=(ClubTest) adapterView.getAdapter().getItem(position);
            //  LogUtils.e(clubTest.getClubname());
            showInfoDetail(clubTest);
        }
    };
    private void showInfoDetail(ClubTest data) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), MeClub.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("club", data);
        intent.putExtras(bundle);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
    }
}
