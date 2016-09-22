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
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/5/28.
 */
public class AlreadyMatchAdapter extends BaseAdapter{
    private List<AranaMatchs> listinfo;
    private android.content.Context context;
    private LayoutInflater mInflater;
    Bitmap bitmap;

    AlreadyMatchAdapter()
    {}
    ClubList clubList;
    public AlreadyMatchAdapter(Context context, List<AranaMatchs> listinfo,ClubList clubList)
    {
        this.context=context;
        this.listinfo=listinfo;
        mInflater=LayoutInflater.from(context);
        this.clubList=clubList;
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
            convertView = mInflater.inflate(R.layout.challenge_already_match_item, null);

            viewHolder.tv_me = (TextView) convertView.findViewById(R.id.tv_me);
            viewHolder.tv_your = (TextView) convertView.findViewById(R.id.tv_your);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            viewHolder.ways = (TextView) convertView.findViewById(R.id.ways);
            viewHolder.place = (TextView) convertView.findViewById(R.id.way);


            viewHolder.image_yours = ( CircleButton) convertView.findViewById(R.id.image_yours);
            viewHolder.image_my = (CircleButton) convertView.findViewById(R.id.image_my);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        AranaMatchs matchInfo = listinfo.get(i);
        int num = i;

        if (matchInfo.getMyClub().equals("Home"))//主队
        {
            viewHolder.tv_me.setText(clubList.getClubName());
            viewHolder.tv_your.setText(matchInfo.getVisitingTeam().getClubName());
            viewHolder.time.setText(matchInfo.getMatchDate());
            viewHolder.ways.setText(matchInfo.getMatchRule()+"v"+matchInfo.getMatchRule());
            viewHolder.place.setText(matchInfo.getAranaName());
            if (matchInfo.getVisitingTeam().getClubLogo() != null && !matchInfo.getVisitingTeam().getClubLogo().equals("")) {
                bitmap = bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(matchInfo.getVisitingTeam().getClubLogo()));
                viewHolder.image_yours.setImageBitmap(bitmap);
            } else {
                viewHolder.image_yours.setImageResource(R.mipmap.term_sign);
            }

            if (clubList.getLogo() != null && !clubList.getLogo().equals("")) {
                bitmap = bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(clubList.getLogo()));
                viewHolder.image_my.setImageBitmap(bitmap);
            } else {
                viewHolder.image_my.setImageResource(R.mipmap.term_sign);
            }
        }
         else if (matchInfo.getMyClub().equals("Visting")){//客队
            viewHolder.tv_me.setText(clubList.getClubName());
            viewHolder.tv_your.setText(matchInfo.getHomeTeam().getClubName());
            viewHolder.time.setText(matchInfo.getMatchDate());
            viewHolder.ways.setText(matchInfo.getMatchRule());
            viewHolder.place.setText(matchInfo.getAranaName());
            if (matchInfo.getHomeTeam().getClubLogo() != null && !matchInfo.getHomeTeam().getClubLogo().equals("")) {
                bitmap = bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(matchInfo.getHomeTeam().getClubLogo()));
                viewHolder.image_yours.setImageBitmap(bitmap);
            } else {
                viewHolder.image_yours.setImageResource(R.mipmap.term_sign);
            }

            if (clubList.getLogo() != null && !clubList.getLogo().equals("")) {
                bitmap = bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(clubList.getLogo()));
                viewHolder.image_my.setImageBitmap(bitmap);
            } else {
                viewHolder.image_my.setImageResource(R.mipmap.term_sign);
            }
        }




        return convertView;
    }


    class ViewHolder
    {
        public TextView tv_me,tv_your,ways,time,place;
        public CircleButton image_yours,image_my;
    }
}
