package com.footballcitymobileandroid.BLL.Adapter.GymkhanaAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.BaseViewHolder;
import com.footballcitymobileandroid.Controller.TestData.ClubTest;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/5/19.
 */
public class GymPkRankAdapter extends BaseAdapter {

    private List<ClubTest> listinfo;
    private Context Context;

    GymPkRankAdapter()
    {}

    public GymPkRankAdapter(Context Context, List<ClubTest> listinfo)
    {
        this.Context=Context;
        this.listinfo=listinfo;
    }

    @Override
    public int getCount() {
        return listinfo.size();
    }

    @Override
    public Object getItem(int i) {
        return listinfo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        if(i==0)
        {
            if (convertView == null) {
                convertView = LayoutInflater.from(Context).inflate(
                        R.layout.gym_list_title, parent, false);
            }
        }
        else {
            if (convertView == null) {
                convertView = LayoutInflater.from(Context).inflate(
                        R.layout.gym_rank_club_item, parent, false);
            }

            TextView club_num = BaseViewHolder.get(convertView, R.id.club_num);
            TextView club_title = BaseViewHolder.get(convertView, R.id.club_title);
            TextView club_price = BaseViewHolder.get(convertView, R.id.club_price);
            ClubTest clubTest = listinfo.get(i-1);
            LogUtils.e(clubTest.getClubname() + "");

            int num = i ;
            club_num.setText(num + "");
            club_title.setText(clubTest.getClubname());
            club_price.setText(clubTest.getPaice() + "");
        }


        return convertView;
    }

}
