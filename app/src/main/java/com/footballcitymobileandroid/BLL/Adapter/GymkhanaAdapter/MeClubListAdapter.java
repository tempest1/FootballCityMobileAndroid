package com.footballcitymobileandroid.BLL.Adapter.GymkhanaAdapter;

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
import com.footballcitymobileandroid.DAL.Data.LogUtil.StringUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/7/21.
 */
public class MeClubListAdapter extends BaseAdapter{
    private List<ClubList> listinfo;
    private android.content.Context context;
    Bitmap bitmap;
    MeClubListAdapter()
    {}

    public MeClubListAdapter(Context context, List<ClubList> listinfo)
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
        ClubList clubs = listinfo.get(i);
        int num = i ;
        club.setText(clubs.getClubName());
        if (clubs.getLogo() != null&&!clubs.getLogo().equals("")) {
            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(clubs.getLogo()));
            image.setImageBitmap(bitmap);
        }
        else {
            image.setImageResource(R.mipmap.term_sign);
        }
        place.setText(StringUtils.AtyField(clubs.getAtyField())+"");
        time.setText(clubs.getAtyTime()+"");
        String week="";
        String[] sourceStrArray = clubs.getAtyTime().split(",");
        for (int j = 0; j < sourceStrArray.length; j++) {
            LogUtils.e(sourceStrArray[j]);
        }
        LogUtils.e("number="+sourceStrArray.length);

        for (int j=0;j<sourceStrArray.length;j++)
        {
            if (sourceStrArray[j].equals("0"))
            {


            }else {
                if(j==0){
                    week=week+"周一 ";
                }if(j==1){
                    week=week+"周二 ";

                }if (j==2) {
                    week=week+"周三 ";

                }if (j==3) {
                    week=week+"周四 ";

                }if (j==4) {
                    week=week+"周五 ";

                }if (j==5) {
                    week=week+"周六 ";

                }if (j==6) {
                    week=week+"周日 ";

                }
            }
        }

        time.setText(week);
        // place.setText(clubs.getClubRecords().getArena_Name()+"");
//        for (int x=0;x<clubs.getStyTime().size();i++)
//        {LogUtils.e(clubs.getStyTime().get(i));}
        return convertView;
    }

}
