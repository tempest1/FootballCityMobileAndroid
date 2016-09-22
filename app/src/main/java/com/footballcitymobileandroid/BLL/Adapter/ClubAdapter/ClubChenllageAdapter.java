package com.footballcitymobileandroid.BLL.Adapter.ClubAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.BaseViewHolder;
import com.footballcitymobileandroid.DAL.Data.LogUtil.StringUtils;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.MatchingInfo;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/5/31.
 */
public class ClubChenllageAdapter extends BaseAdapter{
    private List<MatchingInfo> listinfo;
    private android.content.Context context;

    ClubChenllageAdapter()
    {}

    public ClubChenllageAdapter(android.content.Context context, List<MatchingInfo> listinfo)
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
                    R.layout.club_challenge_item, parent, false);
        }

        TextView you_club = BaseViewHolder.get(convertView, R.id.you_club);

      //  TextView me_club = BaseViewHolder.get(convertView, R.id.me_club);
        TextView time = BaseViewHolder.get(convertView, R.id.time);
        TextView place = BaseViewHolder.get(convertView, R.id.way);
        TextView play = BaseViewHolder.get(convertView, R.id.play);
        TextView pay = BaseViewHolder.get(convertView, R.id.pay);


        MatchingInfo matchingInfo=new MatchingInfo();
        matchingInfo=listinfo.get(i);

        you_club.setText(" "+matchingInfo.getSender().getClubName());
        time.setText(" "+matchingInfo.getMatchingDate()+" "+matchingInfo.getMatchingTime().substring(0,5));
        place.setText(" "+matchingInfo.getAranaName());
        play.setText(" "+matchingInfo.getMatchRule()+"v"+matchingInfo.getMatchRule());
        pay.setText(" "+StringUtils.costMode(matchingInfo.getCostMode()));

        return convertView;
    }

}
