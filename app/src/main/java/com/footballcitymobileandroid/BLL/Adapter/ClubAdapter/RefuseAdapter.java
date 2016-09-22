package com.footballcitymobileandroid.BLL.Adapter.ClubAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.BaseViewHolder;
import com.footballcitymobileandroid.Controller.Club.ChallengeAlreadyInfoDeal;
import com.footballcitymobileandroid.Controller.TestData.MatchNormal;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/5/28.
 */
public class RefuseAdapter extends BaseAdapter{
    private List<String> listinfo;
    private android.content.Context context;
    private LayoutInflater mInflater;

    RefuseAdapter()
    {}

    public RefuseAdapter(Context context, List<String> listinfo)
    {
        this.context=context;
        this.listinfo=listinfo;
        mInflater=LayoutInflater.from(context);
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


        ViewHolder  viewHolder;

        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.refuse_adapter,null);

            viewHolder.fiction= (TextView) convertView.findViewById(R.id.fiction);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }




        viewHolder.fiction.setText(listinfo.get(i));


        return convertView;
    }
    class ViewHolder
    {
        public TextView fiction;
    }
}
