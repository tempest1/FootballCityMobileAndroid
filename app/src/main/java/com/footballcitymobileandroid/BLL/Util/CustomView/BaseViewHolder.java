package com.footballcitymobileandroid.BLL.Util.CustomView;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by zhoudi on 16/5/19.
 */
public class BaseViewHolder {
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
