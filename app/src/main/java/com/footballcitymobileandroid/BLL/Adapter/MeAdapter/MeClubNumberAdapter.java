package com.footballcitymobileandroid.BLL.Adapter.MeAdapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Util.CustomView.BaseViewHolder;
import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.Entity.MeEntity.Players;
import com.footballcitymobileandroid.R;

import java.util.List;

/**
 * Created by zhoudi on 16/6/7.
 */
public class MeClubNumberAdapter extends BaseAdapter {
    private List<Players> listinfo;
    private android.content.Context context;
    Bitmap bitmap;
    MeClubNumberAdapter()
    {}

    public MeClubNumberAdapter(android.content.Context context, List<Players> listinfo)
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
                    R.layout.me_club_number_list_item, parent, false);
        }

        CircleButton image=BaseViewHolder.get(convertView,R.id.image);
        TextView name= BaseViewHolder.get(convertView, R.id.name);
        TextView age= BaseViewHolder.get(convertView, R.id.age);
        TextView goodplace= BaseViewHolder.get(convertView, R.id.goodplace);
        TextView time= BaseViewHolder.get(convertView, R.id.time);
      //  TextView money= BaseViewHolder.get(convertView, R.id.money);
        Button invest= BaseViewHolder.get(convertView, R.id.investment);



        Players players=new Players();
        players=listinfo.get(i);
        name.setText(players.getName());
        age.setText(players.getAge());
        if (players.getPhoto() != null&&!players.getPhoto().equals("")) {
            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(players.getPhoto()));
            image.setImageBitmap(bitmap);
        }
        else {
            image.setImageResource(R.mipmap.personhead);
        }

        try {
            goodplace.setText(players.getPosition().get(0));

        }catch (Exception e) {
            goodplace.setText("未设置");
        }
        time.setText(players.getAty_Time().toString());
      //  money.setText(players.get()+"");
        final View view=convertView;

        invest.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//                Intent intent=new Intent();
//                intent.setClass(view.getContext(),MeClubNumberList.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                Toast.makeText(view.getContext(),"邀请成功",Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
    class ViewHolder
    {
        public TextView age,goodplace,name,time;
        public ImageView ivIcon;
        public String c;

    }
}
