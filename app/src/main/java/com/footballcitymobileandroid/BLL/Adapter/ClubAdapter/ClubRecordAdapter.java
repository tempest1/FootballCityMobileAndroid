package com.footballcitymobileandroid.BLL.Adapter.ClubAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.PieView;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubRecord;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/7/19.
 */
public class ClubRecordAdapter extends BaseAdapter {
    private List<ClubRecord> listinfo;
    private android.content.Context context;
    private LayoutInflater mInflater;

    ClubRecordAdapter()
    {}

    public ClubRecordAdapter(Context context, List<ClubRecord> listinfo)
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
            convertView=mInflater.inflate(R.layout.club_record_item,null);
            viewHolder.seasa= (TextView) convertView.findViewById(R.id.seasa);
            viewHolder.point= (TextView) convertView.findViewById(R.id.point);
            viewHolder.rank= (TextView) convertView.findViewById(R.id.rank);
            viewHolder.win= (TextView) convertView.findViewById(R.id.win);
            viewHolder.equit= (TextView) convertView.findViewById(R.id.equit);
            viewHolder.fail= (TextView) convertView.findViewById(R.id.fail);
            viewHolder.getpoint= (TextView) convertView.findViewById(R.id.get_point);
            viewHolder.losepoint= (TextView) convertView.findViewById(R.id.lose_point);
            viewHolder.winpoint= (TextView) convertView.findViewById(R.id.win_point);
            viewHolder.pv=(PieView)convertView.findViewById(R.id.pv);


            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }


        ClubRecord record=new ClubRecord();
        record=listinfo.get(i);

        LogUtils.e("pkkk"+record.getArena_SeasonName());
//        viewHolder.tv_me.setText(record.getClub_me());
//        viewHolder.tv_your.setText(record.getClub_you());
//        viewHolder.time.setText(record.getDate());
//        viewHolder.ways.setText(record.getPayways());
//        viewHolder.place.setText(record.getPlace());
//        viewHolder.me_point.setText(record.getPoint_me()+"");
//        viewHolder.your_point.setText(record.getPint_you()+"");

        viewHolder.point.setText(record.getPoint());
        viewHolder.rank.setText(record.getRankings());
        viewHolder.win.setText(record.getWins());
        viewHolder.equit.setText(record.getDraws());
        viewHolder.fail.setText(record.getLoses());
        viewHolder.seasa.setText(record.getArena_SeasonName());
        int win,fail,equit;
        try {
             win = Integer.parseInt(record.getWins());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            win=0;
        }
        try {
            fail = Integer.parseInt(record.getLoses());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            fail=0;
        }
        try {
            equit = Integer.parseInt(record.getDraws());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            equit=0;
        }
        int playway;
        playway=win+fail+equit;

        viewHolder.pv.update(this.context,record.getWins(),record.getDraws(),record.getLoses(),playway+"");

        return convertView;
    }

    class ViewHolder
    {
        public TextView point,rank,win,equit,fail,getpoint,losepoint,winpoint,seasa;
        public PieView pv;
    }
}
