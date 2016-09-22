package com.footballcitymobileandroid.BLL.Adapter.ClubAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.BaseViewHolder;
import com.footballcitymobileandroid.Controller.TestData.Chat;
import com.footballcitymobileandroid.Controller.TestData.ClubTest;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhoudi on 16/7/2.
 */
public class ChattingAdapter extends BaseAdapter {
    private List<Chat> listinfo;
    private android.content.Context context;

    ChattingAdapter()
    {}

    public ChattingAdapter(Context context, List<Chat> listinfo)
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


        final Chat chat = listinfo.get(i);

        if (convertView == null) {
            if (chat.getName().equals("自己的名字")) {
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.chatting_item, parent, false);
            }else {
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.chatting_item_other, parent, false);
            }
        }

        TextView response_content = BaseViewHolder.get(convertView, R.id.response_content);
        TextView response_name = BaseViewHolder.get(convertView, R.id.response_name);
        TextView response_time = BaseViewHolder.get(convertView, R.id.response_time);

        response_content.setText(""+chat.getContext());
        response_name.setText(""+chat.getName());
        response_time.setText(""+chat.getTime());
        return convertView;
    }
}
