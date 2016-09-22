package com.footballcitymobileandroid.BLL.Adapter.ClubAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.Controller.Base.BaseActivity;
import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubMemb;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.PlayerRankings;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/7/19.
 */
public class MeClubNumbersListAdapter extends BaseAdapter {
    //    private List<PlayerTest> listinfo;
    private android.content.Context Context;

    private LayoutInflater mInflater;

    List<ClubMemb> data;
    MeClubNumbersListAdapter()
    {}

    public MeClubNumbersListAdapter(android.content.Context context, List<ClubMemb> data)
    {
        this.Context=Context;
//        this.listinfo=listinfo;
        this.data=data;
        mInflater=LayoutInflater.from(context);
    }
    Bitmap bitmap;
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {



//            if (convertView == null) {
//                convertView = LayoutInflater.from(Context).inflate(
//                        R.layout.gym_player_pirce_item, parent, false);
//            }
//            TextView player_num = BaseViewHolder.get(convertView, R.id.player_num);
//            TextView player_name = BaseViewHolder.get(convertView, R.id.player_name);
//            TextView player_pirce = BaseViewHolder.get(convertView, R.id.player_pirce);
//            CircleButton circleButton = BaseViewHolder.get(convertView, R.id.user_icon);
//
//            PlayerTest playerTest = listinfo.get(i);
//            //LogUtils.e(clubTest.getClubname()+"");
//
//            int num = i+1 ;
//            player_num.setText(num + "");
//            player_name.setText(playerTest.getPlayname());
//            player_pirce.setText(playerTest.getPaice() + "");
//            //    circleButton.setImageBitmap(R.drawable.test1);

        ViewHolder  viewHolder;
        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.gym_player_pirce_item,null);
            viewHolder.ivIcon= (ImageView) convertView.findViewById(R.id.user_icon);   //club logo
            viewHolder.club_num= (TextView) convertView.findViewById(R.id.player_num);    //排序
            viewHolder.club_price= (TextView) convertView.findViewById(R.id.player_pirce); //身价
            viewHolder.name= (TextView) convertView.findViewById(R.id.player_name);        //club name
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }
//        viewHolder.ivIcon.setTag(mList.get(position).newsIconURL);
//        new ImageLoader().showImageBuAsyncTask(viewHolder.ivIcon, mList.get(position).newsIconURL);
        ClubMemb clubMemb=data.get(i);
        if (clubMemb.getPhoto() != null&&!clubMemb.getPhoto().equals("")) {
            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(clubMemb.getPhoto()));
            viewHolder.ivIcon.setImageBitmap(bitmap);
        }
        else {
            viewHolder.ivIcon.setImageResource(R.mipmap.personhead);
        }

         i=i+1;
        viewHolder.club_num.setText(i+"");
        try {
            viewHolder.club_price.setText("$"+clubMemb.getWorth().get(0).getArena_worth()+"");
        }catch (Exception e){
            viewHolder.club_price.setText("0");

        }
        viewHolder.name.setText(clubMemb.getName());
        return convertView;
    }
    class ViewHolder
    {
        public TextView club_num,club_price,name;
        public ImageView ivIcon;
        public String c;

    }
}
