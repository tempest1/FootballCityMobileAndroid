package com.footballcitymobileandroid.BLL.Adapter.MeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.BaseViewHolder;
import com.footballcitymobileandroid.Controller.TestData.Invest;
import com.footballcitymobileandroid.Controller.TestData.PlayRecord;
import com.footballcitymobileandroid.Entity.ClubEntity.join.Invitations;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/5/27.
 */
public class MyInvestAdapter extends BaseAdapter {

    private List<Invitations> listinfo;
    private android.content.Context context;
    private LayoutInflater mInflater;

    MyInvestAdapter()
    {}

    public MyInvestAdapter(android.content.Context context, List<Invitations> listinfo)
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
            convertView=mInflater.inflate(R.layout.me_invest_item,null);

            viewHolder.club= (TextView) convertView.findViewById(R.id.club);


            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }




        Invitations record=new Invitations();
        record=listinfo.get(i);
        viewHolder.club.setText(record.getSenderInfo().getClubName());

//        tv_me.setText(record.getClub_me());
//        tv_you.setText(record.getClub_you());
//        time.setText(record.getDate());
//        ways.setText(record.getPayways());
//        place.setText(record.getPlace());
//        me_point.setText(record.getPoint_me()+"");
//        your_point.setText(record.getPint_you()+"");

        return convertView;
    }
    class ViewHolder
    {
        public TextView leader,club;

    }

}
