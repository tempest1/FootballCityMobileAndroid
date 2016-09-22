package com.footballcitymobileandroid.BLL.Adapter.ClubAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.MatchMemb;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/7/28.
 */
public class GvAdpater  extends BaseAdapter{
    private LayoutInflater mInflater;

    List<MatchMemb> membs;
    public GvAdpater(List<MatchMemb> membs, Context context) {
        
        mInflater = LayoutInflater.from(context);
        this.membs=membs;
    }

    public int getCount() {
        return membs.size();

    }

    public Object getItem(int position) {
        return membs.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.gv_item, null);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) convertView.findViewById(R.id.text);
            viewHolder.image = (CircleButton) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.text.setText(membs.get(position).getName());

        return convertView;
    }

    class ViewHolder {

        TextView text;

        CircleButton image;

    }
}
