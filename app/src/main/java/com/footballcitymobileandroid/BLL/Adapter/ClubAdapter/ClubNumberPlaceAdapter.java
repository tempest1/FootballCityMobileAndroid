package com.footballcitymobileandroid.BLL.Adapter.ClubAdapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.BaseViewHolder;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.LogUtil.StringUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubMemb;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoudi on 16/7/18.
 */
public class ClubNumberPlaceAdapter extends BaseAdapter {
    private List<ClubMemb> listinfo=new ArrayList<>();
    private android.content.Context context;
    private String postion;
    public static List<Boolean>list=new ArrayList<>();
    Bitmap bitmap;
    // 填充数据的list

    ClubNumberPlaceAdapter()
    {}

    public ClubNumberPlaceAdapter(android.content.Context context, List<ClubMemb> listinfo,String postion)
    {
        this.context=context;
        for (int i=0;i<listinfo.size();i++)
        {
            if (listinfo.get(i).getClubPosition().equals(postion)) {
                this.listinfo.add(listinfo.get(i));
                LogUtils.e(listinfo.get(i).getName());
            }
        }
        this.postion=postion;
    }



    @Override
    public int getCount() {
        if(listinfo.size()%6==0) {
            return listinfo.size()/6;
        }else {
            return listinfo.size()/6+1;
        }
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
                    R.layout.club_number_place_item, parent, false);
        }

        ImageView iagme1= BaseViewHolder.get(convertView, R.id.iamge1);
        ImageView iagme2= BaseViewHolder.get(convertView, R.id.iamge2);
        ImageView iagme3= BaseViewHolder.get(convertView, R.id.iamge3);
        ImageView iagme4= BaseViewHolder.get(convertView, R.id.iamge4);
        ImageView iagme5= BaseViewHolder.get(convertView, R.id.iamge5);
        ImageView iagme6= BaseViewHolder.get(convertView, R.id.iamge6);

        TextView doorname1= BaseViewHolder.get(convertView, R.id.door_name1);
        TextView doorname2= BaseViewHolder.get(convertView, R.id.door_name2);
        TextView doorname3= BaseViewHolder.get(convertView, R.id.door_name3);
        TextView doorname4= BaseViewHolder.get(convertView, R.id.door_name4);
        TextView doorname5= BaseViewHolder.get(convertView, R.id.door_name5);
        TextView doorname6= BaseViewHolder.get(convertView, R.id.door_name6);

        int x=0;

        try {
             doorname1.setText(listinfo.get(i*6).getName());
            if (listinfo.get(i*6).getPhoto() != null&&!listinfo.get(i*6).getPhoto().equals("")) {
                bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(listinfo.get(i*6).getPhoto()));
                iagme1.setImageBitmap(bitmap);
            }
            else {
                iagme1.setImageResource(R.mipmap.personhead);
            }
            x++;
             doorname2.setText(listinfo.get(i*6+1).getName());
            if (listinfo.get(i*6+1).getPhoto() != null&&!listinfo.get(i*6+1).getPhoto().equals("")) {
                bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(listinfo.get(i*6+1).getPhoto()));
                iagme2.setImageBitmap(bitmap);
            }
            else {
                iagme2.setImageResource(R.mipmap.personhead);
            }
            x++;
             doorname3.setText(listinfo.get(i*6+2).getName());
            if (listinfo.get(i*6+2).getPhoto() != null&&!listinfo.get(i*6+1).getPhoto().equals("")) {
                bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(listinfo.get(i*6+2).getPhoto()));
                iagme3.setImageBitmap(bitmap);
            }
            else {
                iagme3.setImageResource(R.mipmap.personhead);
            }
            x++;
             doorname4.setText(listinfo.get(i*6+3).getName());
            if (listinfo.get(i*6+3).getPhoto() != null&&!listinfo.get(i*6+1).getPhoto().equals("")) {
                bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(listinfo.get(i*6+3).getPhoto()));
                iagme4.setImageBitmap(bitmap);
            }
            else {
                iagme4.setImageResource(R.mipmap.personhead);
            }
            x++;
             doorname5.setText(listinfo.get(i*6+4).getName());
            if (listinfo.get(i*6+4).getPhoto() != null&&!listinfo.get(i*6+1).getPhoto().equals("")) {
                bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(listinfo.get(i*6+4).getPhoto()));
                iagme5.setImageBitmap(bitmap);
            }
            else {
                iagme5.setImageResource(R.mipmap.personhead);
            }
            x++;
             doorname6.setText(listinfo.get(i*6+5).getName());
            if (listinfo.get(i*6+5).getPhoto() != null&&!listinfo.get(i*6+1).getPhoto().equals("")) {
                bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(listinfo.get(i*6+5).getPhoto()));
                iagme6.setImageBitmap(bitmap);
            }
            else {
                iagme6.setImageResource(R.mipmap.personhead);
            }
            x++;






    }catch (Exception e){
            if (x==1){

                iagme2.setVisibility(View.GONE);
                iagme3.setVisibility(View.GONE);
                iagme4.setVisibility(View.GONE);
                iagme5.setVisibility(View.GONE);
                iagme6.setVisibility(View.GONE);

                doorname2.setText("");
                doorname3.setText("");
                doorname4.setText("");
                doorname5.setText("");
                doorname6.setText("");

            }else if (x==2){

                iagme3.setVisibility(View.GONE);
                iagme4.setVisibility(View.GONE);
                iagme5.setVisibility(View.GONE);
                iagme6.setVisibility(View.GONE);

                doorname3.setText("");
                doorname4.setText("");
                doorname5.setText("");
                doorname6.setText("");
            }else if (x==3){

                iagme4.setVisibility(View.GONE);
                iagme5.setVisibility(View.GONE);
                iagme6.setVisibility(View.GONE);

                doorname4.setText("");
                doorname5.setText("");
                doorname6.setText("");
            }else if (x==4){

                iagme5.setVisibility(View.GONE);
                iagme6.setVisibility(View.GONE);

                doorname5.setText("");
                doorname6.setText("");
            }else if (x==5){

                iagme6.setVisibility(View.GONE);

                doorname6.setText("");


            }
        }

        return convertView;
    }



}
