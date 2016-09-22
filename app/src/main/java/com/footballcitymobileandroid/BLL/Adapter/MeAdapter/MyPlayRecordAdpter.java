package com.footballcitymobileandroid.BLL.Adapter.MeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.BaseViewHolder;
import com.footballcitymobileandroid.BLL.Util.CustomView.PieView;
import com.footballcitymobileandroid.Controller.TestData.PlayRecord;
import com.footballcitymobileandroid.Entity.MeEntity.PlayerRecord;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/5/23.
 */
public class MyPlayRecordAdpter extends BaseAdapter {
    private List<PlayerRecord> listinfo;
    private android.content.Context context;
    private LayoutInflater mInflater;


    MyPlayRecordAdpter() {
    }

    public MyPlayRecordAdpter(Context context, List<PlayerRecord> listinfo) {
        this.context = context;
        this.listinfo = listinfo;
        mInflater = LayoutInflater.from(context);

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

        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.my_play_recode_item, null);
            viewHolder.seasa = (TextView) convertView.findViewById(R.id.seasa);
            viewHolder.point = (TextView) convertView.findViewById(R.id.point);
            viewHolder.rank = (TextView) convertView.findViewById(R.id.rank);
            viewHolder.win = (TextView) convertView.findViewById(R.id.win);
            viewHolder.equit = (TextView) convertView.findViewById(R.id.equit);
            viewHolder.fail = (TextView) convertView.findViewById(R.id.fail);
            viewHolder.pv=(PieView)convertView.findViewById(R.id.pv);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        PlayerRecord record = new PlayerRecord();
        record = listinfo.get(i);

        viewHolder.point.setText(record.getWorth());
        viewHolder.win.setText(record.getWins());
        viewHolder.fail.setText(record.getLoses());
        viewHolder.equit.setText(record.getDraws());
        viewHolder.seasa.setText(record.getArena_Name()+" "+record.getArena_SeasonName());
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

        viewHolder.pv.update(this.context,record.getWins(),record.getDraws(),record.getLoses(),""+playway);
//
//        try {
//            int win = Integer.valueOf(record.getWorth()).intValue();
//            int fail = Integer.valueOf(record.getLoses()).intValue();
//            int equit = Integer.valueOf(record.getDraws()).intValue();
//            viewHolder.pie.reDraw(win,equit,fail,win+fail+equit);
//        }catch (Exception e){
//            e.printStackTrace();
//        }


        return convertView;
    }

    class ViewHolder {
        public TextView point, rank, win, equit, fail,  seasa;
        public PieView pv;
    }
}
