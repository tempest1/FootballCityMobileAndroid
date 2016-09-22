package com.footballcitymobileandroid.BLL.Adapter.ClubAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.BaseViewHolder;
import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.Controller.TestData.MeClubNumbers;
import com.footballcitymobileandroid.Controller.TestData.PlayRecord;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.LogUtil.StringUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubMemb;
import com.footballcitymobileandroid.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhoudi on 16/5/25.
 */
public class MeClubNumberAdapter extends BaseAdapter {
    private List<ClubMemb> listinfo;
    private android.content.Context context;
    public static HashMap<String,Boolean> states=new HashMap<String,Boolean>();

    MeClubNumberAdapter()
    {}
    Bitmap bitmap;

    public MeClubNumberAdapter(android.content.Context context, List<ClubMemb> listinfo)
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
    public View getView(final int i, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.me_club_number_item, parent, false);
        }

        TextView player_name = BaseViewHolder.get(convertView, R.id.player_name);
       // TextView player_age = BaseViewHolder.get(convertView, R.id.player_age);
        TextView play_well = BaseViewHolder.get(convertView, R.id.play_well);
        CircleButton user_icon=BaseViewHolder.get(convertView, R.id.user_icon);


        final   RadioButton radio = BaseViewHolder.get(convertView, R.id.radio);


//        TextView ways = BaseViewHolder.get(convertView, R.id.ways);
//        TextView place = BaseViewHolder.get(convertView, R.id.place);
//        TextView me_point = BaseViewHolder.get(convertView, R.id.me_point);
//        TextView your_point = BaseViewHolder.get(convertView, R.id.you_point);

        ClubMemb record=new ClubMemb();
        record=listinfo.get(i);

        player_name.setText(record.getName());
       // player_age.setText(record.getAge());

        play_well.setText(StringUtils.playPlace(record.getClubPosition()));

        if (record.getPhoto() != null&&!record.getPhoto().equals("")) {
            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(record.getPhoto()));
            user_icon.setImageBitmap(bitmap);
        }
        else {
            user_icon.setImageResource(R.mipmap.personhead);
        }
//当RadioButton被选中时，将其状态记录进States中，并更新其他RadioButton的状态使它们不被选中
        radio.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                //重置，确保最多只有一项被选中
                for(String key:states.keySet()){
                    states.put(key, false);

                }
                states.put(String.valueOf(i), radio.isChecked());
                notifyDataSetChanged();


            }
        });

        boolean res=false;
        if(states.get(String.valueOf(i)) == null || states.get(String.valueOf(i))== false){
            res=false;
            states.put(String.valueOf(i), false);
        }
        else
            res = true;

        radio.setChecked(res);

        return convertView;
    }
}
