package com.footballcitymobileandroid.BLL.Adapter.ClubAdapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.footballcitymobileandroid.BLL.Util.CustomView.BaseViewHolder;
import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bitmapwithbyte;
import com.footballcitymobileandroid.BLL.Util.ImageUtil.bytewithString;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Controller.TestData.MeClubNumbers;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.LogUtil.StringUtils;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubMemb;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhoudi on 16/6/28.
 */
public class ClubNumberPlaceChooseAdapter  extends BaseAdapter {
    private List<ClubMemb> listinfo;
    private android.content.Context context;
    public static List<Boolean>list=new ArrayList<>();
    // 填充数据的list
    private static HashMap<Integer, Boolean> isSelected;

    ClubNumberPlaceChooseAdapter()
    {}
    Bitmap bitmap;
    public ClubNumberPlaceChooseAdapter(android.content.Context context, List<ClubMemb> listinfo)
    {
        this.context=context;
        this.listinfo=listinfo;
        isSelected = new HashMap<Integer, Boolean>();
        // 初始化数据
        initDate();
    }

    // 初始化isSelected的数据
    private void initDate() {
        for (int i = 0; i < listinfo.size(); i++) {
            getIsSelected().put(i, false);
        }
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

        if(MainApplication.CHOOSE_NUMBER==null)
        {
            MainApplication.CHOOSE_NUMBER = new int[listinfo.size()];

        }else if (MainApplication.CHOOSE_NUMBER.length!=listinfo.size())
        {
            MainApplication.CHOOSE_NUMBER = new int[listinfo.size()];
        }


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.club_number_place_choose_item, parent, false);
        }

        CircleButton image= BaseViewHolder.get(convertView, R.id.image);
        TextView name= BaseViewHolder.get(convertView, R.id.name);
        TextView age= BaseViewHolder.get(convertView, R.id.age);
        TextView goodplace= BaseViewHolder.get(convertView, R.id.goodplace);
        TextView time= BaseViewHolder.get(convertView, R.id.time);
        TextView money= BaseViewHolder.get(convertView, R.id.money);
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.item_cb);

        ClubMemb club=new ClubMemb();
        club=listinfo.get(i);
        name.setText(club.getName());
      //  age.setText(club.get());
        goodplace.setText(StringUtils.playPlace(club.getClubPosition()));
      //  time.setText(club.get());
        try {
            money.setText(club.getWorth().get(0).getArena_worth()+"");
        }catch (Exception e){
            money.setText("0");
        }
        final View view=convertView;

        if (club.getPhoto() != null&&!club.getPhoto().equals("")) {
            bitmap= bitmapwithbyte.Bytes2Bimap(bytewithString.hexStringToBytes(club.getPhoto() ));
            image.setImageBitmap(bitmap);
        }
        else {
            image.setImageResource(R.mipmap.personhead);
        }

        // 监听checkBox并根据原来的状态来设置新的状态
        cb.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (isSelected.get(i)) {
                    isSelected.put(i, false);
                    MainApplication.CHOOSE_NUMBER[i]=0;
                    setIsSelected(isSelected);
                } else {
                    isSelected.put(i, true);
                    setIsSelected(isSelected);
                    MainApplication.CHOOSE_NUMBER[i]=1;

                }
                for (int i=0;i<MainApplication.CHOOSE_NUMBER.length;i++)
                {
                    LogUtils.e(""+MainApplication.CHOOSE_NUMBER[i]);
                }
            }
        });


        // 根据isSelected来设置checkbox的选中状况
        cb.setChecked(getIsSelected().get(i));


        return convertView;
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        ClubNumberPlaceChooseAdapter.isSelected = isSelected;
    }
}
