package com.footballcitymobileandroid.BLL.Adapter.ClubAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.footballcitymobileandroid.Entity.ClubEntity.join.Invitations;
import com.footballcitymobileandroid.Entity.ClubEntity.join.JoinApplys;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/7/22.
 */
public class ClubInvestAdapter extends BaseAdapter{

    private List<JoinApplys> listinfo;
    private android.content.Context context;
    private LayoutInflater mInflater;

    ClubInvestAdapter()
    {}

    public ClubInvestAdapter(android.content.Context context, List<JoinApplys> listinfo)
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
            convertView=mInflater.inflate(R.layout.club_invest_item,null);

            viewHolder.number= (TextView) convertView.findViewById(R.id.number);


            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }




        JoinApplys record;
        record=listinfo.get(i);
        viewHolder.number.setText(record.getSenderInfo().getName());

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
        public TextView number;

    }
}
