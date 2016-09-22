package com.footballcitymobileandroid.BLL.Adapter.GymkhanaAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.ClubRankings;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/5/19.
 */
public class GymRankAdapter extends BaseAdapter {

//    private List<ClubTest> listinfo;
    Bitmap bitmap;
    private Context context;
    List<ClubRankings> data;
    private LayoutInflater mInflater;
    public GymRankAdapter(Context context, List<ClubRankings> data)
    {
//        this.Context=Context;
//        this.listinfo=listinfo;
        mInflater=LayoutInflater.from(context);
        this.data=data;
    }

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

//
//            if (convertView == null) {
//                convertView = LayoutInflater.from(Context).inflate(
//                        R.layout.gym_rank_club_item, parent, false);
//            }
//
//
//            TextView club_num = BaseViewHolder.get(convertView, R.id.club_num);
//            TextView club_title = BaseViewHolder.get(convertView, R.id.club_title);
//            TextView club_price = BaseViewHolder.get(convertView, R.id.club_price);
//             ImageView club_iamge=BaseViewHolder.get(convertView,R.id.user_icon);
//            ClubTest clubTest = data.getResponse().getClubRankings().get(i);
//            LogUtils.e(clubTest.getClubname() + "");
//            if (i==0)
//            {
//                club_num.setBackgroundResource(R.mipmap.rank_frist);
//            }else if (i==1){
//                club_num.setBackgroundResource(R.mipmap.rank_secound);
//
//            }else if (i==2){
//                club_num.setBackgroundResource(R.mipmap.rank_third);
//
//            }else if(i>2){
//              //  club_num.setBackgroundResource(R.mipmap.rank_third);
//
//            }
//
//            int num = i+1 ;
//            club_num.setText(num + "");
//            club_title.setText(clubTest.getClubname());
//            club_price.setText(clubTest.getPaice() + "");
//
//
//
//
        ViewHolder  viewHolder;
        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.gym_rank_club_item,null);
            viewHolder.ivIcon= (CircleButton) convertView.findViewById(R.id.club_icon);   //club logo
            viewHolder.club_num= (TextView) convertView.findViewById(R.id.club_num);    //排序
            viewHolder.club_price= (TextView) convertView.findViewById(R.id.club_price); //身价
            viewHolder.name= (TextView) convertView.findViewById(R.id.club_name);        //club name
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }

//        viewHolder.ivIcon.setImageResource(R.mipmap.ic_launcher);
//        viewHolder.ivIcon.setTag(mList.get(position).newsIconURL);
//        new ImageLoader().showImageBuAsyncTask(viewHolder.ivIcon, mList.get(position).newsIconURL);


        ClubRankings b =data.get(i);
        viewHolder.club_num.setText(b.getRankings());
        viewHolder.club_price.setText(b.getPoint());
        viewHolder.name.setText(b.getClubName());
        Log.i("logo",b.getClubLogo());
        if (i==0)
        {
            viewHolder.club_num.setBackgroundResource(R.mipmap.rank_frist);
        }else if (i==1){
            viewHolder.club_num.setBackgroundResource(R.mipmap.rank_secound);

        }else if (i==2){
            viewHolder.club_num.setBackgroundResource(R.mipmap.rank_third);

        }
        if(!b.getClubLogo().equals("")&&b.getClubLogo()!=null){
            viewHolder.ivIcon.setImageBitmap(bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(b.getClubLogo())));
        }else {
            viewHolder.ivIcon.setImageResource(R.mipmap.term_sign);
        }

//        if (b.getClubLogo() != null&&!b.getClubLogo().equals("")) {
//            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(b.getClubLogo()));
//             viewHolder.ivIcon.setImageBitmap(bitmap);
//        }
//        else {
          //  viewHolder.ivIcon.setImageResource(R.mipmap.personhead);
//        }
        return convertView;
    }
    class ViewHolder
{
    public TextView club_num,club_price,name;
    public CircleButton ivIcon;
    public String c;

}

}
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder  viewHolder;
//        if(convertView==null)
//        {
//            viewHolder=new ViewHolder();
//            convertView=mInflater.inflate(R.layout.layout_item,null);
//            viewHolder.ivIcon= (ImageView) convertView.findViewById(R.id.icn);
//            viewHolder.tvtime= (TextView) convertView.findViewById(R.id.time);
//            viewHolder.tvtitle= (TextView) convertView.findViewById(R.id.tv_title);
//            convertView.setTag(viewHolder);
//        }
//        else
//        {
//            viewHolder= (ViewHolder) convertView.getTag();
//        }
//
//        viewHolder.ivIcon.setImageResource(R.mipmap.ic_launcher);
//        viewHolder.ivIcon.setTag(mList.get(position).newsIconURL);
//        new ImageLoader().showImageBuAsyncTask(viewHolder.ivIcon, mList.get(position).newsIconURL);
//        viewHolder.tvtitle.setText(mList.get(position).newsTitle);
//        viewHolder.tvtime.setText(mList.get(position).newstime);
//        return convertView;
//    }
//
//class ViewHolder
//{
//    public TextView tvtitle,tvtime,tvnewstimeStamp;
//    public ImageView ivIcon;
//    public String c;
//
//}
