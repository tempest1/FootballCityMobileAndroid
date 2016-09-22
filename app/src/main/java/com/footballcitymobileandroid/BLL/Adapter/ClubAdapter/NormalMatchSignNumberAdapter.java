package com.footballcitymobileandroid.BLL.Adapter.ClubAdapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.MatchMemb;
import com.footballcitymobileandroid.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhoudi on 16/5/30.
 */
public class NormalMatchSignNumberAdapter extends BaseAdapter {
    private List<MatchMemb> listinfo;
    private android.content.Context context;
    // 填充数据的list
    NormalMatchSignNumberAdapter()
    {}

    // 用来控制CheckBox的选中状况
    private static HashMap<Integer, Boolean> isSelected;
    public NormalMatchSignNumberAdapter(android.content.Context context, List<MatchMemb> listinfo)
    {
        this.context=context;
        isSelected = new HashMap<Integer, Boolean>();

        this.listinfo=listinfo;

        // 初始化数据
        initDate();
    }
    // 初始化isSelected的数据
    private void initDate() {
        for (int i = 0; i < listinfo.size(); i++) {
            getIsSelected().put(i, false);
        }
    }
    class ViewHolder {

        TextView name;
        TextView age;
        TextView goodplace;
        TextView money;
        TextView text;
        CheckBox btn_join;
        boolean tag;
        CircleButton image;

    }
    Bitmap bitmap;
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

        if(MainApplication.PK_NUMBER==null)
        {
            MainApplication.PK_NUMBER = new int[listinfo.size()];

        }

        ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(
                    R.layout.normal_match_sign_player_item, null);
            holder = new ViewHolder();
            holder.btn_join = (CheckBox) convertView.findViewById(R.id.btn_join);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.age = (TextView) convertView.findViewById(R.id.age);
            holder.goodplace = (TextView) convertView.findViewById(R.id.goodplace);
            holder.money = (TextView) convertView.findViewById(R.id.money);
            holder.text = (TextView) convertView.findViewById(R.id.choose);
            holder.image=(CircleButton)convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            // 取出holder
            holder = (ViewHolder) convertView.getTag();
        }

        MatchMemb record=new MatchMemb();
        record=listinfo.get(i);
        holder.name .setText(record.getName());
      //  holder.age.setText(record.get());
//        holder.goodplace.setText(record.get());
//        holder.money.setText(record.getMoney()+"");


//        holder.text.setText("");
//
//        for (int j=0;j<MainApplication.PK_REL_NUMBER_SET.size();j++)
//        {
//            if (MainApplication.PK_REL_NUMBER_SET.get(j).equals(listinfo.get(i).getName()))
//            {
//                holder.text.setText("预选定");
//            }
//
//        }

        if (record.getPhoto() != null) {
            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(record.getPhoto()));
            holder.image.setImageBitmap(bitmap);
        }
        else {
            holder.image.setImageResource(R.mipmap.personhead);
        }
        // 监听checkBox并根据原来的状态来设置新的状态
        holder.btn_join.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (isSelected.get(i)) {
                    isSelected.put(i, false);
                    MainApplication.PK_NUMBER[i]=0;
                    setIsSelected(isSelected);
                } else {
                    isSelected.put(i, true);
                    setIsSelected(isSelected);
                    MainApplication.PK_NUMBER[i]=1;

                }


            }
        });


        // 根据isSelected来设置checkbox的选中状况
        holder.btn_join.setChecked(getIsSelected().get(i));

        return convertView;

    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        NormalMatchSignNumberAdapter.isSelected = isSelected;
    }

}
