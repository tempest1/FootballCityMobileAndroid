package com.footballcitymobileandroid.BLL.Adapter.MeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.footballcitymobileandroid.Controller.TestData.PlayRecord;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/5/23.
 */
public class MyClubPlayRecordAdpter extends BaseAdapter {
    private List<PlayRecord> listinfo;
    private android.content.Context context;

    MyClubPlayRecordAdpter()
    {}
    private LayoutInflater mInflater;

    public MyClubPlayRecordAdpter(Context context, List<PlayRecord> listinfo)
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
            convertView=mInflater.inflate(R.layout.play_recode_item,null);

            viewHolder.tv_me= (TextView) convertView.findViewById(R.id.tv_me);
            viewHolder.tv_your= (TextView) convertView.findViewById(R.id.tv_your);
            viewHolder.time= (TextView) convertView.findViewById(R.id.time);
            viewHolder.ways= (TextView) convertView.findViewById(R.id.ways);
            viewHolder.place= (TextView) convertView.findViewById(R.id.way);
            viewHolder.me_point= (TextView) convertView.findViewById(R.id.me_point);
            viewHolder.your_point= (TextView) convertView.findViewById(R.id.you_point);

            viewHolder.image_yours= (ImageView) convertView.findViewById(R.id.image_yours);
            viewHolder.image_my= (ImageView) convertView.findViewById(R.id.image_my);


            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }


        PlayRecord record=new PlayRecord();
        record=listinfo.get(i);

        viewHolder.tv_me.setText(record.getClub_me());
        viewHolder.tv_your.setText(record.getClub_you());
        viewHolder.time.setText(record.getDate());
        viewHolder.ways.setText(record.getPayways());
        viewHolder.place.setText(record.getPlace());
        viewHolder.me_point.setText(record.getPoint_me()+"");
        viewHolder.your_point.setText(record.getPint_you()+"");

        return convertView;
    }

    class ViewHolder
    {
        public TextView tv_me,tv_your,ways,me_point,your_point,time,place;
        public ImageView image_yours,image_my;
    }
}
