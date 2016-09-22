package com.footballcitymobileandroid.BLL.Adapter.ClubAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhoudi on 16/7/2.
 */
public class ChallengeEndMatchVoteAdapter extends BaseAdapter {
    private List<String> listinfo;
    private Context context;
    private LayoutInflater mInflater;

    private static HashMap<Integer, Boolean> isSelected;



    ChallengeEndMatchVoteAdapter()
    {}

    public ChallengeEndMatchVoteAdapter(Context context, List<String> listinfo)
    {
        this.context=context;
        isSelected = new HashMap<Integer, Boolean>();



        this.listinfo=listinfo;
        mInflater=LayoutInflater.from(context);
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

        ViewHolder  viewHolder;

        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.great_number_item,null);

            viewHolder.btn_join = (CheckBox) convertView.findViewById(R.id.btn_join);
            viewHolder.point= (TextView) convertView.findViewById(R.id.point);


            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }


        LogUtils.e(listinfo.get(i) + "");

        int num = i ;
        viewHolder.best.setText(listinfo.get(i) );

        // 监听checkBox并根据原来的状态来设置新的状态
        viewHolder.btn_join.setOnClickListener(new View.OnClickListener() {

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

        viewHolder.point.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        // 根据isSelected来设置checkbox的选中状况
        viewHolder.btn_join.setChecked(getIsSelected().get(i));

        return convertView;
    }




    public  class ViewHolder
    {
        public TextView best,point;
        public CircleButton user_icon;
        public CheckBox btn_join;

    }
    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        ChallengeEndMatchVoteAdapter.isSelected = isSelected;
    }
}
