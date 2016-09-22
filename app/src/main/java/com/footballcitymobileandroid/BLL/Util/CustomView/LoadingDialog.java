package com.footballcitymobileandroid.BLL.Util.CustomView;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.footballcitymobileandroid.R;


/**
 * yu
 * 正在加载中。。。对话框采用PopupWindow实现
 */
public class LoadingDialog {
    private Context context;
    private PopupWindow popupDialog;
    private LayoutInflater layoutInflater;
    private RelativeLayout layout;
    private RelativeLayout layout_bg;
    private View loading_dialog;
    /**
     * RotateAnimation 旋转动画效果
     * */
    private RotateAnimation rotateAnim;
    /**
     * AlphaAnimation 透明度动画效果进入
     * */
    private AlphaAnimation alphaAnim_in;
    private AlphaAnimation alphaAnim_out;
    private int resId;
    private TextView loading;
    /**
     *  @param context Context对象
     * */
    public LoadingDialog(Context context, int resId) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.resId = resId;
    }

    private void initAnim() {
        rotateAnim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(2000);//动画
        rotateAnim.setRepeatMode(Animation.RESTART);
        rotateAnim.setRepeatCount(-1);
        rotateAnim.setInterpolator(new LinearInterpolator());
        alphaAnim_in = new AlphaAnimation(0f, 1f);
        alphaAnim_in.setFillAfter(true);
        alphaAnim_in.setDuration(200);
        alphaAnim_in.setInterpolator(new LinearInterpolator());
        alphaAnim_out = new AlphaAnimation(1f, 0f);
        alphaAnim_out.setFillAfter(true);
        alphaAnim_out.setDuration(100);
        alphaAnim_out.setInterpolator(new LinearInterpolator());
        alphaAnim_out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }
            @Override
            public void onAnimationEnd(Animation arg0) {
                dismiss();
            }
        });
    }
    /**
     * 判断是否显示
     * @return
     */
    public boolean isShowing() {
        if (popupDialog != null && popupDialog.isShowing()) {
            return true;
        }
        return false;
    }
    /**
     * 显示
     */
    public void show() {
        dismiss();
        initAnim();
        layout = (RelativeLayout) layoutInflater.inflate(R.layout.view_loadingdialog, null);
        loading_dialog = (View) layout.findViewById(R.id.loading_dialog);
        loading = (TextView) layout.findViewById(R.id.loading);
        loading_dialog.setBackgroundResource(resId);
        layout_bg = (RelativeLayout) layout.findViewById(R.id.bgLayout);
        popupDialog = new PopupWindow(layout, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        View parentView = ((Activity) context).getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        popupDialog.showAtLocation(parentView, Gravity.CENTER, 0, 0);
        layout_bg.startAnimation(alphaAnim_in);
        loading_dialog.startAnimation(rotateAnim);
    }
    /**
     * 隐藏
     */
    public void dismiss() {
        if (popupDialog != null && popupDialog.isShowing()) {
            layout_bg.clearAnimation();
            loading_dialog.clearAnimation();
            popupDialog.dismiss();
        }
    }

    /**
     *
     * @param message text
     */
    public void setText(String message){
        if (loading != null){
            if (!TextUtils.isEmpty(message)) {
                loading.setText(message);
            }
        }
    }

    /**
     * @param flag 是否可见
     */
    public void setTitleVisibility(boolean flag){
        if (flag) {
            loading.setVisibility(View.VISIBLE);
        } else {
            loading.setVisibility(View.GONE);
        }
    }
}
