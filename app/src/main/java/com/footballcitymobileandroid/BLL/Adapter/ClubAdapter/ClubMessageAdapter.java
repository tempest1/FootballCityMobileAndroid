package com.footballcitymobileandroid.BLL.Adapter.ClubAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.BaseViewHolder;
import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.Controller.TestData.ClubChallenge;
import com.footballcitymobileandroid.Controller.TestData.Message;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/6/14.
 */
public class ClubMessageAdapter  extends BaseAdapter {
    private List<Message> listinfo;
    private android.content.Context context;
    private LayoutInflater mInflater;

    ClubMessageAdapter()
    {}

    public ClubMessageAdapter(android.content.Context context, List<Message> listinfo)
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
            convertView=mInflater.inflate(R.layout.club_message_item,null);
            viewHolder.message= (TextView) convertView.findViewById(R.id.message);
            viewHolder.time= (TextView) convertView.findViewById(R.id.time);
            viewHolder.image= (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }


        Message message1=new Message();
        message1=listinfo.get(i);

        viewHolder.message.setText(message1.getMessage());
        viewHolder.time.setText(message1.getTime());

        return convertView;
    }

    class ViewHolder
    {
        public TextView message,time;
        public ImageView image;


    }

}
