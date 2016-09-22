package com.footballcitymobileandroid.BLL.Adapter.ClubAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.SportDetail;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/5/28.
 */
public class NormalMatchAdapter extends BaseAdapter{
    private List<SportDetail> listinfo;
    private android.content.Context context;
    private LayoutInflater mInflater;
    ClubList clubList;
    Bitmap bitmap;
    NormalMatchAdapter()
    {}

    public NormalMatchAdapter(Context context, List<SportDetail> listinfo,ClubList clubList)
    {
        this.context=context;
        this.listinfo=listinfo;
        this.clubList=clubList;
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
            convertView=mInflater.inflate(R.layout.matchinfo_item,null);

            viewHolder.tv_me= (TextView) convertView.findViewById(R.id.tv_me);
            viewHolder.tv_your= (TextView) convertView.findViewById(R.id.tv_your);
            viewHolder.time= (TextView) convertView.findViewById(R.id.time);
            viewHolder.ways= (TextView) convertView.findViewById(R.id.ways);
            viewHolder.place= (TextView) convertView.findViewById(R.id.way);


            viewHolder.image_yours= (CircleButton) convertView.findViewById(R.id.image_yours);
            viewHolder.image_my= (CircleButton) convertView.findViewById(R.id.image_my);


            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }




        SportDetail matchInfo = listinfo.get(i);
        int num = i ;

        viewHolder.tv_me.setText(clubList.getClubName());

        if (clubList.getLogo() != null) {
            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(clubList.getLogo()));
            viewHolder.image_my.setImageBitmap(bitmap);
        }
        else {
            viewHolder.image_my.setImageResource(R.mipmap.term_sign);
        }
        viewHolder.tv_your.setText(matchInfo.getGuest_club_name());

        String a=matchInfo.getStart_time();
        a= a.substring(0,10);
        viewHolder.time.setText(a);
        viewHolder.place.setText(matchInfo.getField_name());
        viewHolder.ways.setText(matchInfo.getJoin_num()+"v"+matchInfo.getJoin_num());



        return convertView;


    }

    class ViewHolder
    {
        public TextView tv_me,tv_your,ways,time,place,type;
        public CircleButton image_yours,image_my;
    }

}
