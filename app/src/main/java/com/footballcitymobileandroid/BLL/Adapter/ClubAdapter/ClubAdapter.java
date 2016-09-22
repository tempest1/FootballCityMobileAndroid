package com.footballcitymobileandroid.BLL.Adapter.ClubAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.BaseViewHolder;
import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Clubs;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/5/28.
 */
public class ClubAdapter  extends BaseAdapter{
    private List<Clubs> listinfo;
    private android.content.Context context;
    Bitmap bitmap;
    ClubAdapter()
    {}

    public ClubAdapter(Context context, List<Clubs> listinfo)
    {
        this.context=context;
        this.listinfo=listinfo;
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



            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.club_find_item, parent, false);
            }

            TextView club = BaseViewHolder.get(convertView, R.id.club);
            CircleButton image= BaseViewHolder.get(convertView, R.id.iamge);
            TextView place = BaseViewHolder.get(convertView, R.id.way);
            TextView time = BaseViewHolder.get(convertView, R.id.time);
            Clubs clubs = listinfo.get(i);
            int num = i ;
            club.setText(clubs.getClubName());
        if (clubs.getLogo() != null&&!clubs.getLogo().equals("")) {
            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(clubs.getLogo()));
            image.setImageBitmap(bitmap);
        }
        else {
            image.setImageResource(R.mipmap.ic_launcher);
        }
            LogUtils.e(""+clubs.getAtyTime());

        try {
            place.setText(clubs.getClubRecord().get(0).getArena_SeasonName() );
        }catch (Exception e){
            place.setText("暂无赛季");
        }

        try {
            time.setText(clubs.getClubRecord().get(0).getRankings());
        }catch (Exception e){
            time.setText("暂无排名");
        }


//        String week="";
//        try {
//
//            LogUtils.e(clubs.getAtyTime()+"");
//            String[] sourceStrArray = clubs.getAtyTime().split(",");
//            for (int j = 0; j < sourceStrArray.length; j++) {
//                LogUtils.e(sourceStrArray[j]);
//            }
//            LogUtils.e("number="+sourceStrArray.length);
//
//            for (int j=0;j<sourceStrArray.length;j++)
//            {
//                if (sourceStrArray[j].equals("0"))
//                {
//
//
//                }else {
//                    if(j==0){
//                        week=week+"周一 ";
//                    }if(j==1){
//                        week=week+"周二 ";
//
//                    }if (j==2) {
//                        week=week+"周三 ";
//
//                    }if (j==3) {
//                        week=week+"周四 ";
//
//                    }if (j==4) {
//                        week=week+"周五 ";
//
//                    }if (j==5) {
//                        week=week+"周六 ";
//
//                    }if (j==6) {
//                        week=week+"周日 ";
//
//                    }
//                }
//            }
//
//            time.setText(week);
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }

//
// place.setText(clubs.getClubRecords().getArena_Name()+"");
//        for (int x=0;x<clubs.getStyTime().size();i++)
//        {LogUtils.e(clubs.getStyTime().get(i));}
        return convertView;
    }

}
