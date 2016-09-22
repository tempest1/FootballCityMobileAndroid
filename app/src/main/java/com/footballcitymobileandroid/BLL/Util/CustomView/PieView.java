package com.footballcitymobileandroid.BLL.Util.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.footballcitymobileandroid.Controller.Base.MainApplication;
import com.footballcitymobileandroid.DAL.Data.LogUtil.LogUtils;

/**
 * Created by Administrator on 2016/7/11.
 */
public class PieView extends View {
    String win = "0";
    String pingju = "0";
    String fail = "0";
    String play="0";
    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void reDraw(String win,String pingju,String fail,String play)
    {
        this.win=win;
        this.pingju=pingju;
        this.fail=fail;
        this.play=play;
        invalidate();
    }

    public void update (Context context,final String win,final String pingju,final String fail,final String play) {

        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                // delay some minutes you desire.
                /*try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }*/
                handler.post(new Runnable() {
                    public void run() {
                        reDraw(win,pingju,fail,play);
                        invalidate();
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /*
        //绘制弧线区域

    RectF rect = new RectF(0, 0, 100, 100);

    canvas.drawArc(rect, //弧线所使用的矩形区域大小
            0,  //开始角度
            90, //扫过的角度
            false, //是否使用中心
            paint);
         */
        //绘制红色的圆环
        Paint paint= new Paint();//设置画笔无锯齿
        paint.setAntiAlias(true);
        RectF oval1 = new RectF();
        oval1.set(0,0,300,300);
        paint.setColor(Color.RED);
        canvas.drawArc(oval1,0,120,true,paint);

        LogUtils.e("300w="+1.00*300/ MainApplication.getWidth());
        LogUtils.e("300h"+1.00*300/MainApplication.getHeight());

        LogUtils.e("50w="+1.00*50/MainApplication.getWidth());
        LogUtils.e("50h"+1.00*50/MainApplication.getHeight());

        LogUtils.e("250w="+1.00*250/MainApplication.getWidth());
        LogUtils.e("250h"+1.00*250/MainApplication.getHeight());


        //绘制黄色的圆环
        RectF oval2 = new RectF();
        oval2.set(0,0,300,300);
        paint.setColor(Color.rgb(255,165,0));
        canvas.drawArc(oval2,120,120,true,paint);



        //绘制绿色的圆环
        RectF oval3 = new RectF();
        oval3.set(0,0,300,300);
        paint.setColor(Color.GREEN);
        canvas.drawArc(oval3,240,120,true,paint);

        //绘制中间的圆
        RectF oval = new RectF();
        oval.set(50,50,250,250);
        paint.setColor(Color.WHITE);
        canvas.drawArc(oval,0,360,true,paint);

        //绘制圆环中的字
        paint.setTextSize(30);
        canvas.drawText(""+win,210,60,paint);
        canvas.drawText(""+pingju,0,150,paint);
        canvas.drawText(""+fail,200,260,paint);

        //绘制内圆里的字
        paint.setColor(Color.rgb(0,250,154));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        canvas.drawText(""+play,150,150,paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        canvas.drawText("比赛场次",150,200,paint);



    }

}
