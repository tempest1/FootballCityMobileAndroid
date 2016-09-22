package com.footballcitymobileandroid.BLL.Adapter.ClubAdapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.footballcitymobileandroid.BLL.Util.CustomView.BaseViewHolder;
import com.footballcitymobileandroid.BLL.Util.CustomView.CircleButton;
import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.Controller.TestData.ClubTest;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;
import com.footballcitymobileandroid.DAL.Data.LogUtil.StringUtils;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena2.AranaMatchMembs;
import com.footballcitymobileandroid.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoudi on 16/7/2.
 */
public class ChallengeEndMatchInfoGreatNumberAdapter  extends BaseAdapter {

    Context mContext;
    public static Map<Integer, String> mMapContent;
    List<AranaMatchMembs> aranaMatchMembs;
    public static HashMap<Integer, Boolean> isSelected;

    public ChallengeEndMatchInfoGreatNumberAdapter(Context context, List<AranaMatchMembs> aranaMatchMembs)
    {
        mMapContent = new HashMap<Integer, String>();

        this.aranaMatchMembs = aranaMatchMembs;
        mContext = context;
        isSelected = new HashMap<Integer, Boolean>();
        // 初始化数据
        initDate();
    }
    // 初始化isSelected的数据
    private void initDate() {
        for (int i = 0; i < aranaMatchMembs.size(); i++) {
            getIsSelected().put(i, false);
            mMapContent.put(i,"0");
        }
    }
    public int getCount()
    {
        return aranaMatchMembs.size();
    }

    public Object getItem(int arg0)
    {
        return aranaMatchMembs.get(arg0);
    }

    public long getItemId(int arg0)
    {
        return arg0;
    }

    public View getView(final int position, View convertView, ViewGroup arg2)
    {

        if(MainApplication.NUMBER_SETNAME==null)
        {
            MainApplication.NUMBER_SETNAME = new int[aranaMatchMembs.size()];

        }else if (MainApplication.NUMBER_SETNAME.length!=aranaMatchMembs.size())
        {
            MainApplication.NUMBER_SETNAME = new int[aranaMatchMembs.size()];
        }


        ViewHold holder = null;
        if(convertView == null)
        {
            convertView = LinearLayout.inflate(mContext, R.layout.great_number_item, null);
            holder = new ViewHold(convertView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHold)convertView.getTag();
        }

        holder.position = position;
        holder.club_number.setText(aranaMatchMembs.get(position).getPlayerName());
        holder.editText.setText(mMapContent.get(position));//Integer.valueOf(position)
        // 监听checkBox并根据原来的状态来设置新的状态
        holder.btn_join .setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (isSelected.get(position)) {
                    isSelected.put(position, false);
                    MainApplication.NUMBER_SETNAME[position]=0;
                    setIsSelected(isSelected);
                } else {
                    isSelected.put(position, true);
                    setIsSelected(isSelected);
                    MainApplication.NUMBER_SETNAME[position]=1;

                }
                for (int i=0;i<MainApplication.NUMBER_SETNAME.length;i++)
                {
                    LogUtils.e(""+MainApplication.NUMBER_SETNAME[i]);
                    LogUtils.e(""+mMapContent.get(i));
                }
            }
        });


        // 根据isSelected来设置checkbox的选中状况
        holder.btn_join .setChecked(getIsSelected().get(position
        ));
        return convertView;
    }

    class ViewHold {
        TextView club_number;
        EditText editText;
        CheckBox btn_join;
        int position;

        public ViewHold(View v) {
            club_number = (TextView) v.findViewById(R.id.club_number);
            editText = (EditText) v.findViewById(R.id.point);
            btn_join=(CheckBox)v.findViewById(R.id.btn_join);

            editText.addTextChangedListener(new TextWatcher() {
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void afterTextChanged(Editable s) {
                    mMapContent.put(position, s.toString());

                    for (int a = 0; a < mMapContent.size(); a++) {
                        Log.e("sys", mMapContent.get(a));
                    }
                }
            });
        }
    }
    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        ChallengeEndMatchInfoGreatNumberAdapter.isSelected = isSelected;
    }

}
