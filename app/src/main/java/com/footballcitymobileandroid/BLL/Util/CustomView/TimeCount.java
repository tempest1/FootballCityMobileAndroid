package com.footballcitymobileandroid.BLL.Util.CustomView;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * Created by zhoudi on 16/3/24.
 */
public class TimeCount extends CountDownTimer {
    private Button submit;
    private Button next;

    public TimeCount(long millisInFuture, long countDownInterval, Button button, Button button2) {
        super(millisInFuture, countDownInterval);
        submit = button;
        next = button2;
    }
    public TimeCount(long millisInFuture, long countDownInterval, Button button) {
        super(millisInFuture, countDownInterval);
        submit=button;
    }
    @Override
    public void onFinish() {// 计时完毕
        submit.setText("获取短信验证码");
        submit.setClickable(true);
        next.setEnabled(false);
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程
        submit.setClickable(false);//防止重复点击
        submit.setText(millisUntilFinished / 1000 + "s后可重新发送");
    }
}
