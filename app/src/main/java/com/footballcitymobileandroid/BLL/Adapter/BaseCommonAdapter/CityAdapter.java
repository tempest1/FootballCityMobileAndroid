package com.footballcitymobileandroid.BLL.Adapter.BaseCommonAdapter;

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
 * Created by zhoudi on 16/6/21.
 */
public class CityAdapter extends BaseAdapter {
    private List<String> listinfo;
    private android.content.Context Context;

    CityAdapter()
    {}

    public CityAdapter(android.content.Context Context, List<String> listinfo)
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



        if (convertView == null) {
            convertView = LayoutInflater.from(Context).inflate(
                    R.layout.city_item, parent, false);
        }

        TextView club = BaseViewHolder.get(convertView, R.id.club);

        LogUtils.e(listinfo.get(i)+"");
        int num = i ;
        club.setText(listinfo.get(i));

        return convertView;
    }

}
