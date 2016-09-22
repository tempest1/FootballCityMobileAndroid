package com.footballcitymobileandroid.BLL.Adapter.GymkhanaAdapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.BaseViewHolder;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Controller.TestData.MeClubNumbers;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhoudi on 16/6/28.
 */
public class GymPkRelNumberAdapter extends BaseAdapter {
    private List<MeClubNumbers> listinfo;
    private android.content.Context context;
    private boolean tag=false;
    // 填充数据的list
    GymPkRelNumberAdapter()
    {}
    // 用来控制CheckBox的选中状况
    private static HashMap<Integer, Boolean> isSelected;

    public GymPkRelNumberAdapter(android.content.Context context, List<MeClubNumbers> listinfo)
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
        CheckBox btn_join;
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


//        if (MainApplication.PK_REL_Number!=null) {
//            MainApplication.PK_REL_Number.add(0);
//        }
        if (MainApplication.PK_REL_NUMBER==null) {
            MainApplication.PK_REL_NUMBER = new int[listinfo.size()];
        }


        ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(
                    R.layout.gym_pk_rel_number_item, null);
            holder = new ViewHolder();
            holder.btn_join = (CheckBox) convertView.findViewById(R.id.btn_join);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.age = (TextView) convertView.findViewById(R.id.age);
            holder.goodplace = (TextView) convertView.findViewById(R.id.goodplace);
            holder.money = (TextView) convertView.findViewById(R.id.money);

            convertView.setTag(holder);
        } else {
            // 取出holder
            holder = (ViewHolder) convertView.getTag();
        }

        MeClubNumbers record=new MeClubNumbers();
        record=listinfo.get(i);
        holder.name .setText(record.getName());
        holder.age.setText(record.getAge());
        holder.goodplace.setText(record.getGoodplace());
        holder.money.setText(record.getMoney()+"");

        // 监听checkBox并根据原来的状态来设置新的状态
        holder.btn_join.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (isSelected.get(i)) {
                    isSelected.put(i, false);
                    MainApplication.PK_REL_NUMBER[i]=0;
                    setIsSelected(isSelected);
                } else {
                    isSelected.put(i, true);
                    setIsSelected(isSelected);
                    MainApplication.PK_REL_NUMBER[i]=1;


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
        GymPkRelNumberAdapter.isSelected = isSelected;
    }

}
